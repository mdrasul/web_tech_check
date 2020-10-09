package com.amwell.TenantWebSites.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage{

	// Local driver for this page to support automation 
		//WebDriver driver;
		
		/** ##### Constructor ##### */
		public AdminPage(WebDriver driver) {
			super(driver);
			//PageFactory.initElements(driver, this);
		}
//		public AdminPage() {
//			
//		}
		
		/** ##### Page Locators (XPATH, CSS etc) ##### */

		@FindBy(xpath = "//input[@id='clientName']")
		public WebElement clientName;
		
		@FindBy(xpath = "//input[@id='logoEmail']")
		public WebElement clientEmail;
		
		@FindBy(xpath = "//input[@id='logoHeader']")
		public WebElement logHeaderUpload;
		
		@FindBy(xpath = "//input[@id='logoMain']")
		public WebElement mainLogoUpload;
		
		@FindBy(xpath = "//input[@id='background']")
		public WebElement backgroundUpload;
		
		@FindBy(xpath = "//input[@id='logoConsole']")
		public WebElement consoleLogoUpload;
		
		@FindBy(xpath = "//form[@id='uploadForm']/input[7]")
		public WebElement clickUploadButton;
		
		
		
		
		/** ##### Page Specific Methods ##### */
		
		
		public boolean clientNameInput() {
			
			try {
				inputClientName(prop.getProperty("clientName"));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("input Client name failed");
				
			}
			
			
		}
				
		public boolean clientEmailinput() {
			
			try {
				if (inputClientEmail(prop.getProperty("email"))) {
					return true;
				}else {
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("input email failed");
				
			}
			
			return false;
		}
		
		public boolean uploadLogoHeader() {
			
			try {
				if (uploadFile(logHeaderUpload, logoHeader)) {
					return true;
				}else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Logo Upload header failed");
			}
			
			
		}
		
		public boolean uploadLogoMain() {
			
			try {
				if (uploadFile( mainLogoUpload,mainLogo)) {
					return true;
				}else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("main logo Upload failed");
			}
			
			
		}
		
		public boolean uploadBackground() {
			
			try {
				if (uploadFile( backgroundUpload,background)) {
					return true;
				}else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("background upoload failed");
			}
			
			
		}
		
		public boolean uploadLogoConsole() {
			
			try {
				if (uploadFile( consoleLogoUpload,logoConsole)) {
					return true;
				}else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("upload logoConsole failed");
			}
			
			
		}
		
		public boolean clickUploadFile() {
			
			try {
				submitBUtton(clickUploadButton);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("upload logoConsole failed");
			}
			
			
		}
		public boolean varifyData() {
			
			try {
				
				getJshondata(prop.getProperty("clientKey"),prop.getProperty("clientName"));
				getJshondata(prop.getProperty("logoEmailKey"),prop.getProperty("email"));
				getJshondata(prop.getProperty("logoMainKey"),prop.getProperty("logoMain"));
				getJshondata(prop.getProperty("logoHeaderKey"),prop.getProperty("logoHeader"));
				getJshondata(prop.getProperty("backgroundKey"),prop.getProperty("background"));
				getJshondata(prop.getProperty("logoConsoleKey"),prop.getProperty("logoConsole"));
				
				
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Verificain fail");
			}
		
			
		}
//		
//		public void uploadfile() {
//			
//			try {
//				//JavascriptExecutor js = (JavascriptExecutor) driver;
//				//js.executeScript("arguments[0].click();", logHeaderUpload);
//				//logHeaderUpload.click();
//				logHeaderUpload.sendKeys(System.getProperty("user.dir") + "\\BasicTenantUploadImages\\AmWellLogoHeader.png");
//				uploadFile("C:\\Users\\MD.Rasul\\Documents\\Amwell Assets\\AmWellLogoMain.png");
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			
//			//uploadFile("C:\\Users\\MD.Rasul\\Documents\\Amwell Assets\\AmWellLogoMain.png");
//		}
//		
		/** ##### Page Helping Methods ##### */
		
		
		private void inputClientName(String name) {
			clientName.sendKeys(name);
		}
		
		private boolean inputClientEmail(String email) {
			try {
				if (isValidEmailAddress(email)) {
					clientEmail.sendKeys(email);
					return true;
				}else {
					throw new RuntimeException("Not a Vaild Email");
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			
		}

		
		
			
			
		


}
		
		
		
		
		
		
		
	

