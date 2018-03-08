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
import pageObject.Organisation_settingspage;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Facility extends ApplicationKeyword{
	public static List<String> depNames_FacPage= null ;
	public static String defFac=getProperty("userdefaultFac");
	public static String npiNum=getProperty("NPI_FOr_Physician");
	
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Facility.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public void Tc_Fac_01()
	{
		testStarts("Tc_Fac_01" , "Verify that user can search facility by name using the search text field. ");
		Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.AdminAndFacilityLink();
		String firstFacName=getText(OR.Facilty_firstFacility).substring(1);
		typeIn(OR.Facilty_SearchBox, firstFacName);
		clickOn(OR.Facilty_SearchButton);
		waitForElementToDisplay(OR.Facilty_WaitforTableElem, 10);
		String FacNameAfterSearch=getText(OR.Facilty_firstFacility).substring(1);
		if(firstFacName.equals(FacNameAfterSearch))
		{
			testLogPass("Search is working. User is able to search with Facility Name");			
		}
		else
		{
			testLogFail("Search is not working. User is not able to search with Facility Name");
		}
		
	}

	@Test(priority=2)
	public void Tc_Fac_02()
	{
		testStarts("Tc_Fac_02" , "Verify that �Add Facility� pop up opens on clicking Add button.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndFacilityLink();
		clickOn(OR.Facilty_AddFacilityButton);
		waitForElementToDisplay(OR.Facilty_AddFacilityText, 10);
		verifyElementText(OR.Facilty_AddFacilityText, "Add Facility");
		clickOn(OR.Receive_PrintCloseclose);	
		waitTime(3);
		
	}

	@Test(priority=3)
	public void Tc_Fac_03()
	{
		testStarts("Tc_Fac_03" , "Verify that tabs(Facility Details, Shipping Address, Invoice Address) appears on Add/Edit facility pop up.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndFacilityLink();
		clickOn(OR.Facilty_AddFacilityButton);	
		waitForElementToDisplay(OR.Facilty_AddFacilityText, 10);
		List<String> tabs = new ArrayList<String>( Arrays.asList("Facility Detail", "Shipping Address", "Invoice Address"));

		List<WebElement> actualTabs = driver.findElements
				(By.xpath("//ul[@class='nav nav-tabs']//a[@class='nav-link ng-binding']"));

		if(tabs.size() == actualTabs.size())
		{
			for(int i=0; i<tabs.size(); i++)
			{				 
				if(tabs.get(i).equals(actualTabs.get(i).getText()))
				{	
					testLogPass(tabs.get(i)+" Tab matched with " +actualTabs.get(i).getText());					
				}
				else
				{
					testLogFail(tabs.get(i)+" Tab doesnot matched with " +actualTabs.get(i).getText());					
				}
			}
		}
		else
		{
			testLogFail("Both the Lists Donot have same number of Tabs");
		}
		clickOn(OR.Receive_PrintCloseclose);	
		waitTime(3);
		
	}

	@Test(priority=4)
	public void Tc_Fac_04()
	{
		testStarts("Tc_Fac_04" , "Verify that 'Edit Facility' page opens when on clicking Edit button.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndFacilityLink();
		clickOn(OR.Facilty_EditFacility);	
		waitForElementToDisplay(OR.Facilty_EditFacilityText, 10);
		verifyElementText(OR.Facilty_EditFacilityText, "Edit Facility");
		clickOn(OR.Receive_PrintCloseclose);	
		waitTime(3);
	}

	@Test(priority=5)
	public void Tc_Fac_05_Dep_02()
	{
		testStarts("Tc_Fac_05Dep_02" , "Verify that facility name drill down expand and display all associated departments when user clicks on �-� button.");
		NavigateUrl(DashBoardURL);		
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Dep_PageLink);
		String facName=getProperty("userdefaultFac");		
		selectFromDropdown(OR.Dep_SelectFac, facName);
		clickOn(OR.Dep_SearchButton);
		waitForElementToDisplay(OR.Dep_wait, 10);	
		List<WebElement> allDeps=driver.findElements(By.xpath("//*[@class='grid-item ng-scope']//*[@class='grid-heading grid-title-label ng-binding']"));
		depNames_FacPage= new ArrayList<String>() ;
		for(int i=1;i<=allDeps.size();i++)
		{
			String depName=allDeps.get(i-1).getText();
			System.out.println(depName);
			depNames_FacPage.add(depName);
		}
		Organisation_settingspage.AdminAndFacilityLink();	
		waitForElementToDisplay(OR.Facility_wait, 10);
		typeIn(OR.Facilty_SearchBox, facName);
		clickOn(OR.Facilty_SearchButton);
		waitForElementToDisplay(OR.Facility_wait, 10);
		if(getText(OR.Facilty_firstFacility).equalsIgnoreCase("+ "+facName))
		{
			clickOn(OR.Facilty_firstdrillDown);
			List<WebElement> depNames_DepPage=driver.findElements(By.xpath("//ul[@class='facility_department_sublist'][1]//li"));
			for(int i=1;i<=depNames_DepPage.size();i++)
			{
				String temp=depNames_DepPage.get(i-1).getText();
				if(temp.equalsIgnoreCase(depNames_FacPage.get(i-1)))
				{
					testLogPass(depNames_FacPage.get(i-1)+ "- Department matched with -" + temp);
				}
				else
				{
					testLogFail(depNames_FacPage.get(i-1)+"- Department doesnot match with- " + temp);
				}								
			}			

		}
		else
		{
			testLogFail("Facility is not matched-- Either search is not working properly or this is not user;s default facility");
		}

		
	}


	//Departments
	@Test(priority=6)
	public void Tc_Dep_03_04()
	{
		testStarts("Tc_Dep_03_04" , "Verify that user is able to create new department by clicking Add button."
				+"Create Department > Verify that all fields are mandatory.");
		NavigateUrl(DashBoardURL);			
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Dep_PageLink);
		clickOn(OR.Dep_addNewDep);
		waitForElementToDisplay(OR.Dep_addFacName, 10);
		String facName=getProperty("userdefaultFac");
		if(!Organisation_settingspage.ifButtonDisabled(OR.Dep_saveButton))
		{
			testLogFail("SAVE/Create Button is enabled even if mandatory fields are not filled");
		}
		else
		{
			testLogPass("SAVE/Create Button is disabled if mandatory fields are not filled");
		}
		selectFromDropdown(OR.Dep_addFacName, facName);		
		typeIn(OR.Dep_addFacmnemonic, "Testabc");
		String depName="Dep"+ ApplicationKeyword.randomAlphaNumeric(3);	 
		setProperty("Departmentadded", depName);
		typeIn(OR.Dep_addName, depName);
		if(Organisation_settingspage.ifButtonDisabled(OR.Dep_saveButton))
		{		
			testLogFail("SAVE/Create Button is not enabled even if mandatory fields are filled");
		}
		else
		{
			testLogPass("SAVE/Create Button is enabled if mandatory fields are filled");
		}
		clickOn(OR.Dep_saveButton);
		waitForElementToDisplay(OR.Dep_wait, 10);
		typeIn(OR.Dep_SearchTextBox, depName);
		clickOn(OR.Dep_SearchButton);
		waitForElementToDisplay(OR.Dep_wait, 10);
		if(getText(OR.Dep_firstDeps).equals(depName))
		{

			testLogPass("New Department is created and is searched successfully");
		}
		else
		{
			testLogFail("New Department is not created and is not searched successfully");
		}
		
	}
	@Test(priority=7)
	public void Tc_Dep_07()
	{
		testStarts("Tc_Dep_07" , "Edit Department > Verify that changes get saved, when on clicking Save button.");
		NavigateUrl(DashBoardURL);					
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Dep_PageLink);
		String createdDep=getProperty("Departmentadded");
		typeIn(OR.Dep_SearchTextBox, createdDep);
		clickOn(OR.Dep_SearchButton);
		waitForElementToDisplay(OR.Dep_wait, 10);
		if(getText(OR.Dep_firstDeps).equals(createdDep))
		{
			clickOn(OR.Dep_editButton);
			clearEditBox(OR.Dep_addName);
			String depName="Dep"+ ApplicationKeyword.randomAlphaNumeric(3);
			typeIn(OR.Dep_addName,depName);
			setProperty("Departmentadded", depName);
			clickOn(OR.Dep_saveButton);
			waitForElementToDisplay(OR.Dep_wait, 10);
			clearEditBox(OR.Dep_SearchTextBox);
			typeIn(OR.Dep_SearchTextBox, depName);
			clickOn(OR.Dep_SearchButton);
			waitForElementToDisplay(OR.Dep_wait, 10);
			if(getText(OR.Dep_firstDeps).equals(depName))
			{
				testLogPass("Department is edited successfully");				 
			}
			else
			{
				testLogFail("Department is not edited successfully");				 
			}
		}
		else
		{
			testLogFail("Either saerch is not working properly or Department was not added succesfully or could not property from property file.");
		}
		
	}
	//Priority has to be set above 7th test case
	@Test(priority=8)
	public void Tc_Dep_06()
	{
		testStarts("Tc_Dep_06" , "Verify that department gets deleted on clicking Delete button if the same is not used anywhere.");
		NavigateUrl(DashBoardURL);						
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Dep_PageLink);
		String createdDep=getProperty("Departmentadded");
		typeIn(OR.Dep_SearchTextBox, createdDep);
		clickOn(OR.Dep_SearchButton);
		waitForElementToDisplay(OR.Dep_wait, 10);
		if(getText(OR.Dep_firstDeps).equals(createdDep))
		{
			clickOn(OR.Dep_DeleteButton);
			clickOn(OR.Dep_confirmButton);
			waitForElementToDisplay(OR.Dep_wait, 10);
			clearEditBox(OR.Dep_SearchTextBox);
			typeIn(OR.Dep_SearchTextBox, createdDep);
			clickOn(OR.Dep_SearchButton);
			waitForElementToDisplay(OR.Dep_wait, 10);
			if(getText(OR.Dep_firstDeps).equals("No records found"))
			{
				testLogPass("Department is deleted Successfully");
			}
			else
			{
				testLogFail("Department is not deleted Successfully");
			}
		}
		else
		{
			testLogFail("Either saerch is not working properly or Department was not added succesfully or could not property from property file.");
		}
		
	}

	//Manage Physicians

	@Test(priority=9)
	public void Tc_Phy_01_02_03()
	{
		testStarts("Tc_Phy_02_03_04" , "Verify that user is able to add new physician using 'Add Physician' button."
				+"Verify that user can search physician by name using search text field and physician drodown.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.physicianPage();
		waitForElementToDisplay(OR.Phy_wait, 10);
		clickOn(OR.Phy_addNewPhy);
		String phyFirstName="First"+ ApplicationKeyword.randomAlphaNumeric(2);
		String phyLastName="Last"+ ApplicationKeyword.randomAlphaNumeric(2);
		String npiNUm="npi"+ApplicationKeyword.randomAlphaNumeric(3);
		if(!Organisation_settingspage.ifButtonDisabled(OR.Dep_saveButton))
		{
			testLogFail("SAVE/Create Button is enabled even if mandatory fields are not filled");
		}
		else
		{
			testLogPass("SAVE/Create Button is disabled if mandatory fields are not filled");
		}
		setProperty("Physician_FIrstName", phyFirstName);
		setProperty("Physician_LastName", phyLastName);
		setProperty("NPI_FOr_Physician", npiNUm);
		typeIn(OR.Phy_addfirstName, phyFirstName);
		typeIn(OR.Phy_addlastName, phyLastName);
		typeIn(OR.Phy_addnpi, npiNUm);		
		clickOn(OR.Phy_facDropDown);
		String defFac=getProperty("userdefaultFac");
		typeIn(OR.Phy_typeDefault_Fac, defFac);
		System.out.println(getText(OR.Phy_firstFacInDropDown));
		if(getText(OR.Phy_firstFacInDropDown).equalsIgnoreCase(defFac))
		{
			clickOn(OR.Phy_plusIcon);
			clickOn(OR.Phy_saveButton);	
			waitForElementToDisplay(OR.Phy_addNewPhy, 10);
		}
		else
		{
			testLogFail("Default facility of user is not being searched or present");
		}
		waitForElementToDisplay(OR.Phy_wait, 10);
		typeIn(OR.Phy_SearchTextBox, (phyFirstName+ " "+phyLastName));
		selectFromDropdown(OR.Phy_SelectFac, defFac);
		clickOn(OR.Phy_SearchButton);
		waitForElementToDisplay(OR.Phy_wait, 10);
		if(getText(OR.Phy_firstFacAfterSearch).equalsIgnoreCase(phyFirstName+ " "+phyLastName))
		{
			testLogPass("New Physician is added");

		}
		else
		{
			testLogFail("New Physician is not added");
		}		
		
	}

	@Test(priority=10)
	public void Tc_Phy_01()
	{
		testStarts("Tc_Phy_01" , "Verify that user can search physician by npi number using search text field and physician drodown.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.physicianPage();		
		Organisation_settingspage.searchwithNPI();
		waitTime(3);
		String npi=getProperty("NPI_FOr_Physician");		
		if(getText(OR.Phy_firstNPIAfterSearch).equals(npi))
		{
			testLogPass("New Physician is searched with NPI number");	
		}
		else
		{
			testLogFail("New Physician is not searched with NPI number");
		}		
				
	}

	@Test(priority=11)
	public void Tc_Phy_04()
	{
		testStarts("Tc_Phy_04" , "Edit Physician > Verify that all fields are editable.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.physicianPage();
		Organisation_settingspage.searchwithNPI();
		waitTime(3);
		if(getText(OR.Phy_firstNPIAfterSearch).equalsIgnoreCase(npiNum))
		{
			clickOn(OR.Phy_editButton);
			clearEditBox(OR.Phy_addfirstName);
			typeIn(OR.Phy_addfirstName, "Abc");
			String s=getAttributeValue(OR.Phy_addfirstName, "value");			
			clearEditBox(OR.Phy_addlastName);
			typeIn(OR.Phy_addlastName, "Abc");
			String s1=getAttributeValue(OR.Phy_addlastName, "value");
			clearEditBox(OR.Phy_addnpi);
			typeIn(OR.Phy_addnpi, "Abc");
			String s3=getAttributeValue(OR.Phy_addnpi, "value");
			if((s+s1+s3).equals("AbcAbcAbc"))
			{
				testLogPass("All fields are editable");
			}
			else
			{
				testLogFail("All fields are not editable");	
			}
		}	
		clickOn(OR.Receive_PrintCloseclose);	
		waitTime(3);
		
	}


	@Test(priority=12)
	public void Tc_Phy_05()
	{
		testStarts("Tc_Phy_05" , "Verify that corresponding physician gets deleted when user clicks Delete button.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.physicianPage();
		Organisation_settingspage.searchwithNPI();
		String npi=getProperty("NPI_FOr_Physician");	
		waitTime(3);
		//waitForElementToDisplay(OR.Phy_wait_onEdit, 10);
		if(getText(OR.Phy_firstNPIAfterSearch).equalsIgnoreCase(npi))
		{
			clickOn(OR.Phy_deleteButton);
			clickOn(OR.Phy_confirmButton);
			waitForElementToDisplay(OR.Phy_addNewPhy, 10);
			clearEditBox(OR.Phy_SearchTextBox);
			typeIn(OR.Phy_SearchTextBox, npi);
			clickOn(OR.Phy_SearchButton);
			waitForElementToDisplay(OR.Phy_wait, 10);
			if(!getText(OR.Phy_firstNPIAfterSearch).equalsIgnoreCase(npi))
			{
				testLogPass("Physician is deleted Successfuly");
			}
			else
			{
				testLogFail("Physician is not deleted");				
			}
		}
		else
		{
			testLogFail("Either given physician is not added successfuly or it is not being searched properly");
		}
		
	}

	//Manage Approval FLows
	@Test(priority=13)
	public void Tc_ManageApprovalFlow_01()
	{
		testStarts("Tc_ManageApprovalFlow_01" , "Verify that �MANAGE APPROVAL FLOWS� page appears when user clicks on Approval Flow button.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.manageApprovalPage();
		waitTime(3);
		String s=getText(OR.AppPage_headerText);
		if(s.contains("MANAGE APPROVAL FLOWS"))
		{
			testLogPass("MANAGE APPROVAL FLOWS page is opened");
		}
		else
		{
			testLogFail("MANAGE APPROVAL FLOWS page is not opened");
		}
		
	}

	//Manufacturers

	@Test(priority=14)
	public void Tc_Manufacturers_01()
	{
		testStarts("Tc_Manufacturers_01" , "Verify that Manufacturers page appears when user clicks on manufacturer option.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.manufacturerPage();
		verifyElementText(OR.manufacturer_headerText, "MANUFACTURERS");
		

	}
	@Test(priority=15)
	public void Tc_Manufacturers_02()
	{
		testStarts("Tc_Manufacturers_02" , "Verify that user can search manufacturer with the name.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.manufacturerPage();
		if(!getText(OR.manufacturer_1stVendor).equalsIgnoreCase(null))
		{
			String manuFacName=getText(OR.manufacturer_1stVendor);
			System.out.println(manuFacName);
			typeIn(OR.manufacturer_searchTextBox, manuFacName);
			clickOn(OR.manufacturer_searchButton);
			waitForElementToDisplay(OR.manufacturer_wait, 10);
			if(getText(OR.manufacturer_1stVendor).equalsIgnoreCase(manuFacName))
			{
				testLogPass("Manufacturer is successfully searched");
			}
			else
			{
				testLogFail("Manufacturer is not successfully searched");
			}
		}
		else
		{
			testLogFail("First Manufacturer name is BLANK. SO cannot search anything");
		}
		
	}

	//GL COde

	@Test(priority=16)
	public void Tc_GL_Code_01_02()
	{
		testStarts("Tc_GL_Code_01_02" , "Verify that user can search manufacturer with the name."+"Create GL Code > Verify that 'Code' and 'Name' fields are mandatory.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.glCode();
		waitForElementToDisplay(OR.glCode_wait, 10);
		clickOn(OR.glCode_addGlCOde);
		if(!Organisation_settingspage.ifButtonDisabled(OR.glCode_saveButton))
		{
			testLogFail("SAVE/Create Button is enabled even if mandatory fields are not filled");
		}
		else
		{
			testLogPass("SAVE/Create Button is disabled if mandatory fields are not filled");
		}
		String glCode="GLCOde"+ApplicationKeyword.randomAlphaNumeric(2);		
		typeIn(OR.glCode_addcode, glCode);
		String glCodeName="Name"+ApplicationKeyword.randomAlphaNumeric(2);
		typeIn(OR.glCode_addName, glCodeName);
		setProperty("glCodeName", glCodeName);
		if(Organisation_settingspage.ifButtonDisabled(OR.glCode_saveButton))
		{
			testLogFail("SAVE/Create Button is enabled even if mandatory fields are not filled");
		}
		else
		{
			testLogPass("SAVE/Create Button is disabled if mandatory fields are not filled");
		}	
		clickOn(OR.glCode_saveButton);
		waitForElementToDisplay(OR.glCode_wait, 10);
		typeIn(OR.glCode_searchTextBox, glCodeName);
		clickOn(OR.glCode_searchButton);
		waitForElementToDisplay(OR.glCode_wait, 10);
		if(getText(OR.glCode_firstGlCOde).equalsIgnoreCase(glCodeName))
		{
			testLogPass("New GL Code is created");
		}
		else
		{
			testLogFail("New GL Code is not created");
		}
		
	}

	@Test(priority=17)
	public void Tc_GL_Code_04_05()
	{
		testStarts("Tc_GL_Code_04_05" , "Update GL Code > Verify that changes get saved on clicking Save button."+"Update GL Code > Verify that changes get saved on clicking Save button.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.glCode();
		waitForElementToDisplay(OR.glCode_wait, 10);
		String glCodeName=getProperty("glCodeName");
		typeIn(OR.glCode_searchTextBox, glCodeName);
		clickOn(OR.glCode_searchButton);
		waitForElementToDisplay(OR.glCode_wait, 10);
		waitTime(3);
		if(getText(OR.glCode_firstGlCOde).equalsIgnoreCase(glCodeName))
		{
			clickOn(OR.glCode_editButton);
			clearEditBox(OR.glCode_addName);
			String glCodeNameNew="Name"+ApplicationKeyword.randomAlphaNumeric(2);
			typeIn(OR.glCode_addName, glCodeNameNew);
			setProperty("glCodeName", glCodeNameNew);	
			clickOn(OR.glCode_saveButton);
			waitForElementToDisplay(OR.glCode_wait, 10);
			clearEditBox(OR.glCode_searchTextBox);
			typeIn(OR.glCode_searchTextBox, glCodeNameNew);
			clickOn(OR.glCode_searchButton);
			waitForElementToDisplay(OR.glCode_wait, 10);
			if(getText(OR.glCode_firstGlCOde).equalsIgnoreCase(glCodeNameNew))
			{
				testLogPass("Gl code is successfully edited");
			}
			else
			{
				testLogFail("Gl code is not successfully edited");
			}
		}
		else
		{
			testLogFail("Created Gl Code is not searched properly or it was not successfully added");
		}
		
	}

	//Patterns
	@Test(priority=18)
	public void Tc_Patterns_01_03()
	{
		testStarts("Tc_Patterns_01_03" , "Verify that user is able to create new pattern by clicking Add Pattern button."+"Create Pattern > Verify that 'Name'and 'Series template' fields are mandatory.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.patternPage();
		clickOn(OR.pattern_addPattern);	
		if(!Organisation_settingspage.ifButtonDisabled(OR.pattern_saveButton))
		{
			testLogFail("SAVE/Create Button is enabled even if mandatory fields are not filled");
		}
		else
		{
			testLogPass("SAVE/Create Button is disabled if mandatory fields are not filled");
		}
		String patternName="0000000_aa"+ApplicationKeyword.randomAlphaNumeric(2);
		setProperty("patternName", patternName);
		typeIn(OR.pattern_addName, patternName);
		selectFromDropdown(OR.pattern_selectFromTemplate, "series");
		int pattern=ApplicationKeyword.generateRandomInt(30);
		String s=String.valueOf(pattern);
		setProperty("pattern", s);
		typeIn(OR.pattern_initialValue, s);
		clickOn(OR.pattern_plusIcon);
		if(Organisation_settingspage.ifButtonDisabled(OR.pattern_saveButton))
		{
			testLogFail("SAVE/Create Button is enabled even if mandatory fields are not filled");
		}
		else
		{
			testLogPass("SAVE/Create Button is enabled if mandatory fields are filled");
		}
		clickOn(OR.pattern_saveButton);
		waitForElementToDisplay(OR.pattern_wait, 10);
		if(getText(OR.pattern_firstPattern).equalsIgnoreCase(patternName))
		{
			testLogPass("New Pattern is added");
		}
		else
		{
			testLogFail("New Pattern is not added");
		}
		
	}
	@Test(priority=19)
	public void Tc_Patterns_06()
	{
		testStarts("Tc_Patterns_06" , "Update Pattern > Verify that changes get saved, on clicking Save button."+"Verify that corresponding next pattern code appears in Sample Pattern Value popup on clicking Generate Unique Number button.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.patternPage();
		String s=getProperty("patternName");
		String s1=getProperty("pattern");
		int tempPattern=Integer.parseInt(s1);		
		waitForElementToDisplay(OR.pattern_wait, 10);
		waitTime(2);
		if(getText(OR.pattern_firstPattern).equalsIgnoreCase(s))
		{
			clickOn(OR.pattern_gearButton);
			String value=getText(OR.pattern_IncreasedpatternValue);
			int val=Integer.parseInt(value);
			if(val==(tempPattern+1))
			{
				testLogPass("Value of Sample Pattern Value is increased to next one");
			}
			else
			{
				testLogFail("Value of Sample Pattern Value is not increased to next one");
			}
		}
		else
		{
			testLogFail("New Pattern is not added");
		}
		
	}

	@Test(priority=20)
	public void Tc_Patterns_05()
	{
		testStarts("Tc_Patterns_05" , "Update Pattern >Verify that changes get saved, on clicking Save button.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.patternPage();
		String s=getProperty("patternName");
		waitTime(2);
		waitForElementToDisplay(OR.pattern_wait, 10);
		if(getText(OR.pattern_firstPattern).equalsIgnoreCase(s))
		{
			clickOn(OR.pattern_editButton);
			String patternName="0000000_aa"+ApplicationKeyword.randomAlphaNumeric(2);
			setProperty("patternName", patternName);
			clearEditBox(OR.pattern_addName);
			typeIn(OR.pattern_addName, patternName);
			clickOn(OR.pattern_saveButton);
			waitForElementToDisplay(OR.pattern_wait, 10);
			waitForElementToDisplay(OR.pattern_addPattern, 10);	
			if(getText(OR.pattern_firstPattern).equalsIgnoreCase(patternName))
			{
				testLogPass("Pattern is edited successfully");
			}
			else
			{
				testLogFail("Pattern is not edited successfully");
			}
		}
		else
		{
			testLogFail("Either Pattern is not successfully added or search is not working properly");
		}
		
	}
	
	@Test(priority=21)
	public void Tc_Patterns_02()
	{
		testStarts("Tc_Patterns_02" , "Verify that Pattern gets deleted on clicking Delete button.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.patternPage();
		String s=getProperty("patternName");
		waitForElementToDisplay(OR.pattern_wait, 10);
		waitTime(2);
		if(getText(OR.pattern_firstPattern).equalsIgnoreCase(s))
		{
			clickOn(OR.pattern_deleteButton);
			clickOn(OR.pattern_confirmButton);
			waitForElementToDisplay(OR.pattern_wait, 10);
			if(!getText(OR.pattern_firstPattern).equalsIgnoreCase(s))
			{
				testLogPass("Pattern is deleted successfully");
			}
			else
			{
				testLogFail("Pattern is not deleted successfully");
			}			
		}
		else
		{
			testLogFail("Either Pattern is not successfully added or search is not working properly");
		}
		
	}
	
	@AfterTest
	public void endReport()
	{
		extent.flush();
	}
}
