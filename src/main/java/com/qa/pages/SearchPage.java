package com.qa.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // **Locators**
    private By searchIcon = By.xpath("(//span[contains(@class, 'icon-search bhc-icon-search-rounded')])[4]"); // Adjust XPath if needed
    private By searchField = By.xpath("(//input[@id='search-field'])[2]"); // Adjust XPath if needed
    private By searchResults = By.xpath("(//h3[contains(@class, 'title')])[2]"); // Adjust XPath if needed

    // **Constructor**
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // **Click on Search Icon**
    public void clickSearchIcon() {
        WebElement icon = driver.findElement(searchIcon);
        icon.click();
        // Wait for the search field to be visible after clicking the icon
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
    }

    // **Verify Search Field is Displayed**
    public boolean isSearchFieldDisplayed() {
        try {
            // Wait until the search field is visible
            WebElement searchFieldElement = driver.findElement(searchField);
            return searchFieldElement.isDisplayed();
        } catch (Exception e) {
            return false; // If an exception occurs, search field is not displayed
        }
    }

    // **Enter Search Term**
    public void enterSearchTerm(String searchTerm) {
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
    }
}

