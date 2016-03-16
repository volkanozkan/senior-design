package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;
import utility.Utility;

public class DBOperationsUser
{
	DatabaseConnection dbCon = new DatabaseConnection();
	Utility utitlity = new Utility();

	Statement stmt = null;

	///// CHECK IS user is active OR NOT.. RETURN TRUE OR FALSE....
	public boolean isUserActive(String username) throws ClassNotFoundException, SQLException
	{
		dbCon.connectDB();

		boolean isActive = false;

		String sql = "SELECT isActive FROM user WHERE username LIKE ?";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setString(1, username);
		ResultSet rs = preparedStmt.executeQuery();

		if (rs.next() && rs.getString("isActive").charAt(0) == '1')
		{
			isActive = true;
		}

		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return isActive;
	}

	///// CHECK IS LOGIN SUCCESFULL OR NOT.. RETURN TRUE OR FALSE....
	public boolean isLoginSuccesfull(String username, String passwordUserTyped) throws ClassNotFoundException, SQLException
	{
		dbCon.connectDB();

		String pw = utitlity.cryptWithMD5(passwordUserTyped);
		boolean isSuccesfull = false;

		stmt = dbCon.conn.createStatement();
		String sql = "SELECT username, password FROM user";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			if (rs.getString("username").equals(username) && pw.equals(rs.getString("password")))
			{
				isSuccesfull = true;
			}
		}
		rs.close();
		stmt.close();
		dbCon.conn.close();

		return isSuccesfull;
	}

	///// CHECK IS REGISTER SUCCESFULL OR NOT.. RETURN TRUE OR FALSE....
	public boolean isSignUpSuccesfull(String name, String surname, String email, String username, String password,
			String address, String city, Date dateOfBirth) throws ClassNotFoundException
	{
		boolean isSuccesfull = false;

		String query = "insert into user (name, surname, email, username, password, adress, city, dateOfBirth)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, surname);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, username);
			preparedStmt.setString(5, utitlity.cryptWithMD5(password));
			preparedStmt.setString(6, address);
			preparedStmt.setString(7, city);
			preparedStmt.setDate(8, dateOfBirth);

			preparedStmt.execute();
			isSuccesfull = true;
			preparedStmt.close();
			dbCon.conn.close();
		}
		catch (SQLException e)
		{
			isSuccesfull = false;
			e.printStackTrace();
		}
		return isSuccesfull;
	}

	///// GET USER INFORMATIONS. INPUT USERNAME, RETURN USER...
	public User getUserInformation(String username) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		User user = null;

		String sql = "SELECT * FROM user WHERE username LIKE ?";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setString(1, username);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next())
		{
			user = new User();
			user.setId(rs.getInt("ID"));
			user.setName(rs.getString("name"));
			user.setSurname(rs.getString("surname"));
			user.setEmail(rs.getString("email"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setAdress(rs.getString("adress"));
			user.setCity(rs.getString("city"));
			user.setDateOfBirth(rs.getDate("dateOfBirth"));
			user.setIsActive(rs.getString("isActive").charAt(0));
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return user;
	}

	//// uptade user account
	public boolean uptadeUserAccount(String name, String surname, String email, String username, String password,
			String address, String city, Date dateOfBirth, int userid, char isActive) throws ClassNotFoundException
	{
		boolean isSuccesfull = false;

		String query = "UPDATE user SET name = ?, surname = ?, email = ?, username = ?, "
				+ "password = ?, adress = ?, city = ?, dateOfBirth = ?, isActive = ? WHERE ID = ?";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, surname);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, username);
			preparedStmt.setString(5, utitlity.cryptWithMD5(password));
			preparedStmt.setString(6, address);
			preparedStmt.setString(7, city);
			preparedStmt.setDate(8, dateOfBirth);
			preparedStmt.setString(9, String.valueOf(isActive));
			preparedStmt.setInt(10, userid);

			preparedStmt.execute();
			isSuccesfull = true;
			preparedStmt.close();
			dbCon.conn.close();
		}
		catch (SQLException e)
		{
			isSuccesfull = false;
			e.printStackTrace();
		}
		return isSuccesfull;

	}

	//// GET ALL USERNAMES IN USER TABLE.. RETURN ARRAYLIST....
	/// USED BEFORE REGISTER AND GET USER INFO..
	public boolean isUsernameAvailableForSignUp(String username) throws ClassNotFoundException, SQLException
	{
		dbCon.connectDB();

		boolean isUsernameAvailable = true;
		stmt = dbCon.conn.createStatement();
		String sql = "SELECT username FROM user";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			if (rs.getString("username").equals(username))
			{
				isUsernameAvailable = false;
			}
		}
		rs.close();
		dbCon.conn.close();

		return isUsernameAvailable;
	}

	//// GET ALL emails IN USER TABLE.. RETURN ARRAYLIST....
	/// USED BEFORE REGISTER..
	public boolean isEmailAvailableForSignUp(String email) throws ClassNotFoundException, SQLException
	{
		dbCon.connectDB();

		boolean isEmailAvailable = true;
		stmt = dbCon.conn.createStatement();
		String sql = "SELECT email FROM user";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			if (rs.getString("email").equals(email))
			{
				isEmailAvailable = false;
			}
		}
		rs.close();
		dbCon.conn.close();

		return isEmailAvailable;
	}

	public boolean isEmailAvailableForUptadeAccount(int userid, String email)
			throws ClassNotFoundException, SQLException
	{
		dbCon.connectDB();

		boolean isEmailAvailable = true;
		stmt = dbCon.conn.createStatement();
		String sql = "SELECT email FROM user WHERE email NOT IN (SELECT email FROM user WHERE ID = ?)";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, userid);

		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next())
		{
			if (rs.getString("email").equals(email))
			{
				isEmailAvailable = false;
			}
		}
		preparedStmt.close();
		rs.close();
		dbCon.conn.close();

		return isEmailAvailable;
	}

	public boolean isUsernameAvailableForUptadeAccount(int userid, String username)
			throws ClassNotFoundException, SQLException
	{
		dbCon.connectDB();

		boolean isUsernameAvailable = true;
		stmt = dbCon.conn.createStatement();
		String sql = "SELECT username FROM user WHERE username NOT IN (SELECT username FROM user WHERE ID = ?)";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, userid);

		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next())
		{
			if (rs.getString("username").equals(username))
			{
				isUsernameAvailable = false;
			}
		}
		preparedStmt.close();
		rs.close();
		dbCon.conn.close();

		return isUsernameAvailable;
	}

	//// GET ALL IDS IN any TABLE.. RETURN ARRAYLIST....
	public ArrayList<Integer> getAllIDsOfAnyTable(String tableName) throws ClassNotFoundException, SQLException
	{
		dbCon.connectDB();
		ArrayList<Integer> usernameList = new ArrayList<>();

		stmt = dbCon.conn.createStatement();
		String sql = "SELECT ID FROM " + tableName;
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			usernameList.add(rs.getInt("ID"));
		}
		rs.close();
		dbCon.conn.close();

		return usernameList;
	}

}
