package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class P06_CheckoutCompletePage {
    private final WebDriver driver;
    private final By FinishingText = By.className("complete-header");

    public P06_CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean CheckingTheFinalText() {
        return Utility.findWebElement(driver, FinishingText).isDisplayed();
    }


}