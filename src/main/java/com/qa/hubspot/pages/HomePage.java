package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.cssSelector("h1.dashboard-selector__title");
	By accountMenu =By.cssSelector("#account-menu");
	By accountName = By.cssSelector("div.navAccount-accountName");
	
	By contactsLinkPrimary = By.id("nav-primary-contacts-branch");
	By contactsLinkSecondary = By.id("nav-secondary-contacts");
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	@Step("get Home Page Title..")
	public String getHomepageTitle() {
		//return driver.getTitle();
		return elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
	}
	
	@Step("get HomePage header...")
	public String getHomePageheader() {
		elementUtil.waitForElementToBePresent(header, 5);
		if (elementUtil.doIsDisplayed(header)) {

		//if (driver.findElement(header).isDisplayed()) {
		//return	driver.findElement(header).getText();
			return elementUtil.doGetText(header);
		}
		return null;
		
		
	}
	@Step("get account name on Homepage... ")
	public String getAccountname() {
		//elementUtil.getElement(accountMenu).click();
		elementUtil.waitForElementToBePresent(accountMenu, 10);
		elementUtil.doClick(accountMenu);
		//driver.findElement(accountMenu).click();
		elementUtil.waitForElementToBePresent(accountName, 10);
		if (elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
		
	}
	@Step("navigate to Contactspage...")
	public ContactsPage gotoContacts() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	private void clickOnContacts() {
		elementUtil.waitForElementToBePresent(contactsLinkPrimary, 10);
		elementUtil.doClick(contactsLinkPrimary);
		elementUtil.waitForElementToBePresent(contactsLinkSecondary, 5);
		elementUtil.doClick(contactsLinkSecondary);
		
	}
}
