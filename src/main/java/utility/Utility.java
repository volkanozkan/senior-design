package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Utility
{
	private static MessageDigest md;

	public static boolean isNotNull(String text)
	{
		return text != null && text.trim().length() >= 0 ? true : false;
	}

	public static String constructJSON(String tag, boolean status)
	{
		JSONObject obj = new JSONObject();
		try
		{
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return obj.toString();
	}

	public static String constructJSON(String tag, boolean status, String err_msg)
	{
		JSONObject obj = new JSONObject();
		try
		{
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("error_msg", err_msg);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return obj.toString();
	}

	public String cryptWithMD5(String pass)
	{
		try
		{
			md = MessageDigest.getInstance("MD5");
			byte[] passBytes = pass.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++)
			{
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			return sb.toString();
		}
		catch (NoSuchAlgorithmException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
