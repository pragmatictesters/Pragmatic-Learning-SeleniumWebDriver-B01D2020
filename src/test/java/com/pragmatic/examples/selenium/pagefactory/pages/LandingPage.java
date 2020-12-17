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
public class LandingPage {

    private final WebDriver driver;

    @FindBy(id = "welcome")
    WebElement lnkWelcome;


    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getWelcomeMessage() {
        String welcomeMessage = lnkWelcome.getText();
        return welcomeMessage;
    }
}
