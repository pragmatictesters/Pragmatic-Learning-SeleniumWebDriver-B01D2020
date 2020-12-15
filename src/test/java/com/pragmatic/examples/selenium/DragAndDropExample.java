package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class DragAndDropExample {


    private static final String BASE_URL = "http://demosite.pragmatictestlabs.com/Droppable.html";
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
    public void testProductDiscount() {
        System.out.println("SeleniumTemplate.testProductDiscount");

        //Get the source element
        WebElement srcElement = driver.findElement(By.id("draggableview"));

        //Get the target element
        WebElement targetElemet = driver.findElement(By.id("droppableview"));


        //Drag and Drop
        Actions actions = new Actions(driver);
        actions.dragAndDrop(srcElement, targetElemet)
                .perform();


    }

}
