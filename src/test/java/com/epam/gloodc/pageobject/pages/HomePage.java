package com.epam.gloodc.pageobject.pages;

import com.epam.gloodc.pageobject.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Redeem') and @target='_self']")
    private WebElement redeemTab;

    public HomePage (WebDriver driver) { super(driver);}

    public StoreFrontPage clickRedeemTab() {
        waitUntilClickable(redeemTab);
        redeemTab.click();
        return new StoreFrontPage(getWebDriver());
    }
}
