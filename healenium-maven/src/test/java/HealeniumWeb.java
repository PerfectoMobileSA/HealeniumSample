
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.perfecto.reportium.test.TestContext;

import common.Base;
import pages.MainPageWithFindBy;
public class HealeniumWeb extends Base {

	public MainPageWithFindBy mainPage;

	public HealeniumWeb() {
		super();
	}

	@BeforeMethod
	@Parameters({ "type" })
	public void setUp(@Optional("type") String value) throws Exception {
		intialization(value);
		mainPage = new MainPageWithFindBy(driver);
	}

	@Test
	public void perfectoTest() {
    	reportiumClient.testStart("Healenium Maven Web Sample", new TestContext("Healenium", "maven", "mobile-web")); // Starts the Smart Reporting test
		reportiumClient.stepStart("click test and alert!");
		// find test button
		mainPage.open().clickTestButton();
		// confirm Alert
		mainPage.confirmAlert();
		reportiumClient.stepStart("test healing!");
		for (int i = 0; i <= 2; i++) {
			mainPage.generateMarkup() // regenerate Markup
					.clickTestButton(); // find test button again
			mainPage.confirmAlert(); // confirm Alert again
		}
	}

	@Test
	public void localChromeTest() {
		mainPage.open().clickTestButton();
		// confirm Alert
		mainPage.confirmAlert();
		for (int i = 0; i <= 2; i++) {
			mainPage.generateMarkup() // regenerate Markup
					.clickTestButton(); // find test button again
			mainPage.confirmAlert(); // confirm Alert again
		}
	}
}