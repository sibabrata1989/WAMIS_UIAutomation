package pageObjectsRepository;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import common.BrowserHelper;
import common.ExcelHelper;
import common.LibraryFunctions;

public class CETSignUpPage implements LibraryFunctions
{
	WebDriver driver;
	By byDegree = By.id("ddlgraduation_id");
	By byMobileNumber = By.id("stdfrm_login_id");
	By byEmail = By.id("stdfrm_email");
	By byFirstName = By.id("stdfrm_firstname");
	By byLastName = By.id("stdfrm_lastname");
	By byAdharNo = By.id("stdfrm_aadhaar");
	By byPassword = By.id("stdfrm_password");
	By byConfirmPassword = By.id("stdfrm_confirmPwd");
	By bysendOtp = By.id("sendotp");
	By byNotNRIStudent= By.xpath("//*[@id='stdfrm_is_NRI' and @value='N']");
	By byDOB = By.id("txtDate");
	By byGender = By.xpath("//*[@id='stdfrm_gender' and @value='F']");
	By byRegister = By.className("btnRegister");
	 
	
	public CETSignUpPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public void studentRegistration(ExcelHelper objExcel, int iteration, SoftAssert softassert) throws Exception
	{
		for (int i = 0; i < iteration; i++) {
			WebDriverWait wait = new WebDriverWait(driver,10);
		driver.navigate().to("http://14.143.45.237:8122/Student/Registration");
		Thread.sleep(5);
		try{
			driver.findElement(byDegree).click();
			WebElement degreeDrpDown = driver.findElement(byDegree);
			LibraryFunctions.selectDropDownValue(driver,degreeDrpDown,objExcel.GetValue(i, "degree"));
			driver.findElement(byNotNRIStudent).click();
			driver.findElement(byMobileNumber).sendKeys(objExcel.GetValue(i, "mobileNum"));
			driver.findElement(byEmail).sendKeys(objExcel.GetValue(i, "email"));
			driver.findElement(byFirstName).sendKeys(objExcel.GetValue(i, "firstName"));
			driver.findElement(byLastName).sendKeys(objExcel.GetValue(i, "lastName"));
			driver.findElement(byPassword).sendKeys(objExcel.GetValue(i, "password"));
			driver.findElement(byConfirmPassword).sendKeys(objExcel.GetValue(i, "confirmPassword"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(byGender));
			driver.findElement(byGender).click();
			driver.findElement(byDOB).click();
			LibraryFunctions.enterDOB(driver, objExcel.GetValue(i, "DOB"));
			driver.findElement(bysendOtp).click();
			LibraryFunctions.popUpHandleOk(driver);
			Thread.sleep(3);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			driver.findElement(byRegister).click();
			LibraryFunctions.popUpHandleOk(driver);
			Thread.sleep(5);
			softassert.assertEquals(driver.getTitle(), "SAAR Application", "The header title mismatch!");
			
			}

		catch(Exception e)
		{
			BrowserHelper.SaveScreenshot(objExcel.GetValue(i, "TestCaseName"), driver);
			e.printStackTrace();
			Assert.fail("Student Registration Failed due to exception!");
		}
		
		}
	}
}
