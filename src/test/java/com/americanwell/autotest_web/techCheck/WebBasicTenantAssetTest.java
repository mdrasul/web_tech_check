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
import com.americanwell.autotest_web.basictenant.AdminPage;

import org.json.simple.parser.ParseException;
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
 * Web Tech Check Test Class This TestNg Class contains all automated web test
 * against tech chek page & its widgets
 * 
 * @see <a href="https://jira.americanwell.com/browse/OC-46250">Tech Check
 *      Outside of a Visit</a>
 * @author Gary.Cao
 */
//public class WebTechCheck_Test extends BaseTest
public class WebBasicTenantAssetTest {
	private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	public WebDriver driver;
	public AdminPage adminPage;

	@BeforeMethod
	public void init() {
		try {
			driver = WebDriverFactory.getDriver();
			adminPage = new AdminPage(driver);
			Assert.assertTrue(adminPage.launchWebTechCheckPage(), "Failed to launch  Page... ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void ramDown() {
		driver.quit();
	}

	
	
	
	
	/**
	 * Scenario : User Uploads Assets (Header Logo, Main Logo, Background, Console Log) Using a Admin UI
	 * Given I Browse to a ( admin ui ) URL to upload client/tenant assets 
	 * Then I validate a ui input form that can be fill up with all client/tenant assets information
	 * When I Complete the form with all client/tenant assets information
	 * Then I validate a successful post api request happened with all client/tenant assets information
	 * Epic Link : https://jira.americanwell.com/browse/WVL-280
	 * Zephyr Test Link : https://jira.americanwell.com/browse/OC-49582
	 * @throws IOException 
	 * @throws ParseException 
	 */
	
	@Test(enabled = true)
	public void alidateAssetUploadingUsingAdminUI() throws IOException, ParseException {
		
		Assert.assertTrue(adminPage.clientNameInput(), "Failed to input Client Name ....");
		Assert.assertTrue(adminPage.clientEmailinput(), "Failed to input Client email ....");
		Assert.assertTrue(adminPage.uploadLogoHeader(), "Failed to upload header Logo ....");
		Assert.assertTrue(adminPage.uploadLogoMain(), "Failed to upload main logo ....");
		Assert.assertTrue(adminPage.uploadBackground(), "Failed to upload backgorund ....");
		Assert.assertTrue(adminPage.uploadLogoConsole(), "Failed to uoload console logo ....");
		adminPage.waitFor(2000);
		Assert.assertTrue(adminPage.clickUploadFile(), "Failed to click Upload button....");
		adminPage.waitFor(2000);
		Assert.assertTrue(adminPage.varifyData(), "Failed to Varify Uploaded data....");
		
		
		
		
	
	}
	
	
	
	




}