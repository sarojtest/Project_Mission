package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest {
	
	Properties prop;
	BasePage basepage;
	
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage; 
	ContactsPage contactsPage;

	
	@BeforeTest
	public void setUp() {
		basepage = new BasePage();
		prop     = basepage.init_Prop();
	    driver   = basepage.init_driver(prop);
		loginPage=new LoginPage(driver);
		homePage= loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.gotoContacts();
		
	}
	@Test(priority = 1)
	public void verifyContactsPageTitle() {
		String contactsPageTitle =contactsPage.getContactsPageTitle();
		Assert.assertEquals(contactsPageTitle, Constants.CONTACTS_PAGE_TITLE);
		
	}
	@DataProvider
	public Object[][] verifyContactsTestData() {
		Object data[][]=ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
		
	}
	
	@Test(priority = 2, dataProvider = "verifyContactsTestData")
	public void verifyCreateNewContact(String email,String firstname , String lastname , String jobtitle) {
		String name =contactsPage.createNewContact(email, firstname, lastname, jobtitle);
		Assert.assertEquals(name, firstname + " " + lastname);
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
