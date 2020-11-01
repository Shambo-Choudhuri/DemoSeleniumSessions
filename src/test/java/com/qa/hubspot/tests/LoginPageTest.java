package com.qa.hubspot.tests;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.listeners.TestAllureListener;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Listeners(TestAllureListener.class)
@Epic("Epic-101 : Design Login Page with different-features")
@Story("US-102 : Design basic Login Page with SignUp,tile and Login form")
public class LoginPageTest extends BaseTest
{
	Logger log=Logger.getLogger(LoginPageTest.class);
	
	
	@Severity(SeverityLevel.NORMAL)
	@Description("This is Login Page Title Test")
	@Test(priority=1)
	public void verifyLoginPageTitle()
	{
		log.info("Login Page Title = " +loginPage.getLoginPageTitle());
		Assert.assertEquals(loginPage.getLoginPageTitle(), Constants.LOGIN_PAGE_TITLE, "The Login Page Title does not match");
	}
	
	/*
	 * @Test(priority=2,enabled=false) public void verifySignUpLinkPresence() {
	 * Assert.assertTrue(loginPage.verifySignUpLink(),
	 * "Sign Up Link is not present"); }
	 * 
	 * 
	 * @Description("To check whether Login operation is performing correctly or not"
	 * )
	 * 
	 * @Test(priority=3,enabled=false) public void loginTest() throws
	 * InterruptedException { loginPage.doLogin(prop.getProperty("username"),
	 * prop.getProperty("password")); }
	 */
	
	

}
