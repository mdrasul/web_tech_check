package com.americanwell.autotest_web.basictenant;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	// Local driver for this page to support automation
		WebDriver driver;
		public static Properties prop;
		public final String currentUsersHomeDir=System.getProperty("user.dir");
		public final String logoHeader=currentUsersHomeDir + File.separator +  "\\BasicTenantUploadImages\\AmWellLogoHeader.png";
		public final String mainLogo=currentUsersHomeDir + File.separator + "\\BasicTenantUploadImages\\AmWellLogoMain.png";
		public final String background=currentUsersHomeDir + File.separator + "\\BasicTenantUploadImages\\AmwellBackground.png";
		public final String logoConsole=currentUsersHomeDir + File.separator + "\\BasicTenantUploadImages\\AmWellLogoConsole.png";
		
		
		
		
		
		
		
		
		
		/** ##### Constructor ##### */

		public BasePage(WebDriver driver) {
			try {
				this.driver = driver;
				PageFactory.initElements(driver, this);
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/BasicTenantAssetInputData/TenantAsset.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public BasePage() {
			
		}

		/** ##### Launch page.. ##### */
		
		public boolean launchWebTechCheckPage() {
			try {
				driver.get(prop.getProperty("appurl"));
				waitFor(2000);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}


		
		/** ##### Reusable clicking Page Locators (XPATH, CSS etc) ##### */
		
		@FindBy(how = How.XPATH, using = "/html[1]/body[1]/pre[1]")
		public  WebElement readJdata;
		
	
		
		
		
		/** ##### Helper methods Methods ##### */

		public void waitFor(int waitMilis) {
			try {
				Thread.sleep(waitMilis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	    public boolean isValidEmailAddress(String email) {
	           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
	           java.util.regex.Matcher m = p.matcher(email);
	           return m.matches();
	    }
		
	    public void submitBUtton(WebElement element) {
			
	    	try {
				element.submit();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("button not clickable");
			}
		}
	    
	
		public  boolean uploadFile(WebElement element,String fileLocation) {
	        try {
	        element.sendKeys(fileLocation);	
	          return true; 
	        } catch (Exception exp) {
	        	exp.printStackTrace();
	        	return false;
	        }
	    }
	    
	    
	    
	public  boolean getJshondata(String key, String data) throws ParseException {
			String obj =readJdata.getText();
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(obj);
			Object o =json.get(key);
			
			
			// 
			// You Loop through all the keys 
			// in each iteration validate Key value vs test data value 
			//  if(key("clientName").value.equal(clientNameTestdata))
			//     
			
			
			
			
			//System.out.println(o);
			String convertedToString = o.toString();
			
			// Debug & Negetive Logic Puprose 
			// String mockJSon = convertedToString.replace(data, "SomethingElse");
			
			
			
			if (data.equalsIgnoreCase(convertedToString)) {
				return true;
			}else {
				return false;
			}
			
		}
	    
	

	
	
	
	  
	    
	    
	    
	    
	    
	    
	    
	    
//		public static boolean isValidEmailAddress(String email) {
//			   boolean result = true;
//			   try {
//			      InternetAddress emailAddr = new InternetAddress(email);
//			      emailAddr.validate();
//			   } catch (AddressException ex) {
//			      result = false;
//			   }
//			   return result;
//			}
		
		
		
		
}
