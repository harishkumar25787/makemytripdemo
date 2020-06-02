package com.lets.test;
import com.lets.test.DriverScript;
public class Wait extends  DriverScript  {


public Wait() throws NoSuchMethodException, SecurityException {
		super();
		// TODO Auto-generated constructor stub
	}

private static String explicitWait;
	
	private static String implicitWait;
	
	private static String pageloadTime;
	
	
	public static String getExplicitWait() {
		return OR.getProperty("explicitWait");
	}

	public static String getImplicitWait() {
		return OR.getProperty("implicitWait");
	}

	public static String getPageloadTime() {
		return OR.getProperty("pageloadTime");
	}


}

