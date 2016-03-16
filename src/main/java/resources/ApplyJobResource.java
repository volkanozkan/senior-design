package resources;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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

import database.DbOperationsApplyJob;
import model.Job;
import model.User;

@Path("applyjob")
public class ApplyJobResource implements MessageBodyWriter<Boolean>
{
	DbOperationsApplyJob dbOpsApplyJob = new DbOperationsApplyJob();
	
	
	@GET
	@Path("applyjob")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean applyJob(@QueryParam("userid") final Integer userid, @QueryParam("jobid") final Integer jobid) throws SQLException, ClassNotFoundException
	{
		return dbOpsApplyJob.applyJob(jobid, userid);
	}
	
	@GET
	@Path("deleteapply")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean deleteApply(@QueryParam("userid") final Integer userid, @QueryParam("jobid") final Integer jobid) throws SQLException, ClassNotFoundException
	{
		return dbOpsApplyJob.deleteApply(jobid, userid);
	}
	
	@GET
	@Path("getusersappliedandwaitingforworkerjobs")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getUsersAppliedAndWaitingForWorkerJobs(@QueryParam("userid") final Integer userid) throws SQLException, ClassNotFoundException
	{
		return dbOpsApplyJob.getUsersAppliedAndWaitingForWorkerJobs(userid);
	}
	
	@GET
	@Path("getusersappliedcontinuingjobs")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getUsersAppliedContinuingJobs(@QueryParam("userid") final Integer userid) throws SQLException, ClassNotFoundException
	{
		return dbOpsApplyJob.getUsersAppliedContinuingJobs(userid);
	}
	
	@GET
	@Path("getusersexjobsasworker")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<Job> getUsersExJobsAsWorker(@QueryParam("userid") final Integer userid) throws SQLException, ClassNotFoundException
	{
		return dbOpsApplyJob.getUsersExJobsAsWorker(userid);
	}
	
	@GET
	@Path("getworkerlistwhoappliedusersjob")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<User> getWorkerListWhoAppliedUsersJob(@QueryParam("jobid") final Integer jobid) throws SQLException, ClassNotFoundException
	{
		return dbOpsApplyJob.getWorkerListWhoAppliedUsersJob(jobid);
	}
	
	@GET
	@Path("acceptapplication")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean acceptApplication(@QueryParam("jobid") final Integer jobid, @QueryParam("workerid") final Integer workerid) throws SQLException, ClassNotFoundException
	{
		return dbOpsApplyJob.acceptApplication(jobid, workerid);
	}
	
	@GET
	@Path("rejectapplication")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean rejectApplication(@QueryParam("jobid") final Integer jobid, @QueryParam("workerid") final Integer workerid) throws SQLException, ClassNotFoundException
	{
		return dbOpsApplyJob.rejectApplication(jobid, workerid);
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
