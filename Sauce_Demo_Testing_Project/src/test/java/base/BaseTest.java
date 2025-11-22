package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        // Take screenshot on failure
        if (ITestResult.FAILURE == result.getStatus()) {

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File dest = new File("screenshots/" + result.getMethod().getMethodName() + ".png");

            dest.getParentFile().mkdirs(); // create folder if needed

            try {
                Files.copy(screenshot.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Screenshot saved to: " + dest.getAbsolutePath());
        }

        if (driver != null) {
            driver.quit();
        }
    }
    public void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }
}
