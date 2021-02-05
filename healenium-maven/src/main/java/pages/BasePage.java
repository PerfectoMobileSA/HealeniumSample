package pages;


import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected String mainPageUrl = "https://sha-test-app.herokuapp.com/";
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
