package com.qa.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.libs.BaseTest;
import com.qa.pages.FindACenterPage;
import com.qa.pages.HomePage;

public class FindACenterTest extends BaseTest {

    @Test
    public void verifyFindCenterFunctionality() throws InterruptedException {
        HomePage home = new HomePage(driver);
        FindACenterPage findCenter = new FindACenterPage(driver);

        // Step 1: Accept cookies and navigate to Find a Center
        home.acceptCookies();
        findCenter.clickFindACenter();

        // Step 2: Verify that the URL contains '/child-care-locator'
        boolean isURLValid = findCenter.verifyPageURL();
        Assert.assertTrue(isURLValid, "URL does not contain '/child-care-locator'");
        if (isURLValid) {
            System.out.println("URL contains '/child-care-locator': Passed");
        } else {
            System.out.println("URL does not contain '/child-care-locator': Failed");
        }

        // Step 3: Search for a location
        findCenter.searchLocation("New York");
        

        // Step 4: Verify the number of centers displayed
        int actualCenters = findCenter.getNumberOfCentersDisplayed();
        int expectedCenters = 20; // Replace with actual expected value if dynamically available
        Assert.assertEquals(actualCenters, expectedCenters, "Mismatch in number of displayed centers.");
        if (actualCenters == expectedCenters) {
            System.out.println("Number of centers displayed matches the expected number: Passed");
        } else {
            System.out.println("Number of centers displayed does not match the expected number: Failed");
        }

        // Step 5: Click the first center and verify the popup details
        findCenter.clickFirstCenter();
        String centerNamePopup = findCenter.getCenterNameInPopup();
        String addressPopup = findCenter.getAddressInPopup();
        
        // Step 6: Output center name and address in the popup
        System.out.println("Listing name text: " + centerNamePopup);
        System.out.println("Listing address text: " + addressPopup);

        // Step 7: Get the tooltip details for comparison
        String centerNameMapTooltip = findCenter.getCenterNameInMapTooltip();
        String addressMapTooltip = findCenter.getAddressInMapTooltip();

        // Step 8: Output center name and address in the map tooltip
        System.out.println("Center name in popup: " + centerNameMapTooltip);
        System.out.println("Center address in popup: " + addressMapTooltip);

        // Step 9: Compare popup and tooltip details
        Assert.assertEquals(centerNamePopup, centerNameMapTooltip, "Center names do not match.");
        Assert.assertEquals(addressPopup, addressMapTooltip, "Center addresses do not match.");
        if (centerNamePopup.equals(centerNameMapTooltip) && addressPopup.equals(addressMapTooltip)) {
            System.out.println("Center name and address in popup match the list: Passed");
        } else {
            System.out.println("Center name and/or address in popup do not match the list: Failed");
        }
    }
}


