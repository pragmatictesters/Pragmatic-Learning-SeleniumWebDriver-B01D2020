package com.pragmatic.selenium.hrm;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
public class TestUserLogin {

    private WebDriver driver;
    private String BROWSER_TYPE = "safari";

    @BeforeClass
    public void beforeClass() {
        BrowserManager.setupDrivers(BROWSER_TYPE);
    }


    @BeforeMethod
    private void beforeMethod() {
        driver = BrowserManager.launchBrowser(BROWSER_TYPE);
        driver.get("http://hrm.pragmatictestlabs.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
        driver.findElement(By.id("txtPassword")).sendKeys("PTEst");
        driver.findElement(By.id("txtPassword")).submit();

        //Verify the error message
        waitForError("Invalid credentials");
        Assert.assertEquals(driver.findElement(By.id("spanMessage")).getText(), "Invalid credentials");

    }

    @Test(dataProvider = "user-credentials", dataProviderClass = HRMTestData.class)
    public void testInvalidUserLogin(String username, String password, String expectedError) throws InterruptedException {
        //Type the username
        driver.findElement(By.id("txtUsername")).sendKeys(username);

        //Type the password
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("txtPassword")).submit();

        //Verify the error message
        waitForError(expectedError);
        Assert.assertEquals(driver.findElement(By.id("spanMessage")).getText(), expectedError);

    }

    private void waitForError(String error_message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("spanMessage")));
        wait.until(ExpectedConditions.textToBe(By.id("spanMessage"), error_message));
    }


}
