package bazil.FrameworkCreation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.Sign_pageobjects;
import pageObjects.forgotpassword_pageobjects;
import resources.base;

public class forgotpassword_test extends base {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeMethod
	public void launchingBrowser() throws IOException {
		driver = initializeDrver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Driver is Initaialised");
	}
	
	@Test
	public void forgotpassword() {
		Sign_pageobjects sp=new Sign_pageobjects(driver);
		sp.signinbutton().click();
		forgotpassword_pageobjects fp=new forgotpassword_pageobjects(driver);
		fp.forgotpassword_link().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		fp.email_textbox().sendKeys("ibrahimbazil@gmail.com");
		fp.submit_btn().click();
		log.info("Validation completed Successfully for forgotpassword");
	}
	
	@AfterMethod
	public void terminate() {
		driver.close();
	}
	
	
}
