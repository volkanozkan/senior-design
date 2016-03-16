package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Job;
import model.User;

public class DbOperationsApplyJob
{
	DatabaseConnection dbCon = new DatabaseConnection();

	Statement stmt = null;

	/// başvuru yapılan işlere kimin başvurduğunu gör
	public ArrayList<User> getWorkerListWhoAppliedUsersJob(int jobid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<User> userList = new ArrayList<>();

		String sql = "SELECT * FROM applyjob INNER JOIN job on applyjob.JobID = job.ID INNER JOIN user on applyjob.ApplicantID = user.ID"
				+ " WHERE job.ID = ?";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, jobid);

		ResultSet rs = preparedStmt.executeQuery();

		while (rs.next())
		{
			User user = new User();
			user.setId(rs.getInt("user.ID"));
			user.setName(rs.getString("user.name"));
			user.setSurname(rs.getString("user.surname"));
			user.setEmail(rs.getString("user.email"));
			user.setUsername(rs.getString("user.username"));
			user.setAdress(rs.getString("user.adress"));
			user.setCity(rs.getString("user.city"));
			user.setDateOfBirth(rs.getDate("user.dateOfBirth"));

			System.out.println(user);
			userList.add(user);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return userList;
	}

	////// işe birisini atama
	public boolean acceptApplication(int jobid, int workerid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "UPDATE job INNER JOIN applyjob ON job.ID = applyjob.JobID "
				+ "SET job.JobStatusID = '4', applyjob.ApplyJobStatusID='2' "
				+ "WHERE job.ID = ? AND applyjob.ApplicantID = ? ";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, jobid);
			preparedStmt.setInt(2, workerid);

			preparedStmt.execute();
			System.out.println(preparedStmt.getUpdateCount());

			if (preparedStmt.getUpdateCount() != 0 || preparedStmt.getUpdateCount() != -1)
			{
				if (createJob_User(jobid, workerid) > 0)
				{
					isSuccesfull = true;
				}
			}
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

	/// işe alım yaparken çağır üstteki methodda kullanıldı
	public int createJob_User(int jobid, int workerid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		int isSuccesfull = 0;

		String query = "insert into job_user (JobID, UserIDasWorker) values (?, ?)";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmtInsert = dbCon.conn.prepareStatement(query);
			preparedStmtInsert.setInt(1, jobid);
			preparedStmtInsert.setInt(2, workerid);
			preparedStmtInsert.execute();

			isSuccesfull = preparedStmtInsert.getUpdateCount();

			preparedStmtInsert.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			dbCon.conn.close();
		}
		return isSuccesfull;
	}

	public boolean rejectApplication(int jobid, int workerid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "UPDATE job INNER JOIN applyjob ON job.ID = applyjob.JobID "
				+ "SET job.JobStatusID = '3', applyjob.ApplyJobStatusID='3' "
				+ "WHERE job.ID = ? AND applyjob.ApplicantID = ? ";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, jobid);
			preparedStmt.setInt(2, workerid);

			preparedStmt.execute();
			System.out.println(preparedStmt.getUpdateCount());
			isSuccesfull = true;

			if (preparedStmt.getUpdateCount() != 0 || preparedStmt.getUpdateCount() != -1)
			{
				deleteJob_User(jobid, workerid);
			}
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

	public int deleteJob_User(int jobid, int workerid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		int isSuccesfull = 0;

		String query = "DELETE FROM job_user WHERE JobID = ? AND UserIDasWorker = ?";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmtInsert = dbCon.conn.prepareStatement(query);
			preparedStmtInsert.setInt(1, jobid);
			preparedStmtInsert.setInt(2, workerid);
			preparedStmtInsert.execute();

			isSuccesfull = preparedStmtInsert.getUpdateCount();

			preparedStmtInsert.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			dbCon.conn.close();
		}
		return isSuccesfull;
	}

	/// kullanıcının başvurduğu joblar. hala başvuruları devam edenler
	public ArrayList<Job> getUsersAppliedAndWaitingForWorkerJobs(int userid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> usersallappliedjobs = new ArrayList<>();

		String sql = "SELECT * FROM applyjob INNER JOIN job on applyjob.JobID = job.ID INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID INNER JOIN job_user on job_user.JobID = Job.ID WHERE applyjob.ApplicantID = ? AND jobstatus.ID = '3'";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, userid);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next())
		{
			Job job = new Job();
			job.setId(rs.getInt("job.ID"));
			job.setTitle(rs.getString("job.title"));
			job.setDescriptipn(rs.getString("job.description"));
			job.setImageURL(rs.getString("job.imageURL"));
			job.setStartDate(rs.getDate("job.startDate"));
			job.setEndDate(rs.getDate("job.endDate"));
			job.setCity(rs.getString("job.city"));
			job.setIsActive(rs.getString("job.isActive").charAt(0));
			job.setPrice(rs.getDouble("job.price"));
			job.setCustomerID(rs.getInt("user.ID"));
			job.setCustomerName(rs.getString("user.username"));
			job.setJobSubjectID(rs.getInt("jobsubject.ID"));
			job.setJobSubjectName(rs.getString("jobsubject.name"));
			job.setJobStatusID(rs.getInt("jobstatus.ID"));
			job.setJobStatusName(rs.getString("jobstatus.name"));
			job.setPaymentMethodID(rs.getInt("paymentmethods.ID"));
			job.setPaymentMethodName(rs.getString("paymentmethods.name"));

			usersallappliedjobs.add(job);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return usersallappliedjobs;
	}

	/// kullanıcının başvurduğu joblar. devam edenler joblar
	public ArrayList<Job> getUsersAppliedContinuingJobs(int userid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> usersallappliedandcontinuingjobs = new ArrayList<>();

		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID INNER JOIN job_user on job_user.JobID = Job.ID WHERE job_user.UserIDasWorker = ? AND jobstatus.ID = '4'";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, userid);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next())
		{
			Job job = new Job();
			job.setId(rs.getInt("job.ID"));
			job.setTitle(rs.getString("job.title"));
			job.setDescriptipn(rs.getString("job.description"));
			job.setImageURL(rs.getString("job.imageURL"));
			job.setStartDate(rs.getDate("job.startDate"));
			job.setEndDate(rs.getDate("job.endDate"));
			job.setCity(rs.getString("job.city"));
			job.setIsActive(rs.getString("job.isActive").charAt(0));
			job.setPrice(rs.getDouble("job.price"));
			job.setCustomerID(rs.getInt("user.ID"));
			job.setCustomerName(rs.getString("user.username"));
			job.setJobSubjectID(rs.getInt("jobsubject.ID"));
			job.setJobSubjectName(rs.getString("jobsubject.name"));
			job.setJobStatusID(rs.getInt("jobstatus.ID"));
			job.setJobStatusName(rs.getString("jobstatus.name"));
			job.setPaymentMethodID(rs.getInt("paymentmethods.ID"));
			job.setPaymentMethodName(rs.getString("paymentmethods.name"));

			usersallappliedandcontinuingjobs.add(job);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return usersallappliedandcontinuingjobs;
	}

	/// kullanıcının yapmış oldugu biten işler
	public ArrayList<Job> getUsersExJobsAsWorker(int userid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> usersallexjobs = new ArrayList<>();

		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID INNER JOIN job_user on job_user.JobID = Job.ID WHERE job_user.UserIDasWorker = ? AND jobstatus.ID = '5'";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, userid);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next())
		{
			Job job = new Job();
			job.setId(rs.getInt("job.ID"));
			job.setTitle(rs.getString("job.title"));
			job.setDescriptipn(rs.getString("job.description"));
			job.setImageURL(rs.getString("job.imageURL"));
			job.setStartDate(rs.getDate("job.startDate"));
			job.setEndDate(rs.getDate("job.endDate"));
			job.setCity(rs.getString("job.city"));
			job.setIsActive(rs.getString("job.isActive").charAt(0));
			job.setPrice(rs.getDouble("job.price"));
			job.setCustomerID(rs.getInt("user.ID"));
			job.setCustomerName(rs.getString("user.username"));
			job.setJobSubjectID(rs.getInt("jobsubject.ID"));
			job.setJobSubjectName(rs.getString("jobsubject.name"));
			job.setJobStatusID(rs.getInt("jobstatus.ID"));
			job.setJobStatusName(rs.getString("jobstatus.name"));
			job.setPaymentMethodID(rs.getInt("paymentmethods.ID"));
			job.setPaymentMethodName(rs.getString("paymentmethods.name"));

			usersallexjobs.add(job);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return usersallexjobs;
	}

	/// işe başvur
	public boolean applyJob(int jobid, int userid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "insert into applyjob (JobID, ApplicantID) values (?, ?)";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, jobid);
			preparedStmt.setInt(2, userid);

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

	/// başvurduguum jobdan basvurumu cek
	public boolean deleteApply(int jobid, int userid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "DELETE FROM applyjob WHERE JobID = ? AND ApplicantID = ?";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, jobid);
			preparedStmt.setInt(2, userid);

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
