package resources;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	static ExtentReports Extents;
	public static ExtentReports getEXReports()
	{
		Date cd = new Date();
		String Date1 = cd.toString().replace(" ", "-").replace(":", "-");
		String path = System.getProperty("user.dir")+"\\Reports\\index"+Date1+".html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Web Automation Results");
		report.config().setDocumentTitle("Test Results");
		
		Extents = new ExtentReports();
		Extents.attachReporter(report);
		Extents.setSystemInfo("Tester", "Venkat Y");
		return Extents;
	}

}
