package com.epam.gloodc.uitesting.services;

import com.epam.gloodc.uitesting.businessobjects.User;
import com.epam.gloodc.uitesting.pages.HomePage;
import com.epam.gloodc.uitesting.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class LoginService {

    public static HomePage login(WebDriver driver, String url, User user){
        return new LoginPage(driver).open(url)
                .setUsernameText(user.getUsername())
                .setPasswordText(user.getPassword())
                .clickLoginButton();

    }

    public static void logout(WebDriver driver, String url, User user){
        LoginPage loginPage = new LoginPage(driver).open(url);
        loginPage.waitPageFinishLoading(15);
        loginPage.clickLogoutLink();

        }
}

