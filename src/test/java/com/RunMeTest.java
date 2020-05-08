package com;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class RunMeTest {

  @Test
  public void runMeNowTest() throws InterruptedException {
    System.out.println("Hello World");
    
    //System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver_win32\\chromedriver.exe");		
	
    WebDriver driver = null;
    WebDriverManager.chromedriver().version("81.0.4044").setup();
    
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized"); 
    options.addArguments("enable-automation"); 
    options.addArguments("--no-sandbox"); 
    options.addArguments("--disable-infobars");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-browser-side-navigation"); 
    options.addArguments("--disable-gpu"); 
    
    driver = new ChromeDriver(); 
    
    
    //WebDriver driver = new ChromeDriver();		
	driver.get("https://www.bigbasket.com/");		
	driver.manage().window().maximize();	
	
	WebDriverWait wait=new WebDriverWait(driver,180);
	
	
	
	int banner_exist = driver.findElements(By.id("cityWidget")).size();
	
	if (banner_exist==1)
	{
		System.out.println("Banner Present");
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@value='Change Location']")).click();
		
		Thread.sleep(3000);
		
			
		WebElement element = driver.findElement(By.xpath("//input[@placeholder='Select your city']"));
		
		By caret_element = By.xpath("//i[@class='caret pull-right']");
		driver.findElement(caret_element).click();
		driver.findElement(By.xpath("//input[@placeholder='Select your city']")).sendKeys("Kolkata");
		driver.findElement(By.xpath("//input[@placeholder='Select your city']")).sendKeys(Keys.TAB);
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder='Enter your area / apartment / pincode']")).sendKeys("700028");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder='Enter your area / apartment / pincode']")).sendKeys(Keys.TAB);
				
		
		
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		
		///Checking if delivery slots are available///
		
	}
	
	else {
		System.out.println("Cannot Change location....");
		System.exit(0);
	}
	
	int msg_exists= driver.findElements(By.xpath("//label[@id='site_msg_label']")).size();
	
	if(msg_exists==1) {
		
		String msg = driver.findElement(By.xpath("//label[@id='site_msg_label']")).getText();
		System.out.println(msg);
		
		String expected_test = "Dear Customer, today more than 45% of customers in your city have slots available. We are working hard to ensure all customers can find slots.";
		if(msg.equalsIgnoreCase(expected_test))
		{
			org.testng.Assert.fail("Slots are unavailable");
		}
		else
		{
			System.out.println("Slots available..");
		}
	}
	else {
		System.out.println("Check for errors...");
		System.exit(0);
	}
	
	
	
  }
}
