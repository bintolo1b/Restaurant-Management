package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			String url = "jdbc:mySQL://localhost:3306/qlnh";
			String username = "root";
			String password = "Trantai25022004";
			c = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return c;
	}
}