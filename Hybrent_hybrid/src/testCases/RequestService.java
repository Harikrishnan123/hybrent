package testCases;

import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Loginpage;
import pageObject.MycartPage;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class RequestService extends ApplicationKeyword{
	public static String ItemDescription;
	public static String ItemMfrNumber;
	public static String ItemTestID;
	public static String SkuName;
	public static String facility_Name;
	public static String vendor_Name;

	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/RequestService.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Harikrishnan");
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public void Tc_SPO_01()
	{
		testStarts("Tc_SPO_01", "Verify that user can search service items on the basis of Item Name, alias, MFR, SKU in the search field.");
		Loginpage.OpenBrowserAndLogin();
		String ReqServDesc = "ReqServDesc"+ApplicationKeyword.randomAlphaNumeric(2);
		String ReqServ_Mfr = "ReqServMfr"+ApplicationKeyword.randomAlphaNumeric(2);
		setProperty("ReqServ_Mfr", ReqServ_Mfr);
		String ReqServ_ID = "ReqServID"+ApplicationKeyword.randomAlphaNumeric(2);
		String ReqServ_Sku = "ReqServsku"+ApplicationKeyword.randomAlphaNumeric(2);
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Admin_ItemCatalog);
		waitForElementToDisplay(OR.ItemCatalog_AddItem, 60);
		clickOn(OR.ItemCatalog_AddItem);
		typeIn(OR.ItemCatalog_AddItem_ItemDetails_Description, ReqServDesc);
		MycartPage.chkMan_level("ReqServ_Mfr");
		if(isElementDisplayed(OR.ItemCatalog_AddItme_TestId, 10))
		{
			typeIn(OR.ItemCatalog_AddItme_TestId, ReqServ_ID);
		}
		clickOn(OR.Request_isServicePOToggle);
		clickOn(OR.ItemCatalog_VendorsTab);
		clickOn(OR.ItemCatalog_AddVendors);
		clickOn(OR.ItemCatalog_Add_Vendorsname);		        
		clickOn(OR.ItemCatalog_AddItem_VendorSelect_First);		
		typeIn(OR.ItemCatalog_Add_VendorsSkuName, ReqServ_Sku);
		typeIn(OR.ItemCatalog_Add_VendorsMinOrderQty, "3");
		selectFromDropdown(OR.Request_Duration, "For One Time");
		vendor_Name=getText(OR.ItemCatalog_firstvendor);
		MycartPage.chkMan_level("ReqServ_Mfr");
		clickOn(OR.ItemCatalog_AddItem_Man_Save);
		waitForElementToDisplay(OR.ItemCatalog_AddItem_MapValidation, 60);
		verifyElementText(OR.ItemCatalog_AddItem_MapValidation, "Do you want to map this item to your various facilities?");
		clickOn(OR.ItemCatalog_AddItem_MapValidation_yes);
		getText(OR.ItemCatalog_AddItem_Map_Header);
		verifyElementText(OR.ItemCatalog_AddItem_Map_SearchFacility_Text, "Search Facility");
		clickOn(OR.ItemCatalog_mapallbuttontopright);
		clickOn(OR.ItemCatalog_mapwithalltopright);
		waitForElementToDisplay(OR.ItemCatalog_verifytextpopup, 60);
		verifyElementText(OR.ItemCatalog_verifytextpopup, "Map with all facilities");
		typeIn(OR.ItemCatalog_purchaseprice, "60");
		clickOn(OR.ItemCatalog_mapallbutton);
		waitForElementToDisplay(OR.ItemCatalog_AddItem_Map_Closepage, 60);	
		clickOn(OR.ItemCatalog_AddItem_Map_Closepage);		
		clickOn(OR.Request_MenuLink);
		waitForElementToDisplay(OR.Request_drillDown, 20);
		typeIn(OR.Request_searchBox, ReqServDesc);
		waitForElementToDisplay(OR.Request_getItemName, 20);
		String ItemDescAfterSearch=getText(OR.Request_getItemName);
		System.out.println(ItemDescAfterSearch);
		if(ItemDescAfterSearch.equalsIgnoreCase(ReqServDesc))
		{
			testLogPass("Item is searched with Item Description");
		}
		else
		{
			testLogFail("Item is not searched with Item Description");
		}
		clearEditBox(OR.Request_searchBox);
		waitForElementToDisplay(OR.Request_searchBox, 20);
		typeIn(OR.Request_searchBox, ReqServ_Sku);
		waitForElementToDisplay(OR.Request_getSkuName, 20);
		String skuAfterSearch=getText(OR.Request_getSkuName);
		if(skuAfterSearch.equalsIgnoreCase(ReqServ_Sku))
		{
			testLogPass("Item is searched with sku");
		}
		else
		{
			testLogFail("Item is not searched with sku");
		}
		clearEditBox(OR.Request_searchBox);
		waitForElementToDisplay(OR.Request_searchBox, 20);
		String s=getProperty("ReqServ_Mfr");
		typeIn(OR.Request_searchBox, s);
		waitForElementToDisplay(OR.Request_getSkuName, 20);
		if(ItemDescAfterSearch.equalsIgnoreCase(ReqServDesc))
		{
			testLogPass("Item is searched with mfr#");
		}
		else
		{
			testLogFail("Item is not searched with mfr#");
		}
		
	}

	@Test(priority=2)
	public void Tc_SPO_02()
	{
		testStarts("Tc_SPO_02", "Verify that �Add Service� pop up appears when user clicks on Add New Service option. ");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Request_MenuLink);
		waitForElementToDisplay(OR.Request_drillDown, 20);
		clickOn(OR.Request_drillDown);
		clickOn(OR.Request_createNewService);
		verifyElementText(OR.Request_createNewServicePopUpText, "Add Service");	
		

	}
	@Test(priority=3)
	public void Tc_SPO_03()
	{
		testStarts("Tc_SPO_03", "Verify that Service location dropdown with department and facility appears on the page. ");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Request_MenuLink);
		waitForElementToDisplay(OR.Request_ServiceLocationText, 10);
		verifyElementText(OR.Request_ServiceLocationText, "Service Location:");	
		int one=driver.findElements(By.xpath("//select[@id='shippingLocation']//optgroup")).size();
		int two=driver.findElements(By.xpath("//select[@id='shippingLocation']//option")).size();
		testLogPass("Number of Facilities in the DropDown is: "+ one);
		testLogPass("Number of Departments in the DropDown is: "+ two);
		System.out.println(two);				            
		
	}
	
	@Test(priority=4)
	public void Tc_SPO_04()
	{
		testStarts("Tc_SPO_04", "Verify that Service Date field with calendar option appears on page. ");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Request_MenuLink);
		waitForElementToDisplay(OR.Request_ServiceLocationText, 10);
		String s=getText(OR.Request_ServiceDateText);
		if(s.equals("Service date:"))
		{
			testLogPass("Service Date Text is Present on the page");
		}
		else
		{
			testLogFail("Service Date Text is not Present on the page");
		}
		clickOn(OR.Request_CalenderIcon);
		if(isElementDisplayed(OR.Request_calenderIconAttribte))
		{
			testLogPass("Calender icon is Present on the page");
		}
		else
		{
			testLogFail("Calender icon is not Present on the page");
		}
		
	}

	@Test(priority=5)
	public void Tc_SPO_05() throws InterruptedException
	{
		testStarts("Tc_SPO_05", " Verify that list of service vendor appears in the Vendor dropdown.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Request_MenuLink);
		waitForElementToDisplay(OR.Request_ServiceLocationText, 10);
		verifyElementText(OR.Request_VendorText, "Vendor: ");	
		clickOn(OR.Request_vendorDropDown);	
		//List<WebElement> lstElem=driver.findElements(By.xpath("//*[@id='vendor']/ul/li/div[contains(@id,'ui-select-choices-row-')]"));
		List<WebElement> lstElem=driver.findElements(By.xpath("//*[contains(@id,'ui-select-choices-row-')]"));
		for(WebElement we:lstElem)
		{
			testLogPass("All the vendors in the Vendor DropDown are: "+ we.getText());
		}
		
	}
	@AfterTest
	public void endReport()
	{
		closeBrowser();
		extent.flush();
	}


}
