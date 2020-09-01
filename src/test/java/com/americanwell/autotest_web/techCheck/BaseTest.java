package com.americanwell.autotest_web.techCheck;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class BaseTest {
	protected static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	protected static Map<String, SessionData> sessions = new ConcurrentHashMap(32, 0.75F, 32);
	protected static ThreadLocal<String> testName = new ThreadLocal();

	@BeforeClass
	public void btBeforeClass(ITestContext ctx) {
		log.debug("=================================================");
		log.debug("@btBeforeClass starting test -> {}", ctx.getName());
		log.debug("=================================================");
		log.debug("Number of active session {}", sessions.size());
		testName.set(ctx.getName());
	}

public static void addSession(WebDriver driver) {
		SessionData sessionData = new SessionData();
		sessionData.driver = driver;
		sessionData.testName = (String) testName.get();
		String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
		log.debug("Adding session '{}' to the sessions list", sessionId);
		sessions.put(sessionId, sessionData);
		log.debug("Current list of session data:");
		Iterator var3 = sessions.entrySet().iterator();

		while (var3.hasNext()) {
			Entry<String, SessionData> entry = (Entry) var3.next();
			String key = (String) entry.getKey();
			SessionData session = (SessionData) entry.getValue();
			log.debug("Test: {} sessionId: {}", session.testName, key);
		}

	}

	public static void removeSession(String sessionId) {
		if (sessions.containsKey(sessionId)) {
			log.debug("Removing session '{}' from the sessions list", sessionId);
			sessions.remove(sessionId);
		}

	}

	@AfterClass(alwaysRun = true)
	protected void btAfterClass(ITestContext itc) {
		log.debug("========================");
		log.debug("@btAfterClass -> {}", itc.getName());
		log.debug("========================");

		try {
			Iterator var2 = sessions.keySet().iterator();

			label45 : while (true) {
				String key;
				SessionData sessionData;
				do {
					do {
						do {
							if (!var2.hasNext()) {
								break label45;
							}

							key = (String) var2.next();
							sessionData = (SessionData) sessions.get(key);
							if (sessionData == null) {
								log.debug("sessionData is null for session {}", key);
							}
						} while (sessionData == null);
					} while (sessionData.testName == null);
				} while (!sessionData.testName.equals(itc.getName()));

				if (sessionData.driver != null && ((RemoteWebDriver) sessionData.driver).getSessionId() != null) {
					log.debug("Clearing cookies for session {}", key);
					sessionData.driver.manage().deleteAllCookies();
					log.debug("Killing session {}", key);
					sessionData.driver.quit();
				} else {
					log.debug("The WebDriver is null for session {}", key);
				}

				removeSession(key);
			}
		} catch (Exception var5) {
			log.error("Caught an exception in BaseTest @AfterClass", var5);
		}

		log.debug("{} active sessions in btAfterClass", sessions.size());
	}
}
