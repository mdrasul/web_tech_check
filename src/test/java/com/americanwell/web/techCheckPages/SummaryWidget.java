package com.americanwell.web.techCheckPages;

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

	/** ##### Page Locators (XPATH, CSS etc) ##### */

	@FindBy(xpath = "//div[@class='awcore-tmc-test-result-img awcore-tmc-test-result-successful']")
	protected WebElement cumpterTestpasText;

	@FindBy(xpath = "//*[@class='awcore-tmc-test-result-header']")
	protected WebElement summaryPassText;

	/** ##### Page Specific Methods ##### */

	public boolean compterTestPassed() {

		try {
			if (cumpterTestpasText.isDisplayed()) {
				return true;
			} else {
				throw new RuntimeException("Computer Test Failed");
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}

	public boolean compterTestPassed(String text) {

		try {
			if (cumpterTestpasText.isDisplayed() && getText(summaryPassText).contains(text)) {
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
