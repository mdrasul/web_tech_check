package com.americanwell.autotest_web.techCheck;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class TechcheckPage_Speaker {
    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public WebDriver driver;
    TechcheckPage_Mic techcheckPage_mic = new TechcheckPage_Mic(driver);
    TechcheckPage_ButtonsUtility techcheckPage_buttonsUtility = new TechcheckPage_ButtonsUtility();

    public TechcheckPage_Speaker(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(
            xpath = "//div[contains(text(),' Speaker ')]/div"
    )
    public WebElement speakerButton;

    @FindBy(
            xpath = "//h3[contains(text(),'Press the GO button to test if your internet connection is strong enough for a visit.')]"
    )
    public WebElement messageGOButtonInternetConnection;

    @FindBy(
            xpath = "//label[contains(text(),'Select Speaker:')]"
    )
    public WebElement selectSpeakerLabel;

    @FindBy(
            xpath = "//button[contains(text(),'Next')]"
    )
    public WebElement nextSpeakerButton;

    @FindBy(
            xpath = "//button[contains(text(),'Previous')]"
    )
    public WebElement previousSpeakerButton;

    @FindBy(
            xpath = "//label[contains(text(),'Microphone:')]"
    )
    public WebElement selectMicrofonLabel;

    @FindBy(
            xpath = "//div[@class='speaker-container']/select"
    )
    public WebElement speakerDropDown;

    public boolean launch() {
        log.info("launch ");
        try {
            driver.get("https://d1btwmvjnqgwu0.cloudfront.net/");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean click_speakerButton() {
        log.info("click_speakerButton");
        return techcheckPage_buttonsUtility.click_Button_Return_Message(speakerButton,selectSpeakerLabel);
    }

    public boolean click_nextSpeak_Button() {
        log.info("click_nextSpeak_Button");
        return techcheckPage_buttonsUtility.click_Buttons_Return_Message(speakerButton,nextSpeakerButton,messageGOButtonInternetConnection);
    }

    public boolean click_previousButton() {
        log.info("Click_previousButton");
        return techcheckPage_buttonsUtility.clickButton_Wait_till_Element_isDisplay(speakerButton, previousSpeakerButton, selectMicrofonLabel);
    }

    public boolean select_speaker_DropDown(){
        log.info("select_speaker_DropDown");
        return techcheckPage_buttonsUtility.select_DropDown(speakerButton,speakerDropDown,nextSpeakerButton,messageGOButtonInternetConnection);
    }

}




