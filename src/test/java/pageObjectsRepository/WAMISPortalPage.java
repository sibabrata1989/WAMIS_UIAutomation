package pageObjectsRepository;


import org.testng.*;

import java.util.Iterator;
import java.util.Set;

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
	
	//Navigation Screen
	String linkWorkPanel = "//h6[contains(text(),'Works')]/../..//*[@class='list-group']/li/a[contains(text(),'%s')]";
	String linkWorkType = "//div[@id='Masters1']//li//b[contains(text(),'%s')]/..";
	By lblWorksTypeHeader = By.xpath("//*[@class='panel-heading'][contains(text(),'Work Types')]");
	By linkWorkInfo = By.xpath("//div[@id='Proposal_Work2']//li//b[contains(text(),'Work Info')]/..");
	String linkAAandTSDetails = "//div[@id='Approvals3']//li//b[contains(text(),'%s')]/..";
	
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
	
	//Add Proposal
	By txtWorkDesc = By.xpath("//textarea[contains(@name,'workDesc')");
	By selectProposalType = By.xpath("//select[@name='proposerId']");
	By selectProposalDept= By.xpath("//select[@name='proposerDepartmentId']");
	By selectScheme = By.xpath("//select[@name='schemeId']");
	By txtExtimatedCost = By.xpath("//input[@name='estimatedCost']");
	By selectMajorHead = By.xpath("//select[@id='majorHeadId']");
	By selectSubMajorHead = By.xpath("//select[@id='subMajorHeadId']");
	By selectMinorHead= By.xpath("//select[@id='minorHeadId']");
	By selectsubMinorHead = By.xpath("//select[@id='subMinorHeadId']");
	By selectDetailsHead = By.xpath("//select[@id='detailHeadId'");
	By selectObjectHead = By.xpath("//select[@id='objectHeadId']");
	By selectBudgetMonth= By.xpath("//select[@name='budgetMonth']");
	By selectBudgetYear = By.xpath("//select[@name='budgetYear']");
	By selectWorkType = By.xpath("//select[@name='proposalTypeId']");
	By selectProposalSubtype = By.xpath("//select[@name='proposalSubTypeId']");
	By btnProposalSave = By.xpath("//input[contains(@onclick,'save')]");
	String dropdownOption = "//option[@data-content='%s']";
	
	//deposit proposal
	By btnAddproposalWork = By.xpath("//*[@name='WorkInfoForm']//input[@value='Add']");
	By rbtnDepositeWorkcategory = By.xpath("//input[@id='workType2']");
	
	//AA & TS Details
	By btnParentAANumber= By.xpath("//a[@class='btn-link']//span[@class='glyphicon glyphicon-search']");
	By rbtnWorkButton = By.xpath("//input[@name='selectedWorkNumber']");
	By selectApprovingAuthority = By.xpath("//select[@name='aaAuthority']");
	By selectApprovingAmount = By.xpath("//input[@name='aaAmount']");
	By selectTSAuthoriy = By.xpath("//select[@name='tsAuthority']");
	By selectTSSrYear = By.xpath("//select[@name='tsSrYear']");
	By selectZoneId = By.xpath("//select[@name='zoneId']");
	By txtTSAmount = By.xpath("//input[@name='tsAmount']");
	
	//Logout
	By btnWelcome = By.xpath("//button[@class='btn btn-sm btn-info dropdown-toggle']");
	By btnLogout = By.xpath("//a[@href='/wamis/logout.do']");
	
	
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
	
	public void logout(ExcelHelper objExcel,SoftAssert softassert) throws Exception
	{
		
		try
		{
			driver.findElement(btnWelcome).click();
			driver.findElement(btnLogout).click();
		}

		catch(Exception e)
		{
			BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
			e.printStackTrace();
			Assert.fail("Logout Failed due to exception!");
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
	public void proposalScreenNavigation(ExcelHelper objExcel,SoftAssert softassert, String panel) throws Exception
	{
		
		try
		{
			driver.findElement(By.xpath(String.format(linkWorkPanel, panel))).click();
			driver.findElement(linkWorkInfo).click();
		}

		catch(Exception e)
		{
			BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
			e.printStackTrace();
			Assert.fail("Proposal screen navigation Failed due to exception!");
		}
	}
	

	public void approvalScreensNavigation(ExcelHelper objExcel,SoftAssert softassert, String panel, String link) throws Exception
	{
		
		try
		{
			driver.findElement(By.xpath(String.format(linkWorkPanel, panel))).click();
			driver.findElement(By.xpath(String.format(linkAAandTSDetails, link))).click();
		}

		catch(Exception e)
		{
			BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
			e.printStackTrace();
			Assert.fail("Approval screen navigation Failed due to exception!");
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
	public void addProposalRegularWork(ExcelHelper objExcel,SoftAssert softassert) throws Exception
	{
			
			try
			{
				driver.findElement(txtWorkDesc).sendKeys(objExcel.GetValue(0, "workDesc"));
				LibraryFunctions.selectDropDownValue(driver, selectProposalType, objExcel.GetValue(0, "proposalType"));
				LibraryFunctions.selectDropDownValue(driver, selectProposalDept, objExcel.GetValue(0, "proposalDept"));
				LibraryFunctions.selectDropDownValue(driver, selectScheme, objExcel.GetValue(0, "scheme"));
				driver.findElement(txtExtimatedCost).sendKeys(objExcel.GetValue(0, "estimatedCost"));
				LibraryFunctions.selectDropDownValue(driver, selectMajorHead, objExcel.GetValue(0, "majorHead"));
				LibraryFunctions.selectDropDownValue(driver, selectSubMajorHead, objExcel.GetValue(0, "subMajorHead"));
				LibraryFunctions.selectDropDownValue(driver, selectMinorHead, objExcel.GetValue(0, "minorHead"));
				LibraryFunctions.selectDropDownValue(driver, selectsubMinorHead, objExcel.GetValue(0, "subMinorHead"));
				LibraryFunctions.selectDropDownValue(driver, selectDetailsHead, objExcel.GetValue(0, "detailsHead"));
				LibraryFunctions.selectDropDownValue(driver, selectObjectHead, objExcel.GetValue(0, "objectHead"));
				LibraryFunctions.selectDropDownValue(driver, selectBudgetMonth, objExcel.GetValue(0, "budgetMonth"));
				LibraryFunctions.selectDropDownValue(driver, selectBudgetYear, objExcel.GetValue(0, "budgetYear"));
				LibraryFunctions.selectDropDownValue(driver, selectWorkType, objExcel.GetValue(0, "workType"));
				LibraryFunctions.selectDropDownValue(driver, selectProposalSubtype, objExcel.GetValue(0, "proposalSubType"));
				driver.findElement(btnProposalSave).click();
				LibraryFunctions.verifyMessage(driver, "Record Saved Successfully");
				
			}

			catch(Exception e)
			{
				BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
				e.printStackTrace();
				Assert.fail("Add WorkType Failed due to exception!");
			}
		
		}
	public void addProposalDepositWork(ExcelHelper objExcel,SoftAssert softassert) throws Exception
	{
			
			try
			{
				
				driver.findElement(btnAddproposalWork).click();
				driver.findElement(rbtnDepositeWorkcategory).click();
				
				LibraryFunctions.selectDropDownValue(driver, selectProposalType, objExcel.GetValue(0, "proposalType"));
				LibraryFunctions.selectDropDownValue(driver, selectProposalDept, objExcel.GetValue(0, "proposalDept"));
				LibraryFunctions.selectDropDownValue(driver, selectScheme, objExcel.GetValue(0, "scheme"));
				driver.findElement(txtExtimatedCost).sendKeys(objExcel.GetValue(0, "estimatedCost"));
				
				LibraryFunctions.selectDropDownValue(driver, selectBudgetMonth, objExcel.GetValue(0, "budgetMonth"));
				LibraryFunctions.selectDropDownValue(driver, selectBudgetYear, objExcel.GetValue(0, "budgetYear"));
				LibraryFunctions.selectDropDownValue(driver, selectWorkType, objExcel.GetValue(0, "workType"));
				LibraryFunctions.selectDropDownValue(driver, selectProposalSubtype, objExcel.GetValue(0, "proposalSubType"));
				driver.findElement(btnProposalSave).click();
				LibraryFunctions.verifyMessage(driver, "Record Saved Successfully");
				
			}

			catch(Exception e)
			{
				BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
				e.printStackTrace();
				Assert.fail("Add WorkType Failed due to exception!");
			}
		
		}
	
	public void addAADetails(ExcelHelper objExcel,SoftAssert softassert) throws Exception
	{
			
			try
			{
				
				driver.findElement(btnParentAANumber).click();
				 String MainWindow=driver.getWindowHandle();		
	        		
			        // To handle all new opened window.				
			        Set<String> s1=driver.getWindowHandles();		
			        Iterator<String> i1=s1.iterator();		
			        		
			        while(i1.hasNext())			
			        {		
			            String ChildWindow=i1.next();		
			            		
			            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
			            {    		
			                 
			                    // Switching to Child window
			                    driver.switchTo().window(ChildWindow);	                                                                                                           
			                    driver.findElement(rbtnWorkButton).click();     			
			                    
			            }		
			        }		
			        // Switching to Parent window i.e Main Window.
			            driver.switchTo().window(MainWindow);
				
				LibraryFunctions.selectDropDownValue(driver, selectApprovingAuthority, objExcel.GetValue(0, "approvingAuthority"));
				LibraryFunctions.selectDropDownValue(driver, selectApprovingAmount, objExcel.GetValue(0, "approvingAmount"));
				driver.findElement(btnSave).click();
				LibraryFunctions.verifyMessage(driver, "Record Saved Successfully");
				
			}

			catch(Exception e)
			{
				BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
				e.printStackTrace();
				Assert.fail("Add AA Approvals Failed due to exception!");
			}
		
		}
	
	public void addTSDetails(ExcelHelper objExcel,SoftAssert softassert) throws Exception
	{
			
		try
		{
			
			driver.findElement(btnParentAANumber).click();
			 String MainWindow=driver.getWindowHandle();		
        		
		        // To handle all new opened window.				
		        Set<String> s1=driver.getWindowHandles();		
		        Iterator<String> i1=s1.iterator();		
		        		
		        while(i1.hasNext())			
		        {		
		            String ChildWindow=i1.next();		
		            		
		            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
		            {    		
		                 
		                    // Switching to Child window
		                    driver.switchTo().window(ChildWindow);	                                                                                                           
		                    driver.findElement(rbtnWorkButton).click();     			
		                    
		            }		
		        }		
		        // Switching to Parent window i.e Main Window.
		            driver.switchTo().window(MainWindow);
			
			LibraryFunctions.selectDropDownValue(driver, selectTSAuthoriy, objExcel.GetValue(0, "tsAuthority"));
			LibraryFunctions.selectDropDownValue(driver, selectTSSrYear, objExcel.GetValue(0, "tsYear"));
			LibraryFunctions.selectDropDownValue(driver, selectZoneId, objExcel.GetValue(0, "zoneID"));
			LibraryFunctions.selectDropDownValue(driver, txtTSAmount, objExcel.GetValue(0, "tsAmount"));
			driver.findElement(btnSave).click();
			LibraryFunctions.verifyMessage(driver, "Record Saved Successfully");
			
		}

		catch(Exception e)
		{
			BrowserHelper.SaveScreenshot(objExcel.GetValue(0, "TestCaseName"), driver);
			e.printStackTrace();
			Assert.fail("Add AA Approvals Failed due to exception!");
		}
	
	}
	}

