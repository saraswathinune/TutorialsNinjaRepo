package com.tnp.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporter {

	public static ExtentReports generateExtentreport() {
		ExtentReports extentReport=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter= new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialNinja Test Automation Results");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:SS");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties prop=new Properties();
		File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tnp\\qa\\config\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browserName"));
		extentReport.setSystemInfo("Operation System:", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name:", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version:", System.getProperty("java.version"));
		
		return extentReport;
	}
}
