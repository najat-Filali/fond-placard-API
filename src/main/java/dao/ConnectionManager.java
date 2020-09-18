package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static Connection cnx = null;

	private ConnectionManager() {
		initialize();
	}

	private void initialize() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			close();
			cnx = DriverManager.getConnection("jdbc:mysql://localhost:8080/FondPlacard", "root", "");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return cnx;
	}

	public void close() {
		if (cnx != null) {
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
