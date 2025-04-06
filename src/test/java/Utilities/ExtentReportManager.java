package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import testBase.baseClass;


public class ExtentReportManager  implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public String repName;
    public String name;
    ExtentReporter extentReporter = new ExtentReporter();

    public void onStart(ITestContext testContext) {
        name =  testContext.getName();
        if(testContext != null){
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
            //name =  testContext.getName();
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

            String os = testContext.getCurrentXmlTest().getParameter("os");
            extent.setSystemInfo("Operating System", os);

            String browser = testContext.getCurrentXmlTest().getParameter("browser");
            extent.setSystemInfo("Browser", browser);
        }
        else{
            extentReporter.onStart(name);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodname = result.getMethod().getMethodName();
        if(result != null) {
            test = extent.createTest(result.getMethod().getMethodName());
        }
        else{
            extentReporter.OntestStart(methodname);
        }

    }

    public void onTestSuccess(ITestResult result) {
        String methodname = result.getName();
        if(result != null) {
            test.assignCategory(result.getMethod().getGroups()); // to display groups in report
            test.log(Status.PASS, result.getName() + " got successfully executed");
        }
        else{
            extentReporter.onTestSuccess(methodname);
        }

    }

    public void onTestFailure(ITestResult result) {
        String methodname =   result.getName();
        if(result != null) {
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.FAIL, result.getName() + " got failed");
            test.log(Status.INFO, result.getThrowable().getMessage());

            try {
                String imgPath = new baseClass().captureScreen(result.getName());
                test.addScreenCaptureFromPath(imgPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else{
            extentReporter.onTestFailure(methodname);
        }
    }

    public void onTestSkipped(ITestResult result) {
        String methodname =  result.getName();
        String thr =  result.getThrowable().getMessage();

        if(result != null) {
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.SKIP, result.getName() + " got skipped");
            test.log(Status.INFO, result.getThrowable().getMessage());
        }
        else{
            extentReporter.onTestSkipped(methodname,thr);
        }
    }

    public void onFinish(ITestContext testContext) {
        if(testContext != null) {
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
        else{
            extentReporter.onFinish(repName);
        }
    }

    public ExtentTest getTest() {
        return test;
    }
}

