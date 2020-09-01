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

public class MicrophoneWidget extends BasePage {

	// Local driver for this page to support automation 
	//WebDriver driver;
	
	/** ##### Constructor ##### */
	public MicrophoneWidget(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/** ##### Page Locators (XPATH, CSS etc) ##### */

	

	@FindBy(xpath = "//label[contains(text(),'Microphone:')]")
	public WebElement selectMicLabel;

	
	
	
	/** ##### Page Specific Methods ##### */

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
	

}

