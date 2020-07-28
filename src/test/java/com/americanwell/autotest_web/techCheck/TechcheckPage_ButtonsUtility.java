package com.americanwell.autotest_web.techCheck;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class TechcheckPage_ButtonsUtility {
    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public WebDriver driver;


    public boolean click_Button_Return_Message(WebElement button1,WebElement message) {
        try {
            button1.click();
            return message.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean click_Buttons_Return_Message(WebElement button1,WebElement button2,WebElement message) {
        try {
            button1.click();
            button2.click();
            return message.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean clickButton_Wait_till_Element_isDisplay(WebElement button1,WebElement button2,WebElement message) {

            try {
                button1.click();
                WebElement element = (button2);
                if (element.isDisplayed() && element.isEnabled()) {
                    element.click();
                }
            } catch (Exception e) {
                log.info("mik or Yes Buttons Exception Failed");
            }
            for (int i = 0; i < 2; i++) {
                try {
                    return message.isDisplayed();
                } catch (Exception e) {
                    log.info(String.valueOf(i));
                    continue;
                }
            }
            return true;
        }

    public boolean select_DropDown(WebElement button1,WebElement dropDown_Id,WebElement button2,WebElement message) {
        try {
            button1.click();
            Select sel = new Select(dropDown_Id);
            sel.selectByIndex(2);
            button2.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            try {
                return message.isDisplayed();
            } catch (Exception e) {
                log.info(String.valueOf(i));
                log.info("Exception something wrong ");
                continue;
            }
        }
        return true;
    }

    /** Helper methods Methods  **/
    public void waitFor(int waitMilis) {
        try {
            Thread.sleep(waitMilis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }


