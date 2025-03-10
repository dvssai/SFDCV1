package Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class log4j2ExtentIntegration  {

    private static final Logger log = LogManager.getLogger(log4j2ExtentIntegration.class);

    public static void main(String[] args) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-spark-report.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        ExtentTest test = extent.createTest("Log4j 2 Integration Test (No ListAppender)");

        // Redirect System.out to capture logs
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        PrintStream originalOut = System.out;
        System.setOut(ps);

        log.info("This is an info log message (Log4j 2).");
        log.warn("This is a warning log message (Log4j 2).");
        log.error("This is an error log message (Log4j 2).");

        // Restore original System.out
        System.out.flush();
        System.setOut(originalOut);

        String logContent = os.toString();

        test.log(Status.INFO, "Log4j 2 Logs:\n" + logContent);

        extent.flush();
    }
}