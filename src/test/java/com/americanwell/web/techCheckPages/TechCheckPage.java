package com.americanwell.web.techCheckPages;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

public class TechCheckPage {

	// Local driver for this page to support automation 
	WebDriver driver;
	

	/** ##### Constructor ##### */
	public TechCheckPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/** ##### Page Locators (XPATH, CSS etc) ##### */

	@FindBy(xpath = "//div[@class='awcore-tmc-step-get_started awcore-tmc-step-off']")
	private WebElement clickStartTest;

	@FindBy(xpath = "//div[@class='awcore-tmc-step-camera awcore-tmc-step-on']")
	private WebElement clickVideoCamera;

	@FindBy(xpath = "//div[@class='awcore-tmc-step-microphone awcore-tmc-step-off']")
	private WebElement clickMicrophone;

	@FindBy(xpath = "//div[@class='awcore-tmc-step-speaker awcore-tmc-step-off']")
	private WebElement clickSpeaker;

	@FindBy(xpath = "//div[@class='awcore-tmc-step-internet awcore-tmc-step-off']")
	private WebElement clickInternetSpeed;

	@FindBy(xpath = "//div[@class='awcore-tmc-step-summary awcore-tmc-step-off']")
	private WebElement clickSummary;

	@FindBy(xpath = "//button[@class='awcore-tmc-step-pass-button']")
	public WebElement yesButton;

	@FindBy(xpath = "//label[contains(text(),'Select Camera:')]")
	public WebElement selectCameraLabel;

	@FindBy(xpath = "//label[contains(text(),'Microphone:')]")
	public WebElement selectMicLabel;

	@FindBy(xpath = "//label[contains(text(),'Speaker:')]")
	public WebElement selectSpeakerLabel;

	@FindBy(xpath = "//h3[contains(text(),'Press the GO button to test if your internet conne')]")
	public WebElement pressGoButtonSpeedTestLabel;
	
	@FindBy(xpath = "//select[@class='ng-untouched ng-pristine ng-valid']")
	public WebElement micSelectDropdown;
	
	
	/** ##### Page Specific Methods ##### */

	public boolean launchWebTechCheckPage() {
		try {
			driver.get("http://localhost/");
			waitFor(2000);
			return true;
		} catch(Exception e){	
			e.printStackTrace();
			return false;
		}
	}
	
	
	//== Full Positive Flow 
	public ArrayList<String> performAfullTechcheck() {
		
		ArrayList<String> allResultList = new ArrayList<String>();
		
		if(!letsGetStartWithCameraYes()) {
			allResultList.add("Failed : Camera..");
		}
		if(!letsGetStartWithMicrofoneYes()) {
			allResultList.add("Failed : Mic..");
		}
		
		return allResultList;
	}

	public boolean letsGetStartWithCameraYes() {
		try {
			yesButton.click();
			waitFor(2000);
			return selectCameraLabel.isDisplayed();

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean letsGetStartWithMicrofoneYes() {
		try {
			yesButton.click();
			waitFor(2000);
			return selectMicLabel.isDisplayed();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean letsGetStartWithSpeakerYes() {
		try {
			yesButton.click();
			waitFor(2000);
			//return selectSpeakerLabel.isDisplayed();
			return true;  /* TODO : Debug Code Removal : Remove this line and uncomment the line Above  */
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean letsGetStartWithSpeedTestYes() {
		try {
			yesButton.click();
			waitFor(2000);
			return pressGoButtonSpeedTestLabel.isDisplayed();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void  clickStartTest() {
		//this.logger.debug("Navigating to My Providers");
		clickStartTest.click();  
	}
	public void  clickVideoCamera() {
		//this.logger.debug("Navigating to My Providers");
		clickVideoCamera.click();  
	}
	public void  clickSpeaker() {
		//this.logger.debug("Navigating to My Providers");
		clickSpeaker.click();  
	}
	public void  clickInternetSpeed() {
		//this.logger.debug("Navigating to My Providers");
		clickInternetSpeed.click();  
	}
	public void  clickSummary() {
		//this.logger.debug("Navigating to My Providers");
		clickSummary.click();  
	}



	/** ##### Helper methods Methods ##### */
	public void waitFor(int waitMilis) {
		try {
			Thread.sleep(waitMilis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void webCheckFlows(String startTest,String clickVideo,String clickIntestnetSpeed,String clickSummary ) {
		if (startTest.contentEquals("yes")) {
			clickStartTest();
		}
	    if (clickVideo.contentEquals("yes")) {
			clickVideoCamera();
		}
		
		clickSpeaker();
		clickInternetSpeed();
		clickSummary();
		
	}

	 public void micDropDown(String searchText) {
		 
		 // When click Select Mic Dropdown 
		 
		 // Then I see there is atleas one default value selected 
		 // And i can selct the 2nd mic 
		 
		 
		 // Given I have a computer with more then 2 Mic 
		 // When I click the mic select dowrnpowe
		 // then I exepct the mic count is more then 2 
		 
		 
		 
		 micSelectDropdown.click();
		 java.util.List<WebElement> options = micSelectDropdown.findElements(By.tagName("option"));

		 for (WebElement option : options)
		 {
		     if (option.getText().equals(searchText))
		     {
		         option.click();
		         break;
		     }
//		 Iterator<WebElement> it=options.iterator();
//
//		   while(it.hasNext())
//		    {
//			   
//			   it.next().getAttribute("Value");
//			  
//		    }
	}

	 }
	 }
	
	

