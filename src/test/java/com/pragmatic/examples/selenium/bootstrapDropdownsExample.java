package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class bootstrapDropdownsExample {

    private static final String BASE_URL = "https://www.srilankan.com/en_uk/lk";
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
        //driver.close();
    }


    @Test
    public void testDisplayDropDownItemsAndClickItem() {

        //Typing text in departure input element
        driver.findElement(By.id("suggest1")).sendKeys("Col");

        //Get the list of elements in the dropdown
        List<WebElement> webElements = driver.findElements(By.xpath("//div/ul/li[starts-with(@class,'ac_')]"));

        //Iterate through the list and select the required item with partial text
        for (WebElement element : webElements) {
            if (element.getText().contains("(CMB)")) {
                System.out.println("Displayed element  " + element.getText());
                element.click();
            }
        }


    }


}
