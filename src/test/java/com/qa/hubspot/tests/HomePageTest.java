package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic -102 :design HomePagefeature ")
@Feature("US -105 :design test case for Home page feature")
public class HomePageTest {
	Properties prop;
	BasePage basepage;
	
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;  

	
	@BeforeTest
	public void setUp() {
		basepage = new BasePage();
		prop     = basepage.init_Prop();
	    driver   = basepage.init_driver(prop);
		loginPage=new LoginPage(driver);
		homePage= loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
	@Test(priority = 1)
	@Description("verify Home Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageTitletest() {
		String title = homePage.getHomepageTitle();
		System.out.println("HomePage title is " + title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	
	@Test(priority = 2)
	@Description("verify Home Page Header Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageHeaderTest() {
		String homepageHeader =homePage.getHomePageheader();
		System.out.println("HomePage header is " + homepageHeader);
		Assert.assertEquals(homepageHeader, Constants.HOME_PAGE_HEADER);
		
	}
	
	
	@Test(priority = 3)
	@Description("verify User has loggedIn to the app Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoggedInUserTest() {
		String accountname= homePage.getAccountname();
		System.out.println("HomePage accountname is " + accountname);
		Assert.assertEquals(accountname, prop.getProperty("accountname"));
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	

}
