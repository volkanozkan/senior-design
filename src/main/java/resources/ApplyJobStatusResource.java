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

import database.DBOperationsApplyJobStatus;
import model.ApplyJobStatus;

@Path("applyjobstatus")
public class ApplyJobStatusResource implements MessageBodyWriter<Boolean>
{
	DBOperationsApplyJobStatus dbApplyJobStatus = new  DBOperationsApplyJobStatus();
	
	@GET
	@Path("getallapplyjobstatus")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<ApplyJobStatus> getAllApplyJobStatus() throws SQLException, ClassNotFoundException
	{
		return dbApplyJobStatus.getAllApplyJobStatus();
	}
	
	@GET
	@Path("createapplyjobstatus")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean createApplyJobStatus(@QueryParam("name") final String name) throws SQLException, ClassNotFoundException
	{
		return dbApplyJobStatus.createApplyJobStatus(name);
	}
	
	@GET
	@Path("updateapplyjobstatus")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean updateApplyJobStatus(@QueryParam("applyjobstatusid") final int applyjobstatusid, @QueryParam("name") final String name) throws SQLException, ClassNotFoundException
	{
		return dbApplyJobStatus.updateApplyJobStatus(applyjobstatusid, name);
	}
	
	@GET
	@Path("deleteapplyjobstatus")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean deleteApplyJobStatus(@QueryParam("applyjobstatusid") final int applyjobstatusid) throws SQLException, ClassNotFoundException
	{
		return dbApplyJobStatus.deleteApplyJobStatus(applyjobstatusid);
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
