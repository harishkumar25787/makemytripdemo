package config;
import java.sql.DriverAction;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import executionEngine.DriverScriptTest;
import utility.Log;

import org.openqa.selenium.Alert;
import static executionEngine.DriverScriptTest.OR;
public class ActionKeywords {
	
	public static WebDriver driver;
	public static ExtentReports reports;
	public static ExtentTest logger;
	
	public ActionKeywords(){
		reports=new ExtentReports("src\\test\\java\\Reports\\Report.html");
	}
	//This block of code will decide which browser type to start
	//All the methods in this class now accept 'Object' name as an argument
	public static void openBrowser(String object, String data){
		try{
			Log.info("Opening Browser");
			//If value of the parameter is Chrome, this will execute
			if (data.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver",".//Chromedriver//chromedriver.exe");
				driver=new ChromeDriver();	
				logger.log(LogStatus.PASS, "opened Browser -"+ data);
				Log.info("Chrome browser started");}
			else if (data.equals("IE")){
				//You may need to change the code here to start IE Driver
				driver=new InternetExplorerDriver();
				logger.log(LogStatus.PASS, "opened Browser -"+ data);
				Log.info("IE browser started");}
			else if(data.equals("Mozilla")){
				driver=new FirefoxDriver();
				logger.log(LogStatus.PASS, "opened Browser -"+ data);
				Log.info("Mozilla browser started");}
			int implicitWaitTime=(10);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
			//This block will execute only in case of an exception
		}catch(Exception e){
			//This is to print the logs - Method Name & Error description/stack
			Log.info("Not able to open Browser --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to opened Browser -"+ data);
			//Set the value of result variable to false
			DriverScriptTest.bResult = false;
		}
		
	}
	
	public static void navigate(String object, String data){
		try{
			Log.info("Navigating to URL "+ "'" + Constants.URL+"'");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//Constant Variable is used in place of URL
			//As it was declared as 'static', it can be used by referring the class name
			//Type the class name 'Constants' and press '.' dot, it will display all the memebers of the class Constants
			driver.get(Constants.URL);
			logger.log(LogStatus.PASS, "Navigated to URL - "+ Constants.URL);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to Navigate to URL - "+ Constants.URL);
			DriverScriptTest.bResult = false;
		}
			
	}
	
	public static void input(String object, String data){
		try{
			Thread.sleep(5000);
        	String itemslocatory = OR.getProperty(object);
        	String[] split = itemslocatory.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			System.out.println(locatorType);
			System.out.println(locatorValue);
			WebElement element = findMyElement(locatorType,locatorValue);
		    			element.sendKeys(data);
			
			logger.log(LogStatus.PASS, "Entered the text in "+ object);
		}catch(Exception e){
			Log.error("Not able to Enter the text --- " + e.getMessage());
			DriverScriptTest.bResult = false;
			logger.log(LogStatus.FAIL, "Not able to enter text in "+ object);
		}
		 
	}
	
//	public static void input_Password(String object){
//		try{
//			Log.info("Entering the text in "+ object);
//			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.Password);
//		}catch(Exception e){
//			Log.error("Not able to Enter Password --- " + e.getMessage());
//			DriverScriptTest.bResult = false;
//		}
//		
//	}
	
	public static void click(String object, String data){
		try{
			String itemslocatory = OR.getProperty(object);
        	String[] split = itemslocatory.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			System.out.println(locatorType);
			System.out.println(locatorValue);
			WebElement element = findMyElement(locatorType,locatorValue);
		    			element.click();
			Log.info("Clicking on Webelement "+ object);
			
			logger.log(LogStatus.PASS, "Succefully Clicked on button"+ object);
		}catch(Exception e){
			Log.error("Not able to click --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to Click on button "+ object);
 			DriverScriptTest.bResult = false;
		}
		
	}
	
	public static void waitFor(String object, String data) throws Exception{
		try{
			Log.info("Wait for 5 seconds");
			Thread.sleep(2000);
			
		}catch(Exception e){
			Log.error("Not able to Wait --- " + e.getMessage());
			DriverScriptTest.bResult = false;
		}
		
	}

	public static  void  selectByValue(String object,String data){
		try {
			String itemslocatory = OR.getProperty(object);
        	String[] split = itemslocatory.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			WebElement element = findMyElement(locatorType,locatorValue);
			Select select = new  Select(element);
			select.selectByValue(data);
 			
			Log.info("Selecting the webelement "+ object);
			
			logger.log(LogStatus.PASS, "Succefully Selected item"+ object);
		}catch(Exception e){
			Log.error("Not able to Select --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to Click on button "+ object);
 			DriverScriptTest.bResult = false;
		}
		
	}
	
	public static  void  selectcheckorradio(String object, String data){
		try {
			String Value = "value";
			String itemslocatory = OR.getProperty(object);
        	String[] split = itemslocatory.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			List<WebElement> element = findMyElements(locatorType,locatorValue);
			findMyElements(locatorType,locatorValue).size();
			Log.info("size of elements " + findMyElements(locatorType,locatorValue).size());
			for(int i=0; i< findMyElements(locatorType,locatorValue).size(); i++) {
				if(findMyElements(locatorType,locatorValue).get(i).getAttribute(Value).equalsIgnoreCase(data)){
	    			  boolean isselected = findMyElements(locatorType,locatorValue).get(i).isSelected();
	    			  if (isselected == true)
	    				  findMyElements(locatorType,locatorValue).get(i).click();
	    			  else {
	    				  findMyElements(locatorType,locatorValue).get(i).click();
	    			  }
	    		   }
			}
			
			Log.info("Select the Check the  on Webelement "+ object);
			
			logger.log(LogStatus.PASS, "Succefully Selected  the Check or radio"+ object);
		}catch(Exception e){
			Log.error("Not able to click --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to Select by index "+ object);
 			DriverScriptTest.bResult = false;
		}
	}
	public static void Alertaccepts(String object, String data) throws Exception{
		try {
			Log.info("click on alert accept");
			Alert alert = driver.switchTo().alert();
			alert.accept();
			Log.info("Select the Check the  on Webelement "+ object);
			logger.log(LogStatus.PASS, "Succefully Clicked on Accept");
		}
		
		
		catch(Exception e){
			Log.error("Not able to click --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to Click on Alert Accept ");
 			DriverScriptTest.bResult = false;
		}
		
	}
	public static void Switchframes(String object,String data) throws Exception{	
		try {
			Log.info("Switching the Frame");
			driver.switchTo().frame(0);
			
			Log.info("Switching to frame: " );
			logger.log(LogStatus.PASS, "Succefully switched framr:");
		}
		
		
		catch(Exception e){
			Log.error("Not able to Switch --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to switch ");
 			DriverScriptTest.bResult = false;
		}
		
	}
	public static void defaultwindow(String object, String data) throws Exception{
		try {
			Log.info("Switching the main wndow");
			driver.switchTo().defaultContent();
			Log.info("Switching to main: " );
			logger.log(LogStatus.PASS, "Succefully switched main window:"+ data);
		}
		
		
		catch(Exception e){
			Log.error("Not able to Switch --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to switch ");
 			DriverScriptTest.bResult = false;
		}
		
	}
	public static void Alertdismiss() {
		driver.switchTo().alert().dismiss();
		
	}
			
	public static void closeBrowser(String object, String data){
		try{
			Log.info("Closing the Browser");
			driver.quit();
			logger.log(LogStatus.PASS, "Succefully closed the browser -"+ object);
		}catch(Exception e){
			Log.error("Not able to Close the Browser --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to close the browser -"+ object);
			DriverScriptTest.bResult = false;
		}
		
	}
	public static  WebElement findMyElement(String locatorType, String locatorValue) throws Exception {

		switch(locatorType.toLowerCase()) {
		//switch(locatorType.toLowerCase()) {
			case "id":
				return driver.findElement(By.id(locatorValue));
			case "name":
				return driver.findElement(By.name(locatorValue));
			case "xpath":
				return driver.findElement(By.xpath(locatorValue));
			case "css-select":
				return driver.findElement(By.cssSelector(locatorValue));
			case "linktext":
				return driver.findElement(By.linkText(locatorValue));
			case "partial_linktext":
				return driver.findElement(By.partialLinkText(locatorValue));
			case "TAG_NAME":
				return driver.findElement(By.tagName(locatorValue));
			case "class":
				return driver.findElement(By.className(locatorValue));
			default:
				throw new Exception("Unknown selector type " + locatorType);
		}
	}
	public static  List<WebElement> findMyElements(String locatorType, String locatorValue) throws Exception {

		switch(locatorType.toLowerCase()) {
		//switch(locatorType.toLowerCase()) {
			case "id":
				return driver.findElements(By.id(locatorValue));
			case "name":
				return driver.findElements(By.name(locatorValue));
			case "xpath":
				return driver.findElements(By.xpath(locatorValue));
			case "css-select":
				return driver.findElements(By.cssSelector(locatorValue));
			case "linktext":
				return driver.findElements(By.linkText(locatorValue));
			case "partial_linktext":
				return driver.findElements(By.partialLinkText(locatorValue));
			case "TAG_NAME":
				return driver.findElements(By.tagName(locatorValue));
			case "class":
				return driver.findElements(By.className(locatorValue));
			default:
				throw new Exception("Unknown selector type " + locatorType);
		}
	}
	public static String GetText(String object, String data){
		try{
			Log.info("Getting the text of '"+object+"'");
			String Text = driver.findElement(By.xpath(OR.getProperty(object))).getText();
			return Text;
		}catch (Exception e){
			Log.error("Not able read the text --- " + e.getMessage());
			DriverScriptTest.bResult = false;
			return null;
		}
		
	}
	
	public static void compareGetText(String object, String data){
		try{
			Log.info("Comparing the text '" +data+ "' with '"+object+"'" );
			String acutalText = driver.findElement(By.xpath(object)).getText();
			Assert.assertEquals(data, acutalText);
		}catch(Exception e){
			Log.error("Not able to compare the text --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to compare the text"+ object);
			DriverScriptTest.bResult = false;
		}
		
	}
	
}
	

