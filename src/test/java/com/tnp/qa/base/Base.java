package com.tnp.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tnp.qa.utils.Utils;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public Base()
	{
		prop=new Properties();
		File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tnp\\qa\\config\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
		dataProp =new Properties();
		File datapropFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tnp\\qa\\testdata\\testdata.properties");
		FileInputStream datafis;
		try {
			datafis = new FileInputStream(datapropFile);
			dataProp.load(datafis);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();	
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();	
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();	
		}else if(browserName.equalsIgnoreCase("safari"))
		{
			driver=new SafariDriver();	
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
