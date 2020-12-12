package com.pragmatic.selenium.hrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
public class TestUserLogin {

    private WebDriver driver;
    private String BROWSER_TYPE ="firefox";

    @BeforeClass
    public void beforeClass() {

        BrowserManager.setupDrivers(BROWSER_TYPE);
        //WebDriverManager.chromedriver().setup();

    }


    @BeforeMethod
    private void beforeMethod() {
        driver = BrowserManager.launchBrowser(BROWSER_TYPE);
        //driver = new ChromeDriver();
        driver.get("http://hrm.pragmatictestlabs.com");
    }

    @AfterMethod
    private void afterMethod() {
        driver.close();
    }

    @Test
    public void testValidUserLogin() {

        //Type the username
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type the password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");

        //Click the login button
        driver.findElement(By.id("btnLogin")).click();

        //Get the welcome message
        String msgWelcome = driver.findElement(By.id("welcome")).getText();

        Assert.assertEquals(msgWelcome, "Welcome Admin");


    }


    @Test
    public void testValidUserLoginWithEnterKeyFromPasswordField() {

        //Type the username
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");


        //Type the password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321" + Keys.ENTER);


        //Get the welcome message
        String msgWelcome = driver.findElement(By.id("welcome")).getText();

        Assert.assertEquals(msgWelcome, "Welcome Admin");


    }

    @Test
    public void testValidUserLoginWithFormSubmit() {

        //Type the username
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");


        //Type the password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("txtPassword")).submit();


        //Get the welcome message
        String msgWelcome = driver.findElement(By.id("welcome")).getText();

        Assert.assertEquals(msgWelcome, "Welcome Admin");

    }


    @Test
    public void testUserLoginWithBlankUsernameAndPassword() {

        //Type the username
        driver.findElement(By.id("txtUsername")).sendKeys("");


        //Type the password
        driver.findElement(By.id("txtPassword")).sendKeys("");
        driver.findElement(By.id("txtPassword")).submit();



        //Verify the error message
        Assert.assertEquals(driver.findElement(By.id("spanMessage")).getText(), "Username cannot be empty");

    }

    @Test
    public void testUserLoginWithBlankUsername() {

        //Type the username
        driver.findElement(By.id("txtUsername")).sendKeys("");


        //Type the password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("txtPassword")).submit();

        //Verify the error message
        Assert.assertEquals(driver.findElement(By.id("spanMessage")).getText(), "Username cannot be empty");

    }


    @Test
    public void testUserLoginWithBlankPassword() {

        //Type the username
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");


        //Type the password
        driver.findElement(By.id("txtPassword")).sendKeys("");
        driver.findElement(By.id("txtPassword")).submit();

        //Verify the error message
        Assert.assertEquals(driver.findElement(By.id("spanMessage")).getText(), "Password cannot be empty");

    }


@Test
    public void testUserLoginWithInvalidPassword() {
        //Type the username
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type the password
        driver.findElement(By.id("txtPassword")).sendKeys("TEST");
        driver.findElement(By.id("txtPassword")).submit();

        //Verify the error message
        Assert.assertEquals(driver.findElement(By.id("spanMessage")).getText(), "Invalid credentials");

    }







}
