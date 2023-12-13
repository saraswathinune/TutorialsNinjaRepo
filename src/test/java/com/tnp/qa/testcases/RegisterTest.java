package com.tnp.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tnp.qa.base.Base;
import com.tnp.qa.pages.AccountSuccessPage;
import com.tnp.qa.pages.HomePage;
import com.tnp.qa.pages.RegisterPage;
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

public class RegisterTest extends Base{

	public WebDriver driver;
	RegisterPage registerpage;
	AccountSuccessPage accountsuccesspage;
	public RegisterTest() throws IOException {
		super();
	}
	
	
	@BeforeMethod
	public void setUp()
	{
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickonMyAccount();
		registerpage=homePage.selectRegisterOption();
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegistringAnAccountWithMandatoryFields() throws InterruptedException {
		registerpage.enterFirstNameField(dataProp.getProperty("firstname"));
		registerpage.enterLastNameField(dataProp.getProperty("lastname"));
		registerpage.enteremailField(Utils.generateEmailWithTimeStamp());
		registerpage.enterTelephoneField(dataProp.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassowrd"));
		registerpage.enterConfirmPasswordField(prop.getProperty("validPassowrd"));
		registerpage.clickonPrivacyPolicyField();
		Thread.sleep(5000);
		accountsuccesspage=registerpage.clickonContinueBtn();
		String actualSuccessHeading=accountsuccesspage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account Success Page is not displayed");
	}
	@Test(priority=2)
	public void verifyRegistringAnAccountWithAllFields() throws InterruptedException {
	
		registerpage.enterFirstNameField(dataProp.getProperty("firstname"));
		registerpage.enterLastNameField(dataProp.getProperty("lastname"));
		registerpage.enteremailField(Utils.generateEmailWithTimeStamp());
		registerpage.enterTelephoneField(dataProp.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassowrd"));
		registerpage.enterConfirmPasswordField(prop.getProperty("validPassowrd"));
		registerpage.clickonNewsLetterField();
		registerpage.clickonPrivacyPolicyField();
		accountsuccesspage=registerpage.clickonContinueBtn();
						
		String actualSuccessHeading=accountsuccesspage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account Success Page is not displayed");
		
	}
	@Test(priority=3)
	public void verifyRegistringAnAccountWithExistingEmailId() throws InterruptedException {
		
		registerpage.enterFirstNameField(dataProp.getProperty("firstname"));
		registerpage.enterLastNameField(dataProp.getProperty("lastname"));
		registerpage.enteremailField("saraswathi.nuneabc@gmail.com");
		registerpage.enterTelephoneField(dataProp.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassowrd"));
		registerpage.enterConfirmPasswordField(prop.getProperty("validPassowrd"));
		registerpage.clickonPrivacyPolicyField();
		registerpage.clickonContinueBtn();
						
		String actualSuccessHeading=registerpage.getDuplicateEmailAddressWarningText();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("duplicateEmailWarning"), "Email validation is not working");
		
	}
	@Test(priority=4)
	public void verifyRegistringAnAccountWithoutFillingDetails() throws InterruptedException {
	
		registerpage.clickonContinueBtn();
						
		String actualPrivacyPolicy=registerpage.getPrivacyPolicyFieldWarningText();
		Assert.assertTrue(actualPrivacyPolicy.contains(dataProp.getProperty("privacyPolicyWarning")), "Privacy Policy validation is not working");
		
		String actualFirstName=registerpage.getFirstNameFieldWarningWarningText();
		Assert.assertTrue(actualFirstName.contains(dataProp.getProperty("firstnameWarning")), "First Name validation is not working");
		
		String actualLast=registerpage.getLastNameFieldWarningWarningText();
		Assert.assertTrue(actualLast.contains(dataProp.getProperty("lastnameWarning")), "Last Name validation is not working");
		
		String actualEmail=registerpage.getEmailFieldWarningWarningText();
		Assert.assertTrue(actualEmail.contains(dataProp.getProperty("emailWarning")), "Email validation is not working");
		
		String actualTelephone=registerpage.getTelephoneFieldWarningWarningText();
		Assert.assertTrue(actualTelephone.contains(dataProp.getProperty("telephoneWarning")), "Telephone validation is not working");
		
		String actualPassword=registerpage.getPasswordFieldWarningWarningText();
		Assert.assertTrue(actualPassword.contains(dataProp.getProperty("passwordWarning")), "Password validation is not working");
		
		
	}
	
}
