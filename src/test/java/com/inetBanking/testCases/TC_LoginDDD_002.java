package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDD_002 extends BaseClass {
	
	
	@Test(dataProvider = "LoginData")
	public void loginDDT(String username,String password) throws IOException {
		
		driver.get(baseURL);
		driver.manage().window().maximize();
		logger.info("URL is opened");
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("username is entered");
		lp.setPassword(password);
		logger.info("password is entered");
		lp.clickLogin();
		
		if(isAlertPresent()==true) {
			captureScreen(driver, "logintest");
			
			logger.info("login not successul");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			
		}
		else
		{
			logger.info("log in is successul");
			Assert.assertTrue(true);
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		
	}
	
	
	public boolean isAlertPresent() {
		
		try {
			driver.switchTo().alert();
			return true;
			
		}
		catch(NoAlertPresentException e){
			
			return false;
		}
		

	}
	
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/user_data.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData[][] =new String[rownum][colcount];
		
		
		for (int i = 1; i <=rownum; i++) {
			
			for (int j = 0; j <colcount; j++) {
				
				loginData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);//1 0
				
				
			}
			
		}
		
		return loginData;
		
	}

}
