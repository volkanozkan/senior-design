package resources;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import database.DBOperationsJob;
import model.Job;

@Path("job")
public class JobResource implements MessageBodyWriter<Boolean>
{
	DBOperationsJob dbOpJob = new DBOperationsJob();

	@GET
	@Path("getalljobs")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getAllJobs() throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getAllJobs();
	}

	@GET
	@Path("getallactiveandwaitingforworkerjobs")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getAllActiveAndWaitingForWorkerJobs() throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getAllActiveAndWaitingForWorkerJobs();
	}
	
	@GET
	@Path("getactiveandwaitingforworkerjobsbyjobsubjectid")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getActiveAndWaitingForWorkerJobsByJobSubject(@QueryParam("jobsubjectid") final Integer jobsubjectid) throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getActiveAndWaitingForWorkerJobsByJobSubject(jobsubjectid);
	}
	
	@GET
	@Path("getactiveandwaitingforworkerjobsbyjobsubjectidandcity")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getActiveAndWaitingForWorkerJobsByJobSubject(@QueryParam("jobsubjectid") final Integer jobsubjectid, @QueryParam("city") final String city) throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getActiveAndWaitingForWorkerJobsByJobSubjectAndCity(jobsubjectid, city);
	}

	@GET
	@Path("getusersjobs")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getUsersJobs(@QueryParam("userid") final Integer userID)
			throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getUsersJobs(userID);
	}

	@GET
	@Path("getusersactivejobs")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getUsersActiveJobs(@QueryParam("userid") final Integer userID)
			throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getUsersActiveJobs(userID);
	}
	
	@GET
	@Path("getusersactiveandwaitingforworkerjobs")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getUsersActiveAndWaitingForWorkerJobs(@QueryParam("userid") final Integer userID)
			throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getUsersActiveAndWaitingForWorkerJobs(userID);
	}
	
	@GET
	@Path("getusersactiveandcontinuingjobs")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getUsersActiveAndContinuingJobs(@QueryParam("userid") final Integer userID)
			throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getUsersActiveAndContinuingJobs(userID);
	}

	@GET
	@Path("getusersnotactivejobs")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getUsersNotActiveJobs(@QueryParam("userid") final Integer userID)
			throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getUsersNotActiveJobs(userID);
	}

	@GET
	@Path("getjobinfos")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public Job getJobInfos(@QueryParam("jobid") final Integer jobid) throws SQLException, ClassNotFoundException
	{
		return dbOpJob.getJobInfos(jobid);
	}

	@GET
	@Path("createjob")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean createJob(@QueryParam("title") final String title,
			@QueryParam("description") final String description, @QueryParam("imageURL") final String imageURL,
			@QueryParam("startDate") final Date startDate, @QueryParam("endDate") final Date endDate,
			@QueryParam("city") final String city, @QueryParam("price") final Double price,
			@QueryParam("customerID") final int customerID, @QueryParam("jobsubjectID") final int jobsubjectID,
			@QueryParam("paymentmethodID") final int paymentmethodID, @QueryParam("jobstatusID") final int jobstatusID)
					throws SQLException, ClassNotFoundException
	{
		return dbOpJob.createJob(title, description, imageURL, startDate, endDate, city, price, customerID,
				jobsubjectID, paymentmethodID, jobstatusID);
	}
	
	@GET
	@Path("updatejob")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean updateJob(@QueryParam("jobid") final int jobid, @QueryParam("title") final String title,
			@QueryParam("description") final String description, @QueryParam("imageURL") final String imageURL,
			@QueryParam("startDate") final Date startDate, @QueryParam("endDate") final Date endDate,
			@QueryParam("city") final String city, @QueryParam("isActive") final char isActive, @QueryParam("price") final Double price,
			@QueryParam("customerID") final int customerID, @QueryParam("jobsubjectID") final int jobsubjectID,
			@QueryParam("paymentmethodID") final int paymentmethodID, @QueryParam("jobstatusID") final int jobstatusID)
					throws SQLException, ClassNotFoundException
	{
		return dbOpJob.updateJob(jobid, title, description, imageURL, startDate, endDate, city, isActive,
				price, customerID, jobsubjectID, paymentmethodID, jobstatusID);
	}
	
	@GET
	@Path("deletejob")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean deleteJob(@QueryParam("jobid") final int jobid)
					throws SQLException, ClassNotFoundException
	{
		return dbOpJob.deleteJob(jobid);
	}

	///
	@Override
	public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt)
	{
		return true;
	}

	@Override
	public long getSize(Boolean t, Class<?> type, Type type1, Annotation[] antns, MediaType mt)
	{
		return -1;
	}

	@Override
	public void writeTo(Boolean bool, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
					throws IOException, WebApplicationException
	{
		entityStream.write(bool.toString().getBytes());
	}
}
