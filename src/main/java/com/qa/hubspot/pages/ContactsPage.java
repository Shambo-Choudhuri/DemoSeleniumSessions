package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtils;

public class ContactsPage extends BasePage {
	
	private WebDriver driver;
	private ElementUtils elementUtils;
	
	By primaryCreateContactButton=By.xpath("//span[text()='Create contact']");
	By Email=By.xpath("//input[@data-field='email']");
	By FirstName=By.xpath("//input[@data-field='firstname']");
	By LastName=By.xpath("//input[@data-field='lastname']");
	By secondaryCreateContactButton=By.xpath("(//span[text()='Create contact'])[last()]");
	By ContactsBackLink=By.xpath("(//*[text()='Contacts'])[position()=1]");
	
	ContactsPage(WebDriver driver)
	{
		this.driver=driver;
		this.elementUtils=new ElementUtils(driver);
	}
	
	public String contactsPageTitle()
	{
		elementUtils.ExplicitWaitForPageTitle(driver, Constants.CONTACTS_PAGE_TITLE, 20);
		return elementUtils.getPageTitle(driver);
	}
	
	
	
	
	public void createNewContact(String EmailID,String First_Name,String Last_Name)
	{
		//elementUtils.ExplicityWaitForVisibilityOfElement(driver, primaryCreateContactButton, 20);
		elementUtils.clickOn(driver, primaryCreateContactButton);
		
		//elementUtils.ExplicityWaitForVisibilityOfElement(driver, Email, 10);
		elementUtils.sendText(driver, Email, EmailID);
		
		elementUtils.sendText(driver, FirstName, First_Name);
		
		elementUtils.sendText(driver, LastName, Last_Name);
		
		WebElement contactButton=elementUtils.getElement(driver, secondaryCreateContactButton);
		
		elementUtils.ExplicityWaitForElementToBeClickable(driver, contactButton, 10);
		
		elementUtils.ActionsClassClickOperation(driver, contactButton);
		
		//elementUtils.ExplicityWaitForVisibilityOfElement(driver, ContactsBackLink, 10);
		
		elementUtils.clickOn(driver, ContactsBackLink);
		
	}
	
	

}
