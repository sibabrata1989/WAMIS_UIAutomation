package common;

import java.io.FileInputStream;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestConfig
{
	public static String baseDir, testDataDir, testConfigDir, testResultDir, testResourcesDir,testRunDir;
	public static  String browserEnv, driverPath;
	public static  String SCETUrl;
	
	public static void SetCommonEnv()
	{
		try
		{
		baseDir = System.getProperty("user.dir");
		testDataDir = baseDir + "/../TestData/";
		testConfigDir = baseDir + "/../TestConfig/";
		testResultDir = baseDir + "/../TestResults/";
		testResourcesDir = baseDir + "/../TestResources/";
		
		//To create a folder name  like "Run_20170822_183800"
		testRunDir = testResultDir + "Run_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "/";
		
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(testConfigDir + "testConfig.properties");
		props.load(fis);
		fis.close();
		
		browserEnv = props.getProperty("browserEnv"); // Load the Browser type as given in the property file
		driverPath = testResourcesDir + props.getProperty("driverPath_" + browserEnv );// picks the driver browser key from the property file.
		SCETUrl = props.getProperty("SCETUrl");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
}
