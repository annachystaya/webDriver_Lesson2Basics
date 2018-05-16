package com.epam.gloodc.firsttest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FirstTest {


    private WebDriver driver;
    private JavascriptExecutor jsExecutor;
    private static final int implicitTimeout = 15;
    private String searchQuery = "Black";
    private String username="adam_admin";
    private String password="adam_admin1";

    @Parameters({"browser"})
    @Test
    public void startDriver(@Optional(value="chrome") String browser){
        if (browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver",".\\src\\test\\resources\\geckodriver.exe");
            driver= new FirefoxDriver();
        } else if (browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",".\\src\\test\\resources\\chromedriver.exe");
            driver= new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test(description = "Login Test",dependsOnMethods = {"startDriver"})
    public void Login(){
        System.out.println("Login Test is started");
        driver.navigate().to("https://test-web1-12.corp.globoforce.com/microsites/t/home?client=testclient5011");
        //
        WebElement usernameField = waitElementPresent(By.name("username"));
        WebElement passwordField = waitElementPresent(By.name("password"));
        WebElement loginButton = waitElementPresent(By.id("signIn-button"));
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
        Assert.assertEquals(driver.getTitle(), "Welcome", "Home Page Title is incorrect");
        System.out.println("Login Test is finished");
    }

    @Test(description = "Go To EC3 Store by clicking on Redeem Tab",dependsOnMethods = {"Login"})
    public void verifyEc3StoreOpened(){
        System.out.println("Open EC3 Store Test is started");
        WebElement redeemTab = waitElementPresent(By.xpath("//a[contains(text(),'Redeem') and @target='_self']"));
        waitElementAndClick(redeemTab).click();
        Assert.assertEquals(driver.getTitle(), "The Store", "Incorrect Store title");
        System.out.println("Open EC3 Store Test is finished");
    }

    @Test(description = "Verify EC3 Search by text",dependsOnMethods = {"verifyEc3StoreOpened"})
    public void verifyEc3SearchByText(){
        System.out.println("Search in EC3 Store Test is started");
        WebElement searchMerchandiseInputField = waitElementPresent(By.xpath("//input[contains(@class,'search-input')]"));
        searchMerchandiseInputField.click();
        searchMerchandiseInputField.sendKeys(searchQuery);
        WebElement searchMerchandiseButton = driver.findElement(By.xpath("//button[contains(@class,'search-button')]"));
        searchMerchandiseButton.click();
        waitPageFinishLoading(implicitTimeout);
        WebElement searchResultNumber = waitElementPresent(By.xpath("//span[@class='searchresults-size-value']"));
        String numbers = searchResultNumber.getText();
        System.out.println("Items are found: " + numbers + " by query: " + searchQuery);
        Assert.assertTrue(Integer.valueOf(numbers) > 0, "Items are not found for query " + searchQuery);

        WebElement searchInputOnSearchResultField = waitElementPresent(By.xpath("//input[contains(@class, 'ec-search-form-input-query')]"));
        String resultQuery = searchInputOnSearchResultField.getAttribute("value");
        System.out.println("Text in the search input field on Search Result Page is: " + searchQuery);
        Assert.assertEquals(resultQuery, searchQuery, "Incorrect text in Search Input field on Search Result page");

        new Select(driver.findElement(By.xpath("//select[@class='searchresults-sortingSelect']"))).selectByValue("2");
        waitPageFinishLoading(implicitTimeout);
        //Assert
        WebElement nextPageLink = driver.findElement(By.xpath("//a[contains(@href, 'resourceId=2')]"));
        waitElementAndClick(nextPageLink).click();
        waitPageFinishLoading(implicitTimeout);
        System.out.println("Search in EC3 Store Test is finished");

    }

    @AfterClass
    public void closeDriver(){
        if (driver != null)
            driver.quit();
    }

    private WebElement waitElementPresent(final By by){
        return new WebDriverWait(driver, 10)
                .pollingEvery(Duration.ofSeconds(1))
                .withMessage("Failed to wait Element: " + by)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitElementAndClick(WebElement webElement){
        return new WebDriverWait(driver, 5)
                .ignoring(StaleElementReferenceException.class)
                .pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    private void waitPageFinishLoading(int timeoutInSeconds) {
        ExpectedCondition<Boolean> pageLoadFinishedCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript(
                        "return document.readyState").equals("complete");
            }
        };
        new WebDriverWait(driver, timeoutInSeconds).until(pageLoadFinishedCondition);
    }

}
