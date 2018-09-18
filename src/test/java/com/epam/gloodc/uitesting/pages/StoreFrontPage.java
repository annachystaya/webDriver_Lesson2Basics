package com.epam.gloodc.uitesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreFrontPage extends BasePage {

    @FindBy(xpath = "//input[contains(@class,'search-input')]")
    private WebElement searchMerchandiseInput;

    @FindBy (xpath="//div[@class='search-dropdown-input-holder']/input[@placeholder='Min.']")
    private WebElement searchMinPriceTopInput;

    @FindBy (xpath = "//div[@class='search-dropdown-input-holder']/input[@placeholder='Max.']")
    private WebElement searchMaxPriceTopInput;

    @FindBy(xpath = "//button[contains(@class,'search-button')]")
    private WebElement searchMerchandiseButton;

    @FindBy(xpath = "//span[@class='applicationLink-content' and @title='Recognition']")
    private WebElement recognitionLink;

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

    public StoreFrontPage typeTextInMinPriceTopInput(String minPrice){
        clickOnSearchMerchandiseInput();
        waitElementIsVisible(searchMinPriceTopInput);
        searchMinPriceTopInput.sendKeys(minPrice);
        return this;
    }




}
