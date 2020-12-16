package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class CopyAndPasteExample {


    private static final String BASE_URL = "https://www.saucedemo.com";
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
        //driver.close();
    }


    @Test
    public void testExtractUserCredentials() {
        String user_names = driver.findElement(By.id("login_credentials")).getText();
        String firstUsername = user_names.split("\n")[1].trim();
        String passwordArea = driver.findElement(By.cssSelector(".login_password")).getText();
        String password = passwordArea.split("\n")[1].trim();

        driver.findElement(By.id("user-name")).sendKeys(firstUsername);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        //Verify the URL in the address bar
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }


}
