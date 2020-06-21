package common;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileReader;
public interface LibraryFunctions {
	
	public static void selectDropDownValue(WebDriver driver, By xpath ,String value)
	{
		WebElement dropDown = driver.findElement(xpath);
		Select degreeDropDown = new Select(dropDown);
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


	//Pulling Data from JSON datasheet
	static String getTestData(String path, String index) {

		JSONParser parser=new JSONParser();
		JSONObject data=new JSONObject();
		try {
			JSONObject fileData = (JSONObject)parser.parse(new FileReader(path));
			data=(JSONObject) fileData.get(index);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return data.toJSONString();
	}
	public static JsonPath Raw_to_Json(String res)
	{

		JsonPath json = new JsonPath(res);
		return json;
	}

}
