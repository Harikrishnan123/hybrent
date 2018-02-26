package testCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pageObject.Loginpage;
import pageObject.PrefcardPageObject;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Cases extends ApplicationKeyword
{
	public static String facility_Name;
	public static String vendor_Name;
	public static String ItemDescription = "ItemDesTest001";
	public static String ItemMfrNumber = "ItemMfrNumber001";
	public static String ItemTestID = "ItemTestID001";
	public static String SkuName = "sku001";
		
		@Test
		public void Tc_Cases_01()
		{
			testStarts("Tc_Cases_01", "Verify that user gets redirected to case details page on clicking Edit Patient case button.");
			Loginpage.OpenBrowserAndLogin();			
			PrefcardPageObject.casesPageLinkandwait();
			selectFromDropdown(OR.Cases_SelectDateDropDown, "-- All Dates --");
			clickOn(OR.Cases_SearchButton);
			waitForElementToDisplay(OR.Cases_Editbutton, 10);
			clickOn(OR.Cases_Editbutton);
			verifyElementText(OR.Cases_EditCase, "CASE #");
			
		}
		
		@Test
		public void Tc_Cases_02()
		{
			testStarts("Tc_Cases_02", "Verify that Case preview page open when user clicks on Print.");
			NavigateUrl(DashBoardURL);			
			PrefcardPageObject.casesPageLinkandwait();
			selectFromDropdown(OR.Cases_SelectDateDropDown, "-- All Dates --");
			clickOn(OR.Cases_SearchButton);
			waitForElementToDisplay(OR.Cases_Printbutton, 20);
			clickOn(OR.Cases_Printbutton);
			verifyElementText(OR.Cases_PreviewPopUp, "Download case");
			
			
		}
		
		@AfterTest
		public void endReport(){
			closeBrowser();
			extent.flush();
		}
}
