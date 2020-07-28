package com.americanwell.autotest_web.techCheck;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;


public class TechcheckPage_Start {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    TechcheckPage_ButtonsUtility techcheckPage_buttonsUtility = new TechcheckPage_ButtonsUtility();
    public WebDriver driver;


    public TechcheckPage_Start(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(
            xpath = "//div[@class='awcore-tmc-step-get_started awcore-tmc-step-on']"
    )
    public WebElement startButton;


    @FindBy(
            xpath = "//h3[contains(text(),'started to check the browser compatibility')]"
    )
    public WebElement messageStartTest;

    @FindBy(
            xpath = "//label[contains(text(),'Select Camera:')]"

    )
    public WebElement selectCameraLabel;

    @FindBy(
            xpath = "//button[contains(text(),'Next')]"
    )
    public WebElement nextStartButton;

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
    public boolean statrPage(){
        return techcheckPage_buttonsUtility.click_Button_Return_Message(startButton,messageStartTest);
    }
    public boolean clickNextStartButton(){
        return techcheckPage_buttonsUtility.click_Button_Return_Message(nextStartButton,selectCameraLabel);
    }

}
