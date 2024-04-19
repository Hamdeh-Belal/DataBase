
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBConnecter {

	Connection con = null;

	public static Connection connectDB() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Extreme_gaming?autoReconnect=true&useSSL=false", "root",
					"BeLaL17%S");
			return con;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

}