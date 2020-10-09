package com.amwell.webTechCheck.TestCase;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public void onTestFailure(ITestResult result) {
		logger.debug("TestListener has detected a test failure:  " + result.getName());
		WebDriver driver = null;
		Class thisClass = result.getTestClass().getRealClass();
		Field[] fields = thisClass.getDeclaredFields();
		String instanceName = "";
		int driverCount = 0;
		Field[] var7 = fields;
		int var8 = fields.length;

		for (int var9 = 0; var9 < var8; ++var9) {
			Field aField = var7[var9];

			try {
				instanceName = aField.get(result.getInstance()).toString();
				if (instanceName.toLowerCase().contains("driver")) {
					driver = (WebDriver) aField.get(result.getInstance());
					String methodName = result.getName().trim();
					ScreenShot.take(driver, methodName + "_" + aField.getName());
					++driverCount;
				}
			} catch (IllegalAccessException var12) {
				logger.debug("Trapped IllegalAccessException, moving on to next field...");
			} catch (NullPointerException var13) {
				logger.debug("Trapped NullPointerException traversing fields in TestListener...");
			}
		}

		if (driverCount == 0) {
			logger.debug("No WebDriver found so no screenshot taken");
		}

	}

	public void onFinish(ITestContext context) {
	}

	public void onTestStart(ITestResult result) {
		String[] classNamePath = result.getTestClass().getName().split("\\.");
		String className = classNamePath[classNamePath.length - 1];
		String testName = result.getName();
		logger.info("Running test: {}.{}()...", className, testName);
	}

	public void onTestSuccess(ITestResult result) {
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}
}
