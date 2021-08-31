package pageObjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class forgotpassword_pageobjects {
	
	public WebDriver driver;
	
	
	public forgotpassword_pageobjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CSS, using = "a[href*=password]")
	WebElement forgotpwd;
	
	@FindBy(how=How.XPATH, using="//input[@id='user_email']")
	WebElement email;
	
	@FindBy(how=How.XPATH, using="//input[@type='submit']")
	WebElement submit;
	
	public WebElement forgotpassword_link() {
		return forgotpwd;	
	}
	
	public WebElement email_textbox() {
		return email;
	}
	
	public WebElement submit_btn() {
		return submit;
	}

}
