package bazil.FrameworkCreation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.Homepage_pageObjects;
import resources.base;

public class homePage extends base {
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeMethod
	public void launchingBrowser() throws IOException {
		driver = initializeDrver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Driver is Initaialised");
	}

	@Test
	public void titleValidation() {
		Homepage_pageObjects home = new Homepage_pageObjects(driver);
		String actualText = home.title().getText();
		String expectedText = "FEATURED COURSES";
		Assert.assertEquals(actualText, expectedText);
		Assert.assertTrue(home.links().isDisplayed());
		log.info("Successfully validated the Title");
	}

	@AfterMethod
	public void terminate() {
		driver.close();
	}
}
