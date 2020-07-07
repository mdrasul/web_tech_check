/**
 * Copyright 2019 American Well Systems
 * All rights reserved.
 *
 * It is illegal to use, reproduce or distribute
 * any part of this Intellectual Property without
 * prior written authorization from American Well.
 */

package com.americanwell.autotest_web.techCheck;

import com.americanwell.autoframework.utils.WebDriverFactory;
import com.americanwell.web.techCheckPages.TechCheckPage;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * Consumer Navigation Tests. Consumer account created on the fly using UserManager via SOAP request, consumer navigate
 * through UI. Tests run in parallel.
 *
 * @see <a href="https://jira.americanwell.com/browse/QAAUT-466">Consumer Navigation Tests with consumers created on the fly for parallel testing</a>
 * @author Gary.Cao
 */
public class WebTechCheck_Test
{
    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public WebDriver driver;
        
    @BeforeMethod
    public void init() {
        try {
			driver = WebDriverFactory.getDriver();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @AfterMethod
    public void ramDown() {
        driver.quit();
    }
    
    /**
     * Web Teach Check Page Landing Page Test
     *
     * Given I am on Web-Teach Check page 
     * Then I Navigate to Camera Widget by answering yes to question lets get start
     * And I Navigate to Microphone Widget by answering yes to question lets go to microphone
     * And I Navigate to Speaker Widget by answering yes to question lets go to Speaker
     * And I Navigate to Speed Check Widget by answering yes to question lets go to Speed Check
     * And I Navigate to Summary Page by answering yes to question lets go to Summary
     * Zephyr: OC-47455, OC-47458, OC-47467, OC-47470, OC-47472
     * @see <a href="https://jira.americanwell.com/browse/OC-47093">Putting the "Test my Computer" page together</a>
     * @author MD RASUL
     */
    @Test(description = "Web Tech Check Putting the \"Test my Computer\" widget together")
    public void webTectCheckSampleTest() {
        log.info("Web tech check Putting the \"Test my Computer\" widget together...");        
        
        TechCheckPage techCheckPage = new TechCheckPage(driver);
        Assert.assertTrue(techCheckPage.launchWebTechCheckPage(), "Failed to launch Tech Check Page... ");
        Assert.assertTrue(techCheckPage.letsGetStartWithCameraYes(), "Failed on Navigating to cemra widget by clicking Yes... ");
        Assert.assertTrue(techCheckPage.letsGetStartWithMicrofoneYes(), "Failed on Navigating to Mic widget by clicking Yes... ");
        Assert.assertTrue(techCheckPage.letsGetStartWithSpeakerYes(), "Failed on Navigating to Speaker widget by clicking Yes... ");
        Assert.assertTrue(techCheckPage.letsGetStartWithSpeedTestYes(), "Failed on Navigating to Speed Test widget by clicking Yes... ");
    }
    
  
}