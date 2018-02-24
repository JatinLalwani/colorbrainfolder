package com.play.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {
	
	private static Connection connection = null;
	static String url = "jdbc:mysql://localhost:3306/play";
	static String user = "root";
	static String password = "root";
	
	public static Connection createConnection() throws ClassNotFoundException,
	SQLException {
		try {
			//Driver Registration
			Class.forName("com.mysql.jdbc.Driver");
			//Establish Connection/session
			connection = DriverManager.getConnection(url,user,password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver class was not found :(");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Please input correct credentials");
			e.printStackTrace();
		}
		System.out.println("Connected!");
		return connection;
	}
}
