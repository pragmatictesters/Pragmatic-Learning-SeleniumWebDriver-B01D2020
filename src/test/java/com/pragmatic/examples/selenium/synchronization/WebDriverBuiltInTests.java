package com.pragmatic.examples.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class WebDriverBuiltInTests {

    private static final String BASE_URL = "https://eviltester.github.io/synchole/form.html";
    private static WebDriver webDriver;


    @BeforeMethod
    public static void createDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();


    }


    @AfterMethod
    public static void closeDriver() {
        webDriver.close();
    }


    /**
     * WebDriver sync on basic page loads and does not need any explicit waiting
     */
    @Test
    public void builtInSyncOnPageLoad() {
        webDriver.get("https://eviltester.github.io/synchole/collapseable.html");
        Assert.assertEquals(
                "SyncHole",
                webDriver.findElement(By.cssSelector("h2")).getText()
        );

    }


    @Test
    public void submitResultsPageLoadWait() throws InterruptedException {
        webDriver.get(BASE_URL);
        final WebElement txtUsername = webDriver.findElement(By.name("username"));
        txtUsername.sendKeys("Janesh");
        txtUsername.submit();


        Assert.assertEquals("Thanks For Your Submission",
                webDriver.findElement(By.id("thanks")).getText()
        );

    }


    @Test
    public void submitResultsWaitForUsernamePopulation() throws InterruptedException {
        webDriver.get(BASE_URL);
        final WebElement txtUsername = webDriver.findElement(By.name("username"));
        txtUsername.sendKeys("Janesh");
        txtUsername.submit();

        Assert.assertEquals("Janesh",
                webDriver.findElement(By.cssSelector("li[data-name='username']"))
                        .getAttribute("data-value")
        );


    }


}
