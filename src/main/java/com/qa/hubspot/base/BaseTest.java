package com.qa.hubspot.base;



import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;

import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.ElementUtils;



public class BaseTest {
	
	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public ElementUtils elementUtils;
	public Logger log;
	
	
	
	@BeforeTest
	//@Parameters({"url","browser","env"})
	public void setUp() throws Exception
	{
		
		  basePage=new BasePage();
		  
		  prop=basePage.init_prop();
		  
		  //prop=basePage.init_prop(env);
		  
		  //driver=basePage.init_driver(browser);
		  
		  //driver=basePage.init_driver(prop);
		 
		  loginPage=new LoginPage(driver);
		  
		  driver.get(prop.getProperty("url"));
		  
		  elementUtils=new ElementUtils(driver);
			
		  elementUtils.maximizeBrowser(driver);
			
		  elementUtils.deleteAllCookies(driver);
			
		  elementUtils.ImplicitlyWait(driver, 20);
			
		  elementUtils.PageLoadTimeOut(driver, 20);
			
		  //elementUtils.launchURLUsingGET(driver,url);
		  
		  log=Logger.getLogger(BaseTest.class);
		  
		  log.info("Entering Application URL");
		  
		  elementUtils.launchURLUsingGET(driver,prop.getProperty("url"));
		  
		  
	
	}
	
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
