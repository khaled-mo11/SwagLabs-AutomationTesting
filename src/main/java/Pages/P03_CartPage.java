package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    static float totalPriceOfSelectedProducts = 0;
    private final WebDriver driver;
    private final By PricesOfSelectedProductsLocator = By.xpath("//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price']");

    private final By CheckoutButton = By.id("checkout");


    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getTotalPrice() {
        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(PricesOfSelectedProductsLocator);
            for (int i = 1; i <= pricesOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]"); //dynamic locator
                String fullText = Utility.getText(driver, elements);
                totalPriceOfSelectedProducts += Float.parseFloat(fullText.replace("$", ""));

            }
            LogsUtils.info("Cart Total Price = " + totalPriceOfSelectedProducts);
            return String.valueOf(totalPriceOfSelectedProducts);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public boolean comparingPrices(String price) {
        return getTotalPrice().equals(price);
    }


    public P04_CheckoutPage ClickOnCheckoutButton() {
        Utility.clickingOnElement(driver, CheckoutButton);
        return new P04_CheckoutPage(driver);
    }
}