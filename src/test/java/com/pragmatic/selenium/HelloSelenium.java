package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HelloSelenium {


    public static void main(String[] args) {

        //Setup the browser driver
        WebDriverManager.chromedriver().setup();

        //Create an instance of Chrome browser browser
        WebDriver driver = new ChromeDriver();


        //Navigate to the login page
        driver.get("http://hrm.pragmatictestlabs.com/");


        //Closing the browser instance
        driver.close();

    }


}
