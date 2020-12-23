package com.pragmatic.examples.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class WhyNeedSynchronisationTest {

    private static final String BASE_URL = "https://eviltester.github.io/synchole/collapseable.html";
    private static WebDriver webDriver;


    @BeforeMethod
    public static void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(BASE_URL);

    }


    @AfterMethod
    public static void afterMethod() {
        webDriver.close();
    }

    /**
     * This test will fail with ElementNotInteractableException
     */

    @Test
    public void navigatingToAbout() throws ElementNotInteractableException, InterruptedException {

        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        final WebElement section = webDriver.findElement(By.cssSelector("section.condense"));
        section.click();
        final WebElement sectionLink = webDriver.findElement(By.cssSelector("a#aboutlink"));

        sectionLink.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
    }


    @Test
    public void navigatingToAboutAssertElementNotInteractableException() {
        final WebElement section = webDriver.findElement(By.cssSelector("section.condense"));
        section.click();
        final WebElement sectionLink = webDriver.findElement(By.cssSelector("a#aboutlink"));

        Assert.assertThrows(ElementNotInteractableException.class,
                () -> {
                    sectionLink.click();
                }
        );

    }


    @Test
    public void navigatingToAboutWithThreadSleep() throws InterruptedException {
        final WebElement section = webDriver.findElement(By.cssSelector("section.condense"));
        section.click();

        final WebElement sectionLink = webDriver.findElement(
                By.cssSelector("a#aboutlink")
        );
        Thread.sleep(2000);
        sectionLink.click();

        Assert.assertTrue(webDriver.getCurrentUrl().contains("about.html"));

    }


    @Test
    public void navigatingToAboutWithImplicitWait() throws InterruptedException {
        webDriver.manage().timeouts().implicitlyWait(5_000, TimeUnit.SECONDS);
        final WebElement section = webDriver.findElement(By.cssSelector("section.condense"));
        section.click();

        final WebElement sectionLink = webDriver.findElement(
                By.cssSelector("a#aboutlink")
        );
        sectionLink.click();

        Assert.assertTrue(webDriver.getCurrentUrl().contains("about.html"));
    }


}
