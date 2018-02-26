package testCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

public class Patients extends ApplicationKeyword
{
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Patients.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Harikrishnan");
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public void Tc_Patients_01_02()
	{
		testStarts("Tc_Patients_01_02", "Verify that �Select Facility� popup appears when user clicks on facility name with �Cart for� label."
				+ " Verify that �selected� button appears as disabled for the facility which is displayed on Shop page.");
		Loginpage.OpenBrowserAndLogin();		
		PrefcardPageObject.patientsPageLinkandwait();				
		String facility_Name=getText(OR.Patient_getfacilityName);
		System.out.println(facility_Name);
		clickOn(OR.Patient_ShopfaclitySelect);
		waitForElementToDisplay(OR.Patient_Shopfaclity, 60);
		verifyElementText(OR.Patient_Shopfaclity, "Select Facility");
		//waitForElementToDisplay(OR.Shop_countoffacilities, 60);
		int one = driver.findElements(By.xpath("//*[@style='border-right: none;vertical-align: middle;']")).size();
		System.out.println(one);
		boolean facFound=false;
		String xpath;
		String selectedFacility;
		WebElement btn;
		for(int i=1; i<=one; i++)
		{
			xpath="(//table[@class='table table-responsive table-striped table-bordered']/tbody/tr["+i+"]";
			selectedFacility=driver.findElement(By.xpath(xpath+"/td)")).getText();
			if(selectedFacility.equals(facility_Name))
			{  
				facFound=true;
				btn= driver.findElement(By.xpath(xpath+"/td[2]/div/button)"));
				if(btn.getAttribute("disabled")!=null)
				{
					testLogPass("Go the text '"+selectedFacility+ "' Matches with selected Facility" );
				}
				else
				{
					testLogFail("'" + facility_Name + "' is not selected in popup." );
				}
				break;
			}

		}

		if(!facFound)
		{
			testLogFail("Could not Got the text '"+facility_Name+ "' Matches with selected Facility" );

		}

		clickOn(OR.Shop_SHopfor_cancelButtonPopup);
		
	}

	@Test(priority=2)
	public void Tc_Patients_03()
	{
		testStarts("Tc_Patients_03", "Verify that "+" (expand) button appears on left of patient name.");
	NavigateUrl(DashBoardURL);		
		PrefcardPageObject.patientsPageLinkandwait();	
		String s=getText(OR.Patient_plusIcon);
		if(s.equals("+"))
		{
			testLogPass("Drill Down is present");
		}
		else
		{
			testLogFail("Drill Down is not present");

		}
		

	}
//	@Test
//	public void Tc_Patients_04()
//	{
//		testStarts("Tc_Patients_04", "Drill-down > Verify that all preference cards applied for corresponding patient appear on page.");
//	NavigateUrl(DashBoardURL);		
//		//PrefcardPageObject.patientsPageLinkandwait();	
//		//String pateintName=getText(OR.Patient_firstPatient);
//		PrefcardPageObject.casesPageLinkandwait();
//		selectFromDropdown(OR.Cases_SelectDateDropDown, "-- All Dates --");
//		clickOn(OR.Cases_SearchButton);
//		waitForElementToDisplay(OR.Case_firstPatient, 10);	
//		String patientName=getText(OR.Case_firstPatient);
//		typeIn(OR.Cases_searchTextBox,patientName);	
//		clickOn(OR.Cases_SearchButton);
//		waitForElementToDisplay(OR.Case_firstPatient, 10);	
//		//String xpath="//*[@class='table table-bordered']";
//		List<WebElement> allCasessize=driver.findElements(By.xpath("//*[@class='table table-bordered']"));
//		//System.out.println(driver.findElement(By.xpath("//*[@class='table table-bordered']//tbody/tr[1]/td[2]")).getText());
//		List<String> textList=new ArrayList<String>();
//		//List<WebElement> cases=new ArrayList<WebElement>();		
//		for(int i=1; i<=allCasessize.size(); i++)
//		{			
//			//textList.add(allCasessize.get(i-1).findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText());			
//			//textList.add(cases.getText());					
//			textList.add(driver.findElement(By.xpath("//table[@class='table table-bordered']["+i+"]//tbody/tr/td[2]")).getText());
//			System.out.println("Text -- " + textList.get(i-1));
//		
//		}
//		
//	}

	@Test(priority=3)
	public void Tc_Patients_05()
	{
		testStarts("Tc_Patients_07", "Drill-down > Verify that edit case page opens when user clicks Edit button.");
	NavigateUrl(DashBoardURL);	
		PrefcardPageObject.patientsPageLinkandwait();
		clickOn(OR.Patient_EditPAtient);
		verifyElementText(OR.Patient_EditPAtientPopUp, "Edit Patient ");
		
	}


	@Test(priority=4)
	public void Tc_Patients_07()
	{
		testStarts("Tc_Patients_07", "Drill-down > Verify that case preview opens when user clicks Print case button..");
	NavigateUrl(DashBoardURL);	
		PrefcardPageObject.patientsPageLinkandwait();
		clickOn(OR.Patient_plusIcon);
		clickOn(OR.Patient_PrintIcon);
		verifyElementText(OR.Patient_previewpopup, "Download case");
		
	}

	@Test(priority=5)
	public void Tc_Patients_08_09() throws InterruptedException
	{			
		testStarts("Tc_Patients_08_09", "Verify that user is able to add new patient by clicking 'Add Patient' button.+ "
				+ "Create Patient > Verify that First Name, Last Name, MRN Number, Account Number, DOB, Facility and Is Self Pay are mandatory fields.");
	NavigateUrl(DashBoardURL);	
		PrefcardPageObject.patientsPageLinkandwait();
		String facility_Name=getText(OR.Patient_getfacilityName);
		clickOn(OR.Patient_AddPatient);
		//String s=getAttributeValue(OR.Patient_disabledSaveButton, "disabled");	
		//verifyAttribute(OR.Patient_disabledSaveButton, "disabled", "true");
		if(getAttributeValue(OR.Patient_disabledSaveButton, "disabled") != null)
		{
			testLogPass("Save button is disabled");
		}
		else
		{
			testLogFail("Save button is not isabled");
		}
		String firstName="Test";
		String lastName="Patient";		
		typeIn(OR.Patient_firstName, firstName);
		//typeIn(OR.Patient_middleName, "Pat");
		typeIn(OR.Patient_lastName, lastName);
		typeIn(OR.Patient_mrnNumber, "00001");
		typeIn(OR.Patient_accNumber, "465000");
		typeIn(OR.Patient_dob, "11112017");
		clickOn(OR.Patient_facDropDown);
		WebElement elem=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//span[text()='"+facility_Name+"']"));
		elem.click();
		//clickOn(OR.Patient_firstFac);
		if(!isElementDisplayed(OR.Patient_enabledSaveButton,10))
		{
			testLogPass("SAVE button is enabled only when all mandatory fields are filled");
		}
		else
		{
			testLogFail("SAVE button is not enabled when all mandatory fields are filled");				
		}
		clickOn(OR.Patient_disabledSaveButton);
		Thread.sleep(4000);
		//waitForElementToDisplay(OR.Patient_firstPatient, 10);
		typeIn(OR.Patient_searchTextBox, firstName+" "+lastName);
		clickOn(OR.Patient_searchbutton);
		waitForElementToDisplay(OR.Patient_wait, 10);
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

	@Test(priority=6)
	public void Tc_Patients_10()
	{			
		testStarts("Tc_Patients_10", "Edit Patient > Verify that all fields are editable.");
	NavigateUrl(DashBoardURL);	
		PrefcardPageObject.patientsPageLinkandwait();			
		//typeIn(OR.Patient_searchTextBox, Test+"Patient);
		//clickOn(OR.Patient_searchbutton);
		waitForElementToDisplay(OR.Patient_firstPatient, 10);
		clickOn(OR.Patient_EditPAtient);

		List<String> xpaths = new ArrayList<String>( 
				Arrays.asList
				(
						OR.Patient_middleName,OR.Patient_lastName,OR.Patient_mrnNumber,
						OR.Patient_accNumber, OR.Patient_dob, OR.Patient_add01,
						OR.Patient_add02, OR.Patient_city, OR.Patient_state, 
						OR.Patient_zip, OR.Patient_Phone, OR.Patient_secphone, 
						OR.Patient_DOS
						));

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
	
	
	@Test(priority=7)
	public void Tc_Patients_11()
	{			
		testStarts("Tc_Patients_11", "Verify that �Apply Preference Card To� pop up appears when user clicks on Apply Preference Card icon.");
	NavigateUrl(DashBoardURL);	
		PrefcardPageObject.patientsPageLinkandwait();			
		waitForElementToDisplay(OR.Patient_firstPatient, 10);
		clickOn(OR.Patient_Addprefcard);
		//System.out.println(getText(OR.Patient_AddprefcardPopUpText));
		verifyElementText(OR.Patient_AddprefcardPopUpText, " Apply Preference Card To");
		
	}
	@AfterTest
	public void endReport()
	{
		closeBrowser();
		extent.flush();
	}




}
