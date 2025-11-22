package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Perform login
    public void login(String username, String password) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(loginButton).click();
    }

    // Helpers
    public void loginWithStandardUser() {
        login("standard_user", "secret_sauce");
    }

    public void loginWithProblemUser() {
        login("problem_user", "secret_sauce");
    }

    // Check successful login by URL
    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.urlContains("inventory.html"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Check failed login (still on login page)
    public boolean isErrorDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
