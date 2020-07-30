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
import com.americanwell.web.techCheckPages.CameraWidget;
import com.americanwell.web.techCheckPages.InternetSpeedWidget;
import com.americanwell.web.techCheckPages.MicrophoneWidget;
import com.americanwell.web.techCheckPages.SpeakerWidget;
import com.americanwell.web.techCheckPages.TechCheckPage;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

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
 * Web Tech Check Test Class 
 * This TestNg Class contains all automated web test against tech chek page & its widgets
 * @see <a href="https://jira.americanwell.com/browse/OC-46250">Tech Check Outside of a Visit</a>
 * @author Gary.Cao
 */
//public class WebTechCheck_Test extends BaseTest
public class WebTechCheck_Test
{
    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public WebDriver driver;
    public TechCheckPage techCheckPage;
        
    @BeforeMethod
    public void init() {
        try {
			driver = WebDriverFactory.getDriver();
	    	techCheckPage = new TechCheckPage(driver);
	    	Assert.assertTrue(techCheckPage.launchWebTechCheckPage(), "Failed to launch Tech Check Page... ");	 
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @AfterMethod
    public void ramDown() {
        driver.quit();
    }
    
    /**
     * Scenario : User can complete a full test of all connected device 
     * Given I am on Web-Teach Check page 
     * Then I Navigate to Camera Widget by answering yes to question lets get start
     * And I Navigate to Microphone Widget by answering yes to question lets go to microphone
     * And I Navigate to Speaker Widget by answering yes to question lets go to Speaker
     * And I Navigate to Speed Check Widget by answering yes to question lets go to Speed Check
     * And I Navigate to Summary Page by answering yes to question lets go to Summary
     * Zephyr Test Id: OC-47455, OC-47458, OC-47467, OC-47470, OC-47472
     * @see <a href="https://jira.americanwell.com/browse/OC-47093">Putting the "Test my Computer" page together</a>
     * @author MD RASUL
     */
    @Test(enabled =false)
    public void fullTestonAllDevice() {
    	log.info("User can complete a full test of all connected device....");        
    	
    	TechCheckPage techCheckPage = new TechCheckPage(driver);
    	Assert.assertTrue(techCheckPage.launchWebTechCheckPage(), "Failed to launch Tech Check Page... ");
    	//
    	Assert.assertTrue(techCheckPage.letsGetStartWithCameraYes(), "Failed on Navigating to cemra widget by clicking Yes... ");
    	//Assert.assertTrue(techCheckPage.letsGetStartWithMicrofoneYes(), "Failed on Navigating to Mic widget by clicking Yes... ");
    	//Assert.assertTrue(techCheckPage.letsGetStartWithSpeakerYes(), "Failed on Navigating to Speaker widget by clicking Yes... ");
    	//Assert.assertTrue(techCheckPage.letsGetStartWithSpeedTestYes(), "Failed on Navigating to Speed Test widget by clicking Yes... ");    	
    }
    
    /**
     * Scenario : User sees default Microphone & can select 2nd Microphone also
     * Given I am on Web-Teach Check page
     * And I can move to mic by clicking mic icon
     * Then I Expect default mic is slected
     * And I can select secendary mic 
     * Zephyr Test Id: 
     * @see <a href="https://jira.americanwell.com/browse/OC-46250">Mic ..</a>
     * @author MD RASUL
     */
    @Test(enabled = false)
    public void webTectCheckMicTest() {
    	log.info("User sees default Microphone & can select 2nd Microphone also..");        
    	
    	// Real Validation For Microphone
    	MicrophoneWidget mic = techCheckPage.microphoneWidget();
    	mic.clickMiocrophone();
    	Assert.assertTrue(mic.isDefaultMicSelected(), "Failed to Get Default Mic ....");
    	Assert.assertTrue(mic.selectMic("2nd mic"), "Failed to Select Second Mic ....");
    }
    /**
     * Scenario : User sees default Video camera & can select 2nd webcam also
     * Given I am on Web-Teach Check page
     * And I can move to mic by clicking mic icon
     * Then I Expect default mic is slected
     * And I can select secendary mic 
     * Zephyr Test Id: 
     * @see <a href="https://jira.americanwell.com/browse/OC-46250">Mic ..</a>
     * @author MD RASUL
     */
    @Test(enabled = false)
    public void webTectCheckVideoCameraTest() {
    	log.info("User sees default Camera & can select 2nd Camera also..");        

    	// Real Validation For Microphone
    	CameraWidget cameraWidget = techCheckPage.cameraWidget();
    	Assert.assertTrue(cameraWidget.isDefaultCameraSelected(), "Failed to Get Default Camera ....");
    	//Assert.assertTrue(cameraWidget.selectCamera("2nd speaker"), "Failed to Select Second Camera ....");
    	//cameraWidget.selectCamera("2nrd camera");
    }
    
    /**
     * Scenario : User sees default Speaker & can select 2nd Speaker also
     * Given I am on Web-Teach Check page
     * And I can move to Speaker by clicking speaker icon
     * Then I Expect default Speaker is slected
     * And I can select secendary Speaker 
     * Zephyr Test Id: 
     * @see <a href="https://jira.americanwell.com/browse/OC-46250">Speaker ..</a>
     * @author MD RASUL
     */
    @Test(enabled = false)
    public void webTectCheckSpeakerTest() {
    	log.info("User sees default Speaker & can select 2nd Speaker also.....");        
    	
    	SpeakerWidget speakerWidget = techCheckPage.speakerWidget();
    	speakerWidget.clickSpeaker();
    	Assert.assertTrue(speakerWidget.isDefaultSpeakerSelected(), "Failed to Get Default Speaker ....");
    	Assert.assertTrue(speakerWidget.selectSpeaker("2nd speaker"), "Failed to Select Second Speaker ....");
    }
    
    /**
     * Scenario : User sees default Speaker & can select 2nd Speaker also
     * Given I am on Web-Teach Check page
     * And I can move to Speaker by clicking speaker icon
     * Then I Expect default Speaker is slected
     * And I can select secendary Speaker 
     * Zephyr Test Id: 
     * @see <a href="https://jira.americanwell.com/browse/OC-46250">Speaker ..</a>
     * @author MD RASUL
     */
    @Test(enabled = true)
    public void webTectCheckInternetTest() {
        log.info("Use should Check internet speed info like Download,Upload,Ping etc.....");        
        
     
        InternetSpeedWidget internetSpeedWidget = techCheckPage.internetSpeedWidget();
        internetSpeedWidget.clickInternetSpeed(); 
        Assert.assertTrue(internetSpeedWidget.checkInternetGobutton(), "Failed to Get Click button ....");  
        Assert.assertTrue(internetSpeedWidget.TestInternetPingresult(), "Failed to getPing Result ....");
        Assert.assertTrue(internetSpeedWidget.TestInternetJittergresult(), "Failed to get Jitter Result ....");
        Assert.assertTrue(internetSpeedWidget.testInternetDownloadresult(), "Failed to get Download Result ....");  
        Assert.assertTrue(internetSpeedWidget.testInternetUploadresult(), "Failed to get upload Result ....");
        Assert.assertTrue(internetSpeedWidget.checkInternetRetryButton(), "Failed to click  retry button ....");
        
        //techCheckPage.testIntenetSkipButton();  

    }
    
  
}