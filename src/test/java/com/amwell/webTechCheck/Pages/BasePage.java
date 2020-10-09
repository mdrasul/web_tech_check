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

public class BasePage {

	// Local driver for this page to support automation
	WebDriver driver;

	/** ##### Constructor ##### */

	public BasePage() {

	}

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean launchWebTechCheckPage() {
		try {
			driver.get("https://client1.dev.americanwell.com/");
			waitFor(2000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/** ##### Reusable clicking Page Locators (XPATH, CSS etc) ##### */

	@FindBy(xpath = "//div[@class='awcore-tmc-step-camera awcore-tmc-step-on']")
	protected WebElement clickVideoCamera;

	@FindBy(xpath = "//div[contains(text(),'Mic')]")
	protected WebElement clickMiocrophone;

	@FindBy(xpath = "//div[contains(text(),'Speaker')]")
	protected WebElement clickSpeaker;

	@FindBy(xpath = "//*[@class='awcore-tmc-device-instructions-button']/button")
	protected WebElement clickContinue;

	@FindBy(xpath = "//div[@class='awcore-tmc-step-internet']")
	protected WebElement clickInternetSpeed;
	
	@FindBy(xpath = "//div[@class='awcore-tmc-step-summary']")
	protected WebElement clickSummary;

	/** ##### Reusable Page Locators (XPATH, CSS etc) ##### */

	@FindBy(xpath = "//button[contains(text(),'No')]")
	public WebElement noButton;

	@FindBy(xpath = "//button[text()=' Yes ']")
	public WebElement yesButton;

	@FindBy(xpath = "//h2[@class='awcore-tmc-device-tips-header']")
	public WebElement getTroublshoootingTipinfo;

	@FindBy(xpath = "//*[@class='awcore-tmc-select-device-container']/select")
	public WebElement CameracSelectDropdown;

	@FindBy(xpath = "//select[@class='awcore-tmc-device-selector ng-untouched ng-pristine ng-valid']")
	public WebElement micSelectDropdown;

	@FindBy(xpath = "//select[@class='awcore-tmc-device-selector ng-untouched ng-pristine ng-valid']")
	public WebElement SpeakerSelectDropdown;

	@FindBy(xpath = "//*[@class='button background-primary-hover text-primary']")
	public WebElement clickInternetGoButton;

	@FindBy(xpath = "//h2[@class='awcore-tmc-device-instructions-text']")
	public WebElement getInternetTestPassedText;

	
	@FindBy(xpath = "//*[@class='awcore-tmc-test-result-header']")
	protected WebElement summaryPassText;
	
	/** ##### Clicking Wedget Helper methods Methods ##### */

	protected void clickNo() {
		// this.logger.debug("Navigating to My Providers");
		highlightElementBackground(noButton, "pass");
		noButton.click();
	}

	protected void clickYes() {
		// this.logger.debug("Navigating to My Providers");
		highlightElementBackground(yesButton, "pass");
		yesButton.click();
	}

	public void clickVideoCamera() {
		// this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickVideoCamera, "pass");
		clickVideoCamera.click();
	}

	public void clickMiocrophone() {
		// this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickMiocrophone, "pass");
		clickMiocrophone.click();
	}

	public void clickSpeaker() {
		// this.logger.debug("Navigating to My Providers");
		highlightElementBackground(clickSpeaker, "pass");
		clickSpeaker.click();
	}

	/** ##### Helper methods Methods ##### */

	public void waitFor(int waitMilis) {
		try {
			Thread.sleep(waitMilis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void explicitlyWaitForElement(WebElement ele) {
		int explicitWaitTime = 50;
		try {
			WebDriverWait wait = new WebDriverWait(driver, explicitWaitTime);
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			System.out.println("Failed to Get the elelement After waiting " + explicitWaitTime + "Second...");
			e.printStackTrace();
		}
	}

	public boolean explicitlyWaitForElementText(WebElement ele, String text) {
		int explicitWaitTime = 50;
		try {
			WebDriverWait wait = new WebDriverWait(driver, explicitWaitTime);
			return wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
		} catch (Exception e) {
			System.out.println("Failed to Get the Expected Text After waiting " + explicitWaitTime + "Second...");
			e.printStackTrace();
			return false;
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

	public String extractNumberFromString(String number) {
		String num = number.replaceAll("[^0-9]+", " ");
		return num.replaceAll(" ", "");
	}

	public void ClickButtoninsideFrame(WebElement element, int frameInx) {
		// this.logger.debug("Navigating to My Providers");
		driver.switchTo().frame(frameInx);
		waitFor(1000);
		element.click();
		// driver.switchTo().defaultContent();

	}

	public void ClickButtoninsideFrameWithExit(WebElement element, int frameInx) {
		// this.logger.debug("Navigating to My Providers");
		driver.switchTo().frame(frameInx);
		waitFor(1000);
		element.click();
		driver.switchTo().defaultContent();

	}

	protected boolean ipValidate(String ipAddress) {

		Pattern pattern;
		Matcher matcher;
		String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

		pattern = Pattern.compile(IPADDRESS_PATTERN);
		matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

	protected void varifyText(String text, WebElement element) {

		if (element.getText().contains(text)) {
			element.isDisplayed();
		} else {
			throw new RuntimeException("Text Don't Match");
		}

	}

	protected String getText(WebElement element) {

		String getElement = element.getText();
		return getElement;
	}

	public boolean compareresult(String text) {

		try {
			if (summaryPassText.isDisplayed() && getText(summaryPassText).contains(text)) {
				return true;
			} else {
				throw new RuntimeException("Computer Test Failed");
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}

}