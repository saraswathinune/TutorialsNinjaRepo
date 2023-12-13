package com.tnp.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tnp.qa.utils.ExtentReporter;
import com.tnp.qa.utils.Utils;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	public static ExtentTest extentTest;
	String testName;
	@Override
	public void onStart(ITestContext context) {
		
		extentReport=ExtentReporter.generateExtentreport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		testName=result.getName();
		extentTest=extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, testName + " got executed successfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver=null;
		
		try {
			driver= (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		File srcScreenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String srcScreenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
//		try {
//			FileHandler.copy(srcScreenshot, new File(srcScreenshotPath));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		extentTest.addScreenCaptureFromPath(Utils.captureScreenShot(driver, testName));
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " got skipped");
	}

	
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathofExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport=new File(pathofExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
