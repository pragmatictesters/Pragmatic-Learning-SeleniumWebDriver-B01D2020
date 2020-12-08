package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HelloSelenium {


    public static void main(String[] args) {

     //Setup the browser driver
        WebDriverManager.chromedriver().setup();

        //Create an instance of the browser
        WebDriver driver = new ChromeDriver();


        //Navigate to the login page
        driver.get("http://hrm.pragmatictestlabs.com/");

        //Type the username 


        //Type the password

        //Click the login button




        driver.close();







    }




}
