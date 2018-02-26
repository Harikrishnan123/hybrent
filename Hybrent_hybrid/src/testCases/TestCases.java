package testCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;
import pageObject.*;
import pageObject.SwitchUserPage;
import AutomationFramework.Generickeywords;

public class TestCases extends ApplicationKeyword {
	public static String UserName = "admin";
	public static String Password = "goouser";
	public static String URL = "https://qa4.test.hybrent.com/b/#/login/";
	public static String DashBoardURL = "https://qa4.test.hybrent.com/b/#/dashboard";
	public static String ItemDescription;
	public static String ItemMfrNumber;
	public static String ItemTestID;
	public static String SkuName;
	public static String facility_Name;
	public static String vendor_Name;
	
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/STMExtentReport.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Harikrishnan");
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(groups="Login", priority=1)
	public void Tca_Login_001() {
		System.out.println("CameTo");
		testStarts("Tc_Login_001", "Verify that system does not allow user to Login with “Invalid Credentials”");
		openBrowser(URL);
		Loginpage.login("dsds", "dsdskjk");
		waitForElementToDisplay(OR.Login_Error, 60);
		verifyElement(OR.Login_Error);
		verifyElementText(OR.Login_Validation, "Invalid user name or password.");
		verifyElement(OR.Login_Error);
		waitForElementToDisplay(OR.Login_okay, 60);
		clickOn(OR.Login_okay);
		testStarts("Tc_Login_002", "Verify that system allows user to Login with 'Valid Credentials'");
		Loginpage.login(UserName, Password);
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
	}

	@Test(groups="Dashboard", priority=1)
	public void Tc_Dashboard_001() {
		testStarts("Tc_Dashboard_001, Tc_Dashboard_003",
				"Verify that recent notifications appear on clicking Notification (bell) icon button.\n"
						+ "Verify that user gets redirected to “My Notifications” page, on clicking “View All” button\"");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Notification_Bell, 60);
		clickOn(OR.Notification_Bell);
		Dashboardpage.NotificationCount();
		
	}

	@Test(groups="Dashboard", priority=2)
	public void Tc_Dashboard_002() {
		testStarts("Tc_Dashboard_002",
				"Verify that user gets redirected to “Notification Settings” page, on clicking “Notification Settings” button");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		waitForElementToDisplay(OR.User_NotificationSetting, 60);
		clickOn(OR.User_NotificationSetting);
		Dashboardpage.verificationNotificationPage();
		
	}

	@Test(groups="Dashboard", priority=3)
	public void Tc_Dashboard_004() {
		testStarts("Tc_Dashboard_004",
				"Verify that user is redirected to Cart page on clicking Shopping Cart icon button.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.MyCart, 60);
		MycartPage.verificationMycartPage();
		
	}

	@Test(groups="Dashboard", priority=4)
	public void Tc_Dashboard_005() {
		testStarts("Tc_Dashboard_005",
				"Verify that user gets redirected to “Profile” page, on clicking Profile button.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		waitForElementToDisplay(OR.User_Profile, 60);
		clickOn(OR.User_Profile);
		ProfilePage.profileverification();
		
	}

	@Test(groups="Dashboard", priority=5)
	public void Tc_Dashboard_006() {
		testStarts("Tc_Dashboard_006", "Verify that chat window opens up when user clicks on 'view chat' icon.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Chat, 60);
		ChatPage.VerifyChatPage();
		
	}

	@Test(groups="Dashboard", priority=6)
	public void Tc_Dashboard_007() {
		testStarts("Tc_Dashboard_007",
				"Verify that user gets redirected to user alerts page on clicking  alerts option..");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		waitForElementToDisplay(OR.User_alert, 60);
		clickOn(OR.User_alert);
		Alertpage.VerifyAlert();
		
	}

	@Test(groups="Dashboard", priority=7)
	public void Tc_Dashboard_008() {
		testStarts("Tc_Dashboard_008",
				"Verify that “Pending Survey” pop up appears when user clicks on survey option.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		clickOn(OR.User_Survey);
		SurveyPage.VerifySurvey();
		
	}

	@Test(groups="Dashboard", priority=8)
	public void Tc_Dashboard_009() {
		testStarts("Tc_Dashboard_009",
				"Verify that “Change Password” pop up appears when user clicks on Change Password option.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		clickOn(OR.User_ChangePassword);
		Changepasswordpage.VerifyChangePassword();
		
	}

	@Test(groups="Dashboard", priority=9)
	public void Tc_Dashboard_010() {
		testStarts("Tc_Dashboard_010",
				"Verify that “Select user to login” screen appears when user clicks on switch user option.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		clickOn(OR.User_SwitchUser);
		SwitchUserPage.VerifySwitchUser();
		
	}

	@Test(groups="Dashboard", priority=10)
	public void Tc_Dashboard_011() {
		testStarts("Tc_Dashboard_011",
				"Verify that user gets redirected to corresponding Report on clicking View More Reports button.");
		NavigateUrl(DashBoardURL);
		
		Reportspage.VerifyViewReport();
		
	}

	@Test(groups="Dashboard", priority=11)
	public void Tc_Dashboard_012() {
		testStarts("Tc_Dashboard_012",
				"Verify that relevant data appears under Monthly Purchase Order Value, Number of Backorders and Vendor Performance sections");
		NavigateUrl(DashBoardURL);
		
		Dashboardpage.MonthlyReport();
		Dashboardpage.PartialReviews();
		Dashboardpage.Avgperformance();
		
	}

	@Test(groups="Dashboard", priority=12)
	public void Tc_Dashboard_013() {
		testStarts("Tc_Dashboard_013, Tc_Dashboard_014, Tc_Dashboard_015",
				"Verify that “Recent Orders” and “News & Events” sections appear on Page.\n"
						+ "Verify that user gets redirected to corresponding news on clicking any news.\r\n"
						+ "Verify that user gets redirected to corresponding PO details on clicking any PO link under \"“Recent Orders” section,\r\n"
						+ "");
		NavigateUrl(DashBoardURL);
		
		Dashboardpage.PartialReviews();
		Dashboardpage.Avgperformance();
		Dashboardpage.VerifyOrders();
		driver.navigate().back();
		Dashboardpage.News();
		
		
	}

	@Test(groups="Shop", priority=1)
	public void Tc_Shop_1() throws InterruptedException {
		testStarts("Tc_Shop_1", "Verify that only ACTIVE items are appearing on page.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");

		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//a[@href='#/inventory']"));

		je.executeScript("arguments[0].scrollIntoView(true);", element);

		clickOn(OR.manageInvenLink);
		waitForElementToDisplay(OR.manageInventory_waitforelements, 60);
		verifyPageTitle("Manage Inventory : List");
		System.out.println(driver.getTitle());
		selectFromDropdown(OR.manage_stsusDropdwn, "InActive");
		clickOn(OR.manage_searchButton);
		// clickOn(OR.DashBoard_AdminDropdown);
		// clickOn(OR.Admin_ItemCatalog);
		// waitForElementToDisplay(OR.ItemCatalog_AddItem, 60);
		// verifyPageTitle("Items Catalog : List");

		waitForElementToDisplay(OR.manage_inactiveitem, 60);
		String InactiveItemab = getText(OR.manage_inactiveitem);
		System.out.println(InactiveItemab);
		WebElement element2 = driver.findElement(By.xpath("//*[@href='#/shop']"));
		je.executeScript("arguments[0].scrollIntoView(true);", element2);
		clickOn(OR.Shop_Menu);

		waitForElementToDisplay(OR.Shop_searchfield, 60);
		typeIn(OR.Shop_searchfield, InactiveItemab);
		waitForElementToDisplay(OR.Shop_ifnoItemfield, 60);
		verifyElementText(OR.Shop_ifnoItemfield, "No Item Found");
		

		// clickOn(OR.ItemCatalog_AddItem);
		// typeIn(OR.ItemCatalog_AddItem_ItemDetails_Description, "Rav-Test");
		// typeIn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetails, "mrf101");
		// clickOn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetailsDropdown);
		// clickOn(OR.ItemCatalog_AddItem_Man_Select);
		// clickOn(OR.ItemCatalog_VendorsTab);
		// clickOn(OR.ItemCatalog_AddVendors);
		// clickOn(OR.ItemCatalog_Add_Vendorsname);
		// clickOn(OR.ItemCatalog_Add_VendorsnameSelect);
		// typeIn(OR.ItemCatalog_Add_VendorsSkuName, "Aut01");
		// clickOn(OR.ItemCatalog_AddItem_Man_Save);

	}

	//
	@Test(groups="Shop", priority=2)
	public void Tc_Shop_2_3() {
		testStarts("Tc_Shop_2 and Tc_Shop_3",
				"Verify that “Select Facility” pop up appears when user clicks on facility name with “Shopping for” label"
						+ " Verify that “selected” button appears as disabled for the facility which is displayed on Shop page.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.Shop_Menu);
		String facility_Name = getText(OR.Shop_SHopfor_getfacilityName);
		System.out.println(facility_Name);
		clickOn(OR.Shop_SHopfor_ShopfaclitySelect);
		waitForElementToDisplay(OR.Shop_SHopfor_Shopfaclity, 60);
		verifyElementText(OR.Shop_SHopfor_Shopfaclity, "Select Facility");

		waitForElementToDisplay(OR.Shop_countoffacilities, 60);
		int one = driver.findElements(By.xpath("//*[@style='border-right: none;vertical-align: middle;']")).size();
		System.out.println(one);
		boolean facFound = false;
		String xpath;
		String selectedFacility;
		// Map<String, String> countofItems = new HashMap<String, String>();
		// String itemCountText;
		WebElement btn;
		for (int i = 1; i <= one; i++) {

			// String facility=driver.findElement(By.xpath("(//*[@style='border-right:
			// none;vertical-align: middle;'])["+i+"]")).getText();
			xpath = "(//table[@class='table table-responsive table-striped table-bordered']/tbody/tr[" + i + "]";
			selectedFacility = driver.findElement(By.xpath(xpath + "/td)")).getText();

			// itemCountText=driver.findElement(By.xpath(xpath+"/td[2]/div/span)")).getText();
			// //itemCountText=itemCountText.substring(1,1);
			// System.out.println(itemCountText);
			System.out.println(selectedFacility);

			if (selectedFacility.equals(facility_Name)) {
				facFound = true;
				btn = driver.findElement(By.xpath(xpath + "/td[2]/div/button)"));
				if (btn.getAttribute("disabled") != null) {
					testLogPass("Go the text '" + selectedFacility + "' Matches with selected Facility");
				} else {
					testLogFail("'" + facility_Name + "' is not selected in popup.");
				}
				break;
			}

		}

		if (!facFound) {
			testLogFail("Could not Got the text '" + facility_Name + "' Matches with selected Facility");

		}

		clickOn(OR.Shop_SHopfor_cancelButtonPopup);
		
	}

	@Test(groups="Shop", priority=3)
	public void Tc_Shop_4() throws InterruptedException {
		testStarts("Tc_Shop_4",
				"Verify that user can search item on basis of “Item Name, SKU, MFR” and Vendor filter..");
		NavigateUrl(DashBoardURL);
		

		waitForElementToDisplay(OR.Shop_Menu, 60);
		// adding new item
		ItemDescription = "ItemDes" + randomAlphaNumeric(4);
		ItemMfrNumber = "ItemMfrNumber" + randomAlphaNumeric(4);
		ItemTestID = "ItemTestID" + randomAlphaNumeric(4);
		SkuName = "sku" + randomAlphaNumeric(4);

		// NavigateUrl(DashBoardURL);
		// 
		// waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Admin_ItemCatalog);
		waitForElementToDisplay(OR.ItemCatalog_AddItem, 60);

		clickOn(OR.ItemCatalog_AddItem);
		typeIn(OR.ItemCatalog_AddItem_ItemDetails_Description, ItemDescription);
		typeIn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetails, ItemMfrNumber);
		
		Select select = new
				Select(driver.findElement(By.xpath("//select[@ng-model='item.category_id']")));
		WebElement option = select.getOptions().get(2);
		String defaultItem = option.getText();
		System.out.println(defaultItem );
		
		String one = driver.findElement(By.xpath("//select[@ng-model='item.category_id']")).getText();
		String one1 = driver.findElement(By.xpath("//select[@ng-model='item.category_id']")).getAttribute("value");
		System.out.println(one+"-"+ one1);
		typeIn(OR.ItemCatalog_AddItme_TestId, ItemTestID);
		clickOn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetailsDropdown);
		clickOn(OR.ItemCatalog_AddItem_Man_Select);
		clickOn(OR.ItemCatalog_VendorsTab);
		clickOn(OR.ItemCatalog_AddVendors);

		clickOn(OR.ItemCatalog_Add_Vendorsname);

		// String VendorName=getText(OR.ItemCatalog_Add_Vendorsname);
		waitForElementToDisplay(OR.ItemCatalog_AddItem_VendorSelect_First, 60);
		clickOn(OR.ItemCatalog_AddItem_VendorSelect_First);
		typeIn(OR.ItemCatalog_Add_VendorsSkuName, SkuName);
		typeIn(OR.ItemCatalog_Add_VendorsMinOrderQty, "3");

		vendor_Name = getText(OR.ItemCatalog_firstvendor);
		System.out.println(vendor_Name);

		clickOn(OR.ItemCatalog_AddItem_Man_Save);
		waitForElementToDisplay(OR.ItemCatalog_AddItem_MapValidation, 60);
		verifyElementText(OR.ItemCatalog_AddItem_MapValidation,
				"Do you want to map this item to your various facilities?");
		clickOn(OR.ItemCatalog_AddItem_MapValidation_yes);
		getText(OR.ItemCatalog_AddItem_Map_Header);

		verifyElementText(OR.ItemCatalog_AddItem_Map_SearchFacility_Text, "Search Facility");

		clickOn(OR.ItemCatalog_mapallbuttontopright);
		clickOn(OR.ItemCatalog_mapwithalltopright);
		// selectFromDropdown(OR.ItemCatalog_maptoAllfacilities, "Map with all
		// facility");
		waitForElementToDisplay(OR.ItemCatalog_verifytextpopup, 60);

		verifyElementText(OR.ItemCatalog_verifytextpopup, "Map with all facilities");

		typeIn(OR.ItemCatalog_purchaseprice, "60");
		clickOn(OR.ItemCatalog_mapallbutton);
		waitForElementToDisplay(OR.ItemCatalog_AddItem_Map_Closepage, 60);

		// typeIn(OR.ItemCatalog_popupFAcilitySearch, facility_Name);
		// clickOn(OR.ItemCatalog_popupFAcilitySearchbutton);
		// waitForElementToDisplay(OR.ItemCatalog_wait_untilsearch,60);
		// clickOn(OR.ItemCatalog_AddItem_Map_AddFacility);

		// typeIn(OR.ItemCatalog_AddItem_Map_PurchagePrice, "60");
		// typeIn(OR.ItemCatalog_AddItem_Map_GPOPurchagePrice, "10");
		// clickOn(OR.ItemCatalog_AddItem_Map_GPOPurchagePriceSave);
		clickOn(OR.ItemCatalog_AddItem_Map_Closepage);
		// WebElement btn=driver.findElement(By.xpath(//button[@class='close'));
		clickOn(OR.Shop_Menu);
		waitForElementToDisplay(OR.Shop_SHopfor_SearchBox, 15);

		selectFromDropdown(OR.Shop_SHopfor_Search_Match, "Exact match");
		waitForElementToDisplay(OR.Shop_SHopfor_Search_itemdesc, 15);
		// //search the added item on shop page
		typeIn(OR.Shop_SHopfor_SearchBox, ItemDescription);
		// waitForElementToDisplay(OR.Shop_SHopfor_Search_itemdesc, 15);
		String itemname = getAttributeValue(OR.Shop_SHopfor_Search_itemdesc, "name");

		if (itemname.equals(ItemDescription)) {
			testLogPass("Same item is searched '" + itemname + "' on cart page");
		} else {
			testLogFail("Same item is not searched '" + itemname + "' on cart page");
		}

		// //search by sku
		clearEditBox(OR.Shop_SHopfor_SearchBox);
		waitForElementToDisplay(OR.Shop_SHopfor_SearchBox, 15);

		typeIn(OR.Shop_SHopfor_SearchBox, SkuName);
		// waitForElementToDisplay(OR.Shop_SHopfor_Search_skuName, 10);
		String skuaftersearch = getText(OR.Shop_SHopfor_Search_skuName);
		//
		if (skuaftersearch.equals(SkuName)) {
			testLogPass("Same item is searched '" + skuaftersearch + "' on cart page");

		} else {
			testLogFail("Same item is not searched '" + skuaftersearch + "' on cart page");
		}

		// //search with mfr#
		clearEditBox(OR.Shop_SHopfor_SearchBox);
		waitForElementToDisplay(OR.Shop_SHopfor_SearchBox, 15);

		// search with vendor name
		System.out.println(vendor_Name);
		selectFromDropdown(OR.Shop_VendorSelect, vendor_Name);

		waitForElementToDisplay(OR.Shop_SHopfor_SearchBox, 15);
		String vendoraftersearch = getText(OR.Shop_SHopfor_SelectfirstItemvendorName);
		//
		if (vendoraftersearch.equals(vendor_Name)) {
			testLogPass("Same item is searched '" + vendoraftersearch + "' on cart page");

		} else {
			testLogFail("Same item is not searched '" + vendoraftersearch + "' on cart page");
		}
		// Thread.sleep(4000);
		

	}

	@Test(groups="Shop", priority=4)
	public void Tc_Shop_5() {
		testStarts("Tc_Shop_5", "Verify that user can switch between following tabs using corresponding radio button.");
		NavigateUrl(DashBoardURL);
		

		waitForElementToDisplay(OR.Shop_Menu, 60);
		clickOn(OR.Shop_Menu);

		clickOn(OR.Shop_SHopfor_RecentlyOrderedradiobutton);
		verifyElementText(OR.Shop_SHopfor_ResultGridTab, "Last Ordered");

		clickOn(OR.Shop_SHopfor_MostOrderedradiobutton);
		verifyElementText(OR.Shop_SHopfor_ResultGridTab, "PO Count");

		clickOn(OR.Shop_SHopfor_FavOrderedradiobutton);
		verifyAttribute(OR.Shop_SHopfor_favtab, "ng-if", "isFavorite");

		clickOn(OR.Shop_SHopfor_MyInventoryradiobutton);
		verifyAttribute(OR.Shop_SHopfor_myInventoryFavTab, "ng-if", "!isFavorite");

		
	}

	@Test(groups="Shop", priority=5)
	public void Tc_Shop_6() {
		testStarts("Tc_Shop_6", "Verify that 'All fields' dropdwon appears with following options.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Shop_Menu);
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Shop_orgPage);
		clickOn(OR.Shop_FeaturesPage);
		String crossWalkclass = getAttributeValue(OR.orgsetting_crosswalk, "class");
		boolean orgSettingActive = false;
		String crosswalkIDText = "";
		if (crossWalkclass.contains("not-empty")) {
			// to get the text in input tag
			crosswalkIDText = getAttributeValue(OR.orgsetting_crosswalktext, "value");
			testLogPass("CrosswalkID Toggle is Active with value " + crosswalkIDText);
			orgSettingActive = true;
		} else if (crossWalkclass.contains("ng-empty")) {
			testLogPass("CrosswalkID Toggle is not Active");
		}

		waitForElementToDisplay(OR.Shop_Menu, 60);

		clickOn(OR.Shop_Menu);

		clickOn(OR.Shop_SHopfor_allFieldsBtn);

		List<String> exp = new ArrayList<String>(Arrays.asList("SKU", "Mfr Number", "Description", "Quick code",
				"You can select maximum 2 keys for search."));

		if (orgSettingActive) {
			System.out.println("entered");
			exp.add(crosswalkIDText);
		}

		List<WebElement> options = driver
				.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-form']/li[@role='presentation']"));

		Boolean itemFound;
		if (options.size() == exp.size()) {
			for (String lm : exp) {
				itemFound = false;
				for (WebElement we : options) {
					if (we.getText().toLowerCase().trim().equals(lm.toLowerCase())) {
						itemFound = true;
						break;
					}
				}

				if (itemFound) {
					testLogPass("Text '" + lm + "' exists in dropdown.");
				} else {
					testLogPass("Text '" + lm + "' does not exist in dropdown.");
				}
			}
		} else {
			// fail because size is not equal
			testLogFail("Size of two arrayList is not equal");
		}

		
	}

	@Test(groups="Shop", priority=6)
	public void Tc_Shop_7() {
		testStarts("Tc_Shop_7",
				"Verify that show tour pops appear when user clicks on show tour option in dropdown next to refresh button.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		// waitForElementToDisplay(OR.Shop_SHopfor_waitdrilldownicon, 60);
		clickOn(OR.Shop_SHopfor_showTourText);

		// Since the xpath is relative to text of the element, we do not need to compare
		// the text again.
		// No need for explicit messages because method 'getText' implicitly does
		// messaging on the basis of whether text found or not.
		getText(OR.Shop_SHopfor_showtourtextONPOPUP);

		// Since getText would automatically initialize genericKeyword class' static
		// variable webElement, we can use same variable further
		// Since as per current structure of the page, xpath is not possible for finding
		// out buttons,
		// because there are so many elements with same structure, so we will find
		// parent of the search elem and then find buttons relative to the parent.
		WebElement searchParent = Generickeywords.webElement.findElement(By.xpath(".."));
		List<WebElement> btns = searchParent.findElements(By.tagName("button"));

		if (btns == null || btns.size() == 0) {
			testLogFail("No buttons found in tour popup.");
		} else {
			String nextBtnText = btns.get(0).getText();
			if (nextBtnText.contains("Next")) {
				testLogPass("Successfully Matched the Text 'Next' with button '" + nextBtnText + "'");
			} else {
				testLogFail("Could not match Text 'Next' with button '" + nextBtnText + "'");
			}

			String endBtnText = btns.get(1).getText();
			if (endBtnText.contains("End tour")) {
				testLogPass("Successfully Matched the Text 'End tour' with button '" + endBtnText + "'");
			} else {
				testLogFail("Could not match Text 'End tour' with button '" + endBtnText + "'");
			}

			btns.get(1).click();
			testLogPass("Click on :End tour");
		}

		
	}

	@Test(groups="Shop", priority=7)
	public void Tc_Shop_08() {
		testStarts("Tc_Shop_8",
				"Verify that user can switch between table view and grid view using \"view type\" option in the dropdown.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		mouseOver(OR.Shop_mouseoverviewtype);
		clickOn(OR.Shop_gridView);

		waitForElementToDisplay(OR.Shop_SHopfor_drilldownicon, 30);
		if (isElementPresent(By.xpath("//div[@id='isotopeContainer']"))) {
			testLogPass("Successfully changed to GRID View");
		} else {
			testLogFail("View is not changed to GRID view");
		}

		clickOn(OR.Shop_SHopfor_drilldownicon);
		mouseOver(OR.Shop_mouseoverviewtype);
		clickOn(OR.Shop_tableview);

		waitForElementToDisplay(OR.Shop_SHopfor_drilldownicon, 30);

		if (!isElementPresent(By.xpath("//div[@id='isotopeContainer']"))) {
			testLogPass("Successfully changed to TABLE View");
		} else {
			testLogFail("View is not changed to TABLE view");
		}

		
	}

	@Test(groups="Shop", priority=8)
	public void Tc_Shop_09() {
		testStarts("Tc_Shop_9",
				"Verify that \"Configure Shop Layout\" pop up appears when user clicks on change current layout.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Shop_Menu);
		clickOn(OR.Shop_SHopfor_drilldownicon);
		clickOn(OR.Shop_drilldownCreateLayout);
		typeIn(OR.Shop_drilldownLayoutNameText, "---automation layout---");
		clickOn(OR.Shop_drilldownCreateLayoutSave);
		clickOn(OR.Shop_drilldownCreateLayoutSave);

		clickOn(OR.Shop_SHopfor_drilldownicon);
		clickOn(OR.Shop_drilldownChangeLayout);

		waitForElementToDisplay(OR.Shop_drilldownChangeLayoutPopup, 30);

		clickOn(OR.Shop_drilldownChangeLayoutClose);

		clickOn(OR.Shop_SHopfor_drilldownicon);
		mouseOver(OR.Shop_drilldownLayouts);
		clickOn(OR.Shop_drilldownRemoveLayout);
		clickOn(OR.Shop_drilldownRemoveLayoutYes);

		waitForElementToDisplay(OR.Shop_SHopfor_drilldownicon, 30);

		
	}

	@Test(groups="Shop", priority=9)
	public void Tc_Shop_10() {
		testStarts("Tc_Shop_10",
				"Verify that \"Layout\" pop up appears when user clicks on on Create new layout or copy current layout option..");
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

	@Test(groups="Shop", priority=10)
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

	@Test(groups="Shop", priority=11)
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

	@Test(groups="Shop", priority=12)
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

	@Test(groups="Shop", priority=13)
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

	@Test(groups="Shop", priority=14)
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

	@Test(groups="Shop", priority=15)
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

	@Test(groups="Shop", priority=16)
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

	// ---------------------------------------------CART PAGE
	// STARTS--------------------------------------

	@Test(groups="Cart", priority=1)
	public void Tc_Cart_1_2() {
		testStarts("Tc_Cart_1 and Tc_Cart_2",
				"Verify that “Select Facility” popup appears when user clicks on facility name with “Cart for” label."
						+ " Verify that “selected” button appears as disabled for the facility which is displayed on Shop page.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.Shop_Menu);
		String facility_Name = getText(OR.Shop_SHopfor_getfacilityName);
		System.out.println(facility_Name);

		clickOn(OR.MyCart);
		clickOn(OR.Shop_SHopfor_ShopfaclitySelect);
		waitForElementToDisplay(OR.Shop_SHopfor_Shopfaclity, 60);
		verifyElementText(OR.Shop_SHopfor_Shopfaclity, "Select Facility");

		waitForElementToDisplay(OR.Shop_countoffacilities, 60);
		int one = driver.findElements(By.xpath("//*[@style='border-right: none;vertical-align: middle;']")).size();
		System.out.println(one);
		boolean facFound = false;
		String xpath;
		String selectedFacility;
		// Map<String, String> countofItems = new HashMap<String, String>();
		// String itemCountText;
		WebElement btn;
		for (int i = 1; i <= one; i++) {
			xpath = "(//table[@class='table table-responsive table-striped table-bordered']/tbody/tr[" + i + "]";
			selectedFacility = driver.findElement(By.xpath(xpath + "/td)")).getText();
			System.out.println(selectedFacility);

			if (selectedFacility.equals(facility_Name)) {
				facFound = true;
				btn = driver.findElement(By.xpath(xpath + "/td[2]/div/button)"));
				if (btn.getAttribute("disabled") != null) {
					testLogPass("Go the text '" + selectedFacility + "' Matches with selected Facility");
				} else {
					testLogFail("'" + facility_Name + "' is not selected in popup.");
				}
				break;
			}

		}

		if (!facFound) {
			testLogFail("Could not Got the text '" + facility_Name + "' Matches with selected Facility");

		}

		clickOn(OR.Shop_SHopfor_cancelButtonPopup);
		
	}

	@Test(groups="Cart", priority=2)
	public void Tc_Cart_15() {
		testStarts("Tc_Shop_15",
				"Verify that show tour pops appear when user clicks on show tour option in dropdown next to refresh button.");
		NavigateUrl(DashBoardURL);
		
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
	}

	@Test(groups="Cart", priority=3)
	public void addNewItem() {
		waitForElementToDisplay(OR.Shop_Menu, 60);
		// adding new item
		ItemDescription = "ItemDes" + randomAlphaNumeric(4);
		ItemMfrNumber = "ItemMfrNumber" + randomAlphaNumeric(4);
		ItemTestID = "ItemTestID" + randomAlphaNumeric(4);
		SkuName = "sku" + randomAlphaNumeric(4);

		// NavigateUrl(DashBoardURL);
		// 
		// waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Admin_ItemCatalog);
		waitForElementToDisplay(OR.ItemCatalog_AddItem, 60);

		clickOn(OR.ItemCatalog_AddItem);
		typeIn(OR.ItemCatalog_AddItem_ItemDetails_Description, ItemDescription);
		typeIn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetails, ItemMfrNumber);
		typeIn(OR.ItemCatalog_AddItme_TestId, ItemTestID);
		clickOn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetailsDropdown);
		clickOn(OR.ItemCatalog_AddItem_Man_Select);
		clickOn(OR.ItemCatalog_VendorsTab);
		clickOn(OR.ItemCatalog_AddVendors);

		clickOn(OR.ItemCatalog_Add_Vendorsname);

		// String VendorName=getText(OR.ItemCatalog_Add_Vendorsname);
		waitForElementToDisplay(OR.ItemCatalog_AddItem_VendorSelect_First, 60);
		clickOn(OR.ItemCatalog_AddItem_VendorSelect_First);
		typeIn(OR.ItemCatalog_Add_VendorsSkuName, SkuName);
		typeIn(OR.ItemCatalog_Add_VendorsMinOrderQty, "3");

		vendor_Name = getText(OR.ItemCatalog_firstvendor);
		System.out.println(vendor_Name);

		clickOn(OR.ItemCatalog_AddItem_Man_Save);
		waitForElementToDisplay(OR.ItemCatalog_AddItem_MapValidation, 60);
		verifyElementText(OR.ItemCatalog_AddItem_MapValidation,
				"Do you want to map this item to your various facilities?");
		clickOn(OR.ItemCatalog_AddItem_MapValidation_yes);
		getText(OR.ItemCatalog_AddItem_Map_Header);

		verifyElementText(OR.ItemCatalog_AddItem_Map_SearchFacility_Text, "Search Facility");

		clickOn(OR.ItemCatalog_mapallbuttontopright);
		clickOn(OR.ItemCatalog_mapwithalltopright);
		// selectFromDropdown(OR.ItemCatalog_maptoAllfacilities, "Map with all
		// facility");
		waitForElementToDisplay(OR.ItemCatalog_verifytextpopup, 60);

		verifyElementText(OR.ItemCatalog_verifytextpopup, "Map with all facilities");

		typeIn(OR.ItemCatalog_purchaseprice, "60");
		clickOn(OR.ItemCatalog_mapallbutton);

		
	}

	@Test(groups="Order", priority=1)
	public void Tc_Order_001() {
		testStarts("Tc_Order_001", "Verify that user can search PO on basis PO#, Sku, Notes or Documents.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		typeIn(OR.Order_Search_Input, "00");
		clickOn(OR.Order_PO_SearchBtn);
		typeIn(OR.Order_Search_Input, "sky464");
		clickOn(OR.Order_PO_SearchBtn);
		typeIn(OR.Order_Search_Input, "Notes");
		clickOn(OR.Order_PO_SearchBtn);
		
	}

	@Test(groups="Order", priority=2)
	public void Tc_Order_002() {
		testStarts("Tc_Order_002",
				"Verify that following dropdown filters appear on page. Vendor, Status, Type, Facility, Department and Created By .");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		OrderPage.VerifyOrder();
		
	}

	@Test(groups="Order", priority=3)
	public void Tc_Order_003() {
		testStarts("Tc_Order_003", "Verify that sorting works  for PO#, Requested Cost, Created At columns.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_PO_Title);
		ArrayList<String> BeforeSorting = new ArrayList<String>();
		ArrayList<String> afterSorting = new ArrayList<String>();
		int Size = driver.findElements(By.xpath("(//*[@ng-repeat='order in ordersData.purchaseOrders']/td/a)")).size();
		List<WebElement> myElements = driver
				.findElements(By.xpath("(//*[@ng-repeat='order in ordersData.purchaseOrders']/td/a)"));
		for (int i = 0; i < Size; i++) {

			BeforeSorting.add(myElements.get(i).getText().trim());
			afterSorting.add(myElements.get(i).getText().trim());

		}
		if (elementPresent(OR.Assending)) {

			Collections.sort(afterSorting);
			if (BeforeSorting.equals(afterSorting)) {
				testLogPass("Verify object is in ascending  order ");
			} else {

				testLogFail("Verify object is not in ascending order ");
			}

		} else if (elementPresent(OR.Descending)) {
			Comparator<String> comparator = Collections.reverseOrder();
			Collections.sort(afterSorting, comparator);
			if (BeforeSorting.equals(afterSorting)) {
				testLogPass("Verify if object  is in Decending order ");
			} else {
				testLogFail("verify object is not in Decending order ");
			}
		}

		
	}

	@Test(groups="Order", priority=4)
	public void Tc_Order_004() {
		testStarts("Tc_Order_004, Tc_Order_014",
				"Verify that 'Notes for PO #' window appears when user clicks on notes icon with count.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.OrderNotes_PO_First);
		OrderPage.viewNotesForPO();
		
	}

	@Test(groups="Order", priority=5)
	public void Tc_Order_005() {
		testStarts("Tc_Order_003, Tc_Order_005, Tc_Order_015, Tc_Order_019",
				"Verify that 'Notes for PO #' window appears when user clicks on notes icon with count."
						+ "Verify that 'Add Note for PO #' pop up appears when user clicks on “Add New Note'"
						+ "Verify that 'Notes for PO #' window appears when user clicks on notes icon with count.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		String one =getText(OR.OrderNotes_PO_NotesCount);
		clickOn(OR.OrderNotes_PO_First);
		OrderPage.viewNotesForPO();
		OrderPage.AddNotes();
		clickOn(OR.OrderNotes_PO_NotesClose);
		String Two =getText(OR.OrderNotes_PO_NotesCount);
		if(one.contains(Two))
		{
			testLogFail( "Before adding notes is "+ one+  " After adding Notes "+Two);
		}
		else
		{
			testLogPass( "Before adding notes is "+ one+  " After adding Notes "+Two);
		}
		

	}

	@Test(groups="Order", priority=6)
	public void Tc_Order_006() {
		testStarts("Tc_Order_006, Tc_Order_017, Tc_Order_020",
				"Verify that 'PO # XXXX11 Documents' pop up appears when clicks on Documents icon with count. ");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.OrderDoc_PO_First);
		OrderPage.viewDocumentForPO();
		
	}

	@Test(groups="Order", priority=7)
	public void Tc_Order_007() {
		testStarts("Tc_Order_007, Tc_Order_018",
				"Verify that select file from computer window opens when user clicks on Upload File button.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.OrderDoc_PO_First);
		typeIn(OR.Order_PO_Doc_UploadFiles, "D:\\AppMobi\\ServerConfiguration.txt");
		verifyElement(OR.Order_PO_Doc_AfterUploadFiles);
		
	}

	@Test(groups="Order", priority=8)
	public void Tc_Order_008() {
		testStarts("Tc_Order_008",
				"Verify that 'Print PO' preview window appears when user clicks on Print Po option in action dropdown.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		clickOn(OR.Order_PO_first_dropdown);
		clickOn(OR.Order_PO_first_printPo);
		getText(OR.Order_PO_printPo_PDF);
		
	}

	@Test(groups="Order", priority=9)
	public void Tc_Order_009() {
		testStarts("Tc_Order_009",
				"Verify that user gets redirected to 'INVOICE FOR PO #' page, on clicking 'Add Invoice' option.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		clickOn(OR.Order_PO_first_dropdown);
		waitTime(10);
		if (driver.findElements(By.xpath("(//a[text()='Invoices'])[1]")).size() != 0) {
			WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			clickOn(OR.Order_PO_first_Addinvoice);
			getText(OR.Order_PO_Invoice_Header);
		} else if (driver.findElements(By.xpath("(//a[text()='Invoices'])[1]")).size() == 0) {
			clickOn(OR.Order_status_dropdown);
			clickOn(OR.Order_PO_StatusDropdown_Confirmed);
			clickOn(OR.Order_PO_SearchBtn);
			waitTime(10);
			clickOn(OR.Order_PO_first_dropdown);
			waitTime(10);
			if (driver.findElements(By.xpath("(//a[text()='Invoices'])[1]")).size() != 0) {
				WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
				clickOn(OR.Order_PO_first_Addinvoice);
				getText(OR.Order_PO_Invoice_Header);
			}
		}

		
	}

	@Test(groups="Order", priority=10)
	public void Tc_Order_010() {
		testStarts("Tc_Order_010",
				"Verify that 'INVOICES FOR PO #' page appears when user clicks on All Invoices option.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		clickOn(OR.Order_PO_first_dropdown);
		waitTime(10);
		WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		clickOn(OR.Order_PO_first_AllInvoice);
		getText(OR.Order_PO_first_AllInvoice_Header);
		
	}

	@Test(groups="Order", priority=11)
	public void Tc_Order_013() {
		testStarts("Tc_Order_013",
				"Verify that 'PO AUDIT LOGS FOR PO #' pop up window appears when user clicks on Po Log option in the dropdown.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		clickOn(OR.Order_PO_first_dropdown);
		clickOn(OR.Order_PO_first_PoLog);
		getText(OR.Order_PO_PoLogHead);
		
	}

	@Test(groups="Order", priority=12)
	public void Tc_Order_016() {
		testStarts("Tc_Order_016",
				"Verify that notes gets deleted when user clicks on 'Delete' button for the added note.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.OrderNotes_PO_First);
		OrderPage.viewNotesForPO();
		OrderPage.AddNotes();
		waitForElement(OR.Order_PO_Notes_DeleteBtn);
		clickOn(OR.Order_PO_Notes_DeleteBtn);
		waitForElement(OR.Order_PO_Notes_Deletevalidation, 60);
		verifyElementText(OR.Order_PO_Notes_Deletevalidation, "Are you sure you want to delete this note?");
		clickOn(OR.Order_PO_Notes_Delete_yes);
		
	}

	@Test(groups="OrderDetails", priority=1)
	public void Tc_OrderDetails_001() {
		testStarts("Tc_OrderDetails_001",
				"Verify that “Notes for PO #” window appears when user clicks on notes icon with count.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_PO_ID_First);
		OrderDetailsPage.viewNotesForPO();
		
	}
	@Test(groups="OrderDetails", priority=2)
	public void Tc_OrderDetails_002() {
		testStarts("Tc_OrderDetails_002,Tc_OrderDetails_012, Tc_OrderDetails_014",
				"Verify that “Add Note for PO #” pop up appears when user clicks on “Add New Note”"
				+"Verify that notes gets deleted when user clicks on “Delete” button for the added note.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_PO_ID_First);
		OrderDetailsPage.viewNotesForPO();
		OrderDetailsPage.AddNotes();
		waitForElement(OR.Order_PO_Notes_DeleteBtn);
		clickOn(OR.Order_PO_Notes_DeleteBtn);
		waitForElement(OR.Order_PO_Notes_Deletevalidation, 60);
		verifyElementText(OR.Order_PO_Notes_Deletevalidation, "Are you sure you want to delete this note?");
		clickOn(OR.Order_PO_Notes_Delete_yes);
		
	}
	
	
	@Test(groups="OrderDetails", priority=3)
	public void Tc_OrderDetails_003() {
		testStarts("Tc_OrderDetails_003, Tc_OrderDetails_015",
				"Verify that “PO # XXXX11 Documents” pop up appears when clicks on Documents icon with count.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_Doc_Icon);
		OrderDetailsPage.viewDocumentForPO();
		
	}

	@Test(groups="OrderDetails", priority=4)
	public void Tc_OrderDetails_004() {
		testStarts("Tc_OrderDetails_004, Tc_OrderDetails_016",
				"Verify that select file from computer window opens when user clicks on Upload File button.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_Doc_Icon);
		typeIn(OR.Order_PO_Doc_UploadFiles, "D:\\AppMobi\\ServerConfiguration.txt");
		verifyElement(OR.Order_PO_Doc_AfterUploadFiles);
		
	}

	
	@Test(groups="OrderDetails", priority=5)
	public void Tc_OrderDetails_005() {
		testStarts("Tc_OrderDetails_005","Verify that items in PO gets added to cart when user clicks on “Add Items to cart” option");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.MyCart);
		if(driver.findElements(By.xpath("//*[@value='name']")).size()!=0)
		{
			clickOn(OR.OrderDetails_Btn_GeneratePo);
			clickOn(OR.OrderDetails_Btn_ClearAllCart);
			clickOn(OR.OrderDetails_DeleteConfirmion);
			verifyElementText(OR.OrderDetails_DeleteConfirmion, "Are you sure you want to clear cart?");
			clickOn(OR.Order_PO_Notes_Delete_yes);
			verifyElementText(OR.OrderDetails_DeleteSucessConfirmion, "Cart cleared successfully.");
			clickOn(OR.Confirmionok);
		}
		
		clickOn(OR.Order);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		ArrayList<String> ItemNameOrder = new ArrayList<String>();
		ArrayList<String> ItemNameCart = new ArrayList<String>();
		int OrderDetailsNameSize = driver.findElements(By.xpath("//*[@value='name']")).size();
		for(int i=1;i<=OrderDetailsNameSize;i++)
		{
			String Name1 = driver.findElement(By.xpath("(//*[@value='name'])["+i+"]")).getText().trim();
			ItemNameOrder.add(Name1);
		}
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_AddOrderItemsTOCart);
		clickOn(OR.MyCart);
		int MyCatNameSize = driver.findElements(By.xpath("//*[@value='name']")).size();
		for(int i=1;i<=MyCatNameSize;i++)
		{
			String Name1 = driver.findElement(By.xpath("(//*[@value='name'])["+i+"]")).getText().trim();
			ItemNameCart.add(Name1);
		}
		 Collections.sort(ItemNameOrder);
		 Collections.sort(ItemNameCart);
			if (ItemNameOrder.equals(ItemNameCart)) {
				testLogPass("Verify item name in order details and my cart page are same");
			} else {
				testLogFail("Verify item name in order details and my cart page are NOT same");
			}
		
		
	}

	@Test(groups="OrderDetails", priority=4)
	public void Tc_OrderDetails_006() {
		testStarts("Tc_OrderDetails_006",
				"Verify that 'Print PO' preview window appears when user clicks on Print Po option in action dropdown ");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.Order_PO_first_PoLog);
		getText(OR.Order_PO_PoLogHead);
		
	}
	
	@Test(groups="OrderDetails", priority=6)
	public void Tc_OrderDetails_009() {
		testStarts("Tc_OrderDetails_009","Verify that “INVOICES FOR PO #” page appears when user clicks on All Invoices option.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.MyCart);
		if(driver.findElements(By.xpath("//*[@value='name']")).size()!=0)
		{
			clickOn(OR.OrderDetails_Btn_GeneratePo);
			clickOn(OR.OrderDetails_Btn_ClearAllCart);
			clickOn(OR.OrderDetails_DeleteConfirmion);
			verifyElementText(OR.OrderDetails_DeleteConfirmion, "Are you sure you want to clear cart?");
			clickOn(OR.Order_PO_Notes_Delete_yes);
			verifyElementText(OR.OrderDetails_DeleteSucessConfirmion, "Cart cleared successfully.");
			clickOn(OR.Confirmionok);
		}
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		if (driver.findElements(By.xpath("(//a[text()='Invoices'])[1]")).size() != 0) {
			WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			clickOn(OR.OrderDetails_AddInvoice);
		    //driver.findElement(By.linkText("Add Invoice")).click();
			getText(OR.Order_PO_Invoice_Header);
		} else 
			if (driver.findElements(By.xpath("(//a[text()='Invoices'])[1]")).size() == 0) 
			{
				
			clickOn(OR.Order_status_dropdown);
			clickOn(OR.Order_PO_StatusDropdown_Confirmed);
			clickOn(OR.Order_PO_SearchBtn);
			waitTime(10);
			clickOn(OR.Order_PO_first_dropdown);
			waitTime(10);
			
			clickOn(OR.OrderDetails_HeaderDropdown);
			clickOn(OR.OrderDetails_AddOrderItemsTOCart);
			clickOn(OR.MyCart);
			
			if (driver.findElements(By.xpath("(//a[text()='Invoices'])[1]")).size() != 0) {
				WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
				
			  //  driver.findElement(By.linkText("Add Invoice")).click();
				getText(OR.Order_PO_Invoice_Header);
			}
		}

		
		
		clickOn(OR.Order_PO_first_dropdown);
		clickOn(OR.Order_PO_first_printPo);
		getText(OR.Order_PO_printPo_PDF);
		
	}

	@Test(groups="OrderDetails", priority=5)
	public void Tc_OrderDetails_007() {
		testStarts("Tc_OrderDetails_007","Verify that Print popup window opens on clicking Print Items button.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.MyCart);
		if(driver.findElements(By.xpath("//*[@value='name']")).size()!=0)
		{
			clickOn(OR.OrderDetails_Btn_GeneratePo);
			clickOn(OR.OrderDetails_Btn_ClearAllCart);
			clickOn(OR.OrderDetails_DeleteConfirmion);
			verifyElementText(OR.OrderDetails_DeleteConfirmion, "Are you sure you want to clear cart?");
			clickOn(OR.Order_PO_Notes_Delete_yes);
			verifyElementText(OR.OrderDetails_DeleteSucessConfirmion, "Cart cleared successfully.");
			clickOn(OR.Confirmionok);
		}
		
		clickOn(OR.Order);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_PrintItems);
		verifyElementText(OR.OrderDetails_OrderItems, "Order Items");
		
	}
	@Test(groups="OrderDetails", priority=6)
	public void Tc_OrderDetails_008() {
		testStarts("Tc_OrderDetails_008","Verify that “INVOICES FOR PO #” page appears when user clicks on All Invoices option.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.MyCart);
		if(driver.findElements(By.xpath("//*[@value='name']")).size()!=0)
		{
			clickOn(OR.OrderDetails_Btn_GeneratePo);
			clickOn(OR.OrderDetails_Btn_ClearAllCart);
			clickOn(OR.OrderDetails_DeleteConfirmion);
			verifyElementText(OR.OrderDetails_DeleteConfirmion, "Are you sure you want to clear cart?");
			clickOn(OR.Order_PO_Notes_Delete_yes);
			verifyElementText(OR.OrderDetails_DeleteSucessConfirmion, "Cart cleared successfully.");
			clickOn(OR.Confirmionok);
		}
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		if (driver.findElements(By.xpath("(//a[text()='Invoices'])[1]")).size() != 0) {
			WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			clickOn(OR.Order_PO_first_AllInvoice);
		    //driver.findElement(By.linkText("Add Invoice")).click();
			//getText(OR.Order_PO_Invoice_Header);
		} else 
			if (driver.findElements(By.xpath("(//a[text()='Invoices'])[1]")).size() == 0) 
			{
				
			clickOn(OR.Order_status_dropdown);
			clickOn(OR.Order_PO_StatusDropdown_Confirmed);
			clickOn(OR.Order_PO_SearchBtn);
			waitTime(10);
			clickOn(OR.Order_PO_first_dropdown);
			waitTime(10);
			
			clickOn(OR.OrderDetails_HeaderDropdown);
			clickOn(OR.Order_PO_first_AllInvoice);
			
			if (driver.findElements(By.xpath("(//a[text()='Invoices'])[1]")).size() != 0) {
				WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
				clickOn(OR.Order_PO_first_AllInvoice);	
			  //  driver.findElement(By.linkText("Add Invoice")).click();
				
			}
		}

		getText(OR.Order_PO_first_AllInvoice_Header);
		
	}
	
	@Test(groups="OrderDetails", priority=5)
	public void Tc_OrderDetails_008d() {
		testStarts("Tc_OrderDetails_008","Verify that Print popup window opens on clicking Print Items button.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.MyCart);
		if(driver.findElements(By.xpath("//*[@value='name']")).size()!=0)
		{
			clickOn(OR.OrderDetails_Btn_GeneratePo);
			clickOn(OR.OrderDetails_Btn_ClearAllCart);
			clickOn(OR.OrderDetails_DeleteConfirmion);
			verifyElementText(OR.OrderDetails_DeleteConfirmion, "Are you sure you want to clear cart?");
			clickOn(OR.Order_PO_Notes_Delete_yes);
			verifyElementText(OR.OrderDetails_DeleteSucessConfirmion, "Cart cleared successfully.");
			clickOn(OR.Confirmionok);
		}
		
		clickOn(OR.Order);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_PrintItems);
		verifyElementText(OR.OrderDetails_OrderItems, "Order Items");
		
	}

	@Test(groups="OrderDetails", priority=11)
	public void Tc_OrderDetails_010() {
		testStarts("Tc_OrderDetails_010",
				"Verify that “PO AUDIT LOGS FOR PO #” pop up window appears when user clicks on Po Log option in the dropdown.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.Order_PO_first_PoLog);
		//clickOn(OR.OrderDetails_POApprovalLog);
		getText(OR.Order_PO_PoLogHead);
		
	}
	
	@Test(groups="OrderDetails", priority=11)
	public void Tc_OrderDetails_011() {
		testStarts("Tc_OrderDetails_011",
				"Verify that “PO APPROVAL LOGS FOR PO # “ pop up appears when user clicks on PO Approval Log option");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_POApprovalLog);
		getText(OR.Order_PO_PoLogHead);
		
	}
	
	@Test(groups="OrderDetails", priority=11)
	public void Tc_OrderDetails_012() {
		testStarts("Tc_OrderDetails_012",
				"Verify that “PO APPROVAL LOGS FOR PO # “ pop up appears when user clicks on PO Approval Log option");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_POApprovalLog);
		getText(OR.Order_PO_PoLogHead);
		
	}
	
	@Test(groups="OrderDetails", priority=4)
	public void Tc_OrderDetails_018() {
		testStarts("Tc_OrderDetails_018","Verify that Delete PO option appears.\r\n");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		verifyElement(OR.OrderDetails_DeletePO);
		
	}
	
	@Test(groups="OrderDetails", priority=4)
	public void Tc_OrderDetails_019() {
		testStarts("Tc_OrderDetails_019",
				"Verify that show tour pops appear when user clicks on show tour option in dropdown next to refresh button.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		waitTime(10);
		waitForElement(OR.Order_status_dropdown);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_ShowTour);
		getText(OR.Order_PO_PoLogHead);
		
	}
	

	@Test(groups="OrderDetails", priority=4)
	public void Tc_OrderDetails_020() {
		testStarts("Tc_OrderDetails_020","Verify that “Departments” pop up appears when user clicks on Attach  department hyperlink.\r\n");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		waitTime(10);
		waitForElement(OR.Order_status_dropdown);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(10);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		//clickOn(OR.OrderDetails_Dept);
		//getAttributeValue(OR.OrderDetails_Dept, "title");
		waitTime(10);
	    WebElement element = driver.findElement(By.xpath("//vendor-info//*[@ng-mouseenter='fetchData();']"));
		   Actions action = new Actions(driver);
		   
		   action.moveToElement(element);

	        
	    JavascriptExecutor jse = (JavascriptExecutor)driver;

	    //jse.executeScript("return arguments[0].text", element)
	    
		System.out.println("Hari --------->"+jse.executeScript("return arguments[0].text", element));
	    //getText(OR.Order_PO_PoLogHead);
		
		
	}
		
		@Test(groups="OrderDetails", priority=4)
		public void Tc_OrderDetails_021() {
			testStarts("Tc_OrderDetails_021","Verify that “Physicians” pop up appears when user clicks on “Attach Physician” hyperlink.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Order);
			waitTime(10);
			waitForElement(OR.Order_status_dropdown);
			clickOn(OR.Order_status_dropdown);
			clickOn(OR.Order_PO_StatusDropdown_Assigned);
			clickOn(OR.Order_PO_SearchBtn);
			waitTime(10);
			waitForElement(OR.Order_PO_ID_First);
			clickOn(OR.Order_PO_ID_First);
			verifyElement(OR.OrderDetails_Physision);
			verifyElement(OR.OrderDetails_Dept1);
			
		}
		
		@Test(groups="OrderDetails", priority=4)
		public void Tc_OrderDetails_022() {
			testStarts("Tc_OrderDetails_022","Verify that Order detail screen gets closed when user clicks on Close option.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Order);
			waitTime(10);
			waitForElement(OR.Order_status_dropdown);
			clickOn(OR.Order_status_dropdown);
			clickOn(OR.Order_PO_StatusDropdown_Assigned);
			clickOn(OR.Order_PO_SearchBtn);
			waitTime(10);
			waitForElement(OR.Order_PO_ID_First);
			clickOn(OR.Order_PO_ID_First);
			clickOn(OR.OrderDetails_Close);
			verifyElement(OR.OrderDetails_MyOrderTxt);
			
		}
		
		// Template
//		
//		@Test(groups="Template", priority=1)
//		public void Tc_Template_00323() {
//			testStarts("Tc_Template_001, Tc_Template_004","Verify that user is able to add template by clicking Add Template button."
//					+"Verify that only user with edit access of template should be able to edit “Template”.\r\n" + 
//					"");
//			NavigateUrl(DashBoardURL);
//			
//			clickOn(OR.Templates);
//			waitTime(10);
//			Template.AddDeleteTemplate();
//			Template.EditTemplate();
//			Template.verify();
//			
//		}
		
		@Test(groups="Template", priority=1)
		public void Tc_Template_001() {
			testStarts("Tc_Template_001, Tc_Template_002,Tc_Template_005, Tc_Template_006, Tc_Template_008","Verify that user is able to add template by clicking Add Template button.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Templates);
			waitTime(10);
			Template.AddDeleteScheduleTemplate();
			
		}
		
		@Test(groups="Template", priority=2)
		public void Tc_Template_003() {
			testStarts("Tc_Template_003","Verify that template can be used by every user if “Yes” is selected in toggle button");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Templates);
			waitTime(10);
			Template.AddTemplate();
			isElementDisplayed(OR.Template_ShareYes);
			isElementDisplayed(OR.Template_ShareNo);
			
		}
		
		@Test(groups="Template", priority=3)
		public void Tc_Template_004() {
			testStarts("Tc_Template_004, Tc_Template_013","Verify that only user with edit access of template should be able to edit 'Template'\n"
					+ "Verify that use template gets closed when user  clicks on Close button.\r\n" + 
					"");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Templates);
			waitTime(10);
			Template.AddTemplate();
			clickOn(OR.Template_SaveBtn);
			clickOn(OR.Template_CloseBtn);
			typeIn(OR.Template_Search_Input,Template.CreateTemplateName);
			clickOn(OR.Template_SearchBtn);
			waitForElementToDisplay(OR.Template_Edit, 60);
			clickOn(OR.Template_Edit);
			verifyPageTitle("Manage Templates");
			
		}

		@Test(groups="Template", priority=4)
		public void Tc_Template_009() {
			testStarts("Tc_Template_009","Verify that Template gets deleted on clicking Delete button.\r\n");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Templates);
			waitTime(10);
			Template.AddTemplate();
			clickOn(OR.Template_SaveBtn);
			clickOn(OR.Template_CloseBtn);
			typeIn(OR.Template_Search_Input,Template.CreateTemplateName);
			clickOn(OR.Template_SearchBtn);
			waitTime(15);
			clickOn(OR.Template_Delete);
			waitForElement(OR.Template__Deletevalidation, 60);
			verifyElementText(OR.Template__Deletevalidation, "Are you sure?");
			verifyElementText(OR.Template__Deletetemplatevalidation, "Are you sure you want to delete this template ?");
			clickOn(OR.Template_Yes);
			
		}
		
		@Test(groups="Template", priority=4)
		public void Tc_Template_010() {
			testStarts("Tc_Template_010","Verify that users gets redirected to “Use template” screen on clicking Use icon.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Templates);
			waitTime(10);
			Template.AddTemplate();
			Template.AddItemTemplate();
			clickOn(OR.Template_SaveBtn);
			clickOn(OR.Template_CloseBtn);
			typeIn(OR.Template_Search_Input,Template.CreateTemplateName);
			clickOn(OR.Template_SearchBtn);
			waitTime(15);
			clickOn(OR.Template_Use);
			waitTime(10);
			clickOn(OR.Template_GeneratePO);
			verifyElementText(OR.Template_Warning, "Warning");
			verifyElementText(OR.Template_WarningMsg, "Increase qty. of at least one item to generate the PO.");
			clickOn(OR.Template_Warningok);
			clickOn(OR.Template_Plus);
			clickOn(OR.Template_GeneratePO);
			verifyPageTitle("My Orders");
			
		}
		
		@Test(groups="Report", priority=1)
		public void Tc_Report_001() {
			testStarts("Tc_Report_001","Verify that corresponding results appear based on search criterias added by the user.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Report);
			waitTime(10);
			int size = driver.findElements(By.xpath("//*[@ng-if='showReports']//li[@role='presentation']/a")).size();
			for(int i=1; i<=size ; i++)
			{
				clickOn(OR.Report_ViewMoreREports);
				waitTime(3);
				WebElement ReportName = driver.findElement(By.xpath("(//*[@ng-if='showReports']//li[@role='presentation']/a)["+i+"]"));
				String one = ReportName.getText().toLowerCase();
				testLogPass(  "Drop Down Text " + one);
				ReportName.click();
				waitTime(4);
				String ReportNameInside = driver.findElement(By.xpath("//*[@class='pagehead']")).getText().replace("View More Reports", "").replace("\n", "").replace("\r", "").toLowerCase();
				if(one.contains(ReportNameInside)) 
				{
					testLogPass("' "+  one +" ' Page Header '" + ReportNameInside +" '");
				}
				else
				{
					testLogFail("' "+  one +" ' Page Header is not matching '" + ReportNameInside+" '");
				}
			}
			
		}
		
		
		@Test(groups="PreferedCard", priority=4)
		public void PreferedCard_005() {
			testStarts("PreferedCard_005","Create Preference Card > Verify that user is able to reorder stages by clicking Reorder Stage button.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.preferenceCard);
			clickOn(OR.preferenceCard_AddPreferenceCard);
			clickOn(OR.preferenceCard_AddStage);
			typeIn(OR.preferenceCard_InputAddStage,"Test001");
			clickOn(OR.preferenceCard_BtnAddStage);
			clickOn(OR.preferenceCard_AddStage);
			typeIn(OR.preferenceCard_InputAddStage,"Test001");
			clickOn(OR.preferenceCard_BtnAddStage);
			clickOn(OR.preferenceCard_Reorder);
			dropDown();
			
		}
		
		@Test(groups="PreferedCard", priority=4)
		public void PreferedCard_011() {
			testStarts("PreferedCard_011","Create Preference Card > Stage tab > Verify that user is able to reorder items by clicking Reorder items button.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.preferenceCard);
			clickOn(OR.preferenceCard_AddPreferenceCard);
			clickOn(OR.preferenceCard_AddStage);
			typeIn(OR.preferenceCard_InputAddStage,"Test");
			clickOn(OR.preferenceCard_BtnAddStage);
				typeIn(OR.preferenceCard_SearchItem,"Test001");
			if(driver.findElements(By.xpath("//button[@id='btnAdd']")).size()!=0)	
			{
				clickOn(OR.preferenceCard_AddItem);	
			}else if(driver.findElements(By.xpath("//*[text()='No Items Found.']")).size()!=0)
			{
				testLogFail("item is not present");
			}
			
			if(driver.findElements(By.xpath("//*[contains(text(),'Test001')]")).size()!=0)
			{
				testLogPass("Test001 is added item");
			}
			else
			{
				testLogFail("Test001 is Not added item");
			}
		
			typeIn(OR.preferenceCard_SearchItem,"Test002");
			clickOn(OR.preferenceCard_AddItem);
			clickOn(OR.preferenceCard_Reorder);
			dropDown();
			
		}
		
		@Test(groups="Paitent", priority=4)
		public void Paitent_004() {
			testStarts("Paitent_004","Drill-down > Verify that all preference cards applied for corresponding patient appear on page.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Paintet);
			clickOn(OR.Paintet_First);
			String prefcard = driver.findElement(By.xpath("(//*[@ng-if='patient.expanded']//th)[1]")).getText();
			String prefcarddata, prefcardName;
			if(driver.findElements(By.xpath("//*[@ ng-repeat='prefCard in patient.PrefCards.request']")).size()!=0)
			{
				int size = driver.findElements(By.xpath("//*[@ ng-repeat='prefCard in patient.PrefCards.request']")).size();
				for(int i =1 ;i<=size;i++)
				{
					prefcardName = driver.findElement(By.xpath("(//*[@ng-repeat-start='patient in patients'][3])//td[1]")).getText();
					prefcarddata = driver.findElement(By.xpath("(//*[@ ng-repeat='prefCard in patient.PrefCards.request']//td[1])["+i+"]")).getText();
					testLogPass(prefcard+" for Prefered card data is " +prefcarddata);
					clickOn(OR.Cases);
					typeIn(OR.Shop_SHopfor_SearchBox,prefcardName);
					selectFromDropdown(OR.Cases_dateRange, 1);
					clickOn(OR.Cases_searchbutton);
					String one = driver.findElement(By.xpath("(//*[@ng-repeat-start='prefCard in cases']//nobr)[1]")).getText();
					if(prefcardName.contains(one))
					{
						testLogPass("Parent name is equal");
					}
					else
					{
						testLogPass("Parent name are not equal");
					}
				}
			}
			else
			{	
				testLogPass(prefcard +" for Predered card data is not present");
			}

			
		}
		
		@Test(groups="VendorAccountSetup", priority=4)
		public void VendorAccountSetup_001() {
			testStarts("VendorAccountSetup_001","Verify that list of all active vendor appears in select vendor dropdwon.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.DashBoard_AdminDropdown);
			clickOn(OR.Admin_Vendor);
			clickOn(OR.PaginationLast);
			int Paginationsize = driver.findElements(By.xpath("//*[@items-per-page='search.iPerPage']//li")).size();
			// click on pagination link 
			List<String> listName = new ArrayList<String>();
			List<String> VendorlistName = new ArrayList<String>();
			for(int i=1; i<Paginationsize; i++)
			{ 
				int Namesize = driver.findElements(By.xpath("//*[@class='grid-item-name grid-title-label-container']")).size();
				for(int j=1; j<Namesize; j++)
				{
					String Name = driver.findElement(By.xpath("(//*[@class='grid-item-name grid-title-label-container'])["+j+"]")).getText().toLowerCase();
					listName.add(Name);
				}
				driver.findElement(By.xpath("//*[@items-per-page=\\\"search.iPerPage\\\"]//li"));
			} 
			clickOn(OR.DashBoard_AdminDropdown);
			clickOn(OR.Admin_vendoraccountsetup);
			driver.findElement(By.xpath("//*[@id='vendor_dropdown']")).click();
			int vendorDropdownListsize = driver.findElements(By.xpath("//*[@id='vendor_dropdown']/option")).size();
			 
			for(int k=2; k<vendorDropdownListsize; k++)
			{
				String VendorName = driver.findElement(By.xpath("(//*[@id='vendor_dropdown']/option)["+k+"]")).getText().toLowerCase();
				VendorlistName.add(VendorName);
			}
			
		}
	@AfterTest
	public void endReport() {
		// 
		extent.flush();
	}
}
