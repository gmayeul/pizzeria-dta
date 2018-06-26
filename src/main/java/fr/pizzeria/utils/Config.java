package fr.pizzeria.utils;

import java.util.ResourceBundle;

public class Config {
	public static String driver;
	public static String url;
	public static String user;
	public static String pwd;
	public static String instanceName;

	public static void load() {
		ResourceBundle jdbcProperties = ResourceBundle.getBundle("jdbc");

		driver = jdbcProperties.getString("driver");
		url = jdbcProperties.getString("url");
		user = jdbcProperties.getString("user");
		pwd = jdbcProperties.getString("pwd");
		instanceName = jdbcProperties.getString("instanceName");

	}

}
