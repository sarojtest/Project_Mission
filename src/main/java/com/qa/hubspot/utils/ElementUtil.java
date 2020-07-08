package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

	

public class ElementUtil {
	WebDriver driver;
	JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public WebElement getElement(By locator) {
		WebElement element= driver.findElement(locator);
		jsUtil.flash(element);
		return element;
		
	}
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
		
	}
	public void doActionsSendKeys(By locator , String value) {
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.sendKeys(element, value).perform();
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
		
	}
	
	public void doActionsClick(By locator ) {
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.click(element).perform();
	}
	
	
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
		
	}
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public  void getDropDownValues(By loacator) {
		// retrieving all values ofdropdown
		Select select = new Select(getElement(loacator));
		List<WebElement>  optionsList=select.getOptions();
		System.out.println("total values in day dropDown is " +optionsList.size());
		for (int i = 0; i < optionsList.size(); i++) {
			System.out.println(optionsList.get(i).getText());
		}
	}
	
	public  void selectDropdown(By locator , String value) {
		Select select=new Select(getElement(locator));
        select.selectByVisibleText(value);
	}
	
	
	public  void selectDropdown(By locator , int index) {
		Select select=new Select(getElement(locator));
        select.selectByIndex(index);
	}
	
	
	public  void selectDropDownValue(By locator, String value) {
		Select select =new Select(getElement(locator));
		List<WebElement> valuesList=select.getOptions();
		for (int i = 0; i <valuesList.size(); i++) {
			if (valuesList.get(i).getText().equals(value)) {
				valuesList.get(i).click();
				break;
			}
		}
}

	/**
	 * This method is used to select the value from the dropdown - without using Select Class in Selenium.
	 * @param locator
	 * @param locatorvalue
	 * @param value
	 */
	
	public  void doSelectValueFromWithoutSelectmethod(String locator,String locatorvalue ,String value) {
		List<WebElement> valuesList = null;
		
		if (locator.equals("xpath")) {
			 valuesList=driver.findElements(By.xpath(locatorvalue));
			 
		}else if (locator.equals("css")) {
			valuesList=driver.findElements(By.cssSelector(locatorvalue));
		}
		System.out.println(valuesList.size());
		
		for (int i = 0; i <valuesList.size(); i++) {
			if (valuesList.get(i).getText().equals(value)) {
				valuesList.get(i).click();
				break;
		  }	
	}
}
	
	
	public  ArrayList<String> selectDropdown(By locator  ) {
		
		ArrayList<String> ar=new ArrayList<String>();
		
		Select select=new Select(getElement(locator));
		List<WebElement>  optionsList=select.getOptions();
		for (int i = 0; i < optionsList.size(); i++) {
			ar.add(optionsList.get(i).getText());
		}
		return ar;
		
	}
	// wait custom methods : wait utils:
	public void visibilityOfAllElements(List<WebElement> elements,  int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		
		
	}
	
	public WebElement waitForElementToBePresent(By locator , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator);
		
	}
	public WebElement waitForElementToBeClickable(By locator , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator);
		
	}
	public WebElement waitForElementToBeVisible(By locator , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		return getElement(locator);
		
	}
	
	public WebElement waitForElementVisiblityLocated(By locator , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return getElement(locator);
		
	}
	public String waitForUrl(String url , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
		
	}
	public boolean waitForAlertToBePresent( int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
		
	}
	public void clickWhenReady(By locator,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
		
	}
	public String waitForTitleToBePresent( String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
		
	}
	
}



