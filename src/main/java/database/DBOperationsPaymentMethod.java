package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.PaymentMethod;

public class DBOperationsPaymentMethod
{
	DatabaseConnection dbCon = new DatabaseConnection();

	Statement stmt = null;

	public ArrayList<PaymentMethod> getAllPaymentMethods() throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<PaymentMethod> allPMethods = new ArrayList<>();

		stmt = dbCon.conn.createStatement();
		String sql = "SELECT * FROM paymentmethods";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setId(rs.getInt("ID"));
			paymentMethod.setName(rs.getString("name"));
			allPMethods.add(paymentMethod);
		}
		rs.close();
		stmt.close();
		dbCon.conn.close();

		return allPMethods;
	}

	public boolean createPaymentMethod(String name) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "insert into paymentmethods (name) values (?)";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, name);

			preparedStmt.execute();
			isSuccesfull = true;
			preparedStmt.close();
		}
		catch (SQLException e)
		{
			isSuccesfull = false;
			e.printStackTrace();
		}
		finally
		{
			dbCon.conn.close();
		}
		return isSuccesfull;
	}

	public boolean updatePaymentMethod(int paymentMethodID, String name) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "UPDATE paymentmethods SET  name = ? WHERE ID = ?";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, paymentMethodID);

			preparedStmt.execute();
			isSuccesfull = true;
			preparedStmt.close();
		}
		catch (SQLException e)
		{
			isSuccesfull = false;
			e.printStackTrace();
		}
		finally
		{
			dbCon.conn.close();
		}
		return isSuccesfull;
	}

	public boolean deletePaymentMethod(int paymentMethodID) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "DELETE FROM paymentmethods WHERE ID = ? ";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, paymentMethodID);

			preparedStmt.execute();
			isSuccesfull = true;
			preparedStmt.close();
		}
		catch (SQLException e)
		{
			isSuccesfull = false;
			e.printStackTrace();
		}
		finally
		{
			dbCon.conn.close();
		}
		return isSuccesfull;
	}

}
