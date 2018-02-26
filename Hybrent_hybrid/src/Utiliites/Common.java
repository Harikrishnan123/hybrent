package Utiliites;

import AutomationFramework.Generickeywords;

public class Common
{
	  public static void parseidentifyByAndlocator(String identifyByAndLocator)
	  {
	    
	    try
	    {
	      Generickeywords.locatorDescription = identifyByAndLocator.substring(0, identifyByAndLocator.indexOf("#"));
	      identifyByAndLocator = identifyByAndLocator.substring(identifyByAndLocator.indexOf("#") + 1);
	    }
	    catch (Exception e)
	    {
	      Generickeywords.locatorDescription = "";
	    }
	    finally
	    {
	    	Generickeywords.identifier = identifyByAndLocator.substring(0, identifyByAndLocator.indexOf("=", 0)).toLowerCase();
	    	Generickeywords.locator = identifyByAndLocator.substring(identifyByAndLocator.indexOf("=", 0) + 1);
	      
	    	Generickeywords.idType = identifierType.valueOf(Generickeywords.identifier);
	    }
	  }
	  
	  public static enum identifierType
	  {
	    xpath, 
	    name, 
	    id, 
	    lnktext, 
	    partiallinktext, 
	    classname, 
	    cssselector, 
	    tagname;
	  }
	  
}