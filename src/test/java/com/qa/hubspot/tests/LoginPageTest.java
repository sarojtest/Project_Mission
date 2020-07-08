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

@Epic("Epic -101 :design login feature ")
@Feature("US -105 :design test case for login page feature")

public class LoginPageTest {
	Properties prop;
	BasePage basepage;
	
	WebDriver driver;
	LoginPage loginPage;

	
	@BeforeTest
	public void setUp() {
		basepage = new BasePage();
		prop     = basepage.init_Prop();
	    driver   = basepage.init_driver(prop);
		loginPage=new LoginPage(driver);
		
	}
	
	
	@Test(priority = 1,enabled = true)
	@Description("verify Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() {
		String title=loginPage.getLoginPageTitle();
		System.out.println("Loginpage title is " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	@Description("verify SignUp Link Test")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	
	}
	
	
	@Test(priority = 3)
	public void verifyForgotMyPasswordTest() {
		Assert.assertTrue(loginPage.checkForgotMyPassword());
		
	}
	
	
	@Test(priority = 4)
	public void verifyShowpasswordTest() {
		Assert.assertTrue(loginPage.checkShowPassword());
	}
	
	
	@Test(priority = 5)
	public void verifySSOButtonTest() {
		Assert.assertTrue(loginPage.checkSSOButton());
	}
	
	
	@Test(priority = 6)
	@Description("verify user is able to Login -Feature test")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
	HomePage homepage=loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"));
	Assert.assertEquals(homepage.getAccountname(), prop.getProperty("accountname"));
	
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
