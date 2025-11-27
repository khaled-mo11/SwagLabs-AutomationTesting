package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class P02_HomePage {

    static float totalPriceOfSelectedProducts = 0;
    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;
    private final By addToCartButtonForAllProducts = By.xpath("//button[@class]");
    private final By numberOfProductsOnCartIcon = By.className("shopping_cart_badge");
    private final By numberOfSelectedProducts = By.xpath("//button[.='Remove']");
    private final By CartIcon = By.className("shopping_cart_link");//search by text
    private final By PricesOfSelectedProductsLocator = By.xpath("//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price']");
    private final By optionsMenuButton = By.id("react-burger-menu-btn");
    private final By logOutButton = By.id("logout_sidebar_link");

    private final WebDriver driver;

    public P02_HomePage(WebDriver driver) {

        this.driver = driver;
    }


    public By getNumberOfProductsOnCartIcon() {
        return numberOfProductsOnCartIcon;
    }

    public P02_HomePage addAllProductToCart() {
        allProducts = driver.findElements(addToCartButtonForAllProducts);
        LogsUtils.info("number of all products: " + allProducts.size());
        for (int i = 1; i <= allProducts.size(); i++) {
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + i + "]"); //dynamic locator format
            Utility.clickingOnElement(driver, addToCartButtonForAllProducts);
        }

        return this;
    }

    public String getNumberOfProductOnCartIcon() {
        try {
            LogsUtils.info("number of products on cart icon: " + Utility.getText(driver, numberOfProductsOnCartIcon));
            return Utility.getText(driver, numberOfProductsOnCartIcon);
        } catch (Exception e) {//expected exception if no number on the cart the locator wouldn't be found in dom
            LogsUtils.error(e.getMessage());
            return "0";
        }

    }

    public String getNumberOfAllSelectedProducts() { //didn't want to depend on the cart icon only so i will see the no. of remove buttons in the page
        try {
            selectedProducts = driver.findElements(numberOfSelectedProducts);
            LogsUtils.info("number of selected products: " + selectedProducts.size());
            return String.valueOf(selectedProducts.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public boolean comparingNumberOfSelectedProductsWthCartIconNumber() {
        return getNumberOfProductOnCartIcon().equals(getNumberOfAllSelectedProducts());
    }


    public P02_HomePage addRandomProductNumberToCart(int numberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> randomNumbers = Utility.generateUniqueNumbers(numberOfProductsNeeded, totalNumberOfProducts);
        for (int random : randomNumbers) {
            LogsUtils.info("randomNumber " + random);
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + random + "]"); //dynamic locator format
            Utility.clickingOnElement(driver, addToCartButtonForAllProducts);
        }

        return this;
    }

    public P03_CartPage clickOnCartIcon() {
        Utility.clickingOnElement(driver, CartIcon);
        return new P03_CartPage(driver);

    }


    public String getTotalPriceOfSelectedProducts() {
        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(PricesOfSelectedProductsLocator);
            for (int i = 1; i <= pricesOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]"); //dynamic locator
                String fullText = Utility.getText(driver, elements);
                totalPriceOfSelectedProducts += Float.parseFloat(fullText.replace("$", ""));

            }
            LogsUtils.info("Home Price = " + totalPriceOfSelectedProducts);
            return String.valueOf(totalPriceOfSelectedProducts);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public P02_HomePage clickingOnOptionsMenuButton() {
        Utility.clickingOnElement(driver, optionsMenuButton);
        return this;
    }

    public P01_LoginPage clickingOnLogOutButton() {
        Utility.clickingOnElement(driver, logOutButton);
        return new P01_LoginPage(driver);
    }

}
