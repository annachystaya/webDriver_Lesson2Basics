package com.epam.gloodc.uitesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreSearchResultPage extends BasePage{

    @FindBy(xpath = "//span[@class='searchresults-size-value']")
    private WebElement searchResultNumber;

    @FindBy(xpath = "//input[contains(@class, 'ec-search-form-input-query')]")
    private WebElement searchTextOnSearchResultInput;

    @FindBy(xpath = "//button[contains(@class,'ec-search-form-btn-submit')]")
    private WebElement searchButton;

    @FindBy (xpath = "//span[@class='applicationLink-content' and @title='Globostore']")
    private WebElement globostoreAppLink;

    public StoreSearchResultPage (WebDriver driver) { super(driver);}

    public Integer getSearchResultNumber() {
        waitElementIsVisible(searchResultNumber);
        return Integer.valueOf(searchResultNumber.getText());
            }

    public StoreSearchResultPage typeTextInSearchInputOnSearchResult(String searchText){
        waitElementIsVisible(searchTextOnSearchResultInput);
        searchTextOnSearchResultInput.clear();
        searchTextOnSearchResultInput.sendKeys(searchText);
        return this;
    }

    public String getTextFromSearchFieldOnSearchResult(){
        waitElementIsVisible(searchResultNumber);
        return (searchTextOnSearchResultInput.getAttribute("value"));
    }

    public StoreSearchResultPage clickSearchButtonOnSearchResult(){
        waitUntilClickable(searchButton);
        searchButton.click();
        return this;
    }

    public StoreFrontPage clickGlobostoreAppLink(){
        waitUntilClickable(globostoreAppLink);
        globostoreAppLink.click();
        return new StoreFrontPage(getWebDriver());
    }

}
