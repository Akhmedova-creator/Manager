import document.Document;
import document.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTest {
    @Test
    public void testManager() throws InterruptedException {
        final PrintManager manager = new PrintManagerImpl();
        Document doc1 = new Document(Type.A3, "test"); // 2sec
        Document doc2 = new Document(Type.A4, "test"); // 1 sec
        manager.addDocument(doc1);
        manager.addDocument(doc2);
        Assertions.assertEquals(1, manager.getUnHandledDocuments().size());
        // Подождем 4 секунды, чтоб документ распечатался
        Thread.sleep(4000);
        Assertions.assertEquals(manager.getUnHandledDocuments().size(), 0);
        Assertions.assertEquals(manager.getSortedDocuments(Document.FieldOrder.NAME).size(), 2);

    }
}