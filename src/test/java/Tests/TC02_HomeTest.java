package Tests;

import Listeners.IInvokeMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokeMethodListenerClass.class, ITestResultListenerClass.class})
public class TC02_HomeTest {

    private final String USERNAME = DataUtils.getJsonData("validLogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("validLogin", "password");

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(getPropertyValue("enviroment", "Browser"));
        LogsUtils.info("Driver is opened");
        getDriver().get(getPropertyValue("enviroment", "BASE_URL"));
        LogsUtils.info("Page is redirected to base_url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void checkingNumberOfSelectedProductsTC() {
        new P01_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLoginButton()
                .addAllProductToCart();


        Assert.assertTrue(new P02_HomePage(getDriver()).comparingNumberOfSelectedProductsWthCartIconNumber());

    }

    @Test
    public void addingRandomProductsToCartTC() {
        new P01_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLoginButton()
                .addRandomProductNumberToCart(3, 6);


        Assert.assertTrue(new P02_HomePage(getDriver()).comparingNumberOfSelectedProductsWthCartIconNumber());

    }

    @Test
    public void clickingOnCartIconTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLoginButton()
                .clickOnCartIcon();

        Assert.assertTrue(Utility.verifyURL(getDriver(), DataUtils.getPropertyValue("enviroment", "CART_URL")));

    }

    @Test
    public void verifyLogOutTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLoginButton()
                .clickingOnOptionsMenuButton().clickingOnLogOutButton();

        Assert.assertTrue(Utility.verifyURL(getDriver(), DataUtils.getPropertyValue("enviroment", "BASE_URL")));

    }


    @AfterMethod
    public void quit() {
        quitDriver();

    }
}
