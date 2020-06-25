package document;
/**
 * класс содержит описание типа документа.
 * Для каждого типа уникальные реквизиты
 */
public enum  Type {
    /**
     * тип документа A3.
     */
    A3("A3", 2000, new Size(10, 10)),
    /**
     * тип документа A4.
     */
    A4("A4", 1000, new Size(15, 15));
    /**
     * наименование документа.
     */
    private final String name;
    /**
     * продолжительность печати.
     */
    private final int durationPrint;
    /**
     * размер докуметна.
     */
    private final Size size;

    /**
     * Конструктор.
     *
     * @param name1          имя документа
     * @param durationPrint1 продолжительность печати
     * @param size1          размер документа
     */
     Type(final String name1, final int durationPrint1, final Size size1) {
        this.name = name1;
        this.durationPrint = durationPrint1;
        this.size = size1;
    }

    /**
     * Продолжительность печати.
     *
     * @return продолжительность печати
     */
    public int getDuration() {
        return this.durationPrint;
    }

    /**
     * Получение наименования документа.
     * @return наименование
     */
    public String getName() {
        return this.name;
    }

    /**
     * Получение размера документа.
     * @return размер
     */
    public int getSize() {
        return (this.size.getHeight() * this.size.getWidth());
    }

}



