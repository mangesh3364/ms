package com.flip.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.flip.qa.util.TestUtil;
import com.flip.qa.util.WebEventListener;

public class TestBase {
	
	
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public static WebDriver driver;

	    // Locators
	    private By usernameField = By.id("username");
	    private By passwordField = By.id("password");
	    private By loginButton = By.id("login-btn");

	    public TestBase(WebDriver driver) {
	        TestBase.driver = driver;
	    }

	    public void enterUsername(String username) {
	        WebElement usernameInput = driver.findElement(usernameField);
	        usernameInput.clear();
	        usernameInput.sendKeys(username);
	    }

	    public void enterPassword(String password) {
	        WebElement passwordInput = driver.findElement(passwordField);
	        passwordInput.clear();
	        passwordInput.sendKeys(password);
	    }

	    public void clickLoginButton() {
	        WebElement loginBtn = driver.findElement(loginButton);
	        loginBtn.click();
	    }
	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/flip"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/main/resources/drivers/chromedriver.exe");
			
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--incognito");
//			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			
//			driver = new ChromeDriver(capabilities); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "/src/main/resources/drivers/geckodriver");	
			driver = new FirefoxDriver(); 
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	
	
	
	
	
	
	
	

}
