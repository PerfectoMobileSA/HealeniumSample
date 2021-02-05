package perfecto.healenium.gradle;

import org.junit.jupiter.api.Test;

import com.perfecto.reportium.test.TestContext;
import pages.MainPageWithFindBy;


public class MarkupTest extends TestBase {

    @Test
    public void testButtonClickWithFindByAnnotationPage() {
    	reportiumClient.testStart("Healenium Gradle Web Sample", new TestContext("Healenium", "gradle", "web")); // Starts the Smart Reporting test
    	reportiumClient.stepStart("click test and alert!");
        MainPageWithFindBy mainPage = new MainPageWithFindBy(driver);
        //find test button
        mainPage.open().clickTestButton();
        //confirm Alert
        mainPage.confirmAlert();
        reportiumClient.stepStart("test healing!");
        for (int i = 0; i <= 2; i++) {
            mainPage
                .generateMarkup() //regenerate Markup
                .clickTestButton(); //find test button again
            mainPage.confirmAlert();  //confirm Alert again
        }
    }
}
