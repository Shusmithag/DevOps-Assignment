package com.DockerTestUsingGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class AmazonGridTest {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws Exception {

        System.out.println("======================================");
        System.out.println("Trying to connect to Selenium Grid...");
        System.out.println("Browser Requested: " + browser);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browser);

        driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"), cap);

        System.out.println("Connection established with: " + browser);
        System.out.println("======================================");
    }

    @Test
    public void amazonSearchTest() {

        driver.get("https://www.amazon.in");

        driver.findElement(By.id("twotabsearchtextbox"))
              .sendKeys("iphone");

        driver.findElement(By.id("nav-search-submit-button")).click();

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("iphone"));

        System.out.println("TEST PASSED on " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing browser...");
        driver.quit();
    }
}
