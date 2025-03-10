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

public class setupPage extends basePage {
	private WebDriverWait wait;
	public setupPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	@FindBy(xpath="//span[@class='uiImage']")
	WebElement myProfile;
	
	@FindBy(xpath="//a[contains(text(),'Salesforce Classic')]")
	WebElement lnkClassic;
	
	@FindBy(xpath = "//button[@title = 'App Launcher']")
	WebElement btnApplauncher;
	
	
	@FindBy(xpath = "//a[@data-label = 'Sales']")
	WebElement optionSales;
	
	CommonUtils utils = new CommonUtils(driver);	
	public void navigateToSales() {
		CommonUtils.clickOnElement(btnApplauncher);
		List <WebElement> options = driver.findElements(By.xpath("//one-app-launcher-menu-item"));
		for(WebElement option : options) {
			String text = option.getText();
			if(text.equals("Sales")) {
				option.click();
				wait.until(ExpectedConditions.invisibilityOf(option));
			}
		}
		
	}
		
}
