import document.Document;

import java.util.Collection;

/**
 * методы для диспетчера.
 */
public interface PrintManager {

    /**
     * остановка диспетчера.
     */
    void stopTaskManager();

    /**
     * добавление документа.
     *
     * @param doc документ
     */
    void addDocument(Document doc);

    /**
     * метод отменяет печать документа.
     *
     * @param document принимает документ
     * @return возвращает true если
     * документ удаляется из очереди,
     * иначе возвращает false
     */
    boolean isCancelPrint(Document document);

    /**
     * метод выдает отсортированный
     * список документов.
     *
     * @return список напечатанных документов
     * @param name
     */
    Collection<Document> getSortedDocuments(Document.FieldOrder name);

    /**
     * метод для расчета
     * средней продолжительности.
     * напечатанных документов
     *
     * @return double среднюю продолжительность
     */
    double getMedianValue();

    /**
     * Список ненапечатанных документов.
     * @return список
     */
    Collection<Document> getUnHandledDocuments();

    /**
     * Статус потока.
     * @return выдает статус потока
     */
    State getState();
}
