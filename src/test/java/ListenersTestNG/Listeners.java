package ListenersTestNG;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReportsNG;


public class Listeners implements ITestListener {
	ExtentReports Extents = ExtentReportsNG.getEXReports();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public WebDriver driver;
	
	public void onTestStart(ITestResult result) {
		// Extents Report
		test = Extents.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// Extents Report
		extentTest.get().log(Status.PASS, "TestCase Passed");
		//Capturing Screenshots
		Date cd = new Date();
		String Date1 = cd.toString().replace(" ", "-").replace(":", "-");
		String methodName = Date1 + result.getMethod().getMethodName() + "Pass";
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			
		}
		System.out.println(driver);
		System.out.println(methodName);
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(methodName, driver), result.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onTestFailure(ITestResult result) {
		//Extents Report
		extentTest.get() .fail(result.getThrowable());
		//Capturing Screenshots
		Date cd = new Date();
		String Date1 = cd.toString().replace(" ", "-").replace(":", "-");
		String methodName = Date1 + result.getMethod().getMethodName() + "Fail";
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			
		}
		System.out.println(driver);
		System.out.println(methodName);
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(methodName, driver), result.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		Extents.flush();
	}
	
	public String getScreenShotPath(String methodName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File soucre = ts.getScreenshotAs(OutputType.FILE);
		String path2 = System.getProperty("user.dir") + "\\Report\\"+methodName+".png";
		FileUtils.copyFile(soucre,new File(path2));
		return path2;
	}


}
