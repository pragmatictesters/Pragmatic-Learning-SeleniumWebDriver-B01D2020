package com.pragmatic.examples.selenium.frames;

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

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class IFrameExample {


    private static final String BASE_URL = "https://www.thetimes.co.uk/";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }


    @Test
    public void testMovingToIframeAndClosingAcceptCookiesPopup() throws InterruptedException {
        String cssIframe = "iframe#sp_message_iframe_216133";
        String xpathBtnIAgree = "//button[contains(text(), 'I Agree')]";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssIframe)));

        driver.switchTo().frame("sp_message_iframe_216133");

        WebElement btnAgree = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathBtnIAgree)));

        //Verifying the title in the frame
        WebElement lnkPrivatePolicy = driver.findElement(By.linkText("Privacy Policy"));
        Assert.assertTrue(lnkPrivatePolicy.isDisplayed() & lnkPrivatePolicy.isEnabled());
        Assert.assertTrue(driver.getPageSource().contains("<title>Notice Message App</title>"));
        Assert.assertEquals(driver.findElement(By.tagName("title")).getAttribute("innerHTML"), "Notice Message App");

        btnAgree.click();

        driver.switchTo().defaultContent();

        //Verifying the page title after switching
        Assert.assertEquals(driver.getTitle(), "The Times & The Sunday Times");


    }

}
