package com.americanwell.autotest_web.techCheck;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class TechcheckPage_Camera {
    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    TechcheckPage_ButtonsUtility techcheckPage_buttonsUtility = new TechcheckPage_ButtonsUtility();
    public WebDriver driver;


    public TechcheckPage_Camera(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(
            xpath = "//div[contains(text(),' Camera ')]/div"
    )
    public WebElement cameraButton;

    @FindBy(
            xpath = "//label[contains(text(),'Select Camera:')]"
    )
    public WebElement selectCameraLabel;

    @FindBy(
            xpath = "//label[contains(text(),'Microphone:')]"
    )
    public WebElement selectMicrofonLabel;

    @FindBy(
            xpath = "//h3[contains(text(),'started to check the browser compatibility')]"
    )
    public WebElement messageStartTest;

    @FindBy(
            xpath = "//button[contains(text(),'Next')]"
    )
    public WebElement nextcameraButton;

    @FindBy(
            xpath = "//button[contains(text(),'Previous')]"
    )
    public WebElement previousCameraButton;


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

    public boolean click_cameraButton() {
        log.info("click_cameraButton");
        return techcheckPage_buttonsUtility.click_Button_Return_Message(cameraButton, selectCameraLabel);
    }

    public boolean click_cameraButton_MoveNext_MicTest() {
        log.info("click_cameraButton_MoveNext_MicTest");
        return techcheckPage_buttonsUtility.clickButton_Wait_till_Element_isDisplay(cameraButton,nextcameraButton, selectMicrofonLabel);
    }

    public boolean click_cameraButton_Click_Previous_Step() {
        log.info("click_cameraButton_Click_Previous_Step");
        return techcheckPage_buttonsUtility.clickButton_Wait_till_Element_isDisplay(cameraButton, previousCameraButton, messageStartTest);
    }

}



