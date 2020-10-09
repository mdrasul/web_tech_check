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

public class SummaryWidget extends BasePage {

	// Local driver for this page to support automation
	// WebDriver driver;
	// WebDriverWait wait = new WebDriverWait(driver,10);

	/** ##### Constructor ##### */
	public SummaryWidget(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}


	/** ##### Page Specific Methods ##### */


	public boolean compterTestPassed() {

		try {
			if (summaryPassText.isDisplayed() && getText(summaryPassText).contains("Your computer is ready for online visits.")) {
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
