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

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class FluentWait {


    private static final String BASE_URL = "http://hrm.pragmatictestlabs.com/";
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }


    @Test
    public void testLocatingDynamicElementsWithoutWaits() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("TEST");
        driver.findElement(By.id("btnLogin")).click();
        String errorMessage = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(errorMessage, "Invalid credentials");
    }

    @Test
    public void test() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("btnLogin")).click();

        //Logout
        driver.findElement(By.id("welcome")).click();  //Click welcome link

        //Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(200));
        WebElement lnkLogout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']")));
        lnkLogout.click();

        //driver.findElement(By.xpath("//a[text()='Logout']")).click(); //Click logout link
        Assert.assertTrue(driver.findElement(By.id("txtUsername")).isDisplayed()); //Verify if username field is displayed

    }


    @Test
    public void testLogoutWithImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5));
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("btnLogin")).click();

        //Logout
        driver.findElement(By.id("welcome")).click();

        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Assert.assertTrue(driver.findElement(By.id("txtUsername")).isDisplayed());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));


    }


    @Test
    public void testLogoutWithSleep() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5));
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("btnLogin")).click();

        //Logout
        driver.findElement(By.id("welcome")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Assert.assertTrue(driver.findElement(By.id("txtUsername")).isDisplayed());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));


    }

}
