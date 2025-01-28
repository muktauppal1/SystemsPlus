package com.qa.pages;


	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class SearchPage {

	    WebDriver driver;
	    WebDriverWait wait;

	    @BeforeClass
	    public void setup() {
	        // Set up WebDriver
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();

	        // Maximize browser window and set implicit wait
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        // Navigate to Bright Horizons homepage
	        driver.get("https://www.brighthorizons.com/");

	        // Accept cookies
	        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

	        // Initialize WebDriverWait
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    @Test
	    public void testSearchFunctionality() {
	        try {
	            // Step 1: Locate and click the search icon
	            WebElement searchIcon = driver.findElement(By.xpath("(//span[contains(@class, 'icon-search bhc-icon-search-rounded')])[4]"));
	            searchIcon.click();
	            System.out.println("Search icon clicked.");

	            // Step 2: Verify search field is displayed
	            WebElement searchField = driver.findElement(By.xpath("(//input[@id='search-field'])[2]"));
	            Assert.assertTrue(searchField.isDisplayed(), "Search field is not displayed.");
	            System.out.println("Search field is displayed.");

	            // Step 3: Wait for search input field to appear
	            WebElement searchInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='search-field'])[2]")));

	            // Step 4: Input a search term
	            String searchTerm = "Employee Education in 2018: Strategies to Watch";
	            searchInputField.sendKeys(searchTerm);
	            System.out.println("Search term entered: " + searchTerm);

	            // Step 5: Submit the search
	            searchInputField.submit();

	            // Step 6: Verify search results are displayed
	            WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3[contains(@class, 'title')])[2]")));
	            Assert.assertTrue(result.isDisplayed(), "Search results are not displayed.");
	            System.out.println("Search functionality is working correctly!");

	        } catch (Exception e) {
	            Assert.fail("An error occurred during the test: " + e.getMessage(), e);
	        }
	    }

	    @AfterClass
	    public void teardown() {
	        // Close the browser
	        if (driver != null) {
	            driver.quit();
	            System.out.println("Browser closed.");
	        }
	    }
	}
