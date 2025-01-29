package com.qa.pages;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	public class HomePage {
	    private WebDriver driver;

	    // Locators
	    private By acceptCookies = By.id("onetrust-accept-btn-handler");
	    private By findCenterLink = By.xpath("(//a[@href='https://www.brighthorizons.com/child-care-locator'])[4]");

	    // Constructor
	    public HomePage(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Methods
	    public void acceptCookies() {
	        driver.findElement(acceptCookies).click();
	    }

	    public void clickFindACenter() {
	        driver.findElement(findCenterLink).click();
	    }
	}



