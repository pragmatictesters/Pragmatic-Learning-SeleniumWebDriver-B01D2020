package com.pragmatic.examples.selenium.synchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class CustomExpectedConditions {

    private static final String BASE_URL = "https://eviltester.github.io/synchole/collapseable.html";
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
    public void navigatingToAboutWithCustomExpecyedCondition() {
        final WebElement section = webDriver.findElement(By.cssSelector("section.condense"));
        section.click();

        final By linkToClick = By.cssSelector("a#aboutlink");

        By expandingElement = By.cssSelector("section.condense");

        //Custom expected condition
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(new ElementHasExpandedFully(expandingElement));

        final WebElement sectionLink = webDriver.findElement(linkToClick);
        sectionLink.click();

        Assert.assertTrue(webDriver.getCurrentUrl().contains("about.html"));


    }

    @Test
    public void navigatingToAboutWithCustomExpecyedCondition2() {
        final WebElement section = webDriver.findElement(By.cssSelector("section.condense"));
        section.click();

        final By linkToClick = By.cssSelector("a#aboutlink");

        By expandingElement = By.cssSelector("section.condense");

        //Custom expected condition
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(MyExpectedConditions.elementHasExpandedFully(expandingElement));

        final WebElement sectionLink = webDriver.findElement(linkToClick);
        sectionLink.click();

        Assert.assertTrue(webDriver.getCurrentUrl().contains("about.html"));


    }


    @Test
    public void navigatingToAboutWithCustomExpecyedCondition3() {
        final WebElement section = webDriver.findElement(By.cssSelector("section.condense"));
        section.click();

        final By linkToClick = By.cssSelector("a#aboutlink");

        By expandingElement = By.cssSelector("section.condense");

        //Custom expected condition
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(25))
                .until(MyExpectedConditions.elementHasExpandedFully(expandingElement));

        final WebElement sectionLink = webDriver.findElement(linkToClick);
        sectionLink.click();

        Assert.assertTrue(webDriver.getCurrentUrl().contains("about.html"));


    }

    private static class MyExpectedConditions {

        public static ExpectedCondition<Boolean> elementHasExpandedFully(By expandingElement) {
            return new ExpectedCondition<Boolean>() {
                private int lastHeight;

                @Override
                public @Nullable Boolean apply(@Nullable WebDriver webDriver) {
                    int newHeight = webDriver.findElement(expandingElement).getSize().height;
                    System.out.println(String.format(" %d > %d", newHeight, lastHeight));
                    if (newHeight > lastHeight) {
                        lastHeight = newHeight;
                        return false;
                    } else {
                        return true;
                    }
                }
            };

        }
    }

    private class ElementHasExpandedFully implements ExpectedCondition<Boolean> {
        private final By locator;
        private int lastHeight;

        public ElementHasExpandedFully(By expandingElement) {
            locator = expandingElement;
            //lastHeight =  webDriver.findElement(locator).getSize().height;

        }

        @Override
        public @Nullable Boolean apply(@Nullable WebDriver webDriver) {
            int newHeight = webDriver.findElement(locator).getSize().height;
            System.out.println(String.format(" %d > %d", newHeight, lastHeight));
            if (newHeight > lastHeight) {
                lastHeight = newHeight;
                return false;
            } else {
                return true;
            }
        }

        @Override
        public boolean equals(@Nullable Object o) {
            return false;
        }
    }
}
