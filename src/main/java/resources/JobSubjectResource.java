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

import database.DBOperationsJobSubject;
import database.DBOperationsUser;
import model.JobSubject;

@Path("jobsubject")
public class JobSubjectResource implements MessageBodyWriter<Boolean>
{
	DBOperationsUser dbOpUser = new DBOperationsUser();
	DBOperationsJobSubject dbOpJobSubject = new DBOperationsJobSubject();

	@GET
	@Path("getalljobsubjects")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<JobSubject> getNotFollowingJobSubjects() throws SQLException, ClassNotFoundException
	{
		return dbOpJobSubject.getAllJobSubjects();
	}
	
	@GET
	@Path("createjobsubject")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean createJobSubject(@QueryParam("name") final String name, @QueryParam("description") final String description) throws SQLException, ClassNotFoundException
	{
		return dbOpJobSubject.createJobSubject(name, description);
	}
	
	@GET
	@Path("updatejobsubject")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean updateJobSubject(@QueryParam("jobsubjectid") final int jobsubjectid, @QueryParam("name") final String name, @QueryParam("description") final String description) throws SQLException, ClassNotFoundException
	{
		return dbOpJobSubject.updateJobSubject(jobsubjectid, name, description);
	}
	
	@GET
	@Path("deletejobsubject")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean deleteJobSubject(@QueryParam("jobsubjectid") final int jobsubjectid) throws SQLException, ClassNotFoundException
	{
		return dbOpJobSubject.deleteJobSubject(jobsubjectid);
	}

	@GET
	@Path("getfollowingjobsubjects")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<JobSubject> getFollowingJobSubjects(@QueryParam("userid") final Integer userID)
			throws SQLException, ClassNotFoundException
	{
		return dbOpJobSubject.getFollowingJobSubjectListByUserID(userID);
	}

	@GET
	@Path("getnotfollowingjobsubjects")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<JobSubject> getNotFollowingJobSubjects(@QueryParam("userid") final Integer userID)
			throws SQLException, ClassNotFoundException
	{
		return dbOpJobSubject.getNotFollowingJobSubjectListByUserID(userID);
	}

	@GET
	@Path("jobsubjectunfollow")
	@Produces(
	{ MediaType.APPLICATION_JSON })
	public boolean JobSubjectUnFollow(@QueryParam("userid") final Integer userid,
			@QueryParam("jobsubjectid") final Integer jobsubjectid) throws SQLException, ClassNotFoundException
	{

		if (dbOpJobSubject.isUserAlreadyFollowingThatJobSubject(userid, jobsubjectid) == false)
		{
			return false;
		}
		else if (dbOpUser.getAllIDsOfAnyTable("user").contains(userid)
				&& dbOpUser.getAllIDsOfAnyTable("jobsubject").contains(jobsubjectid))
		{
			return dbOpJobSubject.jobSubjectUnfollow(userid, jobsubjectid);
		}
		else
		{
			// throw new WebApplicationException(
			// Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("This
			// id is not found...").build());
			return false;
		}
	}

	@GET
	@Path("jobsubjectfollow")
	@Produces(
	{ MediaType.APPLICATION_JSON })
	public boolean JobSubjectFollow(@QueryParam("userid") final Integer userid,
			@QueryParam("jobsubjectid") final Integer jobsubjectid) throws SQLException, ClassNotFoundException
	{

		if (dbOpJobSubject.isUserAlreadyFollowingThatJobSubject(userid, jobsubjectid) == true)
		{
			return false;

		}
		else if (dbOpUser.getAllIDsOfAnyTable("user").contains(userid)
				&& dbOpUser.getAllIDsOfAnyTable("jobsubject").contains(jobsubjectid))
		{
			return dbOpJobSubject.jobSubjectFollow(userid, jobsubjectid);
		}
		else
		{
			return false;
		}
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
