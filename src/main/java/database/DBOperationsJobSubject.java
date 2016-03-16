package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.JobSubject;

public class DBOperationsJobSubject
{
	DatabaseConnection dbCon = new DatabaseConnection();

	Statement stmt = null;

	//// Get all job subjects
	public ArrayList<JobSubject> getAllJobSubjects() throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<JobSubject> allJs = new ArrayList<>();

		stmt = dbCon.conn.createStatement();
		String sql = "SELECT * FROM jobsubject";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			JobSubject jobsubject = new JobSubject();
			jobsubject.setId(rs.getInt("ID"));
			jobsubject.setName(rs.getString("name"));
			jobsubject.setDescription(rs.getString("description"));
			allJs.add(jobsubject);
		}
		rs.close();
		stmt.close();
		dbCon.conn.close();

		return allJs;
	}

	// check if user_jobsubject table have a row like that. check before follow
	// and unfollow..
	public boolean isUserAlreadyFollowingThatJobSubject(int userid, int jobsubjectid)
			throws ClassNotFoundException, SQLException
	{
		dbCon.connectDB();
		boolean isSuccesfull = false;

		stmt = dbCon.conn.createStatement();
		String sql = "SELECT * FROM user_jobsubject WHERE UserID = ? AND JobSubjectID = ? ";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, userid);
		preparedStmt.setInt(2, jobsubjectid);

		ResultSet rs = preparedStmt.executeQuery();
		if (rs.next())
		{
			isSuccesfull = true;
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return isSuccesfull;
	}

	/// unfollow jobsubjects
	public boolean jobSubjectUnfollow(int userid, int jobsubjectid) throws ClassNotFoundException
	{
		dbCon.connectDB();
		boolean isSuccesfull = false;

		String query = "DELETE FROM user_jobsubject WHERE  UserID = ? AND JobSubjectID = ?";

		try
		{
			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, userid);
			preparedStmt.setInt(2, jobsubjectid);
			preparedStmt.execute();
			preparedStmt.close();

			isSuccesfull = true;
			dbCon.conn.close();
		}
		catch (SQLException e)
		{
			isSuccesfull = false;
			e.printStackTrace();
		}
		return isSuccesfull;
	}

	/// follow jobsubjects
	public boolean jobSubjectFollow(int userid, int jobsubjectid) throws ClassNotFoundException
	{
		dbCon.connectDB();
		boolean isSuccesfull = false;

		String query = "INSERT INTO user_jobsubject (UserID, JobSubjectID) VALUES (?, ?)";

		try
		{
			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, userid);
			preparedStmt.setInt(2, jobsubjectid);
			preparedStmt.execute();
			preparedStmt.close();

			isSuccesfull = true;
			dbCon.conn.close();
		}
		catch (SQLException e)
		{
			isSuccesfull = false;
			e.printStackTrace();
		}
		return isSuccesfull;
	}

	//// GET FOLLOWING JOB SUBJECTS OF USER.INPUT USERID..RETURN JOBSUBJECT list
	public ArrayList<JobSubject> getFollowingJobSubjectListByUserID(int userid)
			throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<JobSubject> followingjobSubjectList = new ArrayList<>();

		String sql = "SELECT * FROM user_jobsubject INNER JOIN jobsubject on "
				+ "user_jobsubject.JobSubjectID = jobsubject.ID WHERE user_jobsubject.UserID LIKE ?";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, userid);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next())
		{
			JobSubject jobsubject = new JobSubject();
			jobsubject.setId(rs.getInt("jobsubject.ID"));
			jobsubject.setName(rs.getString("jobsubject.name"));
			jobsubject.setDescription(rs.getString("jobsubject.description"));
			followingjobSubjectList.add(jobsubject);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return followingjobSubjectList;
	}

	//// GET NOT FOLLOWING JOB SUBJECTS OF USER.INPUT USERID..RETURN JOBSUBJECT
	//// list
	public ArrayList<JobSubject> getNotFollowingJobSubjectListByUserID(int userid)
			throws SQLException, ClassNotFoundException
	{
		ArrayList<JobSubject> list = new ArrayList<>(getAllJobSubjects());
		list.removeAll(getFollowingJobSubjectListByUserID(userid));

		return list;
	}

	public boolean createJobSubject(String name, String description) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "insert into jobsubject (name, description) values (?, ?)";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, description);

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

	public boolean updateJobSubject(int jobsubjectid, String name, String description)
			throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "UPDATE jobsubject SET  name = ?, description = ? WHERE ID = ?";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, description);
			preparedStmt.setInt(3, jobsubjectid);

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

	public boolean deleteJobSubject(int jobsubjectID) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "DELETE FROM jobsubject WHERE ID = ? ";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, jobsubjectID);

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
