package testCases;
import Utilities.ExtentReporter;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.*;
import Utilities.CommonUtils;
import pageObjects.contactPage;
import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.setupPage;
import testBase.baseClass;

public class TC_001_Create_contact extends baseClass{
	ExtentReporter extentReporter = new ExtentReporter();
	String className = Thread.currentThread().getStackTrace()[1].getClassName();

	@BeforeTest
	public void reportSetup() {

		extentReporter.onStart(className);
		extentReporter.OntestStart(className);
	}

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
			extentReporter.test.log(Status.INFO,"Clicked on contact sucessfully.");

		}
		catch(Exception e ) {
			log.error("Failed to click on contact" +e);
			extentReporter.test.log(Status.FAIL,"Failed to click on contact" + e);
			e.printStackTrace();
		}


		try {
			cp.createContact(Firstname, Lastname);
			log.info("contact created sucessfully.");
			extentReporter.test.log(Status.INFO,"contact created sucessfully.");
		}

		catch(Exception e ) {
			log.error("contact created sucessfully.");
			extentReporter.test.log(Status.FAIL,"contact created sucessfully." + e);
		}

		try {

			Assert.assertEquals(cp.verifyContact(name), true);
			log.info("Contact verified successfully");
			extentReporter.test.log(Status.PASS,"Contact verified successfully");

		}
		catch(Exception e) {
			Assert.fail();
			log.error("Failed at verfying new contact");
			extentReporter.test.log(Status.FAIL,"Failed at verfying new contact");
		}
	}
	@AfterTest
	public void reportTeardown(){
		extentReporter.onFinish(className);
	}
}