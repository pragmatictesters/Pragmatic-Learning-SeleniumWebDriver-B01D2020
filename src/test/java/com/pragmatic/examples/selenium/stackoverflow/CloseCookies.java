package com.pragmatic.examples.selenium.stackoverflow;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class CloseCookies {


    private static final String BASE_URL = "https://pasja-informatyki.pl/programowanie-webowe/test/przeglad-html/";
    @FindBy(css = "#nav-link-accountList  span.nav-line-1")
    WebElement element;
    @FindBy(css = "#nav-link-accountList  span.nav-line-1")
    List<WebElement> elements;
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
//        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }


    @Test
    public void test() {
        driver.findElement(By.id("hcks")).click();


    }

}
