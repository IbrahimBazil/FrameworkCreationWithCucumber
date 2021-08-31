package resources;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporter;
import resources.Screenshot;
import resources.base;

public class Listeners extends Screenshot implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporter.extentReportObj();
	public static Logger log=LogManager.getLogger(base.class.getName());
	public String testMethodName;

	// For Parallel Test Exection we cant use the Extent report so declarevthem with
	// thread safe
	ThreadLocal<ExtentTest> extentsTest = new ThreadLocal<ExtentTest>();
	public Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}


	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		testMethodName = result.getMethod().getMethodName();
		test = extent.createTest(testMethodName);
		extentsTest.set(test);
		extentsTest.get().getModel().setStartTime(getTime(result.getStartMillis()));
		log.info(testMethodName+"_Test hasbeen started");

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testMethodName = result.getMethod().getMethodName();
		try {
			//extentsTest.get().addScreenCaptureFromPath(getScreenshot(testMethodName, driver), testMethodName);
			extentsTest.get().addScreenCaptureFromPath(getScreenshot(testMethodName, driver), testMethodName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentsTest.get().log(Status.PASS, "Test Passed Successfully");
		extentsTest.get().getModel().setEndTime(getTime(result.getEndMillis()));
		log.info(testMethodName+"_Test hasbeen Completed_Successfully");
		//String testName = result.getMethod().getMethodName();
		// extentsTest.get().addScreenCaptureFromPath("C:\\Users\\M1058748\\eclipse-workspace\\FrameworkCreation\\reports\\"+testName+".png",testName);
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentsTest.get().log(Status.FAIL, "Test Failed");
		extentsTest.get().log(Status.FAIL, result.getThrowable());
		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testMethodName = result.getMethod().getMethodName();
		try {
			extentsTest.get().addScreenCaptureFromPath(getScreenshot(testMethodName, driver));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(testMethodName+"_Test hasbeen Completed_Failed");
		extentsTest.get().getModel().setEndTime(getTime(result.getEndMillis()));
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
		
		extent.flush();
	}

}
