package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
public class SelectExample {


    private static final String BASE_URL = "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select";
    private ChromeDriver driver;


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
    public void testSelectInIframe() {


        driver.switchTo().frame("iframeResult");

        //Select a
        Select cars = new Select(driver.findElement(By.id("cars")));


        cars.selectByVisibleText("Audi");
        cars.selectByValue("opel");
        cars.selectByIndex(1);

        driver.findElement(By.cssSelector("[type='submit']")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.w3-container.w3-large.w3-border")));

        Assert.assertTrue(result.getText().trim().contentEquals("cars=saab"));
    }


}
