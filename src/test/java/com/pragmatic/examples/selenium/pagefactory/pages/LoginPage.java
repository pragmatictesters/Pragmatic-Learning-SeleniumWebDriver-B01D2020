package com.pragmatic.examples.selenium.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginPage {

    private final WebDriver driver;

    //Define the element
    @FindBy(id = "txtUsername")
    WebElement txtUsername;

    @FindBy(id = "txtPassword")
    WebElement txtPassword;

    @FindBy(id = "btnLogin")
    WebElement btnLogin;

    @FindBy(id = "spanMessage")
    WebElement spanMessage;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage typeUsername(String username) {
        txtUsername.sendKeys(username);
        return this;

    }

    public LoginPage typePassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public String getError() {
        String errorMessage = spanMessage.getText();
        return errorMessage;

    }

    public LoginPage clearUsername() {
        txtUsername.clear();
        return this;
    }

    public LoginPage clearPassword() {
        txtPassword.clear();
        return this;
    }
}
