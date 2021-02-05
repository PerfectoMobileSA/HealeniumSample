package pages;


import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.Alert;

public class BasePage {
    protected String mainPageUrl = "https://sha-test-app.herokuapp.com/";
    protected SelfHealingDriver driver;

    public BasePage(SelfHealingDriver driver) {
        this.driver = driver;
    }

    public void confirmAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
