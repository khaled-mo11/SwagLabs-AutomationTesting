package Tests;

import Listeners.IInvokeMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Pages.P03_CartPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokeMethodListenerClass.class, ITestResultListenerClass.class})
public class TC03_CartTest {
    private final String USERNAME = DataUtils.getJsonData("validLogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("validLogin", "password");

    public TC03_CartTest() throws FileNotFoundException {
    }


    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(getPropertyValue("enviroment", "Browser"));
        LogsUtils.info("Driver is opened");
        getDriver().get(getPropertyValue("enviroment", "BASE_URL"));
        LogsUtils.info("Page is redirected to base_url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void ComparingPricesTC() throws IOException {
        String allPrice = new P01_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLoginButton()
                .addRandomProductNumberToCart(3, 6)
                .getTotalPriceOfSelectedProducts();
        new P02_HomePage(getDriver()).clickOnCartIcon();
        Assert.assertTrue(new P03_CartPage(getDriver()).comparingPrices(allPrice));

    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }

}