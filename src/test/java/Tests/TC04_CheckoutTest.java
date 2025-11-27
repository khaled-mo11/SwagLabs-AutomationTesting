package Tests;

import Listeners.IInvokeMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
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
public class TC04_CheckoutTest {

    private final String FIRSTNAME = DataUtils.getJsonData("CheckoutForm", "firstname");
    private final String LASTNAME = DataUtils.getJsonData("CheckoutForm", "lastname");
    private final String POSTAL_CODE = DataUtils.getJsonData("CheckoutForm", "postalcode");
    private final String USERNAME = DataUtils.getJsonData("validLogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("validLogin", "password");

    public TC04_CheckoutTest() throws FileNotFoundException {
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
    public void CheckOutStepOneTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLoginButton()
                .addRandomProductNumberToCart(3, 6)
                .clickOnCartIcon()
                .ClickOnCheckoutButton();
        Assert.assertTrue(Utility.verifyURL(getDriver(), DataUtils.getPropertyValue("enviroment", "CHECKOUT_STEP_ONE_URL")));
    }

    @Test
    public void CheckOutStepTwoTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLoginButton()
                .addRandomProductNumberToCart(3, 6)
                .clickOnCartIcon()
                .ClickOnCheckoutButton()
                .FillingInformationForm(FIRSTNAME, LASTNAME, POSTAL_CODE)
                .ClickOnContinueButton();
        LogsUtils.info(FIRSTNAME + " " + LASTNAME + " " + POSTAL_CODE + " Sent Successfully");
        Assert.assertTrue(Utility.verifyURL(getDriver(), DataUtils.getPropertyValue("enviroment", "CHECKOUT_STEP_TWO_URL")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }


}
