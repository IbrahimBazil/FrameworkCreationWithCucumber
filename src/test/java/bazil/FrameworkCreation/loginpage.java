package bazil.FrameworkCreation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Sign_pageobjects;
import resources.base;

public class loginpage extends base {
public WebDriver driver;
public static Sign_pageobjects signin;
public static Logger log=LogManager.getLogger(base.class.getName());
	@BeforeMethod
	public void launchingBrowser() throws IOException {
		driver = initializeDrver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Driver is Initaialised");
		
		
	}

	@Test
	public void loginpage_field_validation() {
		signin = new Sign_pageobjects(driver);
		signin.signinbutton().click();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		// driver.switchTo().alert().dismiss();
		signin.email().isDisplayed();
		signin.password().isDisplayed();
		signin.login().isDisplayed();
		log.info("Successfully validated All Fields in login Page");
	}

	@Test(dataProvider = "getData")
	public void loginpage_submit(String email, String password, String user) {
		signin = new Sign_pageobjects(driver);
		// driver.switchTo().alert().dismiss();
		signin.signinbutton().click();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		System.out.println(user);
		signin.email().sendKeys(email);
		signin.password().sendKeys(password);
		signin.login().click();
		log.info(user);
	}
	
	@AfterMethod
	public void terminate() {
		driver.close();
	}

	@DataProvider
	public Object[][] getData() {
		// Row Stand for how many different data types test should return
		// column stand for home many datas should be returned
		Object[][] data = new Object[2][3];
		// 1st Row
		data[0][0] = "ibrahimbazil@gmail.com";
		data[0][1] = "Hashim@3021";
		data[0][2] = "User_Admin";
		// 2nd Row
		data[1][0] = "testuser@gmail.com";
		data[1][1] = "testing@123";
		data[1][2] = "user_client";

		return data;

	}

}
