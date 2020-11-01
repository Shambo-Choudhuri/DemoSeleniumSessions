package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtils;

public class HomePage extends BasePage 
{
	
	private WebDriver driver;
	private ElementUtils elementUtils;
	
	
	//Object Repository using By locator technique
	
	//HomePage class objects
	
	By headerText=By.cssSelector("div.private-header__title.private-page__title>h1");
	By MainMenuContactsLink=By.id("nav-primary-contacts-branch");
	By SubMenuContactsLink=By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		this.elementUtils=new ElementUtils(driver);
	}
	
	public String verifyHeaderText()
	{
		WebElement header=elementUtils.getElement(driver, this.headerText);
		
		if(header.isDisplayed())
		{
			return header.getText();
		
		}
		else
		{
			return "HeaderText is not displayed";
		}
		
	}
	
	public String verifyHomePageTitle()
	{
		return elementUtils.getPageTitle(driver);
	}
	
	
	public ContactsPage clickOnSubMenuContactsLink()
	{
		//elementUtils.ExplicityWaitForVisibilityOfElement(driver, MainMenuContactsLink, 20);
		elementUtils.clickOn(driver, MainMenuContactsLink);
		
		//elementUtils.ExplicityWaitForVisibilityOfElement(driver, SubMenuContactsLink, 20);
		elementUtils.clickOn(driver, SubMenuContactsLink);
		
		return new ContactsPage(driver);
		
	}
	
	

}
