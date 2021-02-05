package perfecto.healenium.gradle;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.epam.healenium.SelfHealingDriver;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;

@Testcontainers
@ExtendWith(PerfectoWatcher.class)
public class TestBase {
	public static SelfHealingDriver driver;
	public static ReportiumClient reportiumClient;

	@BeforeAll
	static public void setUp() throws Exception {

		// Update cloudName variable with your perfecto cloud name
		String cloudName = System.getProperty("cloudName");
		// Update securityToken variable with your perfecto security token.
		String securityToken = System.getProperty("securityToken");
		DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
		capabilities.setCapability("securityToken", securityToken);
		capabilities.setCapability("platformName", "Windows");
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("browserVersion", "latest");
		capabilities.setCapability("location", "US East");
		capabilities.setCapability("resolution", "1024x768");
		RemoteWebDriver remDriver;
		try {
			remDriver = new RemoteWebDriver(
					new URL("https://" + cloudName + ".perfectomobile.com/nexperience/perfectomobile/wd/hub"),
					capabilities);
		} catch (SessionNotCreatedException e) {
			throw new RuntimeException("Driver not created with capabilities: " + capabilities.toString());
		}

		driver = SelfHealingDriver.create(remDriver);

		// Reporting client. For more details, see
		// http://developers.perfectomobile.com/display/PD/Reporting
		PerfectoExecutionContext perfectoExecutionContext;
		if (!System.getProperty("jobName").equals("NA")) {
			perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
					.withProject(new Project("My Project", "1.0"))
					.withJob(new Job(System.getProperty("jobName"), Integer.parseInt(System.getProperty("jobNumber"))))
					.withContextTags("tag1").withWebDriver(driver).build();
		} else {
			perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
					.withProject(new Project("My Project", "1.0")).withContextTags("tag1").withWebDriver(driver)
					.build();
		}
		reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
		reportiumClient.stepStart("Pre-conditions");
		remDriver.manage().window().maximize();
		remDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		remDriver.manage().window().setSize(new Dimension(1200, 800));
	}

	@AfterAll
	static public void afterAll() {
		// Retrieve the URL to the DigitalZoom Report
		if (reportiumClient != null) {
			String reportURL = reportiumClient.getReportUrl();
			System.out.println(reportURL);
		}
		if (driver != null) {
			driver.quit();
		}
	}
}
