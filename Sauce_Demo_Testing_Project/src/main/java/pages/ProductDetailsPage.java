package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

    private WebDriver driver;

    private By productName = By.cssSelector(".inventory_details_name");
    private By productDescription = By.cssSelector(".inventory_details_desc");
    private By productPrice = By.cssSelector(".inventory_details_price");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }
}
