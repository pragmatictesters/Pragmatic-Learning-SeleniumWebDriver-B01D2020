package com.pragmatic.examples.selenium.pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage typeUsername(String username) {
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        return this;

    }

    public LoginPage typePassword(String password) {
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        return this;
    }

    public void clickLogin() {
        driver.findElement(By.id("btnLogin")).click();
    }

    public String getError() {
        String errorMessage = driver.findElement(By.id("spanMessage")).getText();
        return errorMessage;

    }
}
