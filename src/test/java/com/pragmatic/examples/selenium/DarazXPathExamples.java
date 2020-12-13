package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.NumberFormat;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class DarazXPathExamples {


    private static final String BASE_URL = "https://www.daraz.lk/";
    private ChromeDriver driver;


    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();
    }


    @Test
    public void testProductDiscount() {
        String productDescription = "Power";
        String xpathProduct = String.format("//div[contains(text(),'%s')]", productDescription);
        String xpathProductPrice = String.format("%s/following-sibling::div[@class='fs-card-price']/*[@class='price']", xpathProduct);
        String xpathOriginalPrice = String.format("%s/following-sibling::div[@class='fs-card-origin-price']/span/span[@class='price']", xpathProduct);
        String xpathDiscount = String.format("%s/following-sibling::div[@class='fs-card-origin-price']/span[@class='fs-discount']", xpathProduct);


        String price = driver.findElement(By.xpath(xpathProductPrice)).getText();
        String originalPrice = driver.findElement(By.xpath(xpathOriginalPrice)).getText();
        String discount = driver.findElement(By.xpath(xpathDiscount)).getText();

        displayValues(price, originalPrice, discount);

        Assert.assertEquals(discount, calculateDiscount(price, originalPrice));
    }


    private String calculateDiscount(String price, String originalPrice) {
        float priceValue = Float.parseFloat(price.replace(",", "").trim());
        float originalValue = Float.parseFloat(originalPrice.replace(",", "").trim());
        float discount = (priceValue - originalValue) / originalValue;
        NumberFormat percentage = NumberFormat.getPercentInstance();
        percentage.setMaximumFractionDigits(0);

        System.out.println("discount = " + percentage.format(discount));
        return percentage.format(discount);
    }


    private void displayValues(String price, String originalPrice, String discount) {
        System.out.println("discount = " + discount);
        System.out.println("originalPrice = " + originalPrice);
        System.out.println("price = " + price);
    }
}
