package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.quantum.pages.SamplePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class SampleStepDefs {

	SamplePage samplePage = new SamplePage();

	@Given("^I am on Sample Page$")
	public void I_am_on_Google_Search_Page() throws Throwable {
		new WebDriverTestBase().getDriver().get("https://sha-test-app.herokuapp.com");
	}

	@When("^I generate markup$")
	public void I_generate_markup() throws Throwable {
		samplePage.clickTestButton();
		// confirm Alert
		samplePage.confirmAlert();
		for (int i = 0; i <= 2; i++) {
			samplePage.generateMarkup() // regenerate Markup
					.clickTestButton(); // find test button again
			samplePage.confirmAlert(); // confirm Alert again
		}
	}
}
