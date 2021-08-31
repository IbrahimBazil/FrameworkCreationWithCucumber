package resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporter {
	static ExtentReports extent;
	
	private static final String OUTPUT_FOLDER = "./reports/HTML Report/";
	private static String FILE_NAME;
	public static ExtentReports extentReportObj() {
		//String path1 = System.getProperty("user.dir")+"\\reports\\TestResults\\index.html";
		//ExtendSpark Reporter is helper class
		Path path = Paths.get(OUTPUT_FOLDER);
		String date2 =  new SimpleDateFormat("ddMMyyyyhhmm").format(new Date());
		FILE_NAME="TestExecutionResults_"+date2+".html";
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		String destinationFile=OUTPUT_FOLDER + FILE_NAME;
		ExtentSparkReporter report=new ExtentSparkReporter(destinationFile);
		report.config().setReportName("Ibrahim Automation");
		report.config().setDocumentTitle("Test_Results_Automation");
		//report.config().setTestViewChartLocation(ChartLocation.TOP);
		
		report.config().setTheme(Theme.DARK);
		//ExtentReports is the main class
		extent=new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "IbrahimBazil");
		return extent;
	}
	
	
}
