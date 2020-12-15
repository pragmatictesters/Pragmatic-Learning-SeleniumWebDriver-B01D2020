package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
        //driver.close();
    }


    @Test
    public void testDragAndDrop() {
        System.out.println("SeleniumTemplate.testProductDiscount");

        //Get the source element
        WebElement srcElement = driver.findElement(By.id("draggableview"));

        //Get the target element
        WebElement targetElemet = driver.findElement(By.id("droppableview"));


        //Drag and Drop
        Actions actions = new Actions(driver);
        actions.dragAndDrop(srcElement, targetElemet)
                .perform();

        //Verifying the inner text within the target element after dropping the element
        Assert.assertEquals(targetElemet.findElement(By.tagName("p")).getText(), "Dropped!");


    }


    @Test
    public void testDragAndDropToCorner() {
        System.out.println("SeleniumTemplate.testProductDiscount");

        //Get the source element
        WebElement srcElement = driver.findElement(By.id("draggableview"));

        //Get the target element
        WebElement targetElement = driver.findElement(By.id("droppableview"));

        int offsetX = targetElement.getLocation().x - srcElement.getLocation().x;
        int offsetY = targetElement.getLocation().y - srcElement.getLocation().y;


        //Drag and Drop to the left corner of the target element
        Actions actions = new Actions(driver);

        actions.dragAndDropBy(srcElement, offsetX, offsetY)
                .perform();

        //Verifying the inner text within the target element after dropping the element
        Assert.assertEquals(targetElement.findElement(By.tagName("p")).getText(), "Dropped!");


    }


}
