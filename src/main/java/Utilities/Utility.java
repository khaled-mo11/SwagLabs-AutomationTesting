package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utility {

    private static final String SCREENSHOT_PATH = "test-outputs/Screenshots/";


    public static void clickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void sendData(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }

    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            //capture scrrenshot using TakescreenShot
            File screenSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            //save screenshot to file
            File screenDest = new File(SCREENSHOT_PATH + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenSrc, screenDest);

            //attach screenshot to allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenDest.getPath())));
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    public static void takeFullScreenshot(WebDriver driver, String screenshotName, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(findWebElement(driver, locator))
                    .save(SCREENSHOT_PATH);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }


    public static String getTimeStamp() {

        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).deselectByVisibleText(option);

    }

    public static int generateRandomNumber(int upperBoundry) {
        return new Random().nextInt(upperBoundry) + 1;
    }

    public static Set<Integer> generateUniqueNumbers(int numberOfNeededProducts, int numberOfTotalProducts) {
        Set<Integer> generatedNumbers = new HashSet<>();

        while (generatedNumbers.size() < numberOfNeededProducts) {
            int randomNumber = generateRandomNumber(numberOfTotalProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }

    public static boolean verifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
