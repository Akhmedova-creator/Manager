package document;

import java.util.logging.Logger;

/**
 * класс,описивающий документ.
 */

public class Document implements Comparable<Document> {
    /**
     * запись в лог.
     */
    private final Logger log = Logger.getLogger(Document.class.getName());
    /**
     * тип документа.
     */
    private final Type type;
    /**
     * наимкнование документа.
     */
    private final String name;
    /**
     * Сортировка по умолчанию.
     */
    private static FieldOrder fieldOrder = FieldOrder.INTER_TO_QUEUE;
    /**
     * Конструктор.
     *
     * @param type1 тип документа
     * @param name1 наименование документа
     */

    public Document(final Type type1, final String name1) {
        this.type = type1;
        this.name = name1;
    }

    /**
     * метод выдает тип документа.
     *
     * @return возвращает тип документа
     */
    public Type getType() {
        return this.type;
    }

    /**
     * метод для получения имени документа.
     *
     * @return имя документа
     */
    private String getName() {
        return this.name;
    }

    /**
     * метод сравнивает 2 документа.
     *
     * @param document принимает документ
     * @return число относительно сравнения
     */
    @Override
    public int compareTo(final Document document) {
        int result = 0;
        if (fieldOrder.equals(FieldOrder.NAME)) {
            result = this.getName().compareTo(document.getName());
        } else if (fieldOrder.equals(FieldOrder.TYPE)) {
            result = this.getType().compareTo(document.getType());
        }
        return result;
    }

    /**
     * установка сортировки.
     * @param fieldOrder1 сортировка по полю.
     */
    public static void setFieldOrder(final FieldOrder fieldOrder1) {
        fieldOrder =  fieldOrder1; }
    /**
     * метод, переопределяет перевод в строку.
     *
     * @return возвращает наименование документа.
     */
    @Override
    public String toString() {
        return "[" + name + "] ";
    }

    /**
     * Метод выполняет сравнение.
     *
     * @param document принимает документ
     * @return true если наименование
     * в очереди есть документ
     */
    public boolean equals(final Document document) {
       boolean result = false;
        try {
            result = (document.getName().equals(getName())
                    && (document.getType().equals(getType())));
        } catch (NullPointerException e) {
          log.warning("Ошибка" + e);
        }
        return result;
    }

    /**
     * Сортировка документов.
     */
    public enum FieldOrder {
        /**
         * По наименованию.
         */
        NAME,

        /**
         * По типу.
         */
        TYPE,

        /**
         * По проступлению в очередь.
         */
        INTER_TO_QUEUE,
    }
}