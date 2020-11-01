package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;


class InvalidBrowserException extends RuntimeException
{
	
}

public class BasePage
{
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	//This method can be accessed by only one Thread at a time
	
	public synchronized WebDriver getDriverInstance()
	{
		driver=tlDriver.get();
		return driver; //Return the ThreadLocal WebDriver object
		
	}
	
	
	//Non-ThreadLocal WebDriver object without Browser options
	
	/*public WebDriver init_driver(Properties prop)
	{
		
		switch(prop.getProperty("browser"))
		{
			case "chrome":
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver();
					System.out.println("Tests Running on chrome browser");
					break;
					
			case "firefox":
				    WebDriverManager.firefoxdriver().setup();
				    driver=new FirefoxDriver();
				    System.out.println("Tests Running on firefox browser");
				    break;
				    
		    default:
		    	   throw new InvalidBrowserException();
				    
		}
		
		
		return driver; //returning a non-thread safe and normal WebDriver object
	}
	*/
	
	//Non-ThreadLocal WebDriver object with Browser options
	
	/*public WebDriver init_driver(Properties prop)
	{
		optionsManager=new OptionsManager(prop);
		
		switch(prop.getProperty("browser"))
		{
			case "chrome":
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver(optionsManager.getChromeOptions());
					System.out.println("Tests Running on chrome browser");
					break;
					
			case "firefox":
				    WebDriverManager.firefoxdriver().setup();
				    driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
				    System.out.println("Tests Running on firefox browser");
				    break;
				    
		    default:
		    	   throw new InvalidBrowserException();
				    
		}
		
		
		return driver; //returning a non-thread safe and normal WebDriver object
	}*/
	
	
	//ThreadLocal WebDriver object without Browser Options
	
	/*public WebDriver init_driver(Properties prop)
	{
		
		switch(prop.getProperty("browser"))
		{
			case "chrome":
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver();//Non-Thread Local WebDriver object
					tlDriver.set(driver);//Now give the Non-ThreadLocal WebDriver object as input parameter
					System.out.println("Tests Running on chrome browser");
					break;
					
			case "firefox":
				    WebDriverManager.firefoxdriver().setup();
				    driver=new FirefoxDriver();
				    tlDriver.set(driver);
				    System.out.println("Tests Running on firefox browser");
				    break;
				    
		    default:
		    	   throw new InvalidBrowserException();
				    
		}
		
		return getDriverInstance(); //returning a thread safe and ThreadLocal WebDriver object
	}*/
	
	//Thread-Local WebDriver object with Browser Options
	
	/*public WebDriver init_driver(Properties prop) throws Exception
	{
		optionsManager=new OptionsManager(prop);
		
		switch(prop.getProperty("browser"))
		{
				case "chrome":
					WebDriverManager.chromedriver().setup();
					//driver=new ChromeDriver(optionsManager.getChromeOptions());
					tlDriver.set(driver);
					System.out.println("Tests Running on chrome browser");
					break;
					
				case "firefox":
				    WebDriverManager.firefoxdriver().setup();
				    tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));//driver=new FirefoxDriver();
				    System.out.println("Tests Running on firefox browser");
				    break;
				    
		    	default:
		    	throw new InvalidBrowserException();
		    	
		 }
				    
			return getDriverInstance(); //returning a thread safe and ThreadLocal WebDriver object
	}*/
	
	
	//Without Environment Configuration
	
	/*public Properties init_prop()
	{
		prop=new Properties();
		
		FileInputStream FIS;
		
		try 
		{
			FIS = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties");
			prop.load(FIS);
		} 
		catch(FileNotFoundException e) 
		{
			e.getMessage();
		} 
		catch(IOException e)
		{
			e.getMessage();
		}
			
			
		return prop;
		
	}*/
	
	//With Environment Configuration
	
	//If environment is given from command line through maven command
	
	public Properties init_prop()
	{
		prop=new Properties();
		
		String path=null;
		String env=null;
		
		try 
		{
			env=System.getProperty("env");
			
			if(env==null)
			{
				System.out.println("Please Enter a Test Environment");
			}
			else
			{
				switch(env)
				{
					
							
					case "QA":
						// "." reflects the current project path...no need to mention the full project path
							path=".\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
							break;
					case "UAT":
						// "." reflects the current project path...no need to mention the full project path
							path=".\\src\\main\\java\\com\\qa\\hubspot\\config\\uat.config.properties";
							break;
					
					default:
							System.out.println("Please Enter a valid Test Environment");
							break;
							
				}
			}
			
			
			FileInputStream FIS = new FileInputStream(path);
			
			try 
			{
				prop.load(FIS);
			} 
			catch (IOException e) 
			{
				
				e.getMessage();
			}

		} 
		catch (FileNotFoundException e) 
		{
			
			e.getMessage();
		}
		
		return prop;
	}
	
	
	//Take Screenshot
	
	public String getScreenshot()
	{
		//WebDriver driver=new ChromeDriver();
		
		//TakesScreenshot screenshot=new ChromeDriver();
		
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		
		//Take the Screenshot which returns a File class object
		
		File src=screenshot.getScreenshotAs(OutputType.FILE);
		
		//Copy the screenshot into a desired location in the project directory
		
		String path=System.getProperty("user.dir") + "\\screenshots"+"\\"+System.currentTimeMillis()+".png";
		
		File file=new File(path);
		
		try 
		{
			FileUtils.copyFile(src, file);
		} 
		catch (IOException e) 
		{
			e.getMessage();
		}
		
		return path;
	}
	
	//If browser is provided from TestRunner Parameters
	
	/*public WebDriver init_driver(String browser) throws Exception
	{
				
		optionsManager=new OptionsManager(prop);
		
		
			switch(browser)
		  	{
				case "chrome":
					WebDriverManager.chromedriver().setup();
					tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
					System.out.println("Tests Running on chrome browser");
					break;
					
				case "firefox":
				    WebDriverManager.firefoxdriver().setup();
				    tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));//driver=new FirefoxDriver();
				    System.out.println("Tests Running on firefox browser");
				    break;
				    
		    	default:
		    		throw new InvalidBrowserException();
		    	
		    }
				    
		
		
		
		return getDriverInstance(); //returning a thread safe and ThreadLocal WebDriver object
	}*/
	
	//If environment is provided from TestRunner Parameters
	
	/*public Properties init_prop(String env)
	{
		prop=new Properties();
		
		String path=null;
		
		try 
		{
			if(env==null)
			{
				System.out.println("Please Enter a Test Environment");
			}
			else
			{
				switch(env)
				{
					
							
					case "QA":
						// "." reflects the current project path...no need to mention the full project path
							path=".\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
							break;
					case "UAT":
						// "." reflects the current project path...no need to mention the full project path
							path=".\\src\\main\\java\\com\\qa\\hubspot\\config\\uat.config.properties";
							break;
					
					default:
							System.out.println("Please Enter a valid Test Environment");
							break;
							
				}
			}
			
			
			FileInputStream FIS = new FileInputStream(path);
			
			try 
			{
				prop.load(FIS);
			} 
			catch (IOException e) 
			{
				
				e.getMessage();
			}

		} 
		catch (FileNotFoundException e) 
		{
			
			e.getMessage();
		}
		
		return prop;
	}
	
	*/
	
	//If browser is given from command line through maven command
	
	/*public WebDriver init_driver(Properties prop) throws Exception
	{
		optionsManager=new OptionsManager(prop);
		
		switch(System.getProperty("browser"))
		{
				case "chrome":
					WebDriverManager.chromedriver().setup();
					tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
					System.out.println("Tests Running on chrome browser");
					break;
					
				case "firefox":
				    WebDriverManager.firefoxdriver().setup();
				    tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				    System.out.println("Tests Running on firefox browser");
				    break;
				    
		    	default:
		    		throw new InvalidBrowserException();
		    	
		 }
				    
			return getDriverInstance(); //returning a thread safe and ThreadLocal WebDriver object
	}*/
	
	// If you want to run the test cases in some remote machine using Selenium GRID
	
	/*public WebDriver init_driver(Properties prop) throws Exception
	{
		optionsManager=new OptionsManager(prop);
		
		DesiredCapabilities cap=null;
		
		switch(System.getProperty("browser"))
		{
				case "chrome":
					
					WebDriverManager.chromedriver().setup();
					
					cap=DesiredCapabilities.chrome();
					
					cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
					
					try
					{
						tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")),cap));
					}
					catch(MalformedURLException e)
					{
						e.getMessage();
					}
					
					System.out.println("Tests Running on chrome browser");
					
					break;
					
				case "firefox":
					
					WebDriverManager.firefoxdriver().setup();
					
					cap=DesiredCapabilities.firefox();
					
					cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
					
					try
					{
						tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")),cap));
					}
					catch(MalformedURLException e)
					{
						e.getMessage();
					}
					
					System.out.println("Tests Running on firefox browser");
					
					break;
				    
		    	default:
		    			throw new InvalidBrowserException();
		    	
		 }
				    
			return getDriverInstance(); //returning a thread safe and ThreadLocal WebDriver object
	}*/
	
	
	
	
	
}
