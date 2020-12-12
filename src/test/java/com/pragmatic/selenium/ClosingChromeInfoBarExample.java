package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ClosingChromeInfoBarExample {


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        //Create an instance of Chrome browser browser
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches",
                Collections.singletonList("enable-automation"));

        WebDriver driver = new ChromeDriver(options);


        //Navigate to the login page
        driver.get("http://hrm.pragmatictestlabs.com/");




    }


}
