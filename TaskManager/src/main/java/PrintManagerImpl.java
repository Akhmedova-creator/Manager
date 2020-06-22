import document.Document;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * Класс Менеджер работы с документами.
 */
public class PrintManagerImpl implements PrintManager {
    /**
     * лог для записи.
     */
    private final Logger log = Logger.getLogger(PrintManagerImpl.class.getName());
    /**
     * Поток для вывода документов на печать.
     */
    private Thread taskManager;

    /**
     * Очередь доя записи докуметов.
     */
    private final LinkedBlockingQueue<Document> queue;

    /**
     *Копия очереди документов.
     */
    private final List<Document> handled = new CopyOnWriteArrayList<>();

    /**
     * Статус потока.
     */
    private volatile State state = State.RUNNING;

    /**
     * Конструктор.
     */
    public PrintManagerImpl() {
        this.queue = new LinkedBlockingQueue<>();
        startTaskManager();
    }

    /**
     * Метод  для остановки.
     * диспетчера
     * * выводид список ненапечатанных
     * документов
     */
    @Override
    public void stopTaskManager() {
        log.info("Остановка диспетчера");
        if (taskManager.isAlive()) { //если существует поток
            taskManager.interrupt(); //останавливаем
        }
        Collection<Document> listDocument = getUnHandledDocuments();
        if (!listDocument.isEmpty()) {
            log.info("Список ненапечатанных документов :" + listDocument);
        } else {
            log.info("Список ненапечатанных документов пуст");
        }
    }

    /**
     * Добавление документа.
     *
     * @param doc документ
     */
    @Override
    public void addDocument(final Document doc) {
        if (doc == null) {
            return;
        }
        queue.add(doc);
    }

    /**
     * метод отменяет печать документа.
     *
     * @param document принимает документ
     * @return возвращает true если
     * документ удаляется из очереди,
     * иначе возвращает false
     */
    @Override
    public boolean isCancelPrint(final Document document) {
        return queue.remove(document);
    }

    /**
     * метод выдает отсортированный
     * список документов.
     *
     * @return список напечатанных документов
     */
    @Override
    public Collection<Document> getSortedDocuments(final Document.FieldOrder fieldOrder) {
        Document.setFieldOrder(fieldOrder);
        if (handled.isEmpty()) {
            return Collections.emptyList();
        }

        LinkedList<Document> tempList = new LinkedList<>((handled));
        Collections.sort((tempList));
        return tempList;
    }

    /**
     * метод для расчета.
     * средней продолжительности.
     * напечатанных документов
     *
     * @return double среднюю продолжительность
     */
    @Override
    public double getMedianValue() throws ArithmeticException {
        long sum = 0;
        for (Document document : handled) {
            sum += document.getType().getDuration();
        }
        return (double) sum / handled.size();
    }

    /**
     * Список ненапечатанных документов.
     *
     * @return список
     */

    public Collection<Document> getUnHandledDocuments() {
        if (queue.isEmpty()) {
            return Collections.emptyList();
        }
        return new LinkedList<>(queue);
    }

    /**
     * Метод запускает диспетчер.
     */

    private void startTaskManager() {
        state = State.RUNNING;
        taskManager = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("Запуск  обработчика");
                state = State.READY;
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Document document = queue.take();
                        Thread.sleep(document.getType().getDuration());
                        handled.add(document);
                    } catch (InterruptedException e) {
                        state = State.STOP;
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                log.info("Остановка  обработчика");
            }
        });
        taskManager.setDaemon(true);
        taskManager.start();
    }

    public State getState() {
        return this.state;
    }

}
