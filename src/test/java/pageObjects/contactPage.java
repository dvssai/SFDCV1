package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.CommonUtils;

public class contactPage extends basePage {
	private WebDriverWait wait;

	public contactPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));;
	}
	
	@FindBy(xpath="//button[@name = 'NewContact']")
	WebElement btnNewcontact;
	
	@FindBy(xpath = "//button[@name = 'salutation']")
	WebElement Salutationdrpdown;
	
	@FindBy(xpath = "//input[contains(@name ,'first')]")
	WebElement txtFirstname;
	
	@FindBy(xpath = "//input[contains(@name ,'last')]")
	WebElement txtLastname;
	
	@FindBy(xpath = "//button[@name ='SaveEdit']")
	WebElement btnSavecontact;
	
	@FindBy(xpath = "//h1/child::slot/lightning-formatted-name")
	WebElement lblContactname;
	
	CommonUtils utils = new CommonUtils(driver);
	
	public void createContact(String Firstname,String Lastname) throws InterruptedException {
		CommonUtils.clickOnElement(btnNewcontact);
		utils.Type(txtFirstname,Firstname);
		utils.Type(txtLastname, Lastname);
		CommonUtils.clickOnElement(Salutationdrpdown);
		Thread.sleep(5000);
		List <WebElement> options = driver.findElements(By.xpath("//lightning-base-combobox-item"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//lightning-base-combobox-item")));
		for(WebElement option : options) {
			String text = option.getText();
			if(text.equals("Mr.")) {
				
				option.click();
				wait.until(ExpectedConditions.invisibilityOf(option));
			}
				
		}
		CommonUtils.clickOnElement(btnSavecontact);
	}
	
	public boolean verifyContact(String name) {
		return (lblContactname.getText().equals(name)); 
	}
	
	
}
