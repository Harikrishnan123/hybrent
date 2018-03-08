package testCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Loginpage;
import pageObject.MycartPage;
import pageObject.OrderDetailsPage;
import pageObject.OrderPage;
import pageObject.Template;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;
import AutomationFramework.Generickeywords;

public class Tc_Template extends ApplicationKeyword{
	
	public static String facility_Name;
	public static String vendor_Name;	
	public static String firstCategory;
	public static String ItemDescription ;
	public static String ItemMfrNumber;
	public static String ItemTestID ;
	public static String SkuName;
	public static String aliasName;


	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Template.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}

	@Test(groups="Template", priority=1)
	public void Tc_Template_001() {
		try
		{
		testStarts("Tc_Template_001, Tc_Template_002,Tc_Template_005, Tc_Template_006, Tc_Template_008","Verify that user is able to add template by clicking Add Template button.");
		//NavigateUrl(DashBoardURL);
		Loginpage.OpenBrowserAndLogin();
		clickOn(OR.Templates);
		waitTime(5);
		Template.AddDeleteScheduleTemplate();
		}

		catch(Exception e)
		{
			NavigateUrl(DashBoardURL);
		}
	}
	
	@Test(groups="Template", priority=2)
	public void Tc_Template_003() {
		try
		{
		testStarts("Tc_Template_003","Verify that template can be used by every user if �Yes� is selected in toggle button");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Templates);
		waitTime(5);
		Template.AddTemplate();
		isElementDisplayed(OR.Template_ShareYes);
		isElementDisplayed(OR.Template_ShareNo);
		}
		catch(Exception e)
		{
			NavigateUrl(DashBoardURL);
		}
	}
	
	@Test(groups="Template", priority=3)
	public void Tc_Template_004() {
		try
		{
		testStarts("Tc_Template_004, Tc_Template_013","Verify that only user with edit access of template should be able to edit 'Template'\n"
				+ "Verify that use template gets closed when user  clicks on Close button.\r\n" + 
				"");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Templates);
		waitTime(5);
		Template.AddTemplate();
		clickOn(OR.Template_SaveBtn);
		clickOn(OR.Template_CloseBtn);
		typeIn(OR.Template_Search_Input,Template.CreateTemplateName);
		clickOn(OR.Template_SearchBtn);
		waitForElementToDisplay(OR.Template_Edit, 60);
		waitTime(5);
		clickOn(OR.Template_Edit);
		verifyPageTitle("Manage Templates");
		}

		catch(Exception e)
		{
			NavigateUrl(DashBoardURL);
		}
	}

	@Test(groups="Template", priority=4)
	public void Tc_Template_009() {
		try
		{
		testStarts("Tc_Template_009","Verify that Template gets deleted on clicking Delete button.\r\n");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Templates);
		waitTime(5);
		Template.AddTemplate();
		clickOn(OR.Template_SaveBtn);
		clickOn(OR.Template_CloseBtn);
		typeIn(OR.Template_Search_Input,Template.CreateTemplateName);
		clickOn(OR.Template_SearchBtn);
		waitTime(5);
		clickOn(OR.Template_Delete);
		waitForElement(OR.Template__Deletevalidation, 60);
		verifyElementText(OR.Template__Deletevalidation, "Are you sure?");
		verifyElementText(OR.Template__Deletetemplatevalidation, "Are you sure you want to delete this template ?");
		clickOn(OR.Template_Yes);
		}
		catch(Exception e)
		{
			NavigateUrl(DashBoardURL);
		}
	}
	
	@Test(groups="Template", priority=4)
	public void Tc_Template_010() {
		try
		{
		testStarts("Tc_Template_010","Verify that users gets redirected to �Use template� screen on clicking Use icon.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Templates);
		Template.AddTemplate();
		Template.AddItemTemplate();
		clickOn(OR.Template_SaveBtn);
		clickOn(OR.Template_CloseBtn);
		typeIn(OR.Template_Search_Input,Template.CreateTemplateName);
		clickOn(OR.Template_SearchBtn);
		waitTime(5);
		clickOn(OR.Template_Use);
		waitTime(5);
		clickOn(OR.Template_GeneratePO);
		verifyElementText(OR.Template_Warning, "Warning");
		verifyElementText(OR.Template_WarningMsg, "Increase qty. of at least one item to generate the PO.");
		clickOn(OR.Template_Warningok);
		waitTime(3);
		clickOn(OR.Template_Plus);
		waitForElement(OR.Template_GeneratePO, 60);
		clickOn(OR.Template_GeneratePO);
		waitTime(5);
		verifyPageTitle("My Orders");
	}
		catch(Exception e)
		{
			NavigateUrl(DashBoardURL);
		}
	}
	@AfterTest
	public void endReport(){
		closeBrowser();
		extent.flush();
	}
}


