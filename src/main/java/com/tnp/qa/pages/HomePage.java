package com.tnp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//Objects
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchBtn;
	
	public HomePage(WebDriver driver) {
		
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	//Actions
	public void clickonMyAccount()
	{
		myAccountDropMenu.click();
	}
	public LoginPage selectLoginOption()
	{
		loginOption.click();
		
		return new LoginPage(driver);
	}
	public RegisterPage selectRegisterOption()
	{
		registerOption.click();
		
		return new RegisterPage(driver);
	}
	
	public void enterProductsIntoSearchBoxFiled(String productText) {
		searchBoxField.sendKeys(productText);
	}
	
	public SearchPage clickOnSearchBtn()
	{
		searchBtn.click();
		return new SearchPage(driver);
	}
	
}
