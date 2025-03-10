package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.CommonUtils;
import pageObjects.homePage;
import pageObjects.leadPage;
import testBase.baseClass;

public class TC_002_Create_lead extends baseClass{
	
	@Test
	public void createLead() {
		homePage hp = new homePage(driver);
		leadPage ldp = new leadPage(driver);
		
		String Firstname = CommonUtils.randomString(4).toLowerCase();
		String Lastname  = CommonUtils.randomString(4).toLowerCase();
		String name = "Mr." +" "+ Firstname +" "+ Lastname;
		
		try {
			hp.Clicklead();
		}
		catch(Exception e ) {
			System.out.println("Failed to click on lead"+e);
		}
		

		try {
			ldp.createLead(Firstname, Lastname);	
		}
		
		catch(Exception e ) {
			System.out.println("Failed to create new Lead"+e);
			e.printStackTrace();
		}
		
		
		try {
			
			Assert.assertEquals(ldp.verifyLead(name), true);
		}
		catch(Exception e) {
			Assert.fail();
			System.out.println("Failed at verfying new lead"+e);
		}
		
	}
}
