import java.sql.*;

public class DBUtil {
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/exercises?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	static final String USER_NAME = "root";
	static final String PASS = "1234";
	
	private Connection connection = null;
	private Statement statement = null;
	
	void connect() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER_NAME, PASS);
		} catch (Exception e) {
			System.out.println("connection failed");
			e.printStackTrace();
		}
	}
	
	void closeConnect() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	ResultSet query(String sql) {
		try {
			statement = connection.createStatement();
			return statement.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
