package QKART_TESTNG;

import java.io.File;
import java.sql.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener{

    public static void takeScreenshot(WebDriver driver,String screenshotType,String description){
        try{
            File theDir=new File("/screenshots");
            if(!theDir.exists()){
                theDir.mkdirs();
            }
            String timestamp = String.valueOf(java.time.LocalDateTime.now());
            String fileName =String.format("screenshot_%s_%s_%s.png",timestamp,screenshotType);
            TakesScreenshot scrShot =((TakesScreenshot)driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile=new File("screenshots/"+ fileName);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

        
    
    public void onStart(ITestContext context){
        System.out.println("onStart method started");
    }
    
    public void onTestFinish(ITestResult result){
        System.out.println("onFinish method started");
    }
    public void onTestStart(ITestResult result){
        System.out.println("New Test started"+result.getName());
        //String screenshotType;
       // TakesScreenshot(QKART_Tests.driver,screenshotType:"Test Start",result.getName());
        
        takeScreenshot(QKART_Tests.driver, "Test Start", result.getName());
    }
    
    public void onTestSuccess(ITestResult result){
        System.out.println("onTestSuccess Method"+result.getName());
        takeScreenshot(QKART_Tests.driver,"Test Start",result.getName());
    }

    public void onTestFailure(ITestResult result){
        System.out.println("onTestFailure method started"+result.getName());
        takeScreenshot(QKART_Tests.driver,"Test Start",result.getName());
    }

    public void onTestSkipped(ITestResult result){
        System.out.println("onTestSkipped method started"+result.getName());
    }
}

