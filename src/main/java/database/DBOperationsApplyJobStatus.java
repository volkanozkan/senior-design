
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ApplyJobStatus;

public class DBOperationsApplyJobStatus
{
	DatabaseConnection dbCon = new DatabaseConnection();

	Statement stmt = null;
	//// Get all job subjects
	public ArrayList<ApplyJobStatus> getAllApplyJobStatus() throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<ApplyJobStatus> allApplyJobStatus = new ArrayList<>();

		stmt = dbCon.conn.createStatement();
		String sql = "SELECT * FROM applyjobstatus";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			ApplyJobStatus applyJobStatus = new ApplyJobStatus();
			applyJobStatus.setId(rs.getInt("ID"));
			applyJobStatus.setName(rs.getString("name"));
			allApplyJobStatus.add(applyJobStatus);
		}
		rs.close();
		stmt.close();
		dbCon.conn.close();

		return allApplyJobStatus;
	}

	public boolean createApplyJobStatus(String name) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "insert into applyjobstatus (name) values (?)";

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

	public boolean updateApplyJobStatus(int applyjobstatusID, String name) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "UPDATE applyjobstatus SET  name = ? WHERE ID = ?";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, applyjobstatusID);

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

	public boolean deleteApplyJobStatus(int applyjobstatusID) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "DELETE FROM applyjobstatus WHERE ID = ? ";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, applyjobstatusID);

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
