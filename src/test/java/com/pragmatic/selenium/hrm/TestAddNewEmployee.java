package com.pragmatic.selenium.hrm;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
public class TestAddNewEmployee {

    private static final By TXT_USERNAME = By.id("txtUsername");
    private static final By TXT_PASSWORD = By.id("txtPassword");
    private static final By BTN_LOGIN = By.id("btnLogin");
    private static final By MNU_PIM= By.id("menu_pim_viewPimModule");
    private static final By MNU_ADD_EMPLOYEE= By.id("menu_pim_addEmployee");
    private static final By TXT_FIRSTNAME= By.id("firstName");
    private static final By TXT_LASTNAME= By.id("lastName");
    private static final By TXT_EMP_ID= By.id("employeeId");
    private static final By PROFILE = By.id("photofile");
    private static final By BTN_SAVE = By.id("btnSave");
    private static final By CHK_LOGIN = By.id("chkLogin");
    private static final By LST_STATUS = By.id("status");
    private static final By TXT_LOGIN_USERNAME = By.id("user_name");
    private static final By TXT_LOGIN_PASSWORD = By.id("user_password");
    private static final By TXT_LOGIN_PASSWORD_CONFIRM = By.id("re_password");
    private WebDriver driver;
    private Faker faker;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    private WebDriver beforeMethod() {
        faker = new Faker();
        driver = new ChromeDriver();

        driver.get("http://hrm.pragmatictestlabs.com");
        login();
        navigateToAddEmployeePage();
        return driver;
    }

    @AfterMethod
    private void afterMethod() {
        //driver.close();
    }

    @Test
    public void testAddNewEmployeeWithMandatoryInfo() {
        driver.findElement(TXT_FIRSTNAME).sendKeys(faker.name().firstName());
        driver.findElement(TXT_LASTNAME).sendKeys(faker.name().lastName());
        driver.findElement(BTN_SAVE).click();
    }


    @Test
    public void testAddNewEmployeeWithLoginDetails() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = String.format("%s.%s", firstName, lastName);

        driver.findElement(TXT_FIRSTNAME).sendKeys(firstName);
        driver.findElement(TXT_LASTNAME).sendKeys(lastName);
        driver.findElement(CHK_LOGIN).click();
        driver.findElement(TXT_LOGIN_USERNAME).sendKeys(username);
        driver.findElement(TXT_LOGIN_PASSWORD).sendKeys("Ptl@#321A");
        driver.findElement(TXT_LOGIN_PASSWORD_CONFIRM).sendKeys("Ptl@#321A");

        driver.findElement(BTN_SAVE).click();
    }



    @Test
    public void testAddNewEmployeeWithLoginDetailsDisabled() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = String.format("%s.%s", firstName, lastName);

        driver.findElement(TXT_FIRSTNAME).sendKeys(firstName);
        driver.findElement(TXT_LASTNAME).sendKeys(lastName);
        driver.findElement(CHK_LOGIN).click();

//        Select status = new Select(driver.findElement(LST_STATUS));
//        status.selectByVisibleText("Disabled");


        Select status = new Select(driver.findElement(LST_STATUS));
        status.selectByVisibleText("Disabled");

        status.selectByIndex(0);
        status.selectByValue("Disabled");


        driver.findElement(TXT_LOGIN_USERNAME).sendKeys(username);
        driver.findElement(TXT_LOGIN_PASSWORD).sendKeys("Ptl@#321A");
        driver.findElement(TXT_LOGIN_PASSWORD_CONFIRM).sendKeys("Ptl@#321A");

        driver.findElement(BTN_SAVE).click();
    }







    @Test
    public void testAddNewEmployeeWithProfileImage() {
        driver.findElement(TXT_FIRSTNAME).sendKeys("Janesh");
        driver.findElement(TXT_LASTNAME).sendKeys("Chandika");
        driver.findElement(PROFILE).sendKeys("/Users/hansi/learning/selenium/dec/test_data/tester.png");

        driver.findElement(BTN_SAVE).click();
    }




    private void login() {
        driver.findElement(TXT_USERNAME).sendKeys("Admin");
        driver.findElement(TXT_PASSWORD).sendKeys("Ptl@#321");
        driver.findElement(BTN_LOGIN).click();
    }

    private void navigateToAddEmployeePage() {
        //Click the PIM menu
        driver.findElement(MNU_PIM).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


        //Click the Add Employee menu
        wait.until(ExpectedConditions.elementToBeClickable(MNU_ADD_EMPLOYEE)).click();

    }


}
