package fr.pizzeria.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

	private static Connection connection = null;

	public static Connection openConnection() {
		Properties jdbcProperties = new Properties();
		try {
			jdbcProperties.load(Connection.class.getResourceAsStream("/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Class.forName(jdbcProperties.getProperty("driver"));
			connection = DriverManager.getConnection(jdbcProperties.getProperty("url"),
					jdbcProperties.getProperty("user"), jdbcProperties.getProperty("pwd"));
//			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;

	}

	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
