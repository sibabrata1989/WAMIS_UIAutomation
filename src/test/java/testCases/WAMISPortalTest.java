package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.BrowserHelper;
import common.ExcelHelper;
import common.TestConfig;
import pageObjectsRepository.*;

public class WAMISPortalTest
{
	WebDriver driver = null;
	ExcelHelper objExcel = null;
	String tcName = null;
	SoftAssert softassertion;
	
	
	@BeforeClass
	public void classSetup()
	{
		objExcel = new ExcelHelper();
		objExcel.SetListHeader(TestConfig.testDataDir + "TestData_CET.xlsx", 0);
		driver = BrowserHelper.LaunchBrowser(driver);
		driver.get((TestConfig.SCETUrl));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	}

	@BeforeTest
	public void TestSetUp()
	{
		TestConfig.SetCommonEnv();
		softassertion = new SoftAssert();
	}
	
	@Test
	public void verifyWorkTypes() throws Exception
	{
		try{
		tcName = "TC01_WorkTypes";
		System.out.println(tcName);
		objExcel.SetListData(TestConfig.testDataDir + "TestData_CET.xlsx", tcName);
		WAMISPortalPage portal = new WAMISPortalPage(driver);
		portal.loginWAMIS(objExcel, softassertion);
		portal.homeScreenNavigation(objExcel, softassertion, "Masters", "Work Types");
		portal.addWorkTypes(objExcel, softassertion);
		portal.updateWorkTypes(objExcel, softassertion);
		portal.deleteWorkTypes(objExcel, softassertion);
		softassertion.assertAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BrowserHelper.SaveScreenshot(tcName, driver);
		}
	}

	
	@AfterMethod
	public void MethodCleanUp()
	{
		objExcel.ClearDataList();
		
	}
	@AfterClass
	public void CloseDriver()
	{
		//driver.close();
	}
	
}
