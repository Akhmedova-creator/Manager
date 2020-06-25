package document;

import org.junit.Test;

import static org.junit.Assert.*;

public class SizeTest {
    private final Size size = new Size(10,20);
    @Test
    public void getHeight() {
        assertEquals(10,size.getHeight());
    }

    @Test
    public void getWidth() {
        assertEquals(20,size.getWidth());
    }
}