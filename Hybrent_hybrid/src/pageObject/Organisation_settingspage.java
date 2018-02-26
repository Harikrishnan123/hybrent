package pageObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Organisation_settingspage extends ApplicationKeyword {
	public static String ItemCatName;
	public static String facName=null;
	public static List<String> depNames_FacPage= null ;
	//For User Page
	public static void adminAndUserPage()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Users_page);
		waitForElementToDisplay(OR.Users_FirstUserName, 10);					 
	}
	public static void searchAndUserWait()
	{	

		clickOn(OR.Users_SearchButton);
		waitForElementToDisplay(OR.Users_SearchTextBox, 10);	

	}

	public static void verifyTabs()
	{

		List<String> tabs = new ArrayList<String>( Arrays.asList("User", "User Access", "Item Catalog Access", "PO Alerts", "Facility Access", "Vendor Access", "Notification Access"));
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

	}

	//For Facility Page

	public static void AdminAndFacilityLink()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Facilty_FacilityLink);	
		waitForElementToDisplay(OR.Facilty_WaitforTableElem, 10);
	}
	//Item CAtegory page
	public static void AdminAndItemCat()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.ItemCat_PageLink);	
		waitForElementToDisplay(OR.ItemCat_pageContainerWait, 10);
	}

	public static void AdminAndPrintBarcodeLink()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.PrintBarcodes_PageLink);	
		//waitForElementToDisplay(OR.PrintBarcodes_waitforForFIrst, 10);
	}
	
	public static void SIPage()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.SI_PageLink);	
		//waitForElementToDisplay(OR.SI_TableWait, 10);
	}
	
	public static void PhysicianPage()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Physician_PageLink);	
		//waitForElementToDisplay(OR.SI_TableWait, 10);
	}


	public static void InvoicePayementTermPage()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Pterms_PageLink);	
		
	}

	public static void newsPage()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.News_PageLink);			
	}
	
	public static void vendorsPage()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Vendor_PageLink);	
	}
	public static void priceTierPage()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.priceTier_PageLink);	
	}
	public static void itemCatalogPage()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Admin_ItemCatalog);
		waitForElementToDisplay(OR.ItemCatalog_AddItem, 60);	
	}
	
	public static void InvLocationPage()
	{	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.InvLoc_PageLink);
		//waitForElementToDisplay(OR.ItemCatalog_AddItem, 60);	
	}
	
	public static WebElement matchFac_Inv()
	{	
		String INvLoc=getProperty("InventoryLocation");
		String userfac=getProperty("userdefaultFac");
		typeIn(OR.InvLoc_searchTextBox, INvLoc);
		waitForElementToDisplay(OR.InvLoc_waitfortable, 10);
		

		System.out.println("UserFac--" + userfac);
		System.out.println("GetText--" + getText(OR.InvLoc_firstInventoryLoc));
		

		if(getText(OR.InvLoc_firstInventoryLoc).trim().equalsIgnoreCase(userfac.trim()))
		{
			clickOn(OR.InvLoc_firstPlusIcon);
			List<WebElement> allFac=driver.findElements(By.xpath("//ul[@class='facility_department_sublist']/li"));
			for(WebElement elem:allFac)
			{
				String invName=elem.getText();
				if(INvLoc.trim().equalsIgnoreCase(invName.trim()))
				{ 
					return elem;					
				}
			}
		}
		return null;
	}

	public static List<String> getAllDeps()
	{	
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
		return depNames_FacPage;
	}
	
	public static boolean ifButtonDisabled(String objLocator)
	{
		if(getAttributeValue(objLocator, "disabled")!=null)
			{
				return true;
			}
			else
			{
				return false;
			}			
	}
	
	public static void physicianPage()
	{
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Phy_pageLink);
		
	}
	
	public static void searchwithNPI()
	{
	String npi=getProperty("NPI_FOr_Physician");
	typeIn(OR.Phy_SearchTextBox, npi);
	String defFac=getProperty("userdefaultFac");
	selectFromDropdown(OR.Phy_SelectFac, defFac);
	clickOn(OR.Phy_SearchButton);
	waitForElementToDisplay(OR.Phy_wait, 20);
	
	}
	
	public static void manageApprovalPage()
	{
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.AppPage_PageLink);
		
	}
	public static void manufacturerPage()
	{
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.manufacturer_PageLink);
		
	}
	public static void glCode()
	{
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.glCode_PageLink);
		
	}
	public static void patternPage()
	{
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.pattern_PageLink);
		
	}
	
}