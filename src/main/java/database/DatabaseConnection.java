package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseConnection
{
	Connection conn = null;

	/////////////////// CONNECTION TO DATABASE ///////////////////
	public void connectDB() throws ClassNotFoundException
	{
		final String DB_URL = "";

		final String USER = "";
		final String PASS = "";

		Class.forName("com.mysql.jdbc.Driver");

		System.out.println("Connecting to a selected database...");
		try
		{
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
