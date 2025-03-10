package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utilities.CommonUtils;

public class loginPage extends basePage {

	public loginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//input[@name = 'username']")
    WebElement TxtUsername;

    @FindBy(xpath = "//input[@type = 'password']")
    WebElement TxtPassword;

    @FindBy(xpath = "//input[@name = 'Login']")
    WebElement btnLogin;
    
    CommonUtils  utils = new CommonUtils(driver); 
    
    public void login (String username, String password) {
    	utils.Type(TxtUsername, username);
    	utils.Type(TxtPassword, password);
    	CommonUtils.clickOnElement(btnLogin);
    	
    }

}
