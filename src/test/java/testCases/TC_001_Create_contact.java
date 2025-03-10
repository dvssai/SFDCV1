package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.CommonUtils;
import pageObjects.contactPage;
import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.setupPage;
import testBase.baseClass;

public class TC_001_Create_contact extends baseClass{
	
	
	@Test
	public void createContact() {
	new loginPage(driver);	
	new setupPage(driver);
	homePage hp = new homePage(driver);
	contactPage cp = new contactPage(driver);
	
	String Firstname = CommonUtils.randomString(4).toLowerCase();
	String Lastname  = CommonUtils.randomString(4).toLowerCase();
	String name = "Mr." +" "+ Firstname +" "+ Lastname;
	
	
	try {
	hp.Clickcontact();
	log.info("Clicked on contact sucessfully.");
	
	}
	catch(Exception e ) {
		log.error("Failed to click on contact" +e);
		e.printStackTrace();
	}
	
	
	try {
		cp.createContact(Firstname, Lastname);
		log.info("contact created sucessfully.");
	}
	
	catch(Exception e ) {
		log.error("Failed to create new Contact");
	}

	try {
		
		Assert.assertEquals(cp.verifyContact(name), true);
		log.info("Contact verified successfully");
		
	}
	catch(Exception e) {
		Assert.fail();
		System.out.println("Failed at verfying new contact"+e);
		log.error("Failed at verfying new contact");
	}
	
	}
	
	}

