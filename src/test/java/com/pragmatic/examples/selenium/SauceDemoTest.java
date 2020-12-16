package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
public class SauceDemoTest {


    private static final String BASE_URL = "https://www.saucedemo.com/";
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
    public void testAddingProducts() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Verify the URL in the address bar
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");


        final String productName = "Sauce Labs Bolt T-Shirt";
        String xpathProductLink = "//div[text()='" + productName + "']/parent::a";
        String xpathItemDescription = xpathProductLink + "/following-sibling::div[@class='inventory_item_desc']";
        String xpathPrice = xpathProductLink + "/following::div[@class='inventory_item_price'][1]";

        String productDescription = driver.findElement(By.xpath(xpathItemDescription)).getText();
        String productPrice = driver.findElement(By.xpath(xpathPrice)).getText();

        System.out.println("productPrice = " + productPrice);
        System.out.println("productDescription = " + productDescription);
        System.out.println("productName = " + productName);

        //Select a product (clicking a link or image)
        driver.findElement(By.xpath(xpathProductLink)).click();


        //Get the product name
        String productNameInDetailPage = driver.findElement(By.xpath("//div[@class='inventory_details_name']")).getText();
        String productDecInDetailPage = driver.findElement(By.xpath("//div[@class='inventory_details_desc']")).getText();
        String productPriceInDetailPage = driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText();

        Assert.assertEquals(productNameInDetailPage, productName);
        Assert.assertEquals(productDecInDetailPage, productDescription);
        Assert.assertEquals(productPriceInDetailPage, productPrice);

        //Click add to cart button
        driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']")).click();

        //Clicking the cart icon
        driver.findElement(By.xpath("//a[@href='./cart.html']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");


        //Verify the product information in the cart

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText(), productName);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='inventory_item_desc']")).getText(), productDescription);
        //Assert.assertEquals(driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText(), productPrice); This fails as currency code is not displayed
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText(), productPrice.replace("$", ""));


        //Click the remove button
        driver.findElement(By.xpath("//button[text()='REMOVE']")).click();

        //Chek if the product it not available in the cart
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(),'" + productName + "')]"));
        Assert.assertTrue(elements.size() == 0);

        Assert.assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
        });


        elements = driver.findElements(By.xpath("//*[contains(text(),'" + productDescription + "')]"));
        Assert.assertTrue(elements.size() == 0);

        elements = driver.findElements(By.xpath("//*[contains(text(),'" + productPrice.replace("$", "") + "')]"));
        Assert.assertTrue(elements.size() == 0);


    }

}
