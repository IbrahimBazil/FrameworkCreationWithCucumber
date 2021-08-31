package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Sign_pageobjects {

	public WebDriver driver;

	public Sign_pageobjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[contains(@href,'sign_in')]")
	WebElement signinbtn;

	@FindBy(how = How.XPATH, using = "//input[@id='user_email']")
	WebElement emailtxtbx;

	@FindBy(how = How.XPATH, using = "//input[@id='user_password']")
	WebElement passwordtxtbx;

	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	WebElement loginbtn;
	
	@FindBy(how=How.XPATH, using="//div[@role='alert']")
	WebElement errormsg;

	

	public WebElement signinbutton() {
		return signinbtn;
	}

	public WebElement email() {
		return emailtxtbx;
	}

	public WebElement password() {
		return passwordtxtbx;
	}

	public WebElement login() {
		return loginbtn;
	}
	
	public WebElement error() {
		return errormsg;
	}

}
