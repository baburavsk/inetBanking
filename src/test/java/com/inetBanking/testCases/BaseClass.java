package com.inetBanking.testCases;



import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	
	ReadConfig readConfig=new ReadConfig();
	
	public String baseURL= readConfig.getApplicationURL();
	public String username=readConfig.getUsername();
	public String password=readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) {
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");

		
		  if(br.equalsIgnoreCase("CHROME")) { WebDriverManager.chromedriver().setup();
		  driver= new ChromeDriver();
		  
		  } else if(br.equalsIgnoreCase("FIREFOX")) {
		  WebDriverManager.firefoxdriver().setup(); driver= new FirefoxDriver();
		  
		  }else if(br.equalsIgnoreCase("IE")) { WebDriverManager.iedriver().setup();
		  driver= new InternetExplorerDriver(); }
		 

		 
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	
	public void captureScreen(WebDriver driver,String tname) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png"); 
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
		
	}
}
