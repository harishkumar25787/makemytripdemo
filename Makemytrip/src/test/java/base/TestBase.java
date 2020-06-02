package base;

import java.io.File;

//import static executionEngine.DriverScriptTest.OR;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.relevantcodes.extentreports.LogStatus;

import listener.CustomListeners;

import utilities.ExtentManager;

//import executionEngine.DriverScriptTest;
import io.github.bonigarcia.wdm.WebDriverManager;
//import utility.Log;


public class TestBase {
	//again test2
	/*
	 * WebDriver - done Properties - done Logs - log4j jar, .log,
	 * log4j.properties, Logger ExtentReports DB Excel Mail ReportNG,
	 * ExtentReports Jenkins
	 * 
	 */
	//suite.xlsx
	


	// current test suite
	
	public static    WebDriver driver = null;
	
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static boolean pass=false;
	public static boolean fail=false;
	public static boolean skip=false;
	public static boolean isTestPass=true;
	
	
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	public static WebDriverWait wait;

	public static String browser;
	public static boolean setUp = false;
	
	@BeforeSuite
	  public    void setUp()   throws Exception{
		
		 

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			

			pass = false;
			fail = false;
			skip = false;
			
		}
		}

	

	
	public void beforeMethod(Method method){
		
		log.info("**************"+method.getName()+"Started***************");
	}
	public  void click(String object) throws Exception {
		try {
			
		
		String itemslocatory = OR.getProperty(object);
    	String[] split = itemslocatory.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		System.out.println(locatorType);
		System.out.println(locatorValue);
		WebElement element = findMyElement(locatorType,locatorValue);
	    			element.click();
		CustomListeners.testReport.get().log(Status.INFO, "Clicking on : " + itemslocatory);
		log.debug("Clicking on the object :"  + locatorValue );
	}catch(Exception e){
		log.error("Not able to click --- " + e.getMessage());
		log.debug(LogStatus.FAIL);
			
		
	}
		
	}
	  public void openBrowser(){
			 
	        // Chrome Driver Path
		  
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
			}else{
				
				browser = config.getProperty("browser");
				
			}
			
			config.setProperty("browser", browser);
			
			
			

			if (config.getProperty("browser").equals("firefox")) {

				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				/*System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			*/	
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!!");
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);

	    }
	  public void navigate(String object,String data){
	        log.debug("Navigating to URL");
	        try{
	            driver.navigate().to(data);
	        }catch(Exception e){
	            return;
	        }
	        
	    }
	  
	public  void input(String object,String data) {
		try{
		

			String itemslocatory = OR.getProperty(object);
	    	String[] split = itemslocatory.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			System.out.println(locatorType);
			System.out.println(locatorValue);
			WebElement element = findMyElement(locatorType,locatorValue);
		    			element.sendKeys(data);
		    CustomListeners.testReport.get().
		    log(Status.INFO, "Typing in : " + itemslocatory + " entered value as " + data);
		    log.debug("Inputs  on the object :"  + locatorValue );
			
		}catch(Exception e){
			
		}
		

		

	}
	public static  void  selectByText(String object,String data){
		try {
			String itemslocatory = OR.getProperty(object);
        	String[] split = itemslocatory.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			WebElement element = findMyElement(locatorType,locatorValue);
			Select select = new  Select(element);
			select.selectByVisibleText(data);
 			
		
		}catch(Exception e){
			
		}
		
	}
	
	public  void inputs(String object,String data) {
		try{
		

			String itemslocatory = OR.getProperty(object);
	    	String[] split = itemslocatory.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			System.out.println(locatorType);
			System.out.println(locatorValue);
			List<WebElement> element = findMyElements(locatorType,locatorValue);
			for(int i =0;i<element.size();i++)
			{
				if(element.get(i).getText().startsWith("data"))
				{
					element.get(i).click();
					break;
				}
			}
		    CustomListeners.testReport.get().
		    log(Status.INFO, "Typing in : " + itemslocatory + " entered value as " + data);
		    log.debug("Inputs  on the object :"  + locatorValue );
			
		}catch(Exception e){
			
		}
		

		

	}
	public static   WebElement findMyElement(String locatorType, String locatorValue) throws Exception {
		
		if (locatorType.contains("id"))
		 {
		//switch(locatorType.toLowerCase()) {
			return driver.findElement(By.id(locatorValue));
		 }
		else if (locatorType.contains("xpath"))
		 {
		//switch(locatorType.toLowerCase()) {
			return driver.findElement(By.xpath(locatorValue));
		 }
		else if (locatorType.contains("name"))
		 {
		//switch(locatorType.toLowerCase()) {
			return driver.findElement(By.name(locatorValue));
		 }
		else if (locatorType.contains("linktext"))
		 {
		//switch(locatorType.toLowerCase()) {
			return driver.findElement(By.linkText(locatorValue));
		 }
		CustomListeners.testReport.get().log(Status.INFO, "Typing in : " + locatorType + " entered value as " + locatorValue);
		return null;
	}
	
	public   List<WebElement> findMyElements(String locatorType, String locatorValue) throws Exception {

		if (locatorType.contains("id"))
		 {
		//switch(locatorType.toLowerCase()) {
			return driver.findElements(By.id(locatorValue));
		 }
		else if (locatorType.contains("xpath"))
		 {
		//switch(locatorType.toLowerCase()) {
			return driver.findElements(By.xpath(locatorValue));
		 }
		else if (locatorType.contains("name"))
		 {
		//switch(locatorType.toLowerCase()) {
			return driver.findElements(By.name(locatorValue));
		 }
		else if (locatorType.contains("linktext"))
		 {
		//switch(locatorType.toLowerCase()) {
			return driver.findElements(By.linkText(locatorValue));
		 }
		CustomListeners.testReport.get().log(Status.INFO, "Typing in : " + locatorType + " entered value as " + locatorValue);
		return null;
	}
	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		CustomListeners.testReport.get().log(Status.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}

	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}
	@AfterSuite
	public  void closeBrowser(){
		try{
			log.info("Closing the Browser");
			driver.quit();
			
		}catch(Exception e){
			log.error("Not able to Close the Browser --- " + e.getMessage());
			
			
		}
		
	}
	

		
	

	
}
