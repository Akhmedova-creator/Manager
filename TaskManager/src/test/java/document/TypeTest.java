package document;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeTest {
    private final Document document = new Document(Type.A3,"doc");
    @Test
    public void getDuration() {
        assertEquals(2000,document.getType().getDuration());
    }
    @Test
    public void getName() {
        assertEquals("A3",document.getType().getName());
    }

    @Test
    public void getSize() {
        assertEquals(100,document.getType().getSize());
    }

}