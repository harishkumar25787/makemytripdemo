package com.lets.test;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static com.lets.test.DriverScript.*;
import static com.lets.test.GetOSName.OsUtils.isWindows;
public class Keywords {
	  public static WebDriver driver;
	  
	  
	  public String openBrowser(String object,String data){
		 
	        // Chrome Driver Path
		  
		  System.setProperty("webdriver.chrome.driver", ".//Chromedriver//chromedriver.exe");



	        // Internet Explorer Path
	        if (isWindows()) {
	            File file = new File("IEDriver/IEDriver.exe");
	            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	        }

	        APP_LOGS.debug("Opening browser");
	        if(data.equals("Mozilla"))
	            driver=new FirefoxDriver();
	        else if(data.equals("IE"))
	            driver=new InternetExplorerDriver();
	        else if(data.equals("Chrome"))
	        	 driver = new ChromeDriver();
		    driver.manage().window().maximize();;

	        long implicitWaitTime=Long.parseLong(CONFIG.getProperty("implicitwait"));
	        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
	        return Constants.KEYWORD_PASS;

	    }
	  
	  public String navigate(String object,String data){
	        APP_LOGS.debug("Navigating to URL");
	        try{
	            driver.navigate().to(data);
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+" -- Not able to navigate";
	        }
	        return Constants.KEYWORD_PASS;
	    }
		
		public  String clickOnLink() {
			//logger.info("ClickOnLink is called");
			try {
				expliciteWait();
				getWebElement(webElement).click();
			}catch (Throwable t) {
				t.printStackTrace();
				return "Failed - Element not found "+webElement;
			}
			return "Pass";
		}
		public  String inputText(String data) {
			//logger.info("InputText is called");
			try {
				expliciteWait();
				getWebElement(webElement).sendKeys(data);
			}catch (Throwable t) {
				return "Failed - Element not found "+webElement;
			}
			return "Pass";
		}
		
	    public String clickLink(String object,String data){
	        APP_LOGS.debug("Clicking on link ");
	        try{
	     
	            driver.findElement(By.linkText(OR.getProperty(object))).click();
	        }catch(Exception e){
	            return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
	        }

	        return Constants.KEYWORD_PASS;
	    }
	    public  String clickWhenReady(By locator, int timeout) {
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
			return "Pass";

		}


		
		public  String waitFor() throws InterruptedException {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				return "Failed - unable to load the page";
			}
			return "Pass";
		}
	    
		public static  WebElement getWebElement(String locator) throws Exception{
			//logger.info("locator data:-"+locator+"is---"+Repository.getProperty(locator));
			String keywordValue = OR.getProperty(locator);
			return getLocator(keywordValue);
		}
	    public static WebElement getLocator(String locator) throws Exception {
			String[] split = locator.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			if (locatorType.toLowerCase().equals("id"))
				return driver.findElement(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElement(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
				return driver.findElement(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
				return driver.findElement(By.tagName(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
				return driver.findElement(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElement(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
				return driver.findElement(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElement(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		}
	    public static List<WebElement> getLocators(String locator) throws Exception {
			String[] split = locator.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];

			if (locatorType.toLowerCase().equals("id"))
				return driver.findElements(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElements(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
				return driver.findElements(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElements(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
				return driver.findElements(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElements(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		}

	    public static void expliciteWait() throws Exception {
			try {
			//	logger.info("Waiting for webElement..."+webElement.toString());
				WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(Wait.getExplicitWait()));
				wait.until(ExpectedConditions.visibilityOf(getWebElement(webElement)));
			//	logger.info("Element found..."+webElement.toString());
			} catch (Throwable e) {
				throw new TimeoutException(webElement, e);
				
			}
			
		}
	    // not a keyword

	    
	    public void captureScreenshot(String filename, String keyword_execution_result) throws IOException{
	        // take screen shots
	        if(CONFIG.getProperty("screenshot_everystep").equals("Y")){
	            // capturescreen

	            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));

	        }else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equals("Y") ){
	            // capture screenshot
	            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
	        }
	    }
}
