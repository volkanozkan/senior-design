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

import database.DBOperationsPaymentMethod;
import model.PaymentMethod;

@Path("paymentmethod")
public class PaymentMethodResource implements MessageBodyWriter<Boolean>
{
	DBOperationsPaymentMethod dbPaymentMethod = new DBOperationsPaymentMethod();
	
	
	@GET
	@Path("getallpaymentmethods")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public ArrayList<PaymentMethod> getAllPaymentMethods() throws SQLException, ClassNotFoundException
	{
		return dbPaymentMethod.getAllPaymentMethods();
	}
	
	@GET
	@Path("createpaymentmethod")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean createPaymentMethod(@QueryParam("name") final String name)
			throws SQLException, ClassNotFoundException
	{
		return dbPaymentMethod.createPaymentMethod(name);
	}
	
	@GET
	@Path("updatepaymentmethod")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean updatePaymentMethod(@QueryParam("paymentmethodid") final int paymentmethodid,
			@QueryParam("name") final String name) throws SQLException, ClassNotFoundException
	{
		return dbPaymentMethod.updatePaymentMethod(paymentmethodid, name);
	}

	@GET
	@Path("deletepaymentmethod")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public boolean deletePaymentMethod(@QueryParam("paymentmethodid") final int paymentmethodid)
			throws SQLException, ClassNotFoundException
	{
		return dbPaymentMethod.deletePaymentMethod(paymentmethodid);
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
