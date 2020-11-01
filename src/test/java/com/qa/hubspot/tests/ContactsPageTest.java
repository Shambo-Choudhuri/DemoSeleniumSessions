package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtils;


public class ContactsPageTest extends BaseTest {
	
	public ContactsPage contactsPage;
	public HomePage homePage;
	
	/*
	 * @BeforeClass //@Parameters({"env"}) public void contactPageSetup() throws
	 * InterruptedException {
	 * homePage=loginPage.doLogin(prop.getProperty("username"),
	 * prop.getProperty("password"));
	 * contactsPage=homePage.clickOnSubMenuContactsLink();
	 * 
	 * }
	 */
	@Test(priority=1)
	public void verifyContactsPageTitle()
	{
		Assert.assertEquals(contactsPage.contactsPageTitle(),Constants.CONTACTS_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getContactsTestData()
	{
		Object data[][]=ExcelUtils.getTestData(Constants.CONTACTS_TEST_DATA_SHEET_NAME);
		return data;
	}
	
	@Test(priority=2,dataProvider="getContactsTestData")
	//@Parameters({"env"})
	public void verifyCreatedContact(String Email,String FirstName,String LastName)
	{
		contactsPage.createNewContact(Email,FirstName,LastName);
	}



	
	

}
