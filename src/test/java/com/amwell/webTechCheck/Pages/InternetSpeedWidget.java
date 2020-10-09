package com.amwell.webTechCheck.Pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

public class InternetSpeedWidget extends BasePage{

	// Local driver for this page to support automation 
	WebDriver driver;
	
	/** ##### Constructor ##### */
	public InternetSpeedWidget(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	public InternetSpeedWidget() {
		
	}
	
	/** ##### Page Locators (XPATH, CSS etc) ##### */


	@FindBy(xpath = "//h3[contains(text(),'Press the GO button to test if your internet conne')]")
	public WebElement pressGoButtonSpeedTestLabel;
	
	@FindBy(xpath = "//*[@class='pure-button share-button share-button--go']/span")
	public WebElement clickINternetTryAgainButton;
	
	@FindBy(xpath = "//*[@class='result-body']/div/div")
	public WebElement getResult;
	
	@FindBy(xpath = "//*[@class='skiptest']/a")
	public WebElement clickInternetSkipButton;
	
	@FindBy(how=How.XPATH,using="//*[@class='result-value']")
	public java.util.List<WebElement> resultElement;
	
	@FindBy(how=How.XPATH,using="//*[@class='host-display__connection-body']/h4")
	public java.util.List<WebElement> cognetIp;
	
	
	
	/** ##### Page Specific Methods ##### */

	
	public boolean checkInternetGobutton() {
		//boolean buttonAvailable;
		try {
			waitFor(2000);
			//buttonAvailable=clickINternetGoButton.isDisplayed();
			ClickButtoninsideFrame(clickInternetGoButton,0);
			return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkInternetRetryButton() {
		//boolean buttonAvailable;
		try {
			//waitFor(2000);
			//buttonAvailable=clickINternetGoButton.isDisplayed();
			explicitlyWaitForElement(clickINternetTryAgainButton);
			clickINternetTryAgainButton.click();
			return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean TestInternetPingresult() {
		
		explicitlyWaitForElement(clickINternetTryAgainButton);
		int number =Integer.parseInt(extractNumberFromString(getResults("Ping").get(0)));
		//System.out.println(number);
		try {
			if (number >= 0) {
				System.out.println("ping Result is "+" "+ number);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		
	}
	
	public boolean testIntenetSkipButton() {
		
		boolean testIntenetSkipButton;
		try {
			// waitFor(2000);
			testIntenetSkipButton = clickInternetSkipButton.isDisplayed();
			clickInternetSkipButton();		
			return testIntenetSkipButton;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean TestInternetJittergresult() {
		
		int number =Integer.parseInt(extractNumberFromString(getResults("Jitter").get(1)));
		//System.out.println(number);
		try {
			if (number >= 0) {
				System.out.println("Jitter Result is "+" "+number);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
	public boolean testInternetDownloadresult() {
		
		float number =Integer.parseInt(extractNumberFromString(getResults("Download").get(2)));
		
		try {
			if (number >= 0) {
				System.out.println("Download Result is :"+" "+number);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
	public boolean testInternetUploadresult() {
		
		float number =Integer.parseInt(extractNumberFromString(getResults("Upload").get(3)));
		
		try {
			if (number >= 0) {
				System.out.println("upload Result is :"+" "+number);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	}

	public boolean ipTest() {
		
		try {
			//if (cognetIp.get(1).getText().contentEquals(ipValidate(cognetIp.get(1).getText()))) {
			if (ipValidate(cognetIp.get(1).getText())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	
	/** ##### Page Clicking Methods ##### */

	public void  clickInternetSpeed() {
		//this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickInternetSpeed, "pass");
		clickInternetSpeed.click();  
		
	}
	
	public void  clickInternetSkipButton() {
		//this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickInternetSkipButton, "pass");
		clickInternetSkipButton.click();  
	}
	
	
	/** ##### Helper  Methods ##### */
	
   public ArrayList<String> getResults(String text) {
	    
		int count = resultElement.size();
		//System.out.println(count);
		ArrayList<String> testlist= new ArrayList<>();
 		
		for (int i = 0; i < count; i++) {		
			
			if (text.equalsIgnoreCase("Ping")) {
				highlightElementBackground(resultElement.get(0), "pass");
				resultElement.get(0).click();
				testlist.add(resultElement.get(0).getText());

			}else if(text.equalsIgnoreCase("Jitter")) {
				
				highlightElementBackground(resultElement.get(1), "pass");
				resultElement.get(1).click();
				testlist.add(resultElement.get(1).getText());
			}else if(text.equalsIgnoreCase("Download")) {
				
				highlightElementBackground(resultElement.get(2), "pass");
				resultElement.get(2).click();
				testlist.add(resultElement.get(2).getText());
			}else if(text.equalsIgnoreCase("Upload")) {
				
				highlightElementBackground(resultElement.get(3), "pass");
				resultElement.get(3).click();
				testlist.add(resultElement.get(3).getText());
			}
			
		}
		return testlist;
		
	}
	

	
	
	

	 
	 
	 	
	
	
		
	
	

}

