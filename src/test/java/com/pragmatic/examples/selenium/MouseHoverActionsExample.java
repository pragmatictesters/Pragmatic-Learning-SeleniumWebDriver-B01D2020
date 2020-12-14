package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
public class MouseHoverActionsExample {


    private static final String BASE_URL = "https://www.daraz.lk/";
    private ChromeDriver driver;


    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }


    @Test
    public void testMouseHover() {

        Actions actions = new Actions(driver);

        //Mouse over actions
        actions.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Electronic Devices')]")))
                .pause(Duration.ofMillis(500))
                .moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Mobiles')]")))
                .pause(Duration.ofMillis(500))
                .moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Samsung Mobiles')]")))
                .pause(500)
                .click()
                .perform();


        //Assert the title in the page
        Assert.assertTrue(driver.getTitle().contains("Samsung Phone Price In Sri Lanka"));


    }

}
