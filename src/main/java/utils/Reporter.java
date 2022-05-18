package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This is an abstract class for Reporter. It provides some common functionalities to its subclasses.
 */
public abstract class Reporter {

	/**
	 * This enum represents the status of each test cases.
	 */
	public enum Status {
		FAIL,
		INFO,
		PASS,
		WARN
	}

	public static final String REGION_NAME = "TopDoc";
	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest suiteTest;
	public static String testCaseName;
	public static String jenkinsBuildNumber;
	public static List<String> regionList;
	
	/**
	 * This method initializes the test report data structure.
	 */
	public void startResult() {
		regionList = new ArrayList<String>();
		String currentPAth = System.getProperty("user.dir");
		html = new ExtentHtmlReporter(currentPAth + "/reports/result.html");
		html.loadXMLConfig(currentPAth + "/src/main/resources/runnerconfig/extent.xml");
		html.setAppendExisting(false);
		extent = new ExtentReports();
		extent.attachReporter(html);
		extent.setSystemInfo("Application", "Rapido");
	}

	/**
	 * This method starts the test case and record the result in the report.
	 *
	 * @param testCaseName The name of the test case.
	 * @param testDescription Test description.
	 * @param platform Platform on which we are running the test.
	 * @return ExtentTest item that gets added to the test report.
	 */
	public synchronized ExtentTest startTestCase(String testCaseName, String testDescription, String platform) {
		test = extent.createTest(testCaseName, testDescription);
		return test;
	}


	/**
	 * This method add the test step in the report.
	 *
	 * @param desc Descrition of the step.
	 * @param status Status of the step.
	 * @param snap a flag to enable or disable screenshot capturing.
	 */
	public void reportStep(String desc, Status status, boolean snap) {
		if (status.equals(Status.PASS)) {
			test.pass(desc);
		} else if (status.equals(Status.FAIL)) {
			test.fail(desc);
			throw new RuntimeException();
		} else if (status.equals(Status.WARN)) {
			test.warning(desc);
		} else if (status.equals(Status.INFO)) {
			test.info(desc);
		}
	}


	public void reportStep(String desc, Status status) {
		reportStep(desc, status, true);
	}

	/**
	 * This method finalizes the report.
	 */
	public void endResult() {
		extent.flush();
	}

}