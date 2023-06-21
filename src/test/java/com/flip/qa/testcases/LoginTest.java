package com.flip.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.flip.qa.base.TestBase;


public class LoginTest {
    private WebDriver driver;
    private TestBase testbase;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        testbase= new TestBase(driver);
        driver.get("https://manojs1978-magical-kid.gluu.info/admin/home/dashboard"); 
    }

    @Test
    public void successfulLoginTest() {
    	
        String username = "your_username";
        String password = "your_password";

        testbase.enterUsername(username);
        testbase.enterPassword(password);
        testbase.clickLoginButton();

        // Add assertions to validate successful login
        Assert.assertEquals(driver.getCurrentUrl(), "https://manojs1978-magical-kid.gluu.info/admin/home/dashboard");
        // Add more assertions or validation steps if needed
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

	
	
	
	
	
	
