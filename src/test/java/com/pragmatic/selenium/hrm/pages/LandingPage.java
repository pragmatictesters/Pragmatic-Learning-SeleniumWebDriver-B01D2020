package com.pragmatic.selenium.hrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LandingPage {

    private final WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getWelcomeMessage() {
        String welcomeMessage = driver.findElement(By.id("welcome")).getText();
        return welcomeMessage;
    }
}
