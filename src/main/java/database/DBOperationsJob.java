package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Job;

public class DBOperationsJob
{
	DatabaseConnection dbCon = new DatabaseConnection();

	Statement stmt = null;

	public ArrayList<Job> getAllJobs() throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> allJobs = new ArrayList<>();

		stmt = dbCon.conn.createStatement();
		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID";
		ResultSet rs = stmt.executeQuery(sql);
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

			allJobs.add(job);
		}
		rs.close();
		stmt.close();
		dbCon.conn.close();

		return allJobs;
	}

	public ArrayList<Job> getAllActiveAndWaitingForWorkerJobs() throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> allJobs = new ArrayList<>();

		stmt = dbCon.conn.createStatement();
		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID WHERE job.isActive = '1' AND job.JobStatusID = '3'";
		ResultSet rs = stmt.executeQuery(sql);
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

			allJobs.add(job);
		}
		rs.close();
		stmt.close();
		dbCon.conn.close();

		return allJobs;
	}

	public ArrayList<Job> getActiveAndWaitingForWorkerJobsByJobSubject(int jobsubjectid)
			throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> allJobs = new ArrayList<>();

		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID WHERE job.isActive = '1' AND job.JobStatusID = '3' AND jobsubject.ID = ?";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, jobsubjectid);
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

			allJobs.add(job);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return allJobs;
	}

	public ArrayList<Job> getActiveAndWaitingForWorkerJobsByJobSubjectAndCity(int jobsubjectid, String city)
			throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> allJobs = new ArrayList<>();

		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID WHERE job.isActive = '1' AND job.JobStatusID = '3' AND jobsubject.ID = ? AND job.city LIKE ?";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, jobsubjectid);
		preparedStmt.setString(2, city);
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

			allJobs.add(job);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return allJobs;
	}

	public ArrayList<Job> getUsersJobs(int userid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> allJobs = new ArrayList<>();
		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID WHERE job.CustomerID LIKE ?";

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

			allJobs.add(job);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return allJobs;
	}

	public ArrayList<Job> getUsersActiveJobs(int userid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> allJobs = new ArrayList<>();
		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID WHERE job.CustomerID LIKE ? AND job.isActive = '1'";

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

			allJobs.add(job);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return allJobs;
	}

	public ArrayList<Job> getUsersActiveAndWaitingForWorkerJobs(int userid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> allJobs = new ArrayList<>();
		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID WHERE job.CustomerID LIKE ? AND job.isActive = '1' AND job.JobStatusID = '3'";

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

			allJobs.add(job);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return allJobs;
	}

	public ArrayList<Job> getUsersActiveAndContinuingJobs(int userid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		ArrayList<Job> allJobs = new ArrayList<>();
		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID WHERE job.CustomerID LIKE ? AND job.isActive = '1' AND job.JobStatusID = '4'";

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

			allJobs.add(job);
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return allJobs;
	}

	public ArrayList<Job> getUsersNotActiveJobs(int userid) throws SQLException, ClassNotFoundException
	{
		ArrayList<Job> list = new ArrayList<>((getUsersJobs(userid)));
		list.removeAll(getUsersActiveJobs(userid));

		return list;
	}

	public Job getJobInfos(int jobid) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();
		Job job = null;

		String sql = "SELECT * FROM job INNER JOIN jobsubject on job.JobSubjectID = jobsubject.ID INNER JOIN "
				+ "user on job.CustomerID = user.ID INNER JOIN paymentmethods on job.PaymentmethodID = paymentmethods.ID INNER JOIN "
				+ "jobstatus on job.JobStatusID = jobstatus.ID WHERE job.ID LIKE ?";
		PreparedStatement preparedStmt = dbCon.conn.prepareStatement(sql);

		preparedStmt.setInt(1, jobid);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next())
		{
			job = new Job();
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
		}
		rs.close();
		preparedStmt.close();
		dbCon.conn.close();

		return job;
	}

	public boolean createJob(String title, String description, String imageURL, Date startDate, Date endDate,
			String city, double price, int customerID, int jobsubjectID, int paymentmethodID, int jobstatusID)
					throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "insert into job (title, description, imageURL, startDate, endDate, city, price, CustomerID, JobSubjectID, paymentMethodID, JobStatusID)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, title);
			preparedStmt.setString(2, description);
			preparedStmt.setString(3, imageURL);
			preparedStmt.setDate(4, startDate);
			preparedStmt.setDate(5, endDate);
			preparedStmt.setString(6, city);
			preparedStmt.setDouble(7, price);
			preparedStmt.setInt(8, customerID);
			preparedStmt.setInt(9, jobsubjectID);
			preparedStmt.setInt(10, paymentmethodID);
			preparedStmt.setInt(11, jobstatusID);

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

	public boolean updateJob(int jobID, String title, String description, String imageURL, Date startDate, Date endDate,
			String city, char isActive, double price, int customerID, int jobsubjectID, int paymentmethodID,
			int jobstatusID) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "UPDATE job SET title = ?, description = ?, imageURL = ?, startDate = ?, endDate = ?, city = ?, isActive = ?, price = ?,"
				+ " CustomerID = ?, JobSubjectID = ?, paymentMethodID = ?, JobStatusID = ? WHERE job.ID = ? ";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setString(1, title);
			preparedStmt.setString(2, description);
			preparedStmt.setString(3, imageURL);
			preparedStmt.setDate(4, startDate);
			preparedStmt.setDate(5, endDate);
			preparedStmt.setString(6, city);
			preparedStmt.setString(7, String.valueOf(isActive));
			preparedStmt.setDouble(8, price);
			preparedStmt.setInt(9, customerID);
			preparedStmt.setInt(10, jobsubjectID);
			preparedStmt.setInt(11, paymentmethodID);
			preparedStmt.setInt(12, jobstatusID);
			preparedStmt.setInt(13, jobID);

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

	public boolean deleteJob(int jobID) throws SQLException, ClassNotFoundException
	{
		dbCon.connectDB();

		boolean isSuccesfull = false;

		String query = "DELETE FROM job WHERE job.ID = ? ";

		try
		{
			dbCon.connectDB();

			PreparedStatement preparedStmt = dbCon.conn.prepareStatement(query);
			preparedStmt.setInt(1, jobID);

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
