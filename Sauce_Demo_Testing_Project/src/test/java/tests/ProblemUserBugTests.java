package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.ProductDetailsPage;

import java.time.Duration;
import java.util.List;

public class ProblemUserBugTests extends BaseTest {

    @Test
    public void TC017_removeProductFromCartFails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithProblemUser();
        Assert.assertTrue(loginPage.isLoggedIn(), "Login failed for problem_user");

        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Add first product to cart
        productsPage.addFirstProductToCart();

        // Wait until cart has 1 item
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".shopping_cart_badge"), 0));

        // Click Remove (bug: item will NOT be removed)
        productsPage.removeFirstProductFromCart();

        // Wait briefly for DOM to stabilize
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        // Get current cart count
        int cartCountAfterRemove = cartPage.getCartItemCount();

        // Fail explicitly to show the bug
        Assert.assertTrue(cartCountAfterRemove > 0,
                "Bug observed: Product was not removed from cart (problem_user).");
    }




    @Test
    public void TC018_productDetailsIncorrect() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithProblemUser();
        Assert.assertTrue(loginPage.isLoggedIn(), "Login failed for problem_user");

        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);

        String firstProductName = productsPage.getAllProductNames().get(0);
        productsPage.clickFirstProduct();

        // Explicit wait for product details name to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_details_name")));

        String displayedName = detailsPage.getProductName();
        String displayedDesc = detailsPage.getProductDescription();
        String displayedPrice = detailsPage.getProductPrice();

        Assert.assertEquals(displayedName, firstProductName,
                "Bug: Product details do not match the product clicked (problem_user).");
    }



    @Test
    public void TC019_allProductsSameImage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithProblemUser();
        Assert.assertTrue(loginPage.isLoggedIn());

        ProductsPage productsPage = new ProductsPage(driver);

        // Wait until all images are fully loaded (non-zero width/height)
        List<WebElement> images = driver.findElements(By.cssSelector(".inventory_item_img img"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (WebElement img : images) {
            wait.until((ExpectedCondition<Boolean>) d -> {
                Dimension size = img.getSize();
                return size.getHeight() > 0 && size.getWidth() > 0;
            });
        }

        // Fail test to trigger screenshot
        Assert.fail("UI Bug: All products displayed with the same image (problem_user).");
    }
    @Test
    public void TC020_productSortingFails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithProblemUser();
        Assert.assertTrue(loginPage.isLoggedIn());

        ProductsPage productsPage = new ProductsPage(driver);
        // Wait before sorting to make sure all products are rendered
        waitForPageToLoad();
        productsPage.sortBy("Name (Z to A)");

        List<String> sortedNames = productsPage.getAllProductNames();

        // Fail because sorting does not work for problem_user
        Assert.assertTrue(false, "Bug: Products not sorted correctly (problem_user).");
    }
}

