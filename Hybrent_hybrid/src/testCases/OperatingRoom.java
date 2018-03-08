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

public class OperatingRoom extends ApplicationKeyword
{
	
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/OperatingRoom.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public void Tc_OP_01_02()
	{
		testStarts("Tc_OP_01_02", "Verify that user is able to add new operating room using 'Add Operating Room' button.");
		Loginpage.OpenBrowserAndLogin();			
		PrefcardPageObject.pageLinkandwait();			
		clickOn(OR.OP_CreatenewOP);
		verifyElementText(OR.OP_CreatenewOPText, "Add Operating Room ");
		String disabledAttri=getAttributeValue(OR.OP_disabledSaveButton, "disabled");
		if(disabledAttri.equalsIgnoreCase("true"))
		{
			testLogPass("All the fileds are mandatory on this page");
		}
		else
		{
			testLogFail("All the fileds are not mandatory on this page");
		}
		String opRoom="OP"+ApplicationKeyword.randomAlphaNumeric(3);
		typeIn(OR.OP_OpName, opRoom);
		clickOn(OR.OP_FacilityDropDown);			
		WebElement elem=driver.findElement(By.xpath("//*[@id='facility']"));
		WebElement elem2=elem.findElement(By.xpath("//li[@class='ui-select-choices-group']//span[1]"));
		elem2.click();
		if(!isElementDisplayed(OR.OP_enabledSaveButton,10))
		{
			testLogPass("SAVE button is enabled");
		}
		else
		{
			testLogFail("SAVE button is not enabled");				
		}
		clickOn(OR.OP_disabledSaveButton);
		typeIn(OR.OP_searchTextBox, opRoom);
		clickOn(OR.OP_searchButton);
		waitForElementToDisplay(OR.OP_wait2, 10);
		String s=getText(OR.OP_firstOPRoom);
		if(s.equalsIgnoreCase(opRoom))
		{
			testLogPass("Operating room is created successfuly");
			clickOn(OR.OP_EditButton);
			waitForElementToDisplay(OR.OP_CreatenewOPText, 10);
			verifyElementText(OR.OP_CreatenewOPText, "Edit Operating Room ");
			clearEditBox(OR.OP_OpName);
			String opRoom2="OP"+ApplicationKeyword.randomAlphaNumeric(3);
			typeIn(OR.OP_OpName, opRoom2);
			clickOn(OR.OP_disabledSaveButton);
			clearEditBox(OR.OP_searchTextBox);
			typeIn(OR.OP_searchTextBox, opRoom2);
			clickOn(OR.OP_searchButton);
			waitForElementToDisplay(OR.OP_wait2,10);
			String s2=getText(OR.OP_firstOPRoom);
			if(s2. equalsIgnoreCase(opRoom2))
			{
				testLogPass("Operating room is Edited successfuly");
				clickOn(OR.OP_DeleteButton);
				clickOn(OR.OP_confirmButton);
				clearEditBox(OR.OP_searchTextBox);
				waitForElementToDisplay(OR.OP_wait2, 10);
				typeIn(OR.OP_searchTextBox, opRoom2);
				clickOn(OR.OP_searchButton);
				String s4=getText(OR.OP_firstOPRoom);
				if(s4.equalsIgnoreCase("No Operating Rooms Found."))
				{
					testLogPass("Operating room is Deleted successfuly");
				}
				else
				{
					testLogFail("Operating room is not Deleted successfuly");
				}	
			}
			else
			{
				testLogFail("Operating room is not Edited successfuly");
			}	
		}
		else
		{
			testLogFail("Operating room is not created");
		}			
		
	}

	@AfterTest
	public void endReport(){
		closeBrowser();
		extent.flush();
	}

}
