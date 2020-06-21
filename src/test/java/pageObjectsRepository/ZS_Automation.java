package pageObjectsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import common.BrowserHelper;
import common.ExcelHelper;
import common.LibraryFunctions;

public class ZS_Automation implements LibraryFunctions {
    WebDriver driver;
    public ZS_Automation(WebDriver driver)
    {
        this.driver = driver;
    }

    //ingestion tab
    public void clickIngestion()
    {
        driver.findElement(By.xpath("")).click();
    }

   //Search Configurations
    public void searchConfiguration(String configuration)
     {
         driver.findElement(By.xpath("")).sendKeys(configuration);
         driver.findElement(By.xpath("")).click();
         WrapperFunctionUtilities.waitForTime(3000);
     }

     // Actions
    public void action(String action)
    {
        driver.findElement(By.xpath(action)).click();
        WrapperFunctionUtilities.waitForTime(3000);
    }

    //verify job status
    public void verifyJobStatus(String process, String objectName, String status) throws IOException, InterruptedException {
        String testData = LibraryFunctions.getTestData("src/Resources/TestData.json",process);
        JsonPath js= LibraryFunctions.Raw_to_Json(testData);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<String, String>();
        // convert JSON string to Map
        map = mapper.readValue(testData, new TypeReference<Map<String, Object>>() {});
        for (int i = 1; i <= map.keySet().size(); i++) {
            String object = driver.findElement(By.xpath("//div[@class='moduleJobs']//tr["+i+"]//td[2]")).getText();
            if(object.equalsIgnoreCase(objectName))
            {
                String processName = driver.findElement(By.xpath("//div[@class='moduleJobs']//tbody//tr["+i+"]//td[1]")).getText();
                for (String temp :map.keySet()) {
                    //  Assert.assertEquals(processName,temp,"The process name not found!");
                    Assert.assertTrue(Arrays.asList(map.keySet()).contains(processName),"The process name not found!");
                    String actualStatus = driver.findElement(By.xpath("//tr["+i+"]//td[9]//span[1]")).getAttribute("title");
                    if(!actualStatus.equalsIgnoreCase(status))
                    {
                        //Wait wait = new FluentWait<>(driver).withTimeout(3, TimeUnit.MINUTES).pollingEvery(10, TimeUnit.MINUTES).ignoring(NoSuchElementException.class);
                        String currentStatus = driver.findElement(By.xpath("//tr["+i+"]//td[9]//span[@title='COMPLETE']")).getText();
                        while(currentStatus!="COMPLETE")
                        {
                            Thread.sleep(70000);
                            driver.navigate().refresh();
                        }
                        String finalStatus = driver.findElement(By.xpath("//tr["+i+"]//td[9]//span[1]")).getAttribute("title");
                        if(finalStatus=="COMPLETED")
                        {
                            Assert.assertEquals(status.equalsIgnoreCase(finalStatus),"The final Status is not "+status);
                            driver.findElement(By.xpath("//tr["+i+"]//td[10]//a[1]")).click();
                            WrapperFunctionUtilities.waitForTime(2000);
                            String errorCount = driver.findElement(By.xpath("//span[@id='totalErrors']")).getText();
                            Assert.assertEquals(errorCount.equals("0"),"The "+processName+" have errors in log!");
                        }
                        else if(finalStatus=="FAILED")
                        {
                            Assert.assertEquals(status.equalsIgnoreCase(finalStatus),"The final Status is not "+status);
                            driver.findElement(By.xpath("//tr["+i+"]//td[10]//a[1]")).click();
                            WrapperFunctionUtilities.waitForTime(2000);
                            int errorCount = Integer.parseInt(driver.findElement(By.xpath("//span[@id='totalErrors']")).getText());
                            Assert.assertTrue(errorCount>0,"The "+processName+" have errors in log!");
                        }
                        else if (finalStatus=="CANCELLED")
                        {
                            Assert.assertEquals(status.equalsIgnoreCase(finalStatus),"The final Status is not "+status);
                        }
                        else if (finalStatus=="SUBMITTED")
                        {
                            Assert.assertEquals(status.equalsIgnoreCase("CANCELLED"),"The final Status is not "+status);
                        }

                        else if (finalStatus=="UNKNOWN")
                        {
                            Assert.assertEquals(status.equalsIgnoreCase("CANCELLED"),"The final Status is not "+status);
                        }
                    }
                }


            }
        }
    }

    //Method 2*****************************************************
    public void navigateConfiguration()
    {
        tabConfiguration.click();
        WrapperFunctionUtilities.waitForTime(2000);
    }

    public void stopJobs()
    {
        rbtnAllJobs.click();
        btnStop.click();
        WrapperFunctionUtilities.waitForTime(2000);

    }


}
