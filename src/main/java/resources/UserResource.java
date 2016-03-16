package resources;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import database.DBOperationsUser;

import model.User;
import utility.*;

@Path("user")
public class UserResource implements MessageBodyWriter<Boolean>
{

	DBOperationsUser dbOpUser = new DBOperationsUser();
	Utility utitlity = new Utility();
	
	@GET
	@Path("signin")
	@Produces(
	{ MediaType.APPLICATION_JSON })
	public String userSignin(@QueryParam("username") final String username,
			@QueryParam("password") final String password) throws SQLException, ClassNotFoundException
	{

		String response = "";

		if (!dbOpUser.isLoginSuccesfull(username, password))
		{
			response = Utility.constructJSON("signin", false, "Wrong Password or Username.");
		}
		else if (!dbOpUser.isUserActive(username))
		{
			response = Utility.constructJSON("signin", false, "Your Account Is Not Active.");
		}
		else
		{
			response = Utility.constructJSON("signin", true);
		}
		return response;
	}

	@GET
	@Path("signup")
	@Produces(
	{ MediaType.APPLICATION_JSON })
	public String userSignup(@QueryParam("name") final String name, @QueryParam("surname") final String surname,
			@QueryParam("email") final String email, @QueryParam("username") final String username,
			@QueryParam("password") final String password, @QueryParam("address") final String address,
			@QueryParam("city") final String city, @QueryParam("dateofbirth") final Date dateOfBirth)
					throws SQLException, ClassNotFoundException
	{
		String response = "";

		if (!dbOpUser.isUsernameAvailableForSignUp(username))
		{
			response = Utility.constructJSON("signup", false, "This Username Already Taken.");
		}
		else if (!dbOpUser.isEmailAvailableForSignUp(email))
		{
			response = Utility.constructJSON("signup", false, "This Email Already Registered.");
		}
		else if (dbOpUser.isSignUpSuccesfull(name, surname, email, username, password, address, city, dateOfBirth))
		{
			response = Utility.constructJSON("signup", true);
		}
		else
		{
			response = Utility.constructJSON("signup", false, "Error Occured.");
		}
		return response;
	}

	@GET
	@Path("getuserinfos")
	@Produces(
	{ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public User getUserInformations(@QueryParam("username") final String username)
			throws SQLException, ClassNotFoundException
	{
		return dbOpUser.getUserInformation(username);
	}

	@GET
	@Path("updateuseraccount")
	@Produces(
	{ MediaType.APPLICATION_JSON })
	public String uptadeUserAccount(@QueryParam("userid") final Integer userid, @QueryParam("name") final String name,
			@QueryParam("surname") final String surname, @QueryParam("email") final String email,
			@QueryParam("username") final String username, @QueryParam("password") final String password,
			@QueryParam("address") final String address, @QueryParam("city") final String city,
			@QueryParam("dateofbirth") final Date dateOfBirth, @QueryParam("isActive") final char isActive)
					throws SQLException, ClassNotFoundException
	{
		String response = "";

		if (!dbOpUser.isUsernameAvailableForUptadeAccount(userid, username))
		{
			response = Utility.constructJSON("uptadeUserAccount", false, "This Username Already Taken.");
		}
		else if (!dbOpUser.isEmailAvailableForUptadeAccount(userid, email))
		{
			response = Utility.constructJSON("uptadeUserAccount", false, "This Email Already Registered.");
		}
		else if (dbOpUser.uptadeUserAccount(name, surname, email, username, password, address, city, dateOfBirth, userid, isActive))
		{
			response = Utility.constructJSON("uptadeUserAccount", true);
		}
		else
		{
			response = Utility.constructJSON("uptadeUserAccount", false, "Error Occured.");
		}
		return response;
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
