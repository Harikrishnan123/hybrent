package testCases;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Loginpage;
import pageObject.PrefcardPageObject;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Planner extends ApplicationKeyword
{
	public static String fullName;
	
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Planner.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}

	@Test(priority=1)
	public void Tc_Planner_01() throws InterruptedException
	{
		testStarts("Tc_Planner_01", "Verify that user can add patient using Add Patient option.");
		Loginpage.OpenBrowserAndLogin();
		PrefcardPageObject.plannerPageLinkandwait();
		String facility_Name=getText(OR.Patient_getfacilityName);
		clickOn(OR.Planner_createPatient);		
		//verifyElementText(OR.Planner_popUpText, "Create Patient ");
		String firstName="Test1";
		String lastName="Patient1";		
		typeIn(OR.Patient_firstName, firstName);
		//typeIn(OR.Patient_middleName, "Pat");
		typeIn(OR.Patient_lastName, lastName);
		typeIn(OR.Patient_mrnNumber, "00001");
		typeIn(OR.Patient_accNumber, "465000");
		typeIn(OR.Patient_dob, "11112017");
		clickOn(OR.Patient_facDropDown);
		WebElement elem=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//span[text()='"+facility_Name+"']"));
		elem.click();
		clickOn(OR.Planner_AddPatient);
		Thread.sleep(4000);
		PrefcardPageObject.patientsPageLinkandwait();
		typeIn(OR.Patient_searchTextBox, firstName+" "+lastName);
		clickOn(OR.Patient_searchbutton);
		waitForElementToDisplay(OR.Patient_wait, 20);
		verifyElement(OR.Patient_firstPatient);
		String patientName=getText(OR.Patient_firstPatient);
		String finalName=patientName.substring(2).trim();
		if(finalName.equals(firstName+" "+lastName))
		{
			testLogPass("New Patient is added");
		}
		else
		{
			testLogFail("New Patient is not added");
		}			
		
				
	}
	
	@Test(priority=2)
	public void Tc_Planner_02()
	{
		testStarts("Tc_Planner_02", "Verify that case can be created by clicking on the calendar vie.");
		NavigateUrl(DashBoardURL);
		PrefcardPageObject.plannerPageLinkandwait();
		clickOn(OR.Planner_datatime630);
		verifyElementText(OR.Planner_createCaseHeading, "Schedule Case -");
	}
	
	@AfterTest
	public void endReport()
	{
		closeBrowser();
		extent.flush();
	}
}
