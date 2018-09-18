package com.epam.gloodc.uitesting.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void captureScreenshot (WebDriver driver, ITestResult result) {
        if (!result.isSuccess()) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("target/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
