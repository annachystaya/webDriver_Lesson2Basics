package com.epam.gloodc.pageobject.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected final WebDriver webdriver;

    protected final int PAGE_LOAD_TIMEOUT = 300;
    protected final int IMPLICITLY_WAIT_TIMEOUT = 300;
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;

    public BasePage(WebDriver driver) {
        webdriver = driver;
        webdriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        webdriver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        PageFactory.initElements(webdriver, this);
        wait = new WebDriverWait(webdriver, 20);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public WebDriver getWebDriver() {
        return webdriver;
    }

    public boolean isElementPresent(By by) {
        int i = webdriver.findElements(by).size();
        return i != 0;
    }

    public boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }

    public void waitUntilClickable(WebElement element) {
        wait
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class)
                .pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitPageFinishLoading(int timeoutInSeconds) {
        ExpectedCondition<Boolean> pageLoadFinishedCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript(
                        "return document.readyState").equals("complete");
            }
        };
    }

    public void waitElementIsVisible(WebElement element) {
        wait
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

}
