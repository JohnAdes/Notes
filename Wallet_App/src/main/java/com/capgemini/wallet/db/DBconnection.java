package com.capgemini.wallet.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection  {
	public static final String url = "jdbc:mysql://localhost:3306/wallet";
	public static final String username = "root";
	public static final String password = "password";
	private static Connection connection = null;

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url, username,password);
		return connection;
	}

	public static void getDBConnectionClose() throws SQLException {
		connection.close();
	}

}