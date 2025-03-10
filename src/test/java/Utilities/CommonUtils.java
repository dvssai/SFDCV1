package Utilities;


import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.basePage;

public class CommonUtils extends basePage {
	
	
	
	private WebDriver driver;
	private static WebDriverWait wait;


	public CommonUtils(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		CommonUtils.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	
	
	public static void clickOnElement(WebElement ele) {
		//ScrolltoElement(ele);
		
		wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
	}
	
	public void Type(WebElement ele,String str) {
		wait.until(ExpectedConditions.visibilityOf(ele)).clear();
		ele.sendKeys(str);
	}
	public void handleUnexpectedAlerts() {
        try {
        	
            Alert alert = driver.switchTo().alert();
            System.out.println("Unexpected alert found: " + alert.getText());
            alert.dismiss(); // Or alert.accept() if you want to accept the alert
            System.out.println("Alert dismissed.");
        } catch (NoAlertPresentException e) {
            // No alert present, which is expected in many cases.
            System.out.println("No unexpected alert found.");
        } catch (Exception e) {
            // Handle other potential exceptions (e.g., WebDriverException)
            System.err.println("An error occurred while handling the alert: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        }

	}
	
	public static String randomString (int len) {
		String randomString =RandomStringUtils.randomAlphabetic(len);
		return randomString;
	}
	
	
	public static String randomAlphanumeric (int len) {
		String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(len);
		return randomAlphanumeric;
	}
	
	public static void clickElementJS(WebDriver driver ,WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
	
	public void Salutationdrpdown(WebElement Salutationdrpdown,WebElement txtFirstname, WebElement txtLastname,String Fname,String Lname) {
	CommonUtils.clickOnElement(Salutationdrpdown);
	List <WebElement> options = driver.findElements(By.xpath("//lightning-base-combobox-item"));
	for(WebElement option : options) {
		String text = option.getText();
		if(text.equals("Mr.")) {
			clickOnElement(option);
			wait.until(ExpectedConditions.invisibilityOf(option));
		}
		Type(txtFirstname,Fname);
		Type(txtLastname, Lname);	
	}
	}
	
	
}
