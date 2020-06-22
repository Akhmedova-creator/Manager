package document;
/**
 * Класс содержит размер документа.
 */
public class Size {
    /**
     * height высота документа.
     */
    private final int height;
    /**
     * width ширина документа.
     */
    private final int width;
    /**
     * Конструктор.
     * @param height1 высота документа
     * @param width1 ширина документа
     */
     public Size(final int height1, final int width1) {
         this.height = height1;
         this.width = width1;
    }

    /**
     * Получение высоты документа.
     * @return высота
     */
    public int getHeight() {
        return this.height;
    }
    /**
     * Получение шиирины документа.
     * @return ширина
     */
    public int  getWidth() {
        return this.width;
    }
}