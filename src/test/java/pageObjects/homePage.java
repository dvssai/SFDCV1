package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Utilities.CommonUtils;

public class homePage  extends basePage{
	
	public homePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

	@FindBy(xpath = "//a[@title = 'Contacts']")
	WebElement Contact;
	
	@FindBy(xpath = "//a[@title = 'Leads']")
	WebElement Leads;
	
	@FindBy(xpath = "//a[@title = 'Accounts']")
	WebElement Accounts;
		
	
	
	CommonUtils utils = new CommonUtils(driver);

	
	
	public void Clickcontact() {
		CommonUtils.clickElementJS(driver, Contact);
	}
	
	public void Clicklead() {
		CommonUtils.clickElementJS(driver, Leads);
	}
	
	public void Clickaccount() {
		CommonUtils.clickElementJS(driver, Accounts);
	}
	
	

}
