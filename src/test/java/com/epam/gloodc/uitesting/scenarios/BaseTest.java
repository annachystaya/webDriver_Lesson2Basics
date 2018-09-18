package com.epam.gloodc.uitesting.scenarios;

import com.epam.gloodc.uitesting.utilities.ScreenshotUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static WebDriver driver;
    protected Logger logger = Logger.getLogger(getClass());

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.gecko.driver",".\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }

    @AfterMethod
    public void takeScreenshots(ITestResult result){
        ScreenshotUtils.captureScreenshot(driver, result);
    }

    protected WebDriver getWebDriver() {
        return this.driver;
    }
}
