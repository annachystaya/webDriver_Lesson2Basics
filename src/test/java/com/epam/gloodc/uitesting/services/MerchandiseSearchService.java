package com.epam.gloodc.uitesting.services;

import com.epam.gloodc.uitesting.pages.StoreFrontPage;
import com.epam.gloodc.uitesting.pages.StoreSearchResultPage;

public class MerchandiseSearchService {
    public static StoreSearchResultPage searchMerchandiseByTextFromTop(StoreFrontPage storeFrontPage,String searchText){
        return storeFrontPage.clickOnSearchMerchandiseInput()
                .typeTextInSearchField(searchText)
                .clickSearchButton();
    }

    public static StoreSearchResultPage searchMerchandiseByTextFromSearchResult(StoreSearchResultPage storeSearchResultPage, String searchText){
        return storeSearchResultPage
                .typeTextInSearchInputOnSearchResult(searchText)
                .clickSearchButtonOnSearchResult();
    }

    public static StoreSearchResultPage searchMerchandiseByMinPriceOnly(StoreFrontPage storeFrontPage, String minPrice){
        return storeFrontPage
                .typeTextInMinPriceTopInput(minPrice)
                .clickSearchButton();

    }

//    public static StoreSearchResultPage searchMerchandiseByMaxPriceOnly(StoreSearchResultPage storeSearchResultPage, String maxPrice){
//
//    }
//
//    public static StoreSearchResultPage searchMerchandiseByPriceRange(StoreSearchResultPage storeSearchResultPage, String minPrice, String maxPrice){
//
//    }
}
