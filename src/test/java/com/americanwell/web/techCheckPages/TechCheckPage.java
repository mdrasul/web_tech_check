package com.americanwell.web.techCheckPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TechCheckPage extends BasePage{
	
	/** ##### Constructor ##### */
	public TechCheckPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/** #####  TechCheck Page Returning methord ##### */
	
	public MicrophoneWidget microphoneWidget() {
		return new MicrophoneWidget(driver);
	}
	
	public CameraWidget cameraWidget() {
		return new CameraWidget(driver);
		
	}
	
	public InternetSpeedWidget internetSpeedWidget() {
		return new InternetSpeedWidget(driver);
		
	}
	
	public SpeakerWidget speakerWidget() {
		return new SpeakerWidget(driver);
		
	}
	
	public SummaryWidget summaryWidget() {
		return new SummaryWidget(driver);
	}
	
	public TroubleshootingWidget troubleshootingWidget(){
		return new TroubleshootingWidget(driver);
		
	}
	


	/** #####  TechCheck Page Full Positive Flow ##### */
	

	public boolean letsGetStartWithCameraYes() {
		clickVideoCamera();
		if (CameracSelectDropdown.isDisplayed()) {
			clickYes();
			return true;
		}else {
			return false;
		}
	}

	
	public boolean letsGetStartWithMicrofoneYes() {
		if (micSelectDropdown.isDisplayed()) {
			clickYes();
			return true;
		}else {
			return false;
		}
		
	}

	public boolean letsGetStartWithSpeakerYes() {
		if (SpeakerSelectDropdown.isDisplayed()) {
			clickYes();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean letsGetStartWithSpeedTestYes() {
		try {
			waitFor(2000);
			ClickButtoninsideFrameWithExit(clickInternetGoButton, 0);
			explicitlyWaitForElement(getInternetTestPassedText);
			if (explicitlyWaitForElementText(getInternetTestPassedText,"Your internet connection passed the test")) {
				explicitlyWaitForElement(clickContinue);
				clickContinue.click();
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean letsGetStartWithSummaryresultpassed() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return false;
	}
	
	
	/** #####  TechCheck Page Negative Flow ##### */

	public boolean cameraNagativeFlowTest() {
		try {
			clickVideoCamera();
			clickSpeaker();
			clickInternetGoButton.click();
			
			
			return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	
	}
	

	

}


