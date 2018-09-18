package com.epam.gloodc.uitesting.scenarios;

import com.epam.gloodc.uitesting.businessobjects.User;
import com.epam.gloodc.uitesting.pages.HomePage;
import com.epam.gloodc.uitesting.pages.LoginPage;
import com.epam.gloodc.uitesting.pages.StoreFrontPage;
import com.epam.gloodc.uitesting.pages.StoreSearchResultPage;
import com.epam.gloodc.uitesting.services.LoginService;
import com.epam.gloodc.uitesting.services.MerchandiseSearchService;
import com.epam.gloodc.uitesting.utilities.CustomListener;
import com.epam.gloodc.uitesting.utilities.ScreenshotUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

@Listeners(CustomListener.class)
public class StoreSearchSmokeTest extends BaseTest {
    final String URL = "https://test-web1-12.corp.globoforce.com/microsites/t/home?client=testclient5015";
    String username = "adam_admin";
    String password = "adam_admin1";


    @Test
    public void storeSearchByTextTest(){
        String searchText = "black";
        String anotherSearchText = "white";

        SoftAssert softAssert = new SoftAssert();

        logger.info("Login as " + username);
        User user = new User(username, password);
        logger.info("Go to Home page");
        HomePage homePage = LoginService.login(getWebDriver(), URL, user);

        logger.info("Go to the Store");
        StoreFrontPage storeFrontPage = homePage.clickRedeemTab();
        logger.info("Search merchandise items by text: " + searchText);
        StoreSearchResultPage storeSearchResultPage = MerchandiseSearchService.searchMerchandiseByTextFromTop(storeFrontPage, searchText);

        softAssert.assertTrue(storeSearchResultPage.getSearchResultNumber()>0, "Items are not found for query " + searchText);
        softAssert.assertEquals(storeSearchResultPage.getTextFromSearchFieldOnSearchResult(), searchText, "Incorrect text in Search Input field on Search Result page");

        logger.info("Search merchandise items by text: " + anotherSearchText);
        storeSearchResultPage = MerchandiseSearchService.searchMerchandiseByTextFromSearchResult(storeSearchResultPage, anotherSearchText);

        softAssert.assertTrue(storeSearchResultPage.getSearchResultNumber()>0, "Items are not found for query " + anotherSearchText);
        softAssert.assertEquals(storeSearchResultPage.getTextFromSearchFieldOnSearchResult(), anotherSearchText, "Incorrect text in Search Input field on Search Result page");
        softAssert.assertAll();

        LoginService.logout(getWebDriver(), URL, user);
    }



//    @Test
//    public void storeSearchByPriceTest(){
//        String minPrice = "500";
//        String maxPrice = "1000";
//
//        StoreFrontPage storeFrontPage = new StoreFrontPage(getWebDriver());
//
//        StoreSearchResultPage storeSearchResultPage = MerchandiseSearchService.searchMerchandiseByMinPriceOnly(storeFrontPage, minPrice);
//
//        Assert.assertTrue(storeSearchResultPage.getSearchResultNumber()>0, "Items are not found for minPrice " + minPrice);
//       // Assert.assertEquals(storeSearchResultPage.getTextFromSearchFieldOnSearchResult(), anotherSearchText, "Incorrect text in Search Input field on Search Result page");
//    }
}
