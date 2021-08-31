package bazil.FrameworkCreation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import resources.DatabaseConnection;
import resources.base;

public class dbtest extends base {

	
	
	@BeforeMethod
	public void createconnection() throws SQLException {
		create_dbconnection();
	}
	
	@Test
	public void database_validation() throws SQLException, IOException {
		//String query=System.getProperty("customerTable_query");
		//String query="select customer_id, login, bdr.customer.FIRST_NAME, LAST=_NAME, PASSWORD ,PASSWORD_RESET_DATE from bdr.customer where customer_id='13743196'";
		String SheetName="Query";
		String TableName="customer";
		ArrayList data = dataimport(SheetName,TableName);
		//String query=new String();
		String query=(String) data.get(1);
		ResultSet results=executequery_dbconnection(query);
		while(results.next()) {
			String login=results.getString("login");
			String first_name=results.getString("FIRST_NAME");
			System.out.println("Login id is: "+login);
			System.out.println("First Name is "+first_name);
		}
		
	}
	
	@AfterMethod
	public void closeconnection() throws SQLException {
		close_dbconnection();
	}
	
}
