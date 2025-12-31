package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DBConenction {

	public static void main(String[] args) throws SQLException {

		String dbURL = "jdbc:mysql://localhost:3306/seleniumdbconnection";
		Connection dbConnect = DriverManager.getConnection(dbURL, "root", "root");
		Statement statement = dbConnect.createStatement();
		ResultSet result = statement.executeQuery("select * from logindetails");

		List<HashMap<String, String>> testData = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map;
		
		String key = "", value = "";
		
		while (result.next()) {
			key = result.getString("user_id");
			value = result.getString("user_password");
			map = new HashMap<String, String>();
			map.put(key, value);
			
			testData.add(map);
		}
		
		System.out.println(testData);

	}

}
