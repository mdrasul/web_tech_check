package com.americanwell.web.techCheckPages;

import com.americanwell.autoframework.utils.WebDriverFactory;
import com.americanwell.autotest_web.techCheck.TechcheckPage_Camera;
import com.americanwell.autotest_web.techCheck.TechcheckPage_Mic;
import com.americanwell.autotest_web.techCheck.TechcheckPage_Speaker;
import com.americanwell.autotest_web.techCheck.TechcheckPage_Start;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class WebTechCheck_Test {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public WebDriver driver;

    @BeforeMethod
    public void init() {
        try {
            driver = WebDriverFactory.getDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void ramDown() {
        driver.quit();
    }

    /**
     * Web Tech Check Sample Test Microphone Functionality
     *
     * Given I am on Web-Teach Check page
     * Then I Navigate to Microphone Widget click microphone button and verify if user can select the microphone
     * And I Navigate to Microphone Widget by answering next button and verify if user move to the next test speaker test
     * And I Navigate to Microphone Widget by answering previous button and verify if user move to the previous test,camera test
     * And I Navigate to Microphone Widget by select Microphone drop Down and verify if user can choose microphone from drop down
     *
     * Zephyr: OC-47098, OC-47500
     * @see <a href="https://jira.americanwell.com/browse/OC-47098">Detect microphone and transmit audio - Patient and Providers</a>
     * @author MisirukS
     */

    @Test(description = "Web Tech Check Sample Test Microphone Functionality")
    public void webTechCheck_Mic_Functionality() {
        log.info("Web Tech Check Sample Test Microphone Functionality");
        TechcheckPage_Mic techcheckPages = new TechcheckPage_Mic(driver);
        Assert.assertTrue(techcheckPages.launch(), "Test Failed to launch Tech Check Page");
        Assert.assertTrue(techcheckPages.click_micButton(), "Test Failed to click microphone  Button");
        Assert.assertTrue(techcheckPages.click_NextMic_Button(), "Test Failed to click Yes Button");
        Assert.assertTrue(techcheckPages.click_PreviousMic_Button(), "Test Failed to click No Button");
        Assert.assertTrue(techcheckPages.select_Mic_DropDown(), "Test Failed to select Drop Down ");
    }

    /**
     * Web Tech Check Sample Test Speaker Functionality
     *
     * Given I am on Web-Teach Check page
     * Then I Navigate  to Speaker Widget click speaker button and verify if user can select the speaker
     * And  I Navigate  to Speaker Widget by answering next button and verify if user move to the next test,internet speed test
     * And  I Navigate  to Speaker Widget by answering previous button and verify if user move to the previous test microphone test
     * And  I Navigate  to Speaker Widget by select speaker drop Down and verify if user can choose speaker from drop down
     *
     * Zephyr: OC-47100, OC-47500,OC-47424,OC-47386,
     * @see <a href="https://jira.americanwell.com/browse/OC-47100">Speakers recieveing audio output - Patients and Providers</a>
     * @author MisirukS
     */

    @Test(description = "Web Tech Check Sample Test Speaker Functionality ")
    public void webTechCheck_Speaker_Functionality() {
        log.info("Web Tech Check Sample Test Speaker Functionality");
        TechcheckPage_Speaker techcheckPage_speaker = new TechcheckPage_Speaker(driver);
        Assert.assertTrue(techcheckPage_speaker.launch(), "Test Failed to launch Tech Check Page");
        Assert.assertTrue(techcheckPage_speaker.click_speakerButton(), "Test Failed to click Speaker Button");
        Assert.assertTrue(techcheckPage_speaker.click_nextSpeak_Button(), "Test Failed to click Yes Button");
        Assert.assertTrue(techcheckPage_speaker.click_previousButton(), "Test Failed to click No Button");
        Assert.assertTrue(techcheckPage_speaker.select_speaker_DropDown(),"Test Failed to select_speaker_DropDown");
    }

    /**
     * Web Tech Check Sample Test Video Functionality
     *
     * Given I am on Web-Teach Check page
     * Then I Navigate  to Camera Widget click camera button and verify if user can select the camera
     * And  I Navigate  to Camera Widget by answering previous button and verify if user move to the previous test start test
     * And  I Navigate  to Camera Widget by answering next button and verify if user move to the next test,microphone test
     *
     * Zephyr: OC-47095, OC-47500,OC-47813
     * @see <a href="https://jira.americanwell.com/browse/OC-47095">Detect camera and transmit video- Patient and Providers</a>
     * @author MisirukS
     */

    @Test(description = "Web Tech Check Sample Test Video Functionality ")
    public void WebTechCheckSampleTestVideoFunctionality() {
        log.info("Web Tech Check Sample Test Speaker Functionality");
        TechcheckPage_Camera techcheckPage_camera = new TechcheckPage_Camera(driver);
        Assert.assertTrue(techcheckPage_camera.launch(), "Test Failed to launch Tech Check Page");
        Assert.assertTrue(techcheckPage_camera.click_cameraButton(), "Test Failed to click Camera Button");
        Assert.assertTrue(techcheckPage_camera.click_cameraButton_Click_Previous_Step(),"Test Failed to Click_camera Button_and move Previous_Step");
        Assert.assertTrue(techcheckPage_camera.click_cameraButton_MoveNext_MicTest()," Test click_camera Button_Move Next_MicTest");
    }

    @Test(description = "WebTechCheckSampleTestStartPageTest")
    public void WebTechCheckSampleTestStartPageTest() {
        log.info("Web Tech Check Sample Test Speaker Functionality");
        TechcheckPage_Start techcheckPage_start = new TechcheckPage_Start(driver);
        Assert.assertTrue(techcheckPage_start.launch(), "Test Failed to launch Tech Check Page");
        Assert.assertTrue(techcheckPage_start.statrPage(), "Test Failed to click Start Button");
        Assert.assertTrue(techcheckPage_start.clickNextStartButton(), "Test Failed to click Next Button");

    }
}








