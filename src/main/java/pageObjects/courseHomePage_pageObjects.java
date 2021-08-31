package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class courseHomePage_pageObjects {
	public WebDriver driver;

	public courseHomePage_pageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how=How.XPATH, using="//input[@id='search-courses']")
	WebElement searchbox;
	
	@FindBy(how=How.XPATH, using="//button[@id='search-course-button']")
	WebElement search_lookup;
	
	@FindBy(how=How.XPATH, using="//div[contains(@title,'Selenium Design')]")
	WebElement result;
	
	
	public WebElement seacrhbx() {
		return searchbox;
	}
	
	public WebElement searchLookup() {
		return search_lookup;
	}
	
	public WebElement results() {
		return result;
		
	}
}
