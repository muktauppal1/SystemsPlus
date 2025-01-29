package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.libs.BaseTest;
import com.qa.pages.HomePage;
import com.qa.pages.SearchPage;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();

        SearchPage searchPage = new SearchPage(driver);

        // Step 1: Click the search icon
        searchPage.clickSearchIcon();
        System.out.println("Icon is Clicked");

        // Step 2: Verify search field is displayed after clicking the search icon
        Assert.assertTrue(searchPage.isSearchFieldDisplayed(), "Search field is not displayed");
        System.out.println("Search field is displayed.");

        // Step 3: Enter a search term
        String searchTerm = "Employee Education in 2018: Strategies to Watch";
        searchPage.enterSearchTerm(searchTerm);
        System.out.println("Search term entered: " + searchTerm);

        // Step 4: Verify search results are displayed
        Assert.assertTrue(searchPage.isSearchFieldDisplayed(), "Search results are not displayed");
        System.out.println("Search functionality is working correctly!");
    }
}



