package com.qa.pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindACenterPage {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().create();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to the BH home page and maximize window
        driver.get("https://www.brighthorizons.com/");
        driver.manage().window().maximize();

        // Accept cookies if the prompt is displayed
        driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();
    }

    @Test
    public void findCenterTest() throws InterruptedException {
        // Step 2: Click on "Find a Center" option
        WebElement findCenterLink = driver.findElement(By.xpath("(//a[@href=\"https://www.brighthorizons.com/child-care-locator\"])[4]"));
        findCenterLink.click();

        // Step 3: Verify that the newly opened page contains '/child-care-locator' in the URL
        wait.until(ExpectedConditions.urlContains("/child-care-locator"));
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/child-care-locator")) {
            System.out.println("URL contains '/child-care-locator': Passed");
        } else {
            System.out.println("URL does not contain '/child-care-locator': Failed");
        }

        // Step 4: Type 'New York' into the search box and press Enter
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='addressInput']"));
        searchBox.sendKeys("New York");
        Thread.sleep(2000); // Wait for suggestions (replace with explicit wait if needed)
        searchBox.sendKeys(Keys.RETURN); // Press Enter

        // Step 5: Verify if the number of found centers matches the displayed list count
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='centerDetails results']")));
        WebElement resultsList = driver.findElement(By.xpath("//div[@class='centerDetails results']"));

        int numberOfCentersDisplayed = resultsList.findElements(By.xpath("//div[@class='centerResult infoWindow track_center_select covidOpen']")).size();
        int expectedNumberOfCenters = 20; // Replace this with dynamic count if available
        if (numberOfCentersDisplayed == expectedNumberOfCenters) {
            System.out.println("Number of centers displayed matches the expected number: Passed");
        } else {
            System.out.println("Number of centers displayed does not match the expected number: Failed");
        }

        // Step 6: Click on the first center in the list
        WebElement firstCenter = resultsList.findElements(By.xpath("(//div[@class='heading-section'])[1]")).get(0);
        firstCenter.click();

        // Step 7: Verify that the center name and address in the popup match those in the list
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='centerResult infoWindow track_center_select covidOpen active']")));
        WebElement popup = driver.findElement(By.xpath("//div[@class='centerResult infoWindow track_center_select covidOpen active']"));
        String centerNameInPopup = popup.findElement(By.xpath("(//h3[contains(@class, 'centerResult__name')])[1]")).getText();
        String addressInPopup = popup.findElement(By.xpath("(//span[contains(@class, 'centerResult__address')])[1]")).getText();

        System.out.println("Listing name text: " + centerNameInPopup);
        System.out.println("Listing adress text: " + addressInPopup);

        // Popup text
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mapTooltip']")));
        WebElement mapTooltipPopup = driver.findElement(By.xpath("//div[@class='mapTooltip']"));
        String centerNameInMapTooltip = mapTooltipPopup.findElement(By.xpath("//span[@class=\"mapTooltip__headline\"]")).getText();
        String addressInMapTooltip = mapTooltipPopup.findElement(By.xpath("//div[@class=\"mapTooltip__address\"]")).getText().trim().replaceAll("\\s+", " ");

        System.out.println("Center name in popup: " + centerNameInMapTooltip);
        System.out.println("Center name in popup: " + addressInMapTooltip);

        if (centerNameInPopup.equals(centerNameInMapTooltip) && addressInPopup.equals(addressInMapTooltip)) {
            System.out.println("Center name and address in popup match the list: Passed");
        } else {
            System.out.println("Center name and/or address in popup do not match the list: Failed");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

	


