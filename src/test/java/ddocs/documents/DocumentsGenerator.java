package ddocs.documents;

import ddocs.page.GeneralPage;
import org.junit.jupiter.api.Test;

public class DocumentsGenerator {
    GeneralPage page;

    @Test
    public void documentsGenerator() throws InterruptedException {
        page = new GeneralPage();
        page.open();
        page.login();
        for (int i = 0; i < 110; i++) {
            page.setFileName();
            page.addTextToDo();
            page.clickLeftMenu();
            page.addDocument();
        }
    }
}
