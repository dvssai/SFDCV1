package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pageObjects.loginPage;
import pageObjects.setupPage;

public class baseClass {
    public WebDriver driver;
    public Properties p;
    protected Logger log;

    @BeforeClass
    public void setup() throws IOException {
    	
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        log = LogManager.getLogger(this.getClass());
        log.info("Starting setup for baseClass.");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-notifications");

        try {
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get(p.getProperty("URL"));
            driver.manage().window().maximize();
            log.info("Browser launched and URL opened successfully.");
        } catch (Exception e) {
            log.error("Error during browser setup or URL opening: " + e.getMessage());
            throw new RuntimeException("Failed to setup WebDriver.", e);
        }

        log.info("Setup completed successfully.");
    }

    @BeforeMethod
    public void login() {
        loginPage lp = new loginPage(driver);
        setupPage sp = new setupPage(driver);
        try {
            lp.login(p.getProperty("username"), p.getProperty("password"));
            log.info("User Logged into Sucessfully.");
        } catch (Exception e) {
            log.error("Failed to login into salesforce sucessfully.");
        }

        try {
            sp.navigateToSales();
            log.info("Navigated to Sales sucessfully.");
        } catch (Exception e) {
            log.error("Failed to navigate to Sales" + e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed successfully.");
        } else {
            log.warn("WebDriver was null during teardown.");
        }

    }

    public String captureScreen(String tname) throws IOException {
        if (driver == null) {
            log.error("Driver is NULL!");
            return null;
        }
        try {
            String timeStamp = new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());
            org.openqa.selenium.TakesScreenshot takesScreenshot = (org.openqa.selenium.TakesScreenshot) driver;
            java.io.File sourceFile = takesScreenshot.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
            java.io.File targetFile = new java.io.File(targetFilePath);
            FileUtils.copyFile(sourceFile, targetFile);
            log.info("Screenshot captured at: " + targetFilePath);
            return targetFilePath;
        } catch (Exception e) {
            log.error("Error capturing screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}