package config;

public class Constants {

	
	public static final String URL = "https://learn.letskodeit.com/";
	
	public static final String Path_TestData = "C://Users//hsholapur//eclipse-workspace_newversion//Testng_Maven//src//test//java//DataEngine//DataEngine.xlsx";
	
	public static final String Path_OR = "//src//test//java//config//OR.properties";
	public static final String File_TestData = "DataEngine.xlsx";

	
	//List of Data Sheet Column Numbers
	public static final int Col_TestCaseID = 0;	
	public static final int Col_TestScenarioID =1 ;
	//This is the new column for 'Page Object'
	public static final int Col_PageObject =3 ;
	//This column is shifted from 3 to 4
	public static final int Col_ActionKeyword =4;
	//New entry in Constant variable
	public static final int Col_RunMode =2 ;
	
	//New entry for results column from both the sheets
	public static final int Col_Result =3 ;
	public static final int Col_DataSet =5 ;
	public static final int Col_TestStepResult =6 ;

	//List of Data Engine Excel sheets
	public static final String Sheet_TestSteps = "Test Steps";
	//New entry in Constant variable
	public static final String Sheet_TestCases = "Test Cases";
	
	//Create two new Constants variables for the Pass results & Fail result
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	
	// List of Test Data
		public static final String UserName = "ravikaanthe@rediffmail.com";
		public static final String Password = "test@123";
		
		public static final String UserName1 = "user@phptravels.com";
		public static final String Password1 = "demouser";
			
}
