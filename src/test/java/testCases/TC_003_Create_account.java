package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.CommonUtils;
import pageObjects.accountPage;
import pageObjects.homePage;
import testBase.baseClass;

public class TC_003_Create_account extends baseClass{
	
	
	@Test
	public void createAccount() {
		
		homePage hp = new homePage(driver);
		accountPage ap = new accountPage(driver);
		
		String Firstname = CommonUtils.randomString(4).toLowerCase();
		String Lastname  = CommonUtils.randomString(4).toLowerCase();
		String name =   Firstname +" "+ Lastname;
		
		
			try {
				hp.Clickaccount();
			}
			catch(Exception e) {
				System.out.println("Failed to click on account"+e);
			}
			
			try {
				ap.createAccount(name);
			}
			catch(Exception e){
				System.out.println("Failed to create new account");
			}
			

			try {
				
				Assert.assertEquals(ap.verifyAccount(name), true);
			}
			catch(Exception e) {
				Assert.fail();
				System.out.println("Failed at verfying new account"+e);
			}
			
		
	}

}
