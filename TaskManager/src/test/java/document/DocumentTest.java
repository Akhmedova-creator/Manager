package document;

import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentTest {
   private final Document document1 = new Document(Type.A3,"doc");
   private final Document document2 = new Document(Type.A3,"doc");
    @Test
    public void toStrings() {
        String actual = document1.toString();
        assertEquals("[doc] ",actual);
    }

    @Test
    public void getType() {
     Type actual = document1.getType();
        assertEquals(Type.A3,actual);
    }

    @Test
    public void compareTo() {
        int actual = document1.compareTo(document2);
        assertEquals(actual,0);
    }

    @Test
    public void equals1() {
        boolean actual = document1.equals(document2);
        assertTrue(String.valueOf(actual),true);
    }
}