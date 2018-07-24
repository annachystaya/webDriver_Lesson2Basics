package com.epam.gloodc.pageobject.scenarios;

import com.epam.gloodc.pageobject.pages.LoginPage;
import com.epam.gloodc.pageobject.pages.StoreSearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreSearchSmokeTest extends BaseTest {
    final String URL = "https://test-web1-12.corp.globoforce.com/microsites/t/home?client=testclient5015";
    String username = "adam_admin";
    String password = "password1";


    @Test
    public void storeTopSearchTest(){
        String searchText = "black";
        String anotherSearchText = "white";

        StoreSearchResultPage storeSearchResultPage = new LoginPage(getWebDriver())
                .open(URL)
                .setUsernameText(username)
                .setPasswordText(password)
                .clickLoginButton()
                .clickRedeemTab()
                .clickOnSearchMerchandiseInput()
                .typeTextInSearchField(searchText)
                .clickSearchButton();

        Assert.assertTrue(storeSearchResultPage.getSearchResultNumber()>0, "Items are not found for query " + searchText);
        Assert.assertEquals(storeSearchResultPage.getTextFromSearchFieldOnSearchResult(), searchText, "Incorrect text in Search Input field on Search Result page");

        storeSearchResultPage = storeSearchResultPage
                .typeTextInSearchInputOnSearchResult(anotherSearchText)
                .clickSearchButtonOnSearchResult();

        Assert.assertTrue(storeSearchResultPage.getSearchResultNumber()>0, "Items are not found for query " + anotherSearchText);
        Assert.assertEquals(storeSearchResultPage.getTextFromSearchFieldOnSearchResult(), anotherSearchText, "Incorrect text in Search Input field on Search Result page");


    }

//    @Test
//    public void storeSearchFromSearchResultTest(){
//
//
//        StoreSearchResultPage storeSearchResultPage = new LoginPage(getWebDriver())
//                .open(URL)
//                .setUsernameText(username)
//                .setPasswordText(password)
//                .clickLoginButton()
//                .clickRedeemTab()
//                .clickOnSearchMerchandiseInput()
//                .typeTextInSearchField(anotherSearchText)
//                .clickSearchButton()
//                .typeTextInSearchInputOnSearchResult(anotherSearchText)
//                .clickSearchButtonOnSearchResult();
//
//        Assert.assertTrue(storeSearchResultPage.getSearchResultNumber()>0, "Items are not found for query " + anotherSearchText);
//        Assert.assertEquals(storeSearchResultPage.getTextFromSearchFieldOnSearchResult(), anotherSearchText, "Incorrect text in Search Input field on Search Result page");
//    }
}
