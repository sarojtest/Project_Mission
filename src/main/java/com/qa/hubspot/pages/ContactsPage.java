package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.JavaScriptUtil;

public class ContactsPage {
	
	WebDriver driver;
	ElementUtil elementUtil; 
	JavaScriptUtil jsUtil;
	
	By createContact =     By.xpath("(//span[text()='Create contact'])[1]");
	By createContactForm = By.xpath("(//span[text()='Create contact'])[2]");
	
	By email  =By.xpath("//input[@data-field='email']");
	By firstname =By.xpath("//input[@data-field='firstname']");
	By lastname = By.xpath("//input[@data-field='lastname']");
	By jobtitle = By.xpath("//input[@data-field='jobtitle']");
	By contactsNavigationLink = By.xpath("(//i18n-string[text()='Contacts'])[1]");
	
	
	public ContactsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);	
		jsUtil=new JavaScriptUtil(driver);
		}
	
	public String getContactsPageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.CONTACTS_PAGE_TITLE, 10);
		
	}
	
	public String createNewContact(String emailID, String firstName ,String lastName , String jobTitle) {
		elementUtil.waitForElementToBePresent(createContact, 10);
		elementUtil.doClick(createContact);
		
		elementUtil.waitForElementToBePresent(email, 5).sendKeys(emailID);
		
		elementUtil.waitForElementToBePresent(firstname, 5).sendKeys(firstName);
		elementUtil.waitForElementToBePresent(lastname, 5).sendKeys(lastName);
		elementUtil.waitForElementToBePresent(jobtitle, 5).sendKeys(jobTitle);
		
        elementUtil.waitForElementToBePresent(createContactForm, 10);
        
		jsUtil.clickElementByJS(elementUtil.getElement(createContactForm));
		
		contactsNavigationLink = By.xpath("(//i18n-string[text()='Contacts'])[1]");
		String fullname = firstName + " " + lastName;
		String namexpath = "(//span[text()='"+fullname+"'])[2]";
		
		elementUtil.waitForElementToBePresent(contactsNavigationLink, 15);
		String contactName = elementUtil.doGetText(By.xpath(namexpath)).trim();
		elementUtil.doClick(contactsNavigationLink);
		
		return contactName;
		
	
	}

}

 
