import document.Document;
import document.Type;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/**
 * Главный класс.
 */
class Main {
    /**
     * количесто документов.
     */
    private static final int SIZE = 10;
    /**
     * массив из документов.
     */
    private static final Document[] DOCUMENTS = new Document[SIZE];
    /**
     * лог для записи.
     */
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    /**
     * Настрока лог файла.
     */
    private static void settingLogFile() {
        //файл для настройки логов
        try (FileInputStream ins = new FileInputStream("src/main/resources/log.prop")) {
            LogManager.getLogManager().readConfiguration(ins);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Ошибка в настройках: ", e);
        }
    }

    public  static void main(final String[] args) throws Exception {
        LOG.info("START");
        LOG.info("Добавление файла для настроек логирования");
        settingLogFile();
        LOG.info("Запуск менеджера");
        final PrintManager manager = new PrintManagerImpl();

        //Инициализация документов
        LOG.info("Добавление документов");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int k = 0;
                    while ((k < SIZE) && !(manager.getState().equals(State.STOP))) {
                        if (k % 2 == 0) {
                            DOCUMENTS[k] = new Document(Type.A3,
                                    "document" + k);
                        } else {
                            DOCUMENTS[k] = new Document(Type.A4,
                                    "document" + k);
                        }
                        manager.addDocument(DOCUMENTS[k]);
                        k++;
                    }
                }
            }).start();

        Thread.sleep(100); // можно убрать..нужно, чтоб все методы проверить

        //получение среднего значения
        try {
            LOG.info("Среднее значение напечанных документов="
                    + manager.getMedianValue());
        } catch (ArithmeticException e) {
            LOG.log(Level.SEVERE,
                    "Ошибка при делении на 0");
        }
        //удаление документа
        if (manager.isCancelPrint(DOCUMENTS[SIZE - 1])) {
            LOG.info("Удалили" + DOCUMENTS[SIZE - 1]);
        } else {
            LOG.info(" Удаление невозможно");
        }
        //получение отсортированного списка
        Collection<Document> listSortedDocument =
                manager.getSortedDocuments(Document.FieldOrder.TYPE);
        if (!listSortedDocument.isEmpty()) {
            LOG.info("Список отсортированных по "
                    + Document.FieldOrder.TYPE
                    + " напечатанных документов "
                    + listSortedDocument);
        } else {
            LOG.info("Список отсортированных документов пуст");
        }
        manager.stopTaskManager();
    }
}


