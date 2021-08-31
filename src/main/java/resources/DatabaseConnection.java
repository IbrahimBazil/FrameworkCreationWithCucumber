package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.aventstack.extentreports.model.Log;

public class DatabaseConnection {
/*
	public static String url="jdbc:oracle:thin:@qadb2.aur.test.ziprealty.com:1521:qa2db";
	public static String user="bdr";
	public static String password="Zap#QA#2018";
	public Connection con;
	public Statement smt;
	public ResultSet dbresults;
	//public Properties prop;
	//String path=System.getProperty("user.dir");
	public String query;
	
	public void create_dbconnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		con=DriverManager.getConnection(url, user, password);
		
	}
	
	
	public ResultSet executequery_dbconnection(String queryname) throws SQLException, IOException {
		FileInputStream fis=new FileInputStream(path+"//src//main//java//resources//data.properties");
		prop.load(fis);
		query=System.getProperty(queryname);
		smt=con.createStatement();
		dbresults=smt.executeQuery(query);
		return dbresults;
	}
	
	public void close_dbconnection() throws SQLException {
		con.close();
	}
	
*/	
}
