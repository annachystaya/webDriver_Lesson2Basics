package com.epam.gloodc.pageobject.pages;

import com.epam.gloodc.pageobject.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.net.URL;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "signIn-button")
    private WebElement loginButton;


    public LoginPage open(String url) {
        getWebDriver().get(url);
        return new LoginPage(getWebDriver());

    }

    public LoginPage(WebDriver driver) {super(driver);}

    public LoginPage setUsernameText(String username) {
        waitElementIsVisible(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage setPasswordText(String password) {
        waitElementIsVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public HomePage clickLoginButton() {
        waitUntilClickable(loginButton);
        loginButton.click();
        return new HomePage(getWebDriver());

    }








}
