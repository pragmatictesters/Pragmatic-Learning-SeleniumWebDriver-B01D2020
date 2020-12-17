package com.pragmatic.examples.selenium.pagefactory.tests;

import com.pragmatic.examples.selenium.pagefactory.pages.LandingPage;
import com.pragmatic.examples.selenium.pagefactory.pages.LoginPage;
import com.pragmatic.selenium.hrm.HRMConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
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
public class LoginTest {


    private static final String BASE_URL = HRMConstants.BASE_URL;
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
    public void testValidUserLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("Admin")
                .typePassword("Ptl@#321")
                .clickLogin();
        //Verify the welcome message
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertEquals(landingPage.getWelcomeMessage(), "Welcome Admin");

    }


    @Test
    public void testLoginWithBlankUsernameAndBlankPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearUsername()
                .clearPassword()
                .clickLogin();
        //Verify the error message
        Assert.assertEquals(loginPage.getError(), "Username cannot be empty");
    }

    @Test
    public void testLoginWithBlankUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearUsername()
                .typePassword("PTL@#321")
                .clickLogin();
        //Verify the error message
        Assert.assertEquals(loginPage.getError(), "Username cannot be empty");
    }


}
