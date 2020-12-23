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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class WebDriverWaitTest {

    private static final String BASE_URL = "https://eviltester.github.io/synchole/buttons.html";
    private static WebDriver webDriver;


    @BeforeMethod
    public static void createDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(BASE_URL);

    }


    @AfterMethod
    public static void closeDriver() {
        webDriver.close();
    }


    @Test
    public void testHardToSyncButtons() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.findElement(By.id("button00")).click();
        webDriver.findElement(By.id("button01")).click();
        webDriver.findElement(By.id("button02")).click();
        webDriver.findElement(By.id("button03")).click();

        Assert.assertEquals("All Buttons Clicked",
                webDriver.findElement(By.id("buttonmessage")).getText()
        );

    }


    @Test
    public void secondButtonsWithWebDriverWaitToSyncOnButtonDisplayedAndClick() {

        clickDynamicButton(By.id("button00"));
        clickDynamicButton(By.id("button01"));
        clickDynamicButton(By.id("button02"));
        clickDynamicButton(By.id("button03"));


        Assert.assertEquals("All Buttons Clicked",
                webDriver.findElement(By.id("buttonmessage")).getText()
        );


    }


    private void clickDynamicButton(By button) {
        WebElement btnOne = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(
                        ExpectedConditions.elementToBeClickable(button
                        )
                );
        btnOne.click();
    }


}
