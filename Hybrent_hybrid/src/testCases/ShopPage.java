package testCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Itempage;
import pageObject.Loginpage;
import pageObject.MycartPage;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;
import AutomationFramework.Generickeywords;

public class ShopPage extends ApplicationKeyword{
	public static String facility_Name;
	public static String vendor_Name;
	

	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Shop.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Harikrishnan");
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public void Tc_Shop_1() throws InterruptedException
	{
		testStarts("Tc_Shop_1", "Verify that only ACTIVE items are appearing on page.");
		Loginpage.OpenBrowserAndLogin();
		verifyPageTitle("Dashboard");
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//a[@href='#/inventory']"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);
		clickOn(OR.manageInvenLink);
		waitForElementToDisplay(OR.manageInventory_waitforelements, 20);
		verifyPageTitle("Manage Inventory : List");
		selectFromDropdown(OR.manage_stsusDropdwn, "InActive");		           
		clickOn(OR.manage_searchButton);
		waitForElementToDisplay(OR.manage_wait, 20);
		waitTime(4);
		String InactiveItemab=getText(OR.manage_inactiveitem);
		WebElement element2 = driver.findElement(By.xpath("//*[@href='#/shop']"));
		je.executeScript("arguments[0].scrollIntoView(true);",element2);
		clickOn(OR.Shop_Menu);
		waitForElementToDisplay(OR.Shop_searchfield, 60);
		typeIn(OR.Shop_searchfield, InactiveItemab);
		waitForElementToDisplay(OR.Shop_ifnoItemfield, 60);
		verifyElementText(OR.Shop_ifnoItemfield, "No Item Found");
		
	}
	
	@Test(priority=2)
	public void Tc_Shop_2_3()
	{
		testStarts("Tc_Shop_2 and Tc_Shop_3()" , "Verify that �Select Facility� pop up appears when user clicks on facility name with �Shopping for� label"
				+ " Verify that �selected� button appears as disabled for the facility which is displayed on Shop page.");
		NavigateUrl(DashBoardURL);
		String fac=MycartPage.getFac();		
		MycartPage.matchFac(fac);
	}

	@Test(priority=3)
	public void Tc_Shop_4() throws InterruptedException
	{
		testStarts("Tc_Shop_4" , "Verify that user can search item on basis of �Item Name, SKU, MFR� and Vendor filter..");
		NavigateUrl(DashBoardURL);		
		clickOn(OR.Shop_Menu);
		String itemDsc=getProperty("ItemDesc");
		typeIn(OR.Shop_SHopfor_SearchBox,itemDsc);
		waitForElementToDisplay(OR.Shop_wait, 10);
		String itemname=getAttributeValue(OR.Shop_SHopfor_Search_itemdesc, "name");
		if(itemname.equals(itemDsc))
		{
			testLogPass("Same item is searched ItemDescription on cart page" );
		}
		else
		{
			testLogFail("Same item is not searched ItemDescription on cart page" );
		}
		       //search by sku
		clearEditBox(OR.Shop_SHopfor_SearchBox);
		waitForElementToDisplay(OR.Shop_SHopfor_SearchBox, 15);
		String Sku=getProperty("Sku");
		typeIn(OR.Shop_SHopfor_SearchBox,Sku);
		waitForElementToDisplay(OR.Shop_wait, 10);
		String itemname2=getAttributeValue(OR.Shop_SHopfor_Search_itemdesc, "name");
		if(itemname.equals(itemname2))
		{
			testLogPass("Same item is searched sku on cart page" );
		}
		else
		{
			testLogFail("Same item is not searched with sku on cart page" );
		}
		       //search with mfr#
		clearEditBox(OR.Shop_SHopfor_SearchBox);
		waitForElementToDisplay(OR.Shop_SHopfor_SearchBox, 15);
		String mfr=getProperty("ItemMfr");
		typeIn(OR.Shop_SHopfor_SearchBox,mfr);
		waitForElementToDisplay(OR.Shop_wait, 10);
		String itemname3=getAttributeValue(OR.Shop_SHopfor_Search_itemdesc, "name");
		if(itemname.equals(itemname3))
		{
			testLogPass("Same item is searched Manufacture# on cart page" );
		}
		else
		{
			testLogFail("Same item is not searched Manufacture# on cart page" );
		}
		//search with vendor name
		String vendor=getProperty("vendorName");
		selectFromDropdown(OR.Shop_VendorSelect, vendor);
		waitForElementToDisplay(OR.Shop_SHopfor_SearchBox, 15);
		String vendoraftersearch=getText(OR.Shop_SHopfor_SelectfirstItemvendorName);     
		waitForElementToDisplay(OR.Shop_wait, 10);
		if(vendoraftersearch.equals(vendor))
		{
			testLogPass("Same item is searched vendorname on cart page" );
		}
		else
		{
			testLogFail("Same item is not searched vendorname on cart page" );
		}
		
	}
	@Test(priority=4)
	public void Tc_Shop_5()
	{
		testStarts("Tc_Shop_5" , "Verify that user can switch between following tabs using corresponding radio button.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_RecentlyOrderedradiobutton);
		verifyElementText(OR.Shop_SHopfor_LastorderedText, "Last Ordered");
		clickOn(OR.Shop_SHopfor_MostOrderedradiobutton);
		verifyElementText(OR.Shop_SHopfor_PoCountText, "PO Count");      	
		clickOn(OR.Shop_SHopfor_FavOrderedradiobutton);
		verifyAttribute(OR.Shop_SHopfor_favtab, "ng-if", "isFavorite");		
		clickOn(OR.Shop_SHopfor_MyInventoryradiobutton);
		verifyAttribute(OR.Shop_SHopfor_myInventoryFavTab, "ng-if", "!isFavorite");	
		
	}


	@Test(priority=5)
	public void Tc_Shop_6()
	{
		testStarts("Tc_Shop_6" , "Verify that 'All fields' dropdwon appears with following options.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Shop_Menu);
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Shop_orgPage);
		clickOn(OR.Shop_FeaturesPage);
		String crossWalkclass=getAttributeValue(OR.orgsetting_crosswalk, "class");
		boolean orgSettingActive=false;
		String crosswalkIDText="";
		if(crossWalkclass.contains("not-empty"))
		{
			//to get the text in input tag
			crosswalkIDText=getAttributeValue(OR.orgsetting_crosswalktext, "value");	
			testLogPass("CrosswalkID Toggle is Active with value "+ crosswalkIDText);
			orgSettingActive=true;
		}
		else if(crossWalkclass.contains("ng-empty"))
		{
			testLogPass("CrosswalkID Toggle is not Active");
		}
		waitForElementToDisplay(OR.Shop_Menu, 60);
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_allFieldsBtn);
		List<String> exp = new ArrayList<String>( Arrays.asList("SKU", "Mfr Number", "Description", "Quick code", "You can select maximum 2 keys for search."));
		if(orgSettingActive)
		{
			exp.add(crosswalkIDText);
		}
		List<WebElement> options = driver.findElements
				(By.xpath("//ul[@class='dropdown-menu dropdown-menu-form']/li[@role='presentation']"));
		boolean itemFound;
		if(options.size() == exp.size())
		{
			for(String lm:exp)
			{
				itemFound=false;
				for(WebElement we:options)  
				{	
					if(we.getText().toLowerCase().trim().equals(lm.toLowerCase()))
					{
						itemFound=true;
						break;
					}
				}

				if(itemFound)
				{
					testLogPass("Text '" + lm + "' exists in dropdown.");
				}
				else
				{
					testLogFail("Text '" + lm + "' does not exist in dropdown.");
				}
			}
		}
		else
		{
			//fail because size is not equal
			testLogFail("Size of two arrayList is not equal");
		}
		
	}
	
	@Test(priority=6)
	public void Tc_Shop_7()
	{
		testStarts("Tc_Shop_7" , "Verify that show tour pops appear when user clicks on show tour option in dropdown next to refresh button.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.Shop_Menu, 60);
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		//waitForElementToDisplay(OR.Shop_SHopfor_waitdrilldownicon, 60);  
		clickOn(OR.Shop_SHopfor_showTourText);
		//Since the xpath is relative to text of the element, we do not need to compare the text again. 
		//No need for explicit messages because method 'getText' implicitly does messaging on the basis of whether text found or not.
		getText(OR.Shop_SHopfor_showtourtextONPOPUP);
		//Since getText would automatically initialize genericKeyword class' static variable webElement, we can use same variable further
		//Since as per current structure of the page, xpath is not possible for finding out buttons,
		//because there are so many elements with same structure, so we will find parent of the search elem and then find buttons relative to the parent.
		WebElement searchParent = Generickeywords.webElement.findElement(By.xpath(".."));
		List<WebElement> btns = searchParent.findElements(By.tagName("button"));
		if(btns == null || btns.size() == 0)
		{
			testLogFail("No buttons found in tour popup.");
		}
		else
		{
			String nextBtnText=btns.get(0).getText();
			if(nextBtnText.contains("Next"))
			{
				testLogPass("Successfully Matched the Text 'Next' with button '" + nextBtnText + "'");
			}
			else
			{
				testLogFail("Could not match Text 'Next' with button '" + nextBtnText + "'");
			}
			
			String endBtnText=btns.get(1).getText();
			if(endBtnText.contains("End tour"))
			{
				testLogPass("Successfully Matched the Text 'End tour' with button '" + endBtnText + "'");
			}
			else
			{
				testLogFail("Could not match Text 'End tour' with button '" + endBtnText + "'");
			}					
			btns.get(1).click();
			testLogPass("Click on :End tour");
		}		
		
	}  	
	
	@Test(priority=7)
	public void Tc_Shop_08()
	{
		testStarts("Tc_Shop_8", "Verify that user can switch between table view and grid view using \"view type\" option in the dropdown.");
		NavigateUrl(DashBoardURL);	
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		mouseOver(OR.Shop_mouseoverviewtype);
		clickOn(OR.Shop_gridView);
		waitForElementToDisplay(OR.Shop_SHopfor_drilldownicon, 30);
		if(isElementPresent(By.xpath("//div[@id='isotopeContainer']")))
		{
			testLogPass("Successfully changed to GRID View");
		}
		else
		{
			testLogFail("View is not changed to GRID view");
		}
		clickOn(OR.Shop_SHopfor_drilldownicon);
		mouseOver(OR.Shop_mouseoverviewtype);
		clickOn(OR.Shop_tableview);

		waitForElementToDisplay(OR.Shop_SHopfor_drilldownicon, 30);
		
		if(!isElementPresent(By.xpath("//div[@id='isotopeContainer']")))
		{
			testLogPass("Successfully changed to TABLE View");
		}
		else
		{
			testLogFail("View is not changed to TABLE view");
		}
		
	}
	
	@Test(priority=8)
	public void Tc_Shop_09()
	{
		testStarts("Tc_Shop_9" , "Verify that \"Configure Shop Layout\" pop up appears when user clicks on change current layout.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		clickOn(OR.Shop_drilldownCreateLayout);
		String layout="AAA00"+ApplicationKeyword.randomAlphaNumeric(2);
		typeIn(OR.Shop_drilldownLayoutNameText, layout);
		String NameofLayout="Configure Shop Layout "+layout;
		clickOn(OR.Shop_drilldownCreateLayoutSave);
		//Configure layout save button
		clickOn(OR.Shop_drilldownCreateLayoutSave);
		waitForElementToDisplay(OR.Shop_wait2, 10);
		waitTime(5);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		clickOn(OR.Shop_drilldownChangeLayout);
		//waitForElementToDisplay(OR.Shop_drilldownChangeLayoutPopup, 30);
		String actualName=getText(OR.Shop_changeLayoutTextPopUP);
		if(NameofLayout.equalsIgnoreCase(actualName))
		{
			testLogPass("User has opened Change layout Pop up with name "+ actualName);
			
		}
		clickOn(OR.Shop_drilldownChangeLayoutClose);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		mouseOver(OR.Shop_drilldownLayouts);
		//clickOn(OR.Shop_drilldownRemoveLayout);
		WebElement elem=driver.findElement(By.xpath("//a[contains(text(),'"+layout+"')]/../i"));
		elem.click();
		clickOn(OR.Shop_drilldownRemoveLayoutYes);
		waitForElementToDisplay(OR.Shop_SHopfor_drilldownicon, 30);
		
	}

	@Test(priority=9)
	public void Tc_Shop_10()
	{
		testStarts("Tc_Shop_10" , "Verify that \"Layout\" pop up appears when user clicks on on Create new layout or copy current layout option..");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.Shop_Menu, 60);
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		clickOn(OR.Shop_drilldownCreateLayout);	
		verifyElementText(OR.Shop_SHopfor_Layoutpopup, "Layout");		
		clickOn(OR.Shop_SHopfor_Layoutpoupclose);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		clickOn(OR.Shop_SHopfor_copyLayoutpoup);		
		verifyElementText(OR.Shop_SHopfor_Layoutpopup, "Layout");
		clickOn(OR.Shop_SHopfor_Layoutpoupclose);		
		
	}
	
	@Test(priority=10)
	public void Tc_Shop_11() {
		testStarts("Tc_Shop_11", "Verify that Partial and Exact option appears in Match dropdown.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_ShopfaclitySelect);
		pageObject.ShopPage.VerifyShopPage();
		pageObject.ShopPage.MatchDropdown();
		
	}

	@Test( priority=11)
	public void Tc_Shop_12() {
		testStarts("Tc_Shop_12", "Verify that user can set favorite/unfavorite item by clicking on star icon.\r\n");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.Shop_Menu);
		waitForElementToDisplay(OR.Shop_SHopfor_favtab, 60);
		String Fav = getAttributeValue(OR.Shop_SHopfor_favtab, "ng-if");
		if (Fav.equals("isFavorite")) {
			testLogPass("item is isFavorite");
		} else

		if (Fav.equals("!isFavorite")) {
			testLogPass("item is !isFavorite");
		}
		clickOn(OR.Shop_SHopfor_favtab);
		waitTime(10);
		String Fav1 = getAttributeValue(OR.Shop_SHopfor_favtab, "ng-if");
		if (Fav1.equals("!isFavorite")) {
			testLogPass("item is !isFavorite");
		} else if (Fav1.equals("isFavorite")) {
			testLogPass("item is isFavorite");
		}
		
	}

	@Test( priority=12)
	public void Tc_Shop_15() {
		testStarts("Tc_Shop_15", "Verify that 'Edit Item' page opens on clicking 'Edit' button.\n");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.Shop_Menu);
		waitForElementToDisplay(OR.Shop_ItemNameDropDown_First, 60);
		clickOn(OR.Shop_ItemNameDropDown_First);
		Itempage.FirstItemDropdownEdit();
		
	}

	@Test( priority=13)
	public void Tc_Shop_16() {
		testStarts("Tc_Shop_16",
				"Verify that 'Price Change History' popup opens on clicking Price Change History button.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.Shop_Menu);
		waitForElementToDisplay(OR.Shop_ItemNameDropDown_First, 60);
		clickOn(OR.Shop_ItemNameDropDown_First);
		Itempage.FirstItemDropdownPriceHistory();
		
	}

	@Test( priority=14)
	public void Tc_Shop_17() {
		testStarts("Tc_Shop_17",
				"Verify that 'Item Purchase History' popup opens on clicking 'Purchase History' button.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.Shop_Menu);
		waitForElementToDisplay(OR.Shop_ItemNameDropDown_First, 60);
		clickOn(OR.Shop_ItemNameDropDown_First);
		Itempage.FirstItemDropdownPurchaseHistory();
		
	}

	@Test( priority=15)
	public void Tc_Shop_19() {
		testStarts("Tc_Shop_19", "Verify that user is redirected to Cart page on clicking Checkout button.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.Shop_Menu);
		waitForElementToDisplay(OR.Shop_ItemNameDropDown_First, 60);
		clickOn(OR.Checkout_Btn);
		waitForElementToDisplay(OR.Shop_GeneratePo, 60);
		verifyElement(OR.Shop_GeneratePo);
		verifyPageTitle("My Cart");
		
	}

	@Test(priority=16)
	public void Tc_Shop_21() {
		testStarts("Tc_Shop_21",
				"Verify that user can increase or decrease the quantity of item by clicking (+) and (-) button respectively");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.Shop_Menu);
		waitForElementToDisplay(OR.Shop_ItemNameDropDown_First, 60);
		String one = getAttributeValue(OR.Shop_SHopfor_Search_Addtocart_First, "class");
		if (one.contains("btn btn-default btn-xs ng-hide")) {
			String BeforeIncrease = getText(OR.Shop_Item_Qty_First);
			clickOn(OR.Shop_Item_Qty_Increase_First);
			String AfterIncrease = getText(OR.Shop_Item_Qty_First);
			if (AfterIncrease != BeforeIncrease) {
				testLogPass(BeforeIncrease + " valuse  Before Increase" + AfterIncrease + " After Increase...");
			} else {
				testLogPass(BeforeIncrease + " valuse  Before Increase" + AfterIncrease + " After Increase...");
			}
			clickOn(OR.Shop_Item_Qty_Derease_First);
			String BeforeIncrease1 = getText(OR.Shop_Item_Qty_First);
			if (AfterIncrease != BeforeIncrease1) {
				testLogPass(BeforeIncrease + " valuse  Before Decrease' " + AfterIncrease + " After Increase...");
			} else {
				testLogPass(BeforeIncrease + " valuse  Before Decrease' " + AfterIncrease + " After Increase...");
			}

		} else {
			clickOn(OR.Shop_SHopfor_Search_Addtocart_First);
			String BeforeIncrease = getText(OR.Shop_Item_Qty_First);
			clickOn(OR.Shop_Item_Qty_Increase_First);
			String AfterIncrease = getText(OR.Shop_Item_Qty_First);
			if (AfterIncrease != BeforeIncrease) {
				testLogPass(BeforeIncrease + " valuse  Before Increase" + AfterIncrease + " After Increase...");
			} else {
				testLogPass(BeforeIncrease + " valuse  Before Increase" + AfterIncrease + " After Increase...");
			}
			clickOn(OR.Shop_Item_Qty_Derease_First);
			String BeforeIncrease1 = getText(OR.Shop_Item_Qty_First);
			if (AfterIncrease != BeforeIncrease1) {
				testLogPass(BeforeIncrease + " valuse  Before Increase' " + AfterIncrease + " After Increase...");
			} else {
				testLogPass(BeforeIncrease + " valuse  Before Increase' " + AfterIncrease + " After Increase...");
			}
		}
		
	}
	
	@AfterTest
	public void endReport() {
		closeBrowser();
		extent.flush();
	}
	
}
