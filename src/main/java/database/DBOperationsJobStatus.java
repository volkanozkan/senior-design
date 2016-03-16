package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.JobStatus;

public class DBOperationsJobStatus
{
	DatabaseConnection dbCon = new DatabaseConnection();

	Statement stmt = null;

	public ArrayList<JobStatus> getAllJobStatus() throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<JobStatus> allJS = new ArrayList<>();

		stmt = dbCon.conn.createStatement();
		String sql = "SELECT * FROM jobstatus";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			JobStatus jobStatus = new JobStatus();
			jobStatus.setId(rs.getInt("ID"));
			jobStatus.setName(rs.getString("name"));
			allJS.add(jobStatus);
		}
		rs.close();
		stmt.close();
		dbCon.conn.close();

		return allJS;
	}
	
	public boolean createJobStatus(String name) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "insert into jobstatus (name) values (?)";

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
	
	public boolean updateJobStatus(int jobstatusID, String name)
			throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "UPDATE jobstatus SET  name = ? WHERE ID = ?";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, jobstatusID);

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
	
	public boolean deleteJobStatus(int jobstatusID) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "DELETE FROM jobstatus WHERE ID = ? ";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, jobstatusID);

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
