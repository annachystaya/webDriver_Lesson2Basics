package com.epam.gloodc.uitesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "signIn-button")
    private WebElement loginButton;

    @FindBy (xpath = "//a[contains(@href,'logout')]")
    private WebElement logoutLink;


    public LoginPage open(String url) {
        getWebDriver().get(url);
        return this;

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

    public void clickLogoutLink(){
        waitUntilClickable(logoutLink);
        logoutLink.click();
    }

}
