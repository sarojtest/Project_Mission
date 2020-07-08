package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//1 .  By locators
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By showPassword =By.xpath("//span[text()='Show Password']");
	By forgotMyPassword =By.xpath("//a/i18n-string[text()='Forgot my password']");
	By ssoButton        =By.cssSelector("div #hs-login #ssoBtn i18n-String");
	
	
//2 Constructors to initialise driver
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
    //   3. Actions :
	@Step("get login page title..")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("check signUp Link on login Page...")
	public boolean checkSignUpLink() {
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	
	public boolean checkForgotMyPassword() {
		return elementUtil.doIsDisplayed(forgotMyPassword);
		//return driver.findElement(forgotMyPassword).isDisplayed();
		
	}
	
	
	public boolean checkSSOButton() {
		//return driver.findElement(ssoButton).isDisplayed();
		return elementUtil.doIsDisplayed(ssoButton);
	}
	
	
	
	public boolean checkShowPassword() {
		//return driver.findElement(showPassword).isEnabled();
		return elementUtil.doIsDisplayed(showPassword);
	}
	
	@Step("login with - username :{0} and password : {1}")
	public HomePage doLogIn(String un, String pwd ) {
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
	}
	


}
