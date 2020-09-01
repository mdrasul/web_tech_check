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

public class TroubleshootingWidget extends BasePage {

	// Local driver for this page to support automation
	WebDriver driver;
	// WebDriverWait wait = new WebDriverWait(driver,10);

	/** ##### Constructor ##### */
	public TroubleshootingWidget(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/** ##### Page Locators (XPATH, CSS etc) ##### */

	/** ##### Page Specific Methods ##### */

	public boolean getCameraTroubleShottingTipsInfo() {

		try {
			clickVideoCamera();
			if (CameracSelectDropdown.isDisplayed()) {
				clickNo();
				varifyText("Camera Test Failed", getTroublshoootingTipinfo);
				System.out.println("Camera Test Result " + " : " + getTroublshoootingTipinfo.getText());
				return true;
			} else {
				throw new RuntimeException("TroubleShotting Tips not found ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


	}

	public boolean getMicTroubleShottingTipsInfo() {

		try {
			clickMiocrophone();
			if (micSelectDropdown.isDisplayed()) {
				clickNo();
				varifyText("Microphone Test Failed", getTroublshoootingTipinfo);
				System.out.println("Microphone Test Result " + " : " + getTroublshoootingTipinfo.getText());
				return true;
			} else {
				throw new RuntimeException("TroubleShotting Tips not found ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean getSpeakerTroubleShottingTipsInfo() {

		try {
			clickSpeaker();
			if (SpeakerSelectDropdown.isDisplayed()) {
				clickNo();
				varifyText("Speaker Test Failed", getTroublshoootingTipinfo);
				System.out.println("Speaker Test Result " + " : " + getTroublshoootingTipinfo.getText());
				return true;
			} else {
				throw new RuntimeException("TroubleShotting Tips not found ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
