package com.DockerTestUsingGrid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;

public class AmazonGridTest {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws Exception {

        System.out.println("======================================");
        System.out.println("Trying to connect to Selenium Grid...");
        System.out.println("Browser Requested: " + browser);

        // Setup browser options for Grid
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // optional for Jenkins
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } else if(browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } else if(browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } else {
            throw new Exception("Browser not supported: " + browser);
        }

        System.out.println("Connection established with: " + browser);
        System.out.println("======================================");
    }

    @Test
    public void amazonSearchTest() {

        driver.get("https://www.amazon.in");

        // Explicit wait for search box
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox"))
        );

        searchInput.sendKeys("iphone");

        // Wait and click search button
        WebElement searchButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("nav-search-submit-button"))
        );
        searchButton.click();

        // Verify page title contains 'iphone'
        wait.until(ExpectedConditions.titleContains("iphone"));
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("iphone"),
                "Page title does not contain 'iphone'");

        System.out.println("TEST PASSED on " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing browser...");
        if(driver != null) {
            driver.quit();
        }
    }
}

