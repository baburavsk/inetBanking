package com.inetBanking.utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DowloadFile {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		String DOWNLOAD_FOLDER_PATH="C:\\Users\\Babu\\Desktop\\New Selenium\\inetBankingV1\\Downloaded";
		File downloadFolder = new File(DOWNLOAD_FOLDER_PATH);
		downloadFolder.mkdir();
		
		Map<String,Object> preferences=new HashMap<>();
		preferences.put("download.default_directory",DOWNLOAD_FOLDER_PATH);
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", preferences);
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.fastenal.com/shopping-cart");
		
		driver.findElement(By.xpath("//*[@id=\"file-upload-copy-paste\"]/h2")).click();
		WebElement element=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[4]/div[1]/div/p/a"));
		
		JavascriptExecutor executor= (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(200); 
		WebElement upload=driver.findElement(By.xpath("//*[@id=\"fileUpload\"]/p/input[1]"));
		upload.sendKeys("C:\\Users\\Babu\\Desktop\\New Selenium\\inetBankingV1\\Downloaded\\CartUploadTemplate.xls");
		//driver.close();
	
		
		
		
	}

}
