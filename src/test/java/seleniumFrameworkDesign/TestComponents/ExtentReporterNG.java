package seleniumFrameworkDesign.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getExtentReport() {
		String reportPath = System.getProperty("user.dir") + "//reports//testReport.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setDocumentTitle("Pankil's Report");
		reporter.config().setReportName("New Test Report");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", System.getProperty("user.name"));
		extent.setSystemInfo("Platform", System.getProperty("os.name"));
		extent.setSystemInfo("Environment", "QA");

		return extent;

	}
}
