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

import database.DBOperationsJobStatus;
import model.JobStatus;

@Path("jobstatus")
public class JobStatusResource implements MessageBodyWriter<Boolean>
{
	DBOperationsJobStatus dbJobStatus = new DBOperationsJobStatus();

	@GET
	@Path("getalljobstatus")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<JobStatus> getAllJobStatus() throws SQLException, ClassNotFoundException
	{
		return dbJobStatus.getAllJobStatus();
	}

	@GET
	@Path("createjobstatus")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean createJobStatus(@QueryParam("name") final String name)
			throws SQLException, ClassNotFoundException
	{
		return dbJobStatus.createJobStatus(name);
	}

	@GET
	@Path("updatejobstatus")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean updateJobStatus(@QueryParam("jobstatusid") final int jobstatusid,
			@QueryParam("name") final String name) throws SQLException, ClassNotFoundException
	{
		return dbJobStatus.updateJobStatus(jobstatusid, name);
	}

	@GET
	@Path("deletejobstatus")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean deleteJobStatus(@QueryParam("jobstatusid") final int jobstatusid)
			throws SQLException, ClassNotFoundException
	{
		return dbJobStatus.deleteJobStatus(jobstatusid);
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
