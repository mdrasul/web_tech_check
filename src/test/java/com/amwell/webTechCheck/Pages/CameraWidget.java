package com.amwell.webTechCheck.Pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
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

public class CameraWidget extends BasePage {

	// Local driver for this page to support automation 
	//WebDriver driver;
	//WebDriverWait wait = new WebDriverWait(driver,10);
	
	/** ##### Constructor ##### */
	public CameraWidget(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	/** ##### Page Locators (XPATH, CSS etc) ##### */
	

	@FindBy(xpath = "//*[@class='awcore-tmc-device-preview-text']")
	private WebElement selectCameraLabel;
	

	
	
	/** ##### Page Specific Methods ##### */
	
	
	public boolean isDefaultCameraSelected() {
		Select defaultSelect = new Select(CameracSelectDropdown);
		WebElement option = defaultSelect.getFirstSelectedOption();
		String defaultItem = option.getText();
		System.out.println(defaultItem);
		
		if(defaultItem.contains("fake_device_0")) {
			return true;
		} else {
			return false;
		}	
	}
	
	public boolean selectCamera(String cameraNumber) {
		int intSpeakerNumber =Integer.parseInt(extractNumberFromString(cameraNumber))-1;
		try {
			Select defaultSelect = new Select(CameracSelectDropdown);
			java.util.List<WebElement> intlist = defaultSelect.getAllSelectedOptions();	
			int size =intlist.size();
			System.out.println(size);
			if (size > 1) {
				defaultSelect.selectByIndex(intSpeakerNumber);
			}else {
				throw new RuntimeException("No secondary camera found");
			}
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}	
	}
	

}

