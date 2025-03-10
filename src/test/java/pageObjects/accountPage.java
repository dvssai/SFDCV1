package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utilities.CommonUtils;

public class accountPage extends basePage{

	public accountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//a[@title = 'New']")
	WebElement btnAccountnew;
	
	@FindBy(xpath = "//input[@name = 'Name']")
	WebElement txtAccountname;
	
	@FindBy(xpath = "//button[@name ='SaveEdit']")
	WebElement btnSaveaccount;
	
	@FindBy(xpath = "//h1/child::slot/lightning-formatted-text")
	WebElement lblAccountname;
	
	CommonUtils utils = new CommonUtils(driver);
	
	public void createAccount(String name) {
		CommonUtils.clickOnElement(btnAccountnew);
		utils.Type(txtAccountname, name);
		CommonUtils.clickOnElement(btnSaveaccount);
		
	}
	
	public boolean verifyAccount(String name) {
		return (lblAccountname.getText().equals(name)); 
	}

}
