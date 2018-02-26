package testCases;

import java.io.File;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Loginpage;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Organization extends ApplicationKeyword
{
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Organization.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Harikrishnan");
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public static void TC_ORG_01()
	{	
		testStarts("TC_ORG_01_02_03_04", "Verify that Organization Details page appears when user clicks on organization option in admin dropdown.");
		Loginpage.OpenBrowserAndLogin();
		clickOn(OR.DashBoard_AdminDropdown);
		verifyElementText(OR.orgsetting_AdminMenuText, "Admin");		 
		clickOn(OR.Shop_orgpage);
		waitForElementToDisplay(OR.orgsetting_orgPageTextt, 10);
		verifyPageTitle("Organization");
		verifyElementText(OR.orgsetting_orgPageTextt, "ORGANIZATION DETAILS");		 
		clickOn(OR.Shop_orgFeatures2Page); 
		waitForElementToDisplay(OR.orgsetting_orgFeaturesPageText, 10);
		verifyElementText(OR.orgsetting_orgFeaturesPageText,"INVENTORY");
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.orgsetting_orgBudgetPage);
		waitForElementToDisplay(OR.orgsetting_orgBudgetPageText, 10);
		verifyPageTitle("Organization");
		System.out.println(getText(OR.orgsetting_orgBudgetPageText));
		verifyElementText(OR.orgsetting_orgBudgetPageText, "ORGANIZATION DETAILS");		 
		
	} 
	
	@AfterTest
	public void endReport(){
		closeBrowser();
		extent.flush();
	}	

}
