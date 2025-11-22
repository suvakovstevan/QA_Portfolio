package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By productTitles = By.className("inventory_item_name");
    private By firstProductAddButton = By.cssSelector(".inventory_item:first-of-type button.btn_inventory");
    private By firstProductRemoveButton = By.cssSelector(".inventory_item:first-of-type button.btn_secondary");
    private By cartBadge = By.className("shopping_cart_badge");
    private By sortingDropdown = By.cssSelector("select.product_sort_container");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickFirstProduct() {
        driver.findElement(productTitles).click();
    }

    public void addFirstProductToCart() {
        driver.findElement(firstProductAddButton).click();
    }

    public void removeFirstProductFromCart() {
        driver.findElement(firstProductRemoveButton).click();
    }

    public String getCartBadgeText() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (Exception e) {
            return "0"; // no badge
        }
    }

    public void sortBy(String optionText) {
        driver.findElement(sortingDropdown).click();
        driver.findElement(sortingDropdown).sendKeys(optionText);
    }

    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        driver.findElements(productTitles).forEach(el -> names.add(el.getText()));
        return names;
    }
}
