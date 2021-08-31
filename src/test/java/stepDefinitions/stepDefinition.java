package stepDefinitions;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import pageObjects.Sign_pageobjects;
import pageObjects.courseHomePage_pageObjects;
import resources.base;

import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
public class stepDefinition extends base {

	public WebDriver driver;

	@Given("^Initialize the Browser$")
	public void initialize_the_browser() throws Throwable {
		// throw new PendingException();
		driver = initializeDrver();
	}

	@And("^lauch the url \"([^\"]*)\"$")
	public void lauch_the_url_something(String strArg1) throws Throwable {
		// throw new PendingException();
		driver.get("https://qaclickacademy.com/");
	}

	@And("^Click on the Signin button$")
	public void click_on_the_signin_button() throws Throwable {
		// throw new PendingException();
		Sign_pageobjects signin = new Sign_pageobjects(driver);
		signin.signinbutton().click();
	}

	@When("^user Enters the (.+) with password (.+)$")
	public void user_enters_the_with_password(String username, String password) throws Throwable {
		// throw new PendingException();
		Sign_pageobjects signin = new Sign_pageobjects(driver);
		signin.email().sendKeys(username);
		signin.password().sendKeys(password);
		signin.login().click();
	}

	@When("^user Enters the \"([^\"]*)\" with password \"([^\"]*)\" for happy$")
	public void user_enters_the_something_with_password_something_for_happy(String strArg1, String strArg2)
			throws Throwable {
		// throw new PendingException();
		Sign_pageobjects signin = new Sign_pageobjects(driver);
		signin.email().sendKeys(strArg1);
		signin.password().sendKeys(strArg2);
		signin.login().click();
	}

	@Then("^validate the error message$")
	public void validate_the_error_message() throws Throwable {
		// throw new PendingException();
		Sign_pageobjects signin = new Sign_pageobjects(driver);
		String errorMessage = signin.error().getText();
		/*
		 * if(errorMessage.contains("Invalid email or password")) {
		 * System.out.println(errorMessage); } else {
		 * System.out.println("Error in Validation"); }
		 */

		Assert.assertTrue(errorMessage.contains("Invalid email or password"));
		driver.close();
	}

	@Then("^userloggedin successfully$")
	public void userloggedin_successfully() throws Throwable {
		// throw new PendingException();
		courseHomePage_pageObjects cp = new courseHomePage_pageObjects(driver);
		Thread.sleep(3000);
		cp.seacrhbx().sendKeys("Selenium");
		cp.searchLookup().click();
		System.out.println("Testing");
		Assert.assertTrue(cp.results().isDisplayed(), "Successfully Displayed");

	}

	@And("^close the driver$")
	public void close_the_driver() throws Throwable {
		// throw new PendingException();
		driver.close();
	}

}