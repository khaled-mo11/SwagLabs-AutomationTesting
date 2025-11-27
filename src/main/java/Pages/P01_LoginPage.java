package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");

    private final WebDriver driver;

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_LoginPage enterUsername(String usernameText) {
        Utility.sendData(driver, username, usernameText);
        //LogsUtils.info("Entered username: " + usernameText);
        return this; //after entering the username i will still want to be in the same page so in the same class
    }

    public P01_LoginPage enterPassword(String passwordText) {
        Utility.sendData(driver, password, passwordText);
        // LogsUtils.info("Entered password: " + passwordText);
        return this; //after entering the username i will still want to be in the same page so return in the same class
    }

    public P02_HomePage clickOnLoginButton() {
        Utility.clickingOnElement(driver, loginButton);
        return new P02_HomePage(driver);

    }

    public boolean assertLoginTC(String expectedValue) {

        return driver.getCurrentUrl().equals(expectedValue);


    }


}
