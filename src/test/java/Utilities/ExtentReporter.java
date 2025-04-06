package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import testBase.baseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporter {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public String repName;


    public void onStart(String name){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
        repName = name + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report

        sparkReporter.config().setDocumentTitle("Salesforce Automation Report"); // Title of report
        sparkReporter.config().setReportName("Salesforce Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Salesforce");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environemnt", "QA");
    }
    public void OntestStart(String name){
        test = extent.createTest(name);
    }
    public void onTestSuccess(String name ) {
        test.assignCategory(); // to display groups in report
        test.log(Status.PASS, name + " got successfully executed");
    }
    public void onTestFailure(String name ) {
        test.log(Status.FAIL, name + " got failed");

        try {
            String imgPath = new baseClass().captureScreen(name);
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(String name , String thr) {
        test.log(Status.SKIP, name + " got skipped");
        test.log(Status.INFO, thr);
    }

    public void onFinish(String repName) {
        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        if (extentReport.exists()) {
            try {
                Desktop.getDesktop().browse(extentReport.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Report file not found: " + pathOfExtentReport);
        }
    }
}
