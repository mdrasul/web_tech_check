package com.americanwell.web.techCheckPages;

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

public class TechCheckPage2 {

	// Local driver for this page to support automation 
	WebDriver driver;
	//WebDriverWait wait = new WebDriverWait(driver,10);
	
	/** ##### Constructor ##### */
	public TechCheckPage2(WebDriver driver) {
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
	
	
	
	
	
	
	
	
	
	

	@FindBy(xpath = "//button[@class='awcore-tmc-step-pass-button']")
	private WebElement clickStartTest;

	@FindBy(xpath = "//div[@class='awcore-tmc-step-camera awcore-tmc-step-on']")
	private WebElement clickVideoCamera;

	@FindBy(xpath = "//div[contains(text(),'Mic')]")
	private WebElement clickMiocrophone;

	@FindBy(xpath = "//div[contains(text(),'Speaker')]")
	private WebElement clickSpeaker;
	
	@FindBy(xpath = "//div[@class='awcore-tmc-step-internet']")
	private WebElement clickInternetSpeed;
	
	@FindBy(xpath = "//button[text()=' Yes ']")
	public WebElement yesButton;

	@FindBy(xpath = "//div[@class='awcore-tmc-step-summary awcore-tmc-step-off']")
	private WebElement clickSummary;


	@FindBy(xpath = "//*[@class='awcore-tmc-device-preview-text']")
	public WebElement selectCameraLabel;

	@FindBy(xpath = "//label[contains(text(),'Microphone:')]")
	public WebElement selectMicLabel;

	@FindBy(xpath = "//label[contains(text(),'Select Speaker:')]")
	public WebElement selectSpeakerLabel;

	@FindBy(xpath = "//h3[contains(text(),'Press the GO button to test if your internet conne')]")
	public WebElement pressGoButtonSpeedTestLabel;
	
	@FindBy(xpath = "//select[@class='awcore-tmc-device-selector ng-untouched ng-pristine ng-valid']")
	public WebElement micSelectDropdown;
	
	@FindBy(xpath = "//select[@class='awcore-tmc-device-selector ng-untouched ng-pristine ng-valid']")
	public WebElement SpeakerSelectDropdown;
	
	@FindBy(xpath = "//button[@class='button background-primary-hover text-primary']")
	public WebElement clickInternetGoButton;
	
	@FindBy(xpath = "//button[@class='pure-button share-button share-button--go']")
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

	public boolean launchWebTechCheckPage() {
		try {
			driver.get("https://d2y4cy3z7juar5.cloudfront.net/");
			waitFor(2000);
			return true;
		} catch(Exception e){	
			e.printStackTrace();
			return false;
		}
	}

	public boolean isDefaultMicSelected() {
		Select defaultSelect = new Select(micSelectDropdown);
		WebElement option = defaultSelect.getFirstSelectedOption();
		String defaultItem = option.getText();
		
		if(defaultItem.contains("Default")) {
			return true;
		} else {
			return false;
		}	
	}
	
	public boolean selectMic(String micNumber) {
		int intMicNo =Integer.parseInt(extractNumberFromString(micNumber))-1;
		try {
			Select defaultSelect = new Select(micSelectDropdown);
			defaultSelect.selectByIndex(intMicNo);
			waitFor(2000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean selectSpeaker(String speakerNumber) {
		
		int intSpeakerNo = Integer.parseInt(extractNumberFromString(speakerNumber))-1;
		
		try {
			Select defaultSelect = new Select(SpeakerSelectDropdown);
			defaultSelect.selectByIndex(intSpeakerNo);
			waitFor(2000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		
	}
	
	public boolean isDefaultSpeakerSelected() {
		
		Select defaultSelect = new Select(SpeakerSelectDropdown);
		WebElement option = defaultSelect.getFirstSelectedOption();
		String defaultItem = option.getText();

		if(defaultItem.contains("Default")) {
			return true;
		} else {
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
		}if (!letsGetStartWithSpeakerYes()) {
			allResultList.add("Failed : Speaker");
		}
//		}if (!letsGetStartWithSpeedTestYes()) {
//			allResultList.add("Failed : SpeedTestY");
//		}
		
		return allResultList;
	}

	public boolean letsGetStartWithCameraYes() {		
		try {
			varifyText("Does your video appear in the area above?", selectCameraLabel);
			highlightElementBackground(selectCameraLabel, "pass");
			yesButton.click();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean letsGetStartWithMicrofoneYes() {
		boolean selectMicTextAvailable;
		try {
			//waitFor(2000);
			selectMicTextAvailable=selectMicLabel.isDisplayed();
			highlightElementBackground(selectMicLabel, "pass");
			yesButton.click();
			waitFor(2000);
			return selectMicTextAvailable;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean letsGetStartWithSpeakerYes() {
		boolean selectSpakerTextAvailable;
		try {
			// waitFor(2000);
			selectSpakerTextAvailable = selectSpeakerLabel.isDisplayed();
			highlightElementBackground(selectSpeakerLabel, "pass");
			yesButton.click();
			
			return selectSpakerTextAvailable;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkInternetGobutton() {
		//boolean buttonAvailable;
		try {
			waitFor(2000);
			//buttonAvailable=clickINternetGoButton.isDisplayed();
			internetButton(clickInternetGoButton);
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
			isClickable(clickINternetTryAgainButton);
			clickINternetTryAgainButton.click();
			return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean TestInternetPingresult() {
		
		isClickable(clickINternetTryAgainButton);
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
	
	public boolean letsGetStartWithSpeedTestYes() {
	  
		boolean selectMicTextAvailable;
		try {
			//waitFor(2000);
			selectMicTextAvailable=selectMicLabel.isDisplayed();
			highlightElementBackground(selectMicLabel, "pass");
			yesButton.click();
			waitFor(2000);
			return selectMicTextAvailable;
		} catch(Exception e) {
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
	
	
	
	//Clck Methord
	public void  clickStartTest() {
		//this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickStartTest, "pass");
		waitFor(2000);
		clickStartTest.click();  
	}
	public void  clickVideoCamera() {
		//this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickVideoCamera, "pass");
		clickVideoCamera.click();  
	}
	public void  clickSpeaker() {
		//this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickSpeaker, "pass");
		clickSpeaker.click();  
	}
	public void  clickInternetSpeed() {
		//this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickInternetSpeed, "pass");
		clickInternetSpeed.click();  
		
	}
	public void  clickSummary() {
		//this.logger.debug("Navigating to My Providers");
		clickSummary.click();  
	}
	public void  clickMiocrophone() {
		//this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickMiocrophone, "pass");
		clickMiocrophone.click();  
	}
	public void  clickInternetSkipButton() {
		//this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickInternetSkipButton, "pass");
		clickInternetSkipButton.click();  
	}
	

	
	/** ##### Helper methods Methods ##### */
	public void waitFor(int waitMilis) {
		try {
			Thread.sleep(waitMilis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void highlightElementBackground(WebElement element, String flag) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (flag.equalsIgnoreCase("pass")) {
			// js.executeScript("arguments[0].style.border='1px groove green'", element);
			js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", element);
		} else {
			// js.executeScript("arguments[0].style.border='4px solid red'", element);
			js.executeScript("arguments[0].style.backgroundColor = '" + "red" + "'", element);
		}

		waitFor(2000);
	}

	public void highlightElementBorder(WebElement element, String flag) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (flag.equalsIgnoreCase("pass")) {
			js.executeScript("arguments[0].style.border='2px groove green'", element);
		} else {
			js.executeScript("arguments[0].style.border='2px solid red'", element);

		}
		waitFor(2000);
	}
	
	private String extractNumberFromString(String number) {
	    String num = number.replaceAll("[^0-9]+", " ");
	    return num.replaceAll(" ", "");
	}
	
	public void  internetButton(WebElement element) {
		//this.logger.debug("Navigating to My Providers");
		
		driver.switchTo().frame(0);
		waitFor(1000);
		element.click();
		//driver.switchTo().defaultContent();

	}
		
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

	private  void isClickable(WebElement el) 
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.elementToBeClickable(el)); 
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
	
	
	

	 private boolean ipValidate(String ipAddress) {
		
		 Pattern pattern;
	     Matcher matcher;
	     String IPADDRESS_PATTERN = 
	    	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	    	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	    	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	    	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		
	         pattern = Pattern.compile(IPADDRESS_PATTERN);
	         matcher = pattern.matcher(ipAddress);
	         return matcher.matches();             
	      }

	 private void varifyText(String text,WebElement element) {
		
		if (element.getText().contentEquals(text)) {
			element.isDisplayed();
		}else{
			throw new RuntimeException("Text Don't Match");
		}
		 
	}
	     
	
	 
	 
	 	
	
	
		
	
	
	/** ##### Helper methods Can be use Later ##### */
	
//	public void eXwait(WebElement ele) {
//		
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, 10);
//			//WebElement targetElement4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World4!')]")));
//			WebElement targetElement4 = wait.until(ExpectedConditions.visibilityOf(ele));
//			System.out.println("Element Available4 : " + targetElement4.isDisplayed());
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void getIframe( ) {
//
//		
//		 java.util.List<WebElement> ele = driver.findElements(By.tagName("iframe"));
//		    System.out.println("Number of frames in a page :" + ele.size());
//		    for(WebElement el : ele){
//		     
//		        System.out.println("Frame Id :" + el.getAttribute("id"));
//		      
//		        System.out.println("Frame name :" + el.getAttribute("name"));
//		    }
//	}
//	public void useingAtionClass() {
//		
//		
//		
//	    WebElement frame= driver.findElement(By.xpath("//div[@class='show-iframe speed-test-iframe']//iframe"));
//	    driver.switchTo().frame(frame);
//        WebElement Child_tag= driver.findElement(By.xpath("//*[text()[contains(.,'GO')]"));
//        Actions action = new Actions(driver);
//        action.moveToElement(frame).click(Child_tag).perform();
//	
//}	

}

