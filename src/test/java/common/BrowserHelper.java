package common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

//Method1 : For choosing the browser type
public class BrowserHelper
{
	public static WebDriver LaunchBrowser(WebDriver driver)
	{
		if (driver == null)
		switch(TestConfig.browserEnv)
		
		{
		case"chrome32":
			System.setProperty("webdriver.chrome.driver", TestConfig.driverPath);
			 driver = new ChromeDriver();
			 break;
			 
		case"firefox32":
		case"firefox64":
			System.setProperty("webdriver.gecko.driver", TestConfig.driverPath);
			 driver = new FirefoxDriver();
			 break;
			 
		case"ie32":
		case"ie64":
			System.setProperty("webdriver.ie.driver", TestConfig.driverPath);
			 driver = new InternetExplorerDriver();
			 break;
			 
		default:
			System.out.println("Invalid browser Env");
		}
		return driver;
	}
	
	//Method 2 : Screenshot saving method
	public static void SaveScreenshot(String tcName, WebDriver driver)
	{
		try
		{
			//import java.nio.file.Files;
			Files.createDirectories(Paths.get(TestConfig.testRunDir + tcName));
			String ssFileName = TestConfig.testRunDir + tcName + "/" + new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date()) + ".jpg";
			File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(ss, new File(ssFileName));
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
