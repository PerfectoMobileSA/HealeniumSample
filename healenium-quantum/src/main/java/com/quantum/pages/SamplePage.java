package com.quantum.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.utils.CustomDriver;

public class SamplePage extends WebDriverBaseTestPage<WebDriverTestPage> {

	@Override
	protected void openPage(PageLocator locator, Object... args) {
	}

	@FindBy(locator = "markup.btn")
	private QAFExtendedWebElement generateMarkupBtnId;

	public void confirmAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public SamplePage generateMarkup() {
		generateMarkupBtnId.click();
		return this;
	}

	public QAFExtendedWebElement getTestButton() {
		QAFExtendedWebElement testButton = new QAFExtendedWebElement("test.btn");
		return testButton;
	}

	public void clickTestButton() {
		CustomDriver.waitForVisible(By.xpath(CustomDriver.getLocator(getTestButton())), 15).click();
	}
}
