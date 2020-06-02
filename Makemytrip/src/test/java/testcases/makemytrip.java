package testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class makemytrip extends TestBase {
	
	@Test
	public void maketrip() throws Exception{
		
		
		openBrowser();
		
		click("fromcityid");
		input("inputTxt_fromCity","Hyderabad");
		selectByText("selectfrom","Hyderabad, India");
		click("tocity");
		input("inputTxt_toCity","Mumbai");
		selectByText("selectfrom","Mumbai, India");
		click("");
		click("Search.but");
		
		
		
			
	
			Thread.sleep(5000);
		}
	
		
		
}
