package testCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Loginpage;
import pageObject.PrefcardPageObject;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Procedures extends ApplicationKeyword
{	
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Procedures.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Harikrishnan");
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public void Tc_Procedures_01_02_03()
		{
		testStarts("Tc_Procedures_01_02_03", "Verify that user is able to add procedure using 'Add Procedure' button.");
		Loginpage.OpenBrowserAndLogin();	
		PrefcardPageObject.procedurePageLinkandwait();
		clickOn(OR.Procedure_AddProcedure); 
		if(getAttributeValue(OR.Procedure_SaveButton, "disabled") != null)
		{
			testLogPass("Save button is disabled");
		}
		else
		{
			testLogFail("Save button is not disabled");
		}
		String procName="Proc"+ApplicationKeyword.randomAlphaNumeric(3);
		setProperty("procName",procName);
		typeIn(OR.Procedure_addName, procName);
		clickOn(OR.Procedure_SelectPhysican1);
		String firstPhysician=getText(OR.Procedure_SelectPhysican1Label);
		setProperty("firstPhysician", firstPhysician);
		clickOn(OR.Procedure_SelectPhysican2);
		String cptCode="001"+ApplicationKeyword.randomAlphaNumeric(3);
		setProperty("cptCode",cptCode);
		typeIn(OR.Procedure_cptCode, cptCode);
		if(getAttributeValue(OR.Procedure_SaveButton, "disabled") == null)
		{
			testLogPass("Save button is enabled");
		}
		else
		{
			testLogFail("Save button is not enabled");
		}
		clickOn(OR.Procedure_SaveButton);
		waitForElementToDisplay(OR.Procedure_SearchTextBox, 10);
		typeIn(OR.Procedure_SearchTextBox, procName);
		waitForElementToDisplay(OR.Procedure_wait, 10);
		String firstElemAfterSearch=getText(OR.Procedure_firstElem);
		String finalName=firstElemAfterSearch.substring(2).trim();
		if(finalName.equals(procName))
		{
			testLogPass("Procedure is added successfully");	
		}
		else
		{
			testLogFail("Procedure is not added successfully");			
		}
		
		}
	
	@Test(priority=2)
	public void Tc_Procedures_04()
		{
		testStarts("Tc_Procedures_04", "Edit Procedure > Verify that all fields are editable.");
		NavigateUrl(DashBoardURL);	
		PrefcardPageObject.procedurePageLinkandwait();		
		clickOn(OR.Procedure_AddProcedure); 
		
		List<String> xpaths= new ArrayList<String>(Arrays.asList(OR.Procedure_addName, OR.Procedure_SelectPhysican1, OR.Procedure_cptCode));				
		for(String xpath:xpaths)
		{
			if(getAttributeValue(xpath, "disabled") == null)
			{
				testLogPass("Value of " + xpath + " is null.");
			}
			else
			{
				testLogFail("Value of " + xpath + " is not null.");
			}			
		}		
		
		}
	
	@Test(priority=3)
	public void Tc_Procedures_05_06()
		{
		testStarts("Tc_Procedures_05_06", "Verify that + (expand) button appears on left of procedure name"
				+ "+ Drill-down > Verify that Physician and NPI number appear in drill-down table.");
		NavigateUrl(DashBoardURL);	
		PrefcardPageObject.procedurePageLinkandwait();
		String procNmae=getProperty("procName");		
		typeIn(OR.Procedure_SearchTextBox, procNmae);
		waitForElementToDisplay(OR.Procedure_wait, 10);
		verifyElementText(OR.Procedure_plusIcon, "+");
		clickOn(OR.Procedure_plusIcon);
		String firstPhysicianAdded=getText(OR.Procedure_firstPhysician);
		String firstPhysician=getProperty("firstPhysician");
		if(firstPhysicianAdded.equals(firstPhysician))
		{
			testLogPass("Physician appears under the drill-down table.");
		}
		else
		{
			testLogFail("Physician doesnot appear under the drill-down table.");
		}		
			
		}
	
	
	@Test(priority=4)
	public void Tc_Procedures_07()
		{
		testStarts("Tc_Procedures_07", "Verify that corresponding procedure gets deleted when user clicks Delete button.");
		NavigateUrl(DashBoardURL);	
		PrefcardPageObject.procedurePageLinkandwait();
		String procNmae=getProperty("procName");		
		typeIn(OR.Procedure_SearchTextBox, procNmae);
		waitForElementToDisplay(OR.Procedure_wait, 10);
		String firstElemAfterSearch=getText(OR.Procedure_firstElem);
		if(firstElemAfterSearch.equals("+ "+procNmae))
		{
			waitForElementToDisplay(OR.Procedure_waitForDelete, 5);
			clickOn(OR.Procedure_DeleteButton);
			clickOn(OR.Procedure_ConfirmButton);
			waitForElementToDisplay(OR.Procedure_SearchTextBox, 5);
			clearEditBox(OR.Procedure_SearchTextBox);
			typeIn(OR.Procedure_SearchTextBox, procNmae);
			waitForElementToDisplay(OR.Procedure_NoProcText, 10);
			String NoProcFound=getText(OR.Procedure_NoProcText);
			if(NoProcFound.equalsIgnoreCase("No Procedures Found."))
			{
				testLogPass("Procedure is deleted successfully");		
			}
			else
			{
				testLogFail("Procedure is not deleted successfully");
			}	
		}
		else
		{
			testLogFail("Procedure was not added or searched successfully");			
		}				
		
		}
		@AfterTest
		public void endReport(){
			closeBrowser();
			extent.flush();
		}

		
		
}
