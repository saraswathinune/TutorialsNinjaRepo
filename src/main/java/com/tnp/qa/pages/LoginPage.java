package com.tnp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginBtn;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailAddress(String email)
	{
		emailAddressField.sendKeys(email);
	}
	
	public void enterPasswordField(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public AccountPage clickOnLoginBtn()
	{
		loginBtn.click();
		return new AccountPage(driver);
	}
	
	public boolean retrieveWarningMessageText()
	{
		boolean warningMsgText= emailPasswordNotMatchingWarning.isDisplayed();
		return warningMsgText;
	}
}
