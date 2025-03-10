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
	
public class leadPage extends basePage {

	private WebDriverWait wait;

	public leadPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));;
	}
	
	@FindBy(xpath = "//button[@name = 'New']")
	WebElement btnNewlead;
	
	@FindBy(xpath = "//button[@name = 'salutation']")
	WebElement Salutationdrpdown;
	
	@FindBy(xpath="//input[@name = 'Company']")
	WebElement txtCompany;
	
	@FindBy(xpath = "//input[contains(@name ,'first')]")
	WebElement txtFirstname;
	
	@FindBy(xpath = "//input[contains(@name ,'last')]")
	WebElement txtLastname;
	
	@FindBy(xpath = "//button[@name ='SaveEdit']")
	WebElement btnSavelead;
	

	@FindBy(xpath = "//h1/child::slot/lightning-formatted-name")
	WebElement lblLeadname;
	
	CommonUtils utils = new CommonUtils(driver);
	
	public void createLead(String Firstname , String Lastname) throws InterruptedException {
		CommonUtils.clickOnElement(btnNewlead);
		utils.Type(txtFirstname,Firstname);
		utils.Type(txtLastname, Lastname);
		utils.Type(txtCompany, CommonUtils.randomString(5));
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
		CommonUtils.clickOnElement(btnSavelead);
	}
	
	public boolean verifyLead(String name) {
		return (lblLeadname.getText().equals(name)); 
	}
	

}
