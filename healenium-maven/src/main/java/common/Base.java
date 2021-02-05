package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.epam.healenium.SelfHealingDriver;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;
import com.typesafe.config.ConfigFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public  WebDriver driver;
	public static Properties prop;
	public WebDriver delegate;
	public static URL url;
	public static DesiredCapabilities capabilities;
	public  ReportiumClient reportiumClient;

	public Base() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//resources//healenium.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public  void intialization(String browserName) throws Exception {
		// Update cloudName variable with your perfecto cloud name
		String cloudName = System.getProperty("cloudName");
		// Update securityToken variable with your perfecto security token.
		String securityToken = System.getProperty("securityToken");
		if (browserName.equals("localWebChrome")) {
			WebDriverManager.chromedriver().setup();
			delegate = new ChromeDriver(); // declare delegate
			com.typesafe.config.Config config = (com.typesafe.config.Config) ConfigFactory.load("healenium.properties");
			// create self-healing driver
			driver = (WebDriver)SelfHealingDriver.create(delegate, config);
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} else if (browserName.equals("perfectoWebChrome")) {
			DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
			capabilities.setCapability("securityToken", securityToken);
			capabilities.setCapability("platformName", "Windows");
			capabilities.setCapability("platformVersion", "10");
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("browserVersion", "latest");
			capabilities.setCapability("location", "US East");
			capabilities.setCapability("resolution", "1024x768");
			driver = createPerfectoDriver(cloudName, capabilities);
		} else if (browserName.equals("perfectoMobileChrome")) {
			String browser = "mobileOS";
			DesiredCapabilities capabilities = new DesiredCapabilities(browser, "", Platform.ANY);
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("securityToken", securityToken);
			capabilities.setCapability("useAppiumForWeb", true);
			driver = createPerfectoDriver( cloudName, capabilities);
		} else {
			System.out.println("invalid browserName");
		}
	}

	private WebDriver createPerfectoDriver(String cloudName, DesiredCapabilities capabilities)
			throws MalformedURLException {
		RemoteWebDriver remDriver;
		try {
			remDriver = new RemoteWebDriver(
					new URL("https://" + cloudName + ".perfectomobile.com/nexperience/perfectomobile/wd/hub"),
					capabilities);
		} catch (SessionNotCreatedException e) {
			throw new RuntimeException("Driver not created with capabilities: " + capabilities.toString());
		}

		driver = (WebDriver)SelfHealingDriver.create(remDriver);

		// Reporting client. For more details, see
		// http://developers.perfectomobile.com/display/PD/Reporting
		PerfectoExecutionContext perfectoExecutionContext;
		if (System.getProperty("reportium-job-name") != null) {
			perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
					.withProject(new Project("My Project", "1.0"))
					.withJob(new Job(System.getProperty("reportium-job-name"),
							Integer.parseInt(System.getProperty("reportium-job-number"))))
					.withContextTags("tag1").withWebDriver(driver).build();
		} else {
			perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
					.withProject(new Project("My Project", "1.0")).withContextTags("tag1").withWebDriver(driver)
					.build();
		}
		reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
		reportiumClient.stepStart("Pre-conditions");
		remDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (cloudName.contains("Web")) {
			remDriver.manage().window().maximize();
			remDriver.manage().window().setSize(new Dimension(1200, 800));
		}
		return driver;
	}

	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (reportiumClient != null) {
			TestResult testResult = null;
			if (result.getStatus() == ITestResult.SUCCESS) {
				testResult = TestResultFactory.createSuccess();
			} else if (result.getStatus() == ITestResult.FAILURE) {
				testResult = TestResultFactory.createFailure(result.getThrowable());
			}
			reportiumClient.testStop(testResult);

			// Retrieve the URL to the DigitalZoom Report
			String reportURL = reportiumClient.getReportUrl();
			System.out.println(reportURL);
		}
		driver.quit();
	}
}
