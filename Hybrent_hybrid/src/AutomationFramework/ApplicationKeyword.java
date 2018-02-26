package AutomationFramework;

import java.io.File;
import java.util.Random;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import java.sql.Timestamp;
import java.time.Instant;

public class ApplicationKeyword extends Generickeywords 
{
//	@BeforeTest
//	public void startReport(){
//		try
//		{
//		     Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		     long Time =timestamp.getTime();
//		 	extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/STMExtentReport.html", true);
//			// extent.addSystemInfo("Environment","Environment Name")
//			extent.addSystemInfo("User Name", "Harikrishnan");
//			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
//		}
//		catch(Exception e)
//		{
//			testLogFail("unable to generate the pass report "+e.toString());   
//		}
//	}	

	public static void dropDown()
	{
		try
		{	 
			sscreen.dragDrop("D:\\Testing-Tools\\sikuli\\Test001.png", "D:\\Testing-Tools\\sikuli\\Test002.png");
		}
		catch(Exception e)
		{
			testLogFail("Drag and Drop function  "+e.toString());
		}
	}
	
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

