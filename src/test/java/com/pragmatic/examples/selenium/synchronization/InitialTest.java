package com.pragmatic.examples.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class InitialTest {

    private static final String BASE_URL = "https://eviltester.github.io/synchole/";
    private static WebDriver webDriver;


    @BeforeMethod
    public static void createDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(BASE_URL);

    }


    @AfterMethod
    public static void closeDriver() {
        webDriver.close();
    }

    @Test
    public void anInitialTest() {
        Assert.assertEquals("Index Page", webDriver.getTitle());
    }


}
