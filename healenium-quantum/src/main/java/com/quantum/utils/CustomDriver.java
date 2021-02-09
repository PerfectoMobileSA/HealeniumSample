package com.quantum.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.healenium.SelfHealingDriver;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

public class CustomDriver {

	/**
	 * returns the Healenium driver.
	 * 
	 * @param description
	 */
	public static WebDriver getHealeniumDriver() {
		if (ConfigurationManager.getBundle().getString("healenium", "false").equalsIgnoreCase("true")) {
			return (WebDriver) SelfHealingDriver.create(DeviceUtils.getQAFDriver().getUnderLayingDriver());
		} else {
			return DeviceUtils.getQAFDriver().getUnderLayingDriver();
		}
	}
	
	/**
	 *  waits for visibility of the element and returns it
	 * @param by
	 * @param timeout
	 * @return
	 */
	public static WebElement waitForVisible(By by, int timeout) {
		WebElement element =  new WebDriverWait(CustomDriver.getHealeniumDriver(), timeout).until(ExpectedConditions.visibilityOf(
				CustomDriver.getHealeniumDriver().findElement(by)));
		return element;
	}

	/**
	 * returns QAFExtendedWebElement's locator value from .loc files
	 * 
	 * @param QAFExtendedWebElement
	 */
	public static String getLocator(QAFExtendedWebElement element) {
		String matching = "";
		Pattern pattern = Pattern.compile("(?:\\=|\\:).*");
		Matcher m = pattern.matcher(element.getMetaData().get("locator").toString());
		while (m.find()) {
			matching = m.group(0).substring(1);
		}
		return matching;
	}

	/**
	 * Returns true if Healenium driver is enabled in application.properties
	 * 
	 */
	public static boolean isHealeniumEnabled() {
		String healenium = ConfigurationManager.getBundle().getString("healenium", "false");
		if (healenium.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}
}