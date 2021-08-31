package resources;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {
	public static WebDriver driver;
	public Properties prop;
	String path = System.getProperty("user.dir");
	public String browserName;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public FileInputStream fis;

	// for DB Connection

	public static String url = "jdbc:oracle:thin:@qadb2.aur.test.ziprealty.com:1521:qa2db";
	public static String user = "bdr";
	public static String password = "Zap#QA#2018";
	public Connection con;
	public Statement smt;
	public ResultSet dbresults;
	
	public static Logger log=LogManager.getLogger(base.class.getName());
	// public Properties prop;
	// String path=System.getProperty("user.dir");
	//public String query;

	public WebDriver initializeDrver() throws IOException {
		prop = new Properties();
		fis = new FileInputStream(path + "//src//main//java//resources//data.properties");
		prop.load(fis);
		// To Send Parameters of browser from maven command
		// mvn test-Dbrowser
		// String browserName=System.getProperty("browser");
		String browserValue = System.getProperty("browser");
		if (browserValue != null) {
			browserName = browserValue;
		} else {
			browserName = prop.getProperty("browser");
		}

		// To Load from the Properties Files
		// browserName=prop.getProperty("browser");
		String url = prop.getProperty("url");

		if (browserName.contains("chrome")) {
			String status = prop.getProperty("headless");
			System.setProperty("webdriver.chrome.driver", path + "//browsers//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();

			if ((status.contains("true")) || browserName.contains("headless")) {
				options.addArguments("headless");
			}
			/*
			 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
			 */
			driver = new ChromeDriver(options);

			// WebDriverManager.chromedriver().setup();
			/*
			 * System.setProperty("webdriver.chrome.driver",path+
			 * "//browsers//chromedriver.exe"); driver = new ChromeDriver();
			 */
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", path + "//browsers//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.get(url);
		return driver;
	}

	// Database connection
	public void create_dbconnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		con = DriverManager.getConnection(url, user, password);
		log.info("Db Connection Made Successfully");
	}

	public ResultSet executequery_dbconnection(String query) throws SQLException, IOException {
		// FileInputStream fis=new
		// FileInputStream(path+"//src//main//java//resources//data.properties");
		//prop.load(fis);
		//String query=System.getProperty(tableName);
		smt = con.createStatement();
		dbresults = smt.executeQuery(query);
		log.info("Query Executed Succesfully");
		return dbresults;
	}

	public void close_dbconnection() throws SQLException {
		con.close();
		log.info("Connection closed successfully");
	}
	
	
	public ArrayList<String> dataimport(String SheetName,String QueryName) throws IOException {
		ArrayList<String> a=new ArrayList<String>();
		FileInputStream fis = new FileInputStream(path + "\\TestData.xlsx");
		XSSFWorkbook wrkbk=new XSSFWorkbook(fis);
		int sheetscount=wrkbk.getNumberOfSheets();
		for (int i=0; i<sheetscount; i++) {
			if(wrkbk.getSheetName(i).equalsIgnoreCase(SheetName)) {
				//Getting Access to Sheet
				XSSFSheet sh=wrkbk.getSheetAt(i);
				Iterator<Row> rows= sh.iterator();
				//Getting Access to Rows
				Row data=rows.next();
				Iterator<Cell> cells=data.cellIterator();
				//int k=0;
				int column=0;
				/*while(cells.hasNext()) {
					Cell value=cells.next();
					if(value.getStringCellValue().equalsIgnoreCase("Table Name")) {
						column=k;
					}
					k++;
				}*/
				
				//System.out.println(column);
				
				while(rows.hasNext()) {
					Row r=rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(QueryName)) {
						Iterator<Cell> cv=r.cellIterator();
						while(cv.hasNext()) {
							Cell c=cv.next();
							if (c.getCellTypeEnum() == CellType.STRING) {
								a.add(c.getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
	return a;
	}
	

}
