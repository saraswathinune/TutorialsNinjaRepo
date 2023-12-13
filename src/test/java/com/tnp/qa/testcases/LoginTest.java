package com.tnp.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tnp.qa.base.Base;
import com.tnp.qa.pages.AccountPage;
import com.tnp.qa.pages.HomePage;
import com.tnp.qa.pages.LoginPage;
import com.tnp.qa.utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class LoginTest extends Base{
	public WebDriver driver;
	LoginPage loginpage;
	AccountPage accountPage;
	public LoginTest() throws IOException {
		super();
	}
	@BeforeMethod
	public void setUp()
	{
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickonMyAccount();
		loginpage=homePage.selectLoginOption();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1, dataProvider="validCredentialsDetails")
	public void verifyLoginWithValidCredentials(String email, String password) {
	
		loginpage.enterEmailAddress(email);
		loginpage.enterPasswordField(password);
		accountPage=loginpage.clickOnLoginBtn();
		
		
		Assert.assertTrue(accountPage.getDiaplayStatusOfEditYourAccountOption());
	}
	
	@DataProvider(name="validCredentialsDetails")
	public Object[][] supplyTestData()
	{
		Object[][] data= Utils.readDatafromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInValidCredentials() {
		
		loginpage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
		loginpage.enterPasswordField("1234567");
		loginpage.clickOnLoginBtn();
		//Assert.assertTrue(driver.findElement(By.linkText("Warning: No match for E-Mail Address and/or Password")).isDisplayed());
		Assert.assertTrue(loginpage.retrieveWarningMessageText());
	
	}
	
	@Test(priority=3)
	public void verifyLoginWithInValidUserId() {
		
		loginpage.enterEmailAddress("saraswathi.nuneabc123@gmail.com");
		loginpage.enterPasswordField("12345");
		loginpage.clickOnLoginBtn();
		//Assert.assertTrue(driver.findElement(By.linkText("Warning: No match for E-Mail Address and/or Password")).isDisplayed());
		Assert.assertTrue(loginpage.retrieveWarningMessageText());
				
	}
	
	@Test(priority=4)
	public void verifyLoginWithInValidPassword() {
		
		loginpage.enterEmailAddress("saraswathi.nuneabc@gmail.com");
		loginpage.enterPasswordField("1234567");
		loginpage.clickOnLoginBtn();
		//Assert.assertTrue(driver.findElement(By.linkText("Warning: No match for E-Mail Address and/or Password")).isDisplayed());
		Assert.assertTrue(loginpage.retrieveWarningMessageText());
	}
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		
		loginpage.enterEmailAddress(" ");
		loginpage.enterPasswordField(" ");
		loginpage.clickOnLoginBtn();
		//Assert.assertTrue(driver.findElement(By.linkText("Warning: No match for E-Mail Address and/or Password")).isDisplayed());
		Assert.assertTrue(loginpage.retrieveWarningMessageText());
					
	}
	
}
