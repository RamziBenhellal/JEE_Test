package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Database {

	protected Connection connection ;
	
	// Database attribute
	private final String database = "schooloffice";
	private final String user = "root";
	private final String password = "";
	
	public Database() {
		this.connection = null;
	}

	public void loadDatabase() {
		// Load driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			this.connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+database, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
