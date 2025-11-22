package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    private By removeButtonFirstItem = By.cssSelector(".cart_item:first-of-type button.cart_button");
    private By cartItems = By.className("cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeFirstItem() {
        driver.findElement(removeButtonFirstItem).click();
    }
}
