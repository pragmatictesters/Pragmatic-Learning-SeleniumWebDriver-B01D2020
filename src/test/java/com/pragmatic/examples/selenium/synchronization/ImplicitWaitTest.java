package com.pragmatic.examples.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ImplicitWaitTest {

    private static final String BASE_URL = "https://eviltester.github.io/synchole/buttons.html";
    private static WebDriver webDriver;
    private WebDriverWait wait;


    @BeforeClass
    public void beforeClass() {

    }

    @BeforeMethod
    public void createDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        webDriver.get(BASE_URL);

    }


    @AfterMethod
    public void closeDriver() {
        webDriver.close();
    }

    @Test
    public void implicitWaitToSyncOnButtonDisplayedAndClick() throws InterruptedException {

        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        final WebElement btnOne = webDriver.findElement(By.id("easy00"));
        btnOne.click();

        final WebElement btnTwo = webDriver.findElement(By.id("easy01"));
        btnTwo.click();


        final WebElement btnThree = webDriver.findElement(By.id("easy02"));
        btnThree.click();


        final WebElement btnFour = webDriver.findElement(By.id("easy03"));
        btnFour.click();


        Assert.assertEquals("All Buttons Clicked",
                webDriver.findElement(By.id("easybuttonmessage")).getText()
        );
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }


    /**
     * Clicks does not fail as element is available in the dom
     * Assertion fails at the end of the test
     */

    @Test
    public void secondButtonsWithimplicitWaitToSyncOnButtonDisplayedAndClick() {

        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        final WebElement btnOne = webDriver.findElement(By.id("button00"));
        btnOne.click();

        final WebElement btnTwo = webDriver.findElement(By.id("button01"));
        btnTwo.click();

        final WebElement btnThree = webDriver.findElement(By.id("button02"));
        btnThree.click();

        final WebElement btnFour = webDriver.findElement(By.id("button03"));
        btnFour.click();


        Assert.assertEquals("All Buttons Clicked",
                webDriver.findElement(By.id("easybuttonmessage")).getText()
        );
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));


    }


    @Test
    public void testButtonToBeClickable() {

        waitAndClick(By.id("button00"));
        waitAndClick(By.id("button01"));
        waitAndClick(By.id("button02"));
        waitAndClick(By.id("button03"));

        waitForTextToBe("All Buttons Clicked", By.id("buttonmessage"));

        Assert.assertEquals(webDriver.findElement(By.id("buttonmessage")).getText(), "All Buttons Clicked");


    }

    private void waitForTextToBe(String all_buttons_clicked, By easybuttonmessage) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(easybuttonmessage, all_buttons_clicked));
    }

    private void waitAndClick(By button) {

        WebElement clickableButton = wait.until(ExpectedConditions.elementToBeClickable(button));
        clickableButton.click();
    }


}
