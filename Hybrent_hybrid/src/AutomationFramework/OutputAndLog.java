package AutomationFramework;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

public class OutputAndLog
{
	  public static String am_pm;
	  public static String min;
	  public static String hr;
	  public static String sec;
	  public static int yr;
	  public static String mon;
	  public static String day;

	  public static void createOutputDirectory()
	  {
	    File curdir = new File(".");

	    Calendar calendar = new GregorianCalendar();

	    hr = "0" + calendar.get(10);
	    hr = hr.substring(hr.length() - 2);

	    min = "0" + calendar.get(12);
	    min = min.substring(min.length() - 2);

	    sec = "0" + calendar.get(13);
	    sec = sec.substring(sec.length() - 2);

	    yr = calendar.get(1);

	    mon = "0" + (calendar.get(2) + 1);
	    mon = mon.substring(mon.length() - 2);

	    day = "0" + calendar.get(5);
	    day = day.substring(day.length() - 2);

	    if (calendar.get(9) == 0)
	      am_pm = "AM";
	    else {
	      am_pm = "PM";
	    }
	    Generickeywords.timeStamp = yr + "_" + mon + "_" + day + "_" + hr + "_" + min + "_" + sec + "_" + am_pm;
	    
	    try
	    {
	    	Generickeywords.outputDirectory = curdir.getCanonicalPath() + "\\TestResults\\" + yr + "_" + mon + "_" + day + "_" + hr + "_" + min + "_" + sec + "_" + am_pm;
	     
	    }
	    catch (IOException e)
	    {
	      System.out.println("IO Error while creating Output Directory : " + Generickeywords.outputDirectory);
	    }
	  }
	  }