package AutomationFramework;

import java.util.List;
import java.util.Properties;
import java.util.Random;


public class ApplicationKeyword extends Generickeywords 
{
	public static String facility_Name;
	public static String vendor_Name;	
	public static String firstCategory;
	public static String ItemDescription ;
	public static String ItemMfrNumber;
	public static String ItemTestID ;
	public static String SkuName;
	public static String aliasName;
	public static List<String> depNames_FacPage= null ;
	public static String defFac=getProperty("userdefaultFac");
	public static String npiNum=getProperty("NPI_FOr_Physician");
	public static Properties prop;
	public static String ItemCatName;
	public static String SI;
	public static String PT;
	public static String Days;
	public static String Percentage;
	public static String NewsTitle;
	public static String NewsDesc;
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static String randomAlphaNumeric(int count) 
	{
	StringBuilder builder = new StringBuilder();
	while (count-- != 0) 
	{
	int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
	return builder.toString();
	}
	


	public static int generateRandomInt(int upperRange)
	{
	    Random random = new Random();
	    return random.nextInt(upperRange);
	   
	}
	
	
}

