package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Homepage_pageObjects {
	public WebDriver driver;
	public Homepage_pageObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(how=How.XPATH, using="//div[@class=\"text-center\"]/h2")
	WebElement titletxt;
	
	@FindBy(how=How.XPATH, using="//ul[@class='nav navbar-nav navbar-right']")
	WebElement links;
	
	public WebElement title() {
		return titletxt;
	}
	
	public WebElement links() {
		return links;
	}
	
	
	
}
