package resources;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public WebDriver driver;
	public String path1=System.getProperty("user.dir");
	private static final String OUTPUT_FOLDER = "./reports/Screenshots/";
	private static String FILE_NAME;
	public String getScreenshot(String TestcaseName, WebDriver driver) throws IOException {
		String date =  new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
		String date2 =  new SimpleDateFormat("ddMMyyyyhhmm").format(new Date());
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		FILE_NAME="Screenshot_"+TestcaseName+"_"+date2+".png";
		/*Path Folderpath=Paths.get(OUTPUT_FOLDER);
		
		if (!Files.exists(Folderpath)) {
			try {
				Files.createDirectories(Folderpath);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		String destinationFile2=OUTPUT_FOLDER + FILE_NAME;*/
		String destinationFile=path1+ "/reports/Screenshots/" +FILE_NAME;
		FileUtils.copyFile(src, new File(destinationFile));
		return destinationFile;
	}
}
