package pageObjectsRepository;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.*;

import java.util.Iterator;
import java.util.Set;
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
    public void verifyJobStatus(String objectName, String status)
    {
        String[] process = {"FILE_CLEAN_UP","DATA_TRANSFER","FILE_CHECK","DATA_INGESTION","FILE_ARCHIVAL"};
        for (int i = 0; i < process.length; i++) {
            String object = driver.findElement(By.xpath("+i+")).getText();
            if(object.equalsIgnoreCase(objectName))
            {
                String processName = driver.findElement(By.xpath("+i+")).getText();
                for (String temp :process) {
                    Assert.assertEquals(processName,temp,"The process name not found!");
                    String actualStatus = driver.findElement(By.xpath("")).getText();
                    if(!actualStatus.equalsIgnoreCase(status))
                    {
                        Wait wait = new FluentWait<>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
                        wait.until(ExpectedConditions.textToBe(By.xpath(""),""));
                        driver.findElement(By.xpath("logs")).click();
                        String errorCount = driver.findElement(By.xpath("")).getText();
                        WrapperFunctionUtilities.waitForTime(2000);
                        Assert.assertEquals(errorCount.equals("0"),"The "+processName+" have errors in log!");

                    }
                }


            }
        }
    }



}
