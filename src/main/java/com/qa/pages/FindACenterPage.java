package com.qa.pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindACenterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // **Locators**
    private By findCenterLink = By.xpath("(//a[@href='https://www.brighthorizons.com/child-care-locator'])[4]");
    private By searchBox = By.id("addressInput");
    private By resultsList = By.xpath("//div[@class='centerDetails results']");
    private By centerResults = By.xpath("//div[@class='centerResult infoWindow track_center_select covidOpen']");
    private By firstCenter = By.xpath("(//div[@class='heading-section'])[1]");
    private By centerPopup = By.xpath("//div[@class='centerResult infoWindow track_center_select covidOpen active']");
    private By centerNamePopup = By.xpath("(//h3[contains(@class, 'centerResult__name')])[1]");
    private By addressPopup = By.xpath("(//span[contains(@class, 'centerResult__address')])[1]");
    private By mapTooltipPopup = By.xpath("//div[@class='mapTooltip']");
    private By centerNameMapTooltip = By.xpath("//span[@class='mapTooltip__headline']");
    private By addressMapTooltip = By.xpath("//div[@class='mapTooltip__address']");

    // **Constructor**
    public FindACenterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // **Methods**
    public void clickFindACenter() {
        driver.findElement(findCenterLink).click();
        wait.until(ExpectedConditions.urlContains("/child-care-locator"));
    }

    public boolean verifyPageURL() {
        return driver.getCurrentUrl().contains("/child-care-locator");
    }

    public void searchLocation(String location) throws InterruptedException {
        WebElement searchField = driver.findElement(searchBox);
        searchField.clear();  
        searchField.sendKeys(location);
        Thread.sleep(2000);
        searchField.sendKeys(Keys.RETURN);
        
        wait.until(ExpectedConditions.presenceOfElementLocated(resultsList));
    }

    public int getNumberOfCentersDisplayed() {
        List<WebElement> centers = driver.findElements(centerResults);
        return centers.size();
    }

    public void clickFirstCenter() {
        driver.findElement(firstCenter).click();
    }

    public String getCenterNameInPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(centerPopup));
        return driver.findElement(centerNamePopup).getText();
    }

    public String getAddressInPopup() {
        return driver.findElement(addressPopup).getText();
    }

    public String getCenterNameInMapTooltip() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mapTooltipPopup));
        return driver.findElement(centerNameMapTooltip).getText();
    }

    public String getAddressInMapTooltip() {
        return driver.findElement(addressMapTooltip).getText().trim().replaceAll("\\s+", " ");
    }
}
