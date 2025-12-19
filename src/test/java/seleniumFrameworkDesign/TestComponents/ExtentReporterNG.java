package seleniumFrameworkDesign.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getExtentReport() {
		String reportPath = System.getProperty("user.dir") + "//reports//testReport.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setDocumentTitle("Pankil's Report");
		reporter.config().setReportName("New Report");
		reporter.config().setTimelineEnabled(true);
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pankil");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		
		
		return extent;
		
	}
}
