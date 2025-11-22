package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin_standardUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginWithStandardUser();

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "User should be redirected to inventory page after valid login."
        );
    }

    @Test
    public void invalidLogin_wrongPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "wrongPassword123");

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed for invalid credentials."
        );

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message text is incorrect."
        );
    }

    @Test
    public void validLogin_problemUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginWithProblemUser();

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "Problem user should still be able to log in successfully."
        );
    }
}