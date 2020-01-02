package common;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.*;
public interface LibraryFunctions {
	
	public static void selectDropDownValue(WebDriver driver, WebElement dropDwnElement,String value)
	{
		Select degreeDropDown = new Select(dropDwnElement);
		degreeDropDown.selectByVisibleText(value);
		
	}
	public static void enterDOB(WebDriver driver, String date) throws Exception
	{
		String month,day,year;
		String[] dateSplit = date.split("/");
		day =dateSplit[0];
		month =dateSplit[1];
		year =dateSplit[2];
		WebElement monthElement = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		Select monthSelect = new Select(monthElement);	
		monthSelect.selectByVisibleText(month);		
		
		WebElement yearElement = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
		Select yearSelect = new Select(yearElement);	
		yearSelect.selectByVisibleText(year);
		
		driver.findElement(By.xpath("(//*[@class='ui-state-default'])["+day+"]")).click();
		Thread.sleep(2000);
	
	}
	public static void popUpHandleOk(WebDriver driver)
	{
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
	}
	public static void verifyMessage(WebDriver driver, String expectedMsg)
	{
		String msg = driver.findElement(By.xpath("//*[@class='alert alert-success']//li")).getText();
		Assert.assertEquals(msg, expectedMsg, "The expected message doesn't match!");
	}


}
