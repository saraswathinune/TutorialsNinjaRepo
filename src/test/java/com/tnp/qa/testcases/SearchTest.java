package com.tnp.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tnp.qa.base.Base;
import com.tnp.qa.pages.HomePage;
import com.tnp.qa.pages.SearchPage;

public class SearchTest extends Base{

	public SearchTest() {
		super();
	}
	public WebDriver driver;
	SearchPage searchpage;
	
	@BeforeMethod
	public void setUp()
	{
		driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{
		HomePage homepage= new HomePage(driver);
		homepage.enterProductsIntoSearchBoxFiled("HP");
		searchpage=homepage.clickOnSearchBtn();
			
	    Assert.assertTrue(searchpage.diaplyStatusOfHPProduct(), "Correct Product isnot listed");
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct()
	{
		HomePage homepage= new HomePage(driver);
		homepage.enterProductsIntoSearchBoxFiled("Honda");
		searchpage=homepage.clickOnSearchBtn();
		
		String actualSearchMessage=searchpage.getInValidSearchText();
		String expectedSearchMessage="There is no product that matches the search criteria.";
		Assert.assertEquals(actualSearchMessage, expectedSearchMessage, "Search text is not matching");
				
	}
	
	@Test(priority=3)
	public void verifySearchWithoutProduct()
	{
		HomePage homepage= new HomePage(driver);
		searchpage=homepage.clickOnSearchBtn();
		
        String actualSearchMessage=searchpage.getInValidSearchText();
		String expectedSearchMessage="There is no product that matches the search criteria.";
		Assert.assertEquals(actualSearchMessage, expectedSearchMessage, "Search text is not matching");
				

	}
}
