package com.epam.gloodc.pageobject.pages;

import com.epam.gloodc.pageobject.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreFrontPage extends BasePage {

    @FindBy(xpath = "//input[contains(@class,'search-input')]")
    private WebElement searchMerchandiseInput;

    @FindBy(xpath="//button[contains(@class,'search-button')]")
    private WebElement searchMerchandiseButton;

    public StoreFrontPage (WebDriver driver) {super (driver);}

    public StoreFrontPage clickOnSearchMerchandiseInput(){
        waitUntilClickable(searchMerchandiseInput);
        searchMerchandiseInput.click();
        return this;
    }

    public StoreFrontPage typeTextInSearchField(String searchText){
        waitElementIsVisible(searchMerchandiseInput);
        searchMerchandiseInput.sendKeys(searchText);
        return this;
    }

    public StoreSearchResultPage clickSearchButton(){
        waitUntilClickable(searchMerchandiseButton);
        searchMerchandiseButton.click();
        return new StoreSearchResultPage(getWebDriver());
    }

}
