package com.americanwell.autotest_web.techCheck;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;


public class TechcheckPage_Mic {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    TechcheckPage_ButtonsUtility techcheckPage_buttonsUtility = new TechcheckPage_ButtonsUtility();
    public WebDriver driver;

    public TechcheckPage_Mic(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(
            xpath = "//div[contains(text(),' Mic ')]/div"
    )
    public WebElement micButton;

    @FindBy(
            xpath = "//label[contains(text(),'Microphone:')]"
    )
    public WebElement selectMicrofonLabel;

    @FindBy(
            xpath = "//h3[contains(text(),'Get Started Speaker')]"
    )
    public WebElement messageLetsGetStartedSpeaker;

    @FindBy(
            xpath = "//label[contains(text(),'Select Speaker:')]"
    )
    public WebElement selectSpeakerLabel;

    @FindBy(
            xpath = "//button[contains(text(),'Previous')]"
    )
    public WebElement previousMicButton;

    @FindBy(
            xpath = "//button[contains(text(),'Next')]"
    )
    public WebElement nextMichrButton;

    @FindBy(
            xpath = "//div[@class='awcore-drawer-content awcore-drawer-content-get-started']//app-microphone//div//select"
    )
    public WebElement selectDropDown;

    @FindBy(
            xpath = "//label[contains(text(),'Select Camera:')]"
    )
    public WebElement selectCameraLabel;

    public boolean launch() {
        log.info("launch");
        try {
            driver.get("https://d1btwmvjnqgwu0.cloudfront.net/");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean click_micButton() {
        log.info("click_micButton");
        return techcheckPage_buttonsUtility.click_Button_Return_Message(micButton, selectMicrofonLabel);
    }
    public boolean click_PreviousMic_Button() {
        log.info("click_PreviousMic_Button");
        return techcheckPage_buttonsUtility.click_Buttons_Return_Message(micButton, previousMicButton,selectCameraLabel);
    }

    public boolean click_NextMic_Button() {
        log.info("Click_NextMic_Button");
        return techcheckPage_buttonsUtility.clickButton_Wait_till_Element_isDisplay(micButton, nextMichrButton,selectSpeakerLabel);
    }
    public boolean select_Mic_DropDown(){
        log.info("select_Mic_DropDown");
        return techcheckPage_buttonsUtility.select_DropDown(micButton,selectDropDown,nextMichrButton,selectSpeakerLabel);
    }

}







