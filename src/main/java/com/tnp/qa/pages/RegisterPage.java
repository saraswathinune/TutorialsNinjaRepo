package com.tnp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telePhoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(name="//input[@value='Continue']")
	private WebElement continueBtn;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsletterField;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyFieldWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameFieldWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameFieldWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailFieldWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneFieldWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordFieldWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstNameField(String firstname) {
		
		firstNameField.sendKeys(firstname);
	}
	
   public void enterLastNameField(String lastname) {
		
		lastNameField.sendKeys(lastname);
	}
   
   public void enteremailField(String email) {
		
	   emailField.sendKeys(email);
	}
   
   public void enterTelephoneField(String telephone) {
		
	   telePhoneField.sendKeys(telephone);
	}
   
   public void enterPasswordField(String password) {
		
	   passwordField.sendKeys(password);
	}
   
   public void enterConfirmPasswordField(String confirmpassword) {
		
	   confirmField.sendKeys(confirmpassword);
	}
   
   public void clickonPrivacyPolicyField() {
		
	   privacyPolicyField.click();
	}
   
   public AccountSuccessPage clickonContinueBtn() throws InterruptedException {
		Thread.sleep(5000);
	   continueBtn.click();
	   return new AccountSuccessPage(driver);
	}
   
   public void clickonNewsLetterField() {
	   newsletterField.click();
   }
   
   public String getDuplicateEmailAddressWarningText()
   {
	   String duplicateEmailAddressWarningText= duplicateEmailAddressWarning.getText();
	   return duplicateEmailAddressWarningText;
   }
   
   public String getPrivacyPolicyFieldWarningText()
   {
	   String privacyPolicyFieldWarningText= privacyPolicyFieldWarning.getText();
	   return privacyPolicyFieldWarningText;
   }
   
   public String getFirstNameFieldWarningWarningText()
   {
	   String firstNameFieldWarningText= firstNameFieldWarning.getText();
	   return firstNameFieldWarningText;
   }
   public String getLastNameFieldWarningWarningText()
   {
	   String lastNameFieldWarningText= lastNameFieldWarning.getText();
	   return lastNameFieldWarningText;
   }
   public String getEmailFieldWarningWarningText()
   {
	   String emailFieldWarningText= emailFieldWarning.getText();
	   return emailFieldWarningText;
   }
   public String getTelephoneFieldWarningWarningText()
   {
	   String telephoneFieldWarningText= telephoneFieldWarning.getText();
	   return telephoneFieldWarningText;
   }
   public String getPasswordFieldWarningWarningText()
   {
	   String passwordFieldWarningText= passwordFieldWarning.getText();
	   return passwordFieldWarningText;
   }
   
   public void displayStatusOfWarningMessages() {
	   
   }
   
   
}
