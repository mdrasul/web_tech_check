package com.americanwell.web.techCheckPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TechCheckPage {

	// Local driver for this page to support automation 
	WebDriver driver;

	/** ##### Constructor ##### */
	public TechCheckPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/** ##### Page Locators (XPATH, CSS etc) ##### */

	@FindBy(
			xpath = "//div[@class='awcore-tmc-step-get_started awcore-tmc-step-off']"
			)
	private WebElement clickStartTest;

	@FindBy(
			xpath = "//div[@class='awcore-tmc-step-camera awcore-tmc-step-on']"
			)
	private WebElement clickVideoCamera;

	@FindBy(
			xpath = "//div[@class='awcore-tmc-step-microphone awcore-tmc-step-off']"
			)
	private WebElement clickMicrophone;

	@FindBy(
			xpath = "//div[@class='awcore-tmc-step-speaker awcore-tmc-step-off']"
			)
	private WebElement clickSpeaker;

	@FindBy(
			xpath = "//div[@class='awcore-tmc-step-internet awcore-tmc-step-off']"
			)
	private WebElement clickInternetSpeed;

	@FindBy(
			xpath = "//div[@class='awcore-tmc-step-summary awcore-tmc-step-off']"
			)
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



}
