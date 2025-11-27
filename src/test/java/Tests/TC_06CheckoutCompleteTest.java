package Tests;

import Listeners.IInvokeMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P06_CheckoutCompletePage;
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
public class TC_06CheckoutCompleteTest {
    private final String FIRSTNAME = DataUtils.getJsonData("CheckoutForm", "firstname");
    private final String LASTNAME = DataUtils.getJsonData("CheckoutForm", "lastname");
    private final String POSTAL_CODE = DataUtils.getJsonData("CheckoutForm", "postalcode");
    private final String USERNAME = DataUtils.getJsonData("ValidLogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("ValidLogin", "password");

    public TC_06CheckoutCompleteTest() throws FileNotFoundException {
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
    public void FinishOrderTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLoginButton()
                .addRandomProductNumberToCart(3, 6)
                .clickOnCartIcon().ClickOnCheckoutButton()
                .FillingInformationForm(FIRSTNAME, LASTNAME, POSTAL_CODE)
                .ClickOnContinueButton()
                .ClickOnFinishButton();

        Assert.assertTrue(new P06_CheckoutCompletePage(getDriver()).CheckingTheFinalText());

    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }


}
