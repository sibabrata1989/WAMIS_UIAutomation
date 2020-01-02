package pageObjectsRepository;


import org.testng.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import common.BrowserHelper;
import common.ExcelHelper;
import common.LibraryFunctions;

public class WAMISPortalPage implements LibraryFunctions
{
	WebDriver driver;
	
	public WAMISPortalPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	//login Screen
	By navbarLogin = By.xpath("(//*[@class='navbar-nav nav']//button)[1]");
	By txtUserName = By.xpath("//input[@id='userName']");
	By txtPassword = By.xpath("//input[@id='password']");
	By btnLogin = By.xpath("//button[contains(@class,'btn btn-success')][contains(text(),'Login')]");
	By lblHeader = By.xpath("//div[@class='header']");
	
	//Home Screen
	String linkWorkPanel = "//h6[contains(text(),'Works')]/../..//*[@class='list-group']/li/a[contains(text(),'%s')]";
	String linkWorkType = "//div[@id='Masters1']//li//b[contains(text(),'%s')]/..";
	By lblWorksTypeHeader = By.xpath("//*[@class='panel-heading'][contains(text(),'Work Types')]");
	
	//Add Work types
	By btnAdd = By.xpath("//input[@name='function'][@value='Add']");
	By txtWorkType = By.xpath("//input[@name='proposalName']");
	By txtWorkTypeCode= By.xpath("//input[@name='proposalCatId']");
	By rdbtnIsImp = By.xpath("//*[@name='impFlag'][@value='Y']");
	By btnSave = By.xpath("//*[@name='function'][@value='Save']");
	By lblMsg = By.xpath("//*[@class='alert alert-success']//li");
	
	//EditWorkType
	By btnEditWorktype = By.xpath("//tbody//td[text()='AAA']/../td[5]/a");
	By btnUpdate = By.xpath("//*[@name='function'][@value=Update]");
	
	//Delete WorkTypes
	By btnDeleteWorkType = By.xpath("//tbody//td[text()='AAA']/../td[6]/a");

	 

	
	public void loginWAMIS(ExcelHelper objExcel,SoftAssert softAssert) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver,10);
		driver.navigate().to("http://localhost:8080/wamis/login.do");
		Thread.sleep(5);
		try
		{
			driver.findElement(navbarLogin).click();
			driver.findElement(txtUserName).sendKeys(objExcel.GetValue(0, "userName"));
			driver.findElement(txtPassword).sendKeys(objExcel.GetValue(0, "password"));
			driver.findElement(btnLogin).click();
			String homeScreenHeader = driver.findElement(lblHeader).getText();
			softAssert.assertEquals(homeScreenHeader, "WAMIS, Government of Jharkhand");
			
		}

		catch(Exception e)
		{
			BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
			e.printStackTrace();
			Assert.fail("WAMIS Login Failed due to exception!");
		}
		
	}
		
	public void homeScreenNavigation(ExcelHelper objExcel,SoftAssert softassert, String panel, String masterPanel) throws Exception
	{
		
		try
		{
			driver.findElement(By.xpath(String.format(linkWorkPanel, panel))).click();
			driver.findElement(By.xpath(String.format(linkWorkType, masterPanel))).click();
			String worksHeader = driver.findElement(lblWorksTypeHeader).getText();
			softassert.assertEquals(worksHeader, "Work Types");
		}

		catch(Exception e)
		{
			BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
			e.printStackTrace();
			Assert.fail("Home screen navigation Failed due to exception!");
		}
	}
	
	public void addWorkTypes(ExcelHelper objExcel,SoftAssert softassert) throws Exception
	{
			
			try
			{
				driver.findElement(btnAdd).click();
				driver.findElement(txtWorkType).sendKeys(objExcel.GetValue(0, "workType"));
				driver.findElement(txtWorkTypeCode).sendKeys(objExcel.GetValue(0, "workTypeCode"));
				driver.findElement(rdbtnIsImp).click();
				driver.findElement(btnSave).click();
				LibraryFunctions.verifyMessage(driver, "Record Saved Successfully");
				
			}

			catch(Exception e)
			{
				BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
				e.printStackTrace();
				Assert.fail("Add WorkType Failed due to exception!");
			}
		
		}
	
	
	public void updateWorkTypes(ExcelHelper objExcel,SoftAssert softassert) throws Exception
	{
			
			try
			{
				driver.findElement(btnEditWorktype).click();
				driver.findElement(txtWorkType).sendKeys(objExcel.GetValue(0, "editWorkType"));
				driver.findElement(txtWorkTypeCode).sendKeys(objExcel.GetValue(0, "eidtWorkTypeCode"));
				driver.findElement(rdbtnIsImp).click();
				driver.findElement(btnUpdate).click();
				LibraryFunctions.verifyMessage(driver, "Record Updated Successfully");
				
			}

			catch(Exception e)
			{
				BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
				e.printStackTrace();
				Assert.fail("Update Work Type Failed due to exception!");
			}
		
		}
	public void deleteWorkTypes(ExcelHelper objExcel,SoftAssert softassert) throws Exception
	{
			
			try
			{
				driver.findElement(btnDeleteWorkType).click();
				LibraryFunctions.popUpHandleOk(driver);
				LibraryFunctions.verifyMessage(driver, "Record Deleted Successfully");			
			}

			catch(Exception e)
			{
				BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
				e.printStackTrace();
				Assert.fail("Worked Type deletion Failed due to exception!");
			}
		
		}
	}

