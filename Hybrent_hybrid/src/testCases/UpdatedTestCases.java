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
import AutomationFramework.Generickeywords;

public class UpdatedTestCases extends ApplicationKeyword {
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

			extent = new ExtentReports(OutputDirectory + "/test-output/STMExtentReport.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public void Tc_001() {
		System.out.println("CameTo");
		testStarts("Tc_Login_001", "Verify that system does not allow user to Login with �Invalid Credentials�");
		openBrowser(URL);
		Loginpage.login("dsds", "dsdskjk");
		waitForElementToDisplay(OR.Login_Error, 20);
		verifyElementText(OR.Login_Validation, "Invalid user name or password.");
		waitForElementToDisplay(OR.Login_okay, 60);
		clickOn(OR.Login_okay);
		testStarts("Tc_Login_002", "Verify that system allows user to Login with 'Valid Credentials'");
		Loginpage.login(UserName, Password);
		waitForElementToDisplay(OR.Shop_Menu, 60);
		verifyPageTitle("Dashboard");
	}
	

	@Test(priority=2)
	public void Tc_002()
	{
		testStarts("Tc_Cart_1 and Tc_Cart_2()" , "Verify that �Select Facility� popup appears when user clicks on facility name with �Cart for� label."
				+ " Verify that �selected� button appears as disabled for the facility which is displayed on Shop page.");
		Loginpage.OpenBrowserAndLogin();		
		String fac=MycartPage.getFac();		
		System.out.println(fac);
		clickOn(OR.MyCart);
		MycartPage.matchFac(fac);
		
	}

	@Test(priority=3)
	public void Tc_003()
	{
		testStarts("Tc_Cart_03" ," Verify that user can search/scan with �Item Name, SKU, MFR� in the Add Item search field..");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();		
		addNewItem();
		clickOn(OR.MyCart);
		MycartPage.searchItem();
				
	}

	@Test(priority=4)
	public void Tc_004() throws InterruptedException
	{
		testStarts("Tc_Cart_05" ," Verify that user can change the quantity of item by clicking (+) and (-) button respectively or by entering the value and pressing tick or cancel button..");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();	
		clickOn(OR.MyCart);		
		//waitForElementToDisplay(OR.MyCart_drillDown, 10);	
		MycartPage.checkIfCartIsEmpty();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);
		//typeIn(OR.MyCart_searchInCart,"ItemDesc");
		waitForElementToDisplay(OR.MyCart_firstItemNamewait, 15);
		clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
    		typeIn(OR.MyCart_searchInCart,ItemDescription);
	    }
		waitForElementToDisplay(OR.MyCart_plusIcon, 10);
		String qtyBeforeIncrease=getText(OR.MyCart_qtyOfItemBeforeAfter);
		System.out.println(qtyBeforeIncrease);
		clickOn(OR.MyCart_plusIcon);
		String qtyAfterIncrease=getText(OR.MyCart_qtyOfItemBeforeAfter);	
		int beforeplus_Items=Integer.parseInt(qtyBeforeIncrease);  
		int after_plus=Integer.parseInt(qtyAfterIncrease);
		int final_Items=beforeplus_Items+1;
		if(after_plus==final_Items)
		{
			testLogPass("Item Qty is increased after clicking on + icon");

		}
		else
		{
			testLogFail("Item Qty is not increased after clicking on + icon");

		}
		clickOn(OR.MyCart_minusIcon);
		waitTime(4);
		String qtyAfterdecrese=getText(OR.MyCart_qtyOfItemBeforeAfter);
		int intqtyafterDecrese=Integer.parseInt(qtyAfterdecrese);  
		if(intqtyafterDecrese==1)
		{
			testLogPass("Item Qty is decreased after clicking on - icon");  		
		}
		else
		{
			testLogFail("Item Qty is not decreased after clicking on + icon");		
		}
		waitTime(3);
		clickOn(OR.MyCart_editItemQty);
		clearEditBox(OR.MyCart_editItemQtyTextBox);
		typeIn(OR.MyCart_editItemQtyTextBox, "10");
		clickOn(OR.MyCart_submitQty);		
		typeIn(OR.MyCart_searchInCart,ItemDescription);
		String qtyAftertypedQty=getText(OR.MyCart_qtyOfItemBeforeAfter);
		int afterQtyTypedIn=Integer.parseInt(qtyAftertypedQty); 
		Thread.sleep(5000);
		if(afterQtyTypedIn==10)
		{
			testLogPass("Item Qty is changed after entering QTY manually in text box"); 		
		}
		else
		{
			testLogFail("Item Qty is not changed after entering QTY manually in text box"); 		
		}
		
	}

	@Test(priority=5)
	public void Tc_005()
	{
		testStarts("Tc_Cart_06" ,"Verify that user is able to add �Special Instructions� for each vendor.");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		clickOn(OR.MyCart);
		MycartPage.checkIfCartIsEmpty();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);
		clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
	    }
		//waitForElementToDisplay(OR.MyCart_firstItemName, 15);
		if(isElementDisplayed(OR.MyCart_SiTextBox, 15))
		{
			String typeTextManually="SI for Test";
			typeIn(OR.MyCart_SiTextBox, typeTextManually);
			String returnedValue=getAttributeValue(OR.MyCart_SiTextBox, "value");
			if(returnedValue.equals(typeTextManually))
			{
				testLogPass("SI added in the text Box");
			}
			else
			{
				testLogFail("SI is not added in the text Box");				
			}			
		}
		else
		{
			testLogFail("SI Text Box is not present");			
		}
				
	}

	@Test(priority=6)
	public void Tc_006()
	{
		testStarts("Tc_Cart_07" ,"Verify that user can manually add PO number by selecting �Use PO#� checkbox.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.MyCart);				
		MycartPage.checkIfCartIsEmpty();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);
		//waitForElementToDisplay(OR.MyCart_firstItemName, 15);
		clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
	    }
		if(isElementPresent(By.xpath("//*[@ng-click='test_check(key)']")))
		{
			clickOn(OR.MyCart_UsemyPOCheckBox);
			String POMAnually="PO#1234";
			typeIn(OR.MyCart_typePONumber, POMAnually);			
			String returnedValue=getAttributeValue(OR.MyCart_typePONumber, "value");
			if(returnedValue.equals(POMAnually))
			{
				testLogPass("PO number added in the text Box");
			}
			else
			{
				testLogFail("PO number is not added in PO box");				
			}			
		}
		else
		{
			testLogFail("PO field is not present");			
		}
			
	}

	@Test(priority=7)
	public void Tc_007()
	{
		testStarts("Tc_Cart_08" ,"Verify that vendor gets removed from cart when user clicks on �Remove Vendor from cart� option in dropdown with vendor name.");
		NavigateUrl(DashBoardURL);
		//addNewItem();
		clickOn(OR.MyCart);
		MycartPage.checkIfCartIsEmpty();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);
		clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
	    }
		//clickOn(OR.MyCart_addItemInCart);
		clickOn(OR.MyCart_drillDownVendor);
		clickOn(OR.MyCart_removeVendor);
		clickOn(OR.MyCart_confirmButton);
		clickOn(OR.MyCart_confirmButton);			
		verifyElementText(OR.MyCart_emptycartText, "Your cart is Empty");	
		

	}
	@Test(priority=8)
	public void Tc_008()
	{
		testStarts("Tc_Cart_09" ," �Verify that users gets redirected to �Vendors Account Setup� on clicking � Account Setup. ");
		NavigateUrl(DashBoardURL);
		clickOn(OR.MyCart);
		MycartPage.checkIfCartIsEmpty();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);	
		clickOn(OR.MyCart_addItemInCart);
		//clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
	    }
		clickOn(OR.MyCart_drillDownVendor);
		clickOn(OR.MyCart_accountSetUp); 
		waitForElementToDisplay(OR.MyCart_vendorAccountSetUp, 10);
		verifyPageTitle("Vendors Account Setup");		
				
	}

	@Test(priority=9)
	public void Tc_009()
	{
		testStarts("Tc_Cart_10" ,"Verify that �Departments� pop up appears when user clicks on Attach  department hyperlink.");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();	
		clickOn(OR.MyCart);
		MycartPage.checkIfCartIsEmpty();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);	
		clickOn(OR.MyCart_addItemInCart);
		//clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
	    }
		clickOn(OR.MyCart_AddDepartmenthyperLink);
		waitForElementToDisplay(OR.MyCart_AddDepartmentText, 10);
		verifyElementText(OR.MyCart_AddDepartmentText, "Departments");
		clickOn(OR.MyCart_AddDepartmentPopUPcancel);
		waitTime(3);
			
	}
	@Test(priority=10)
	public void Tc_010()
	{
		testStarts("Tc_Cart_12" ,"Verify that �Physicians� pop up appears when user clicks on �Attach Physician� hyperlink.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.MyCart);
		MycartPage.checkIfCartIsEmpty();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);	
		clickOn(OR.MyCart_addItemInCart);
		//clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
	    }
		clickOn(OR.MyCart_AddPhysicianhyperLink);
		waitForElementToDisplay(OR.MyCart_AddPhysicianText, 10);
		verifyElementText(OR.MyCart_AddPhysicianText, "Physicians");
		clickOn(OR.MyCart_AddDepartmentPopUPcancel);
		waitTime(3);

	}

	@Test(priority=11)
	public void Tc_011()
	{
		testStarts("Tc_Cart_11" ,"Verify that Print window appears when user clicks print items.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.MyCart);
		MycartPage.checkIfCartIsEmpty();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);
		clickOn(OR.MyCart_addItemInCart);
		//clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
	    }
		clickOn(OR.MyCart_drillDown);
		clickOn(OR.MyCart_PrintPO);
		waitForElementToDisplay(OR.MyCart_PrintPOPopUpText, 10);
		verifyElementText(OR.MyCart_PrintPOPopUpText, "Order Items SKU");
		clickOn(OR.MyCart_PrintCloseclose);	
		waitTime(3);

	}

	@Test(priority=12)
	public void Tc_012()
	{
		testStarts("Tc_Cart_13" ,"Verify that when user clicks on �Clear Current cart�, items added in current facility gets cleared.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.MyCart);
		MycartPage.checkIfCartIsEmpty();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);
		clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
	    }
		clickOn(OR.MyCart_drillDown);
		clickOn(OR.MyCart_clearCurrentCart);
		clickOn(OR.MyCart_yesInPopup);
		clickOn(OR.MyCart_OKInPopup);
		waitForElementToDisplay(OR.MyCart_emptycartText, 10);
		verifyElementText(OR.MyCart_emptycartText, "Your cart is Empty");
		

	}

	@Test(priority=13)
	public void Tc_013()
	{
		testStarts("Tc_Cart_14" ,"Verify that when user clicks on �Clear all Cart�, items added in all facilities gets cleared..");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Shop_Menu);
		String facility_Name=getText(OR.Shop_SHopfor_getfacilityName);
		clickOn(OR.MyCart);
		MycartPage.checkIfCartIsEmpty();	
		waitForElementToDisplay(OR.MyCart_cartIconNumber, 10);
		String s1=getText(OR.MyCart_cartIconNumber);
		int qtyInCartbefore=Integer.parseInt(s1);
		//typeIn(OR.MyCart_searchInCart,"ItemDesc");
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart,ItemDescription);
		clickOn(OR.MyCart_addItemInCart);
		waitTime(2);
		if(isElementDisplayed(OR.MyCart_warningPopup, 10))
		{      
        	clickOn(OR.MyCart_continueButton);     
        	waitTime(3);
	    }
		waitForElementToDisplay(OR.MyCart_cartIconNumber, 10);
		String s2=getText(OR.MyCart_cartIconNumber);
		int qtyInCartAfter=Integer.parseInt(s2);
		if(qtyInCartAfter==(qtyInCartbefore+1))
		{
			testLogPass("One item is added to cart");
		}
		else
		{
			testLogPass("Item qty is not increased");
		}
		clickOn(OR.MyCart_drillDown);
		clickOn(OR.MyCart_clearAllCarts);
		clickOn(OR.MyCart_yesInPopup);
		clickOn(OR.MyCart_OKInPopup);
		waitForElementToDisplay(OR.MyCart_emptycartText, 10);
		verifyElementText(OR.MyCart_emptycartText, "Your cart is Empty");
		clickOn(OR.Shop_SHopfor_ShopfaclitySelect);
		waitForElementToDisplay(OR.Shop_SHopfor_Shopfaclity, 60);
		verifyElementText(OR.Shop_SHopfor_Shopfaclity, "Select Facility");
		waitForElementToDisplay(OR.Shop_countoffacilities, 60);
		int one = driver.findElements(By.xpath("//*[@style='border-right: none;vertical-align: middle;']")).size();
		String xpath;
		String selectedFacility;
		String itemCountText;
		for(int i=1; i<=one; i++)
		{
			xpath="(//table[@class='table table-responsive table-striped table-bordered']/tbody/tr["+i+"]";
			selectedFacility=driver.findElement(By.xpath(xpath+"/td)")).getText();
			itemCountText=driver.findElement(By.xpath(xpath+"/td[2]/div/span)")).getText();
			System.out.println(itemCountText);
			String temp=itemCountText.substring(1,2);
			int qtyInFac=Integer.parseInt(temp);	
			System.out.println(qtyInFac);

			if(qtyInFac != 0)
			{  
				testLogFail(selectedFacility + "has" + qtyInFac + "items");
				break;
			}					
		}

		if(facility_Name==null)
		{
			testLogFail("Could not Got the text '"+facility_Name+ "' Matches with selected Facility" );
		}

		clickOn(OR.Shop_SHopfor_cancelButtonPopup);
	}
	
	@Test(priority=14)
	public void Tc_014()
	{
		testStarts("Tc_Cart_15" ,"Verify that show tour pops appear when user clicks on show tour option in dropdown next to refresh button.");
		//NavigateUrl(DashBoardURL);
		Loginpage.OpenBrowserAndLogin();	
		clickOn(OR.MyCart);
		if(!MycartPage.boolcheckCartIsEmpty())
		{
			MycartPage.showTourPopUP();
			verifyElementText(OR.MyCart_showtourtextONPOPUP, "Cart: List of Items");
		}
		else
		{
			String ItemDescription=getProperty("ItemDesc");
			typeIn(OR.MyCart_searchInCart,ItemDescription);
			clickOn(OR.MyCart_addItemInCart);
			waitTime(2);
			if(isElementDisplayed(OR.MyCart_warningPopup, 10))
			{      
	        	clickOn(OR.MyCart_continueButton);     
	        	waitTime(3);
		    }
			MycartPage.showTourPopUP();		
		}
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
				btns.get(1).click();
				testLogPass("Click on :End tour");
			}
			else
			{
				testLogFail("Could not match Text 'End tour' with button '" + endBtnText + "'");
			}		
			
		}
		btns.get(1).click();
		waitTime(3);

	}

	public static void addNewItem()
	{
		waitForElementToDisplay(OR.Shop_Menu, 60);
		//adding new item
		ItemDescription = "ItemDes"+ApplicationKeyword.randomAlphaNumeric(4);	
		setProperty("ItemDesc",ItemDescription);
		ItemMfrNumber = "ItemMfrNmbr"+ApplicationKeyword.randomAlphaNumeric(4);
		setProperty("ItemMfr",ItemMfrNumber);
		ItemTestID = "ItemTestID"+ApplicationKeyword.randomAlphaNumeric(4);
		setProperty("ItemtestID",ItemTestID);	
		SkuName = "sku"+ApplicationKeyword.randomAlphaNumeric(4);
		setProperty("Sku",SkuName);	
		aliasName="alias"+ApplicationKeyword.randomAlphaNumeric(2);
		setProperty("alias",aliasName);	
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Admin_ItemCatalog);
		waitForElementToDisplay(OR.ItemCatalog_AddItem, 60);
		clickOn(OR.ItemCatalog_AddItem);
		typeIn(OR.ItemCatalog_AddItem_ItemDetails_Description, ItemDescription);
		MycartPage.chkMan_level("ItemMfr");
		if(isElementDisplayed(OR.ItemCatalog_AddItme_TestId, 10))
		{
			typeIn(OR.ItemCatalog_AddItme_TestId, ItemTestID);
		}
		firstCategory = getDropDownText(OR.ItemCatalog_itemCategory,1);
		setProperty("firstCat", firstCategory);
		typeIn(OR.ItemCatalog_itemalias, aliasName);
		clickOn(OR.ItemCatalog_VendorsTab);
		clickOn(OR.ItemCatalog_AddVendors);
		clickOn(OR.ItemCatalog_Add_Vendorsname);	
		clickOn(OR.ItemCatalog_AddItem_VendorSelect_First);
		typeIn(OR.ItemCatalog_Add_VendorsSkuName, SkuName);
		typeIn(OR.ItemCatalog_Add_VendorsMinOrderQty, "3");
		vendor_Name=getText(OR.ItemCatalog_firstvendor);
		setProperty("vendorName", vendor_Name);
		MycartPage.chkMan_level("ItemMfr");
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
		closeBrowser();
	}
	
	// SHOP Page
	
	@Test(priority=15)
	public void Tc_015() throws InterruptedException
	{
		testStarts("Tc_SPO_01", "Verify that user can search shipments by Shipment#, Tracking # and PO# ");
		Loginpage.OpenBrowserAndLogin();
		Shipmentpage.InitialSteps();
		waitForElementToDisplay(OR.Shipment_wait, 20);
		String ShipmentNum=getText(OR.Shipment_FirstShipmentNum);	
		System.out.println(ShipmentNum);
		String PoNum=getText(OR.Shipment_ShipmentPONum);
		System.out.println(PoNum);
		typeIn(OR.Shipment_SearchTextBox, ShipmentNum); 
		Shipmentpage.searchAndWait();			
		verifyElementText(OR.Shipment_FirstShipmentNum, ShipmentNum);
		Shipmentpage.clearAndWait();
		typeIn(OR.Shipment_SearchTextBox, PoNum); 
		Shipmentpage.searchAndWait();
		verifyElementText(OR.Shipment_ShipmentPONum, PoNum );
		Shipmentpage.clearAndWait();
//		typeIn(OR.Shipment_SearchTextBox, TrackingNum); 
//		Shipmentpage.searchAndWait();
//		verifyElementText(OR.Shipment_TrackingNum, TrackingNum);
		
	}

	@Test(priority=16)
	public void Tc_016() throws InterruptedException
	{
		testStarts("Tc_SH_02", "Verify that status dropdown appears with(Pending Shipped Partial Received Received) options");
		NavigateUrl(DashBoardURL);
		Shipmentpage.InitialSteps();			
		List<String> list = new ArrayList<String>( Arrays.asList("All", "Pending", "Shipped", "Partial Receive", "Received"));		
		List<WebElement> options = driver.findElements
				(By.xpath("//*[@id='sel1']/option"));	
		boolean textFound;
		if(options.size() == list.size())
		{
			for(String lm:list)
			{
				textFound=false;
				for(WebElement we:options)  
				{
					if(we.getText().trim().equals(lm))
					{
						textFound=true;
					}
				}
				if(textFound)
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

	@Test(priority=17)
	public void Tc_017() throws InterruptedException
	{
		testStarts("Tc_SH_03", "Verify that user gets redirected to shipment details screen on clicking �Shipment #�.");
		NavigateUrl(DashBoardURL);
		Shipmentpage.InitialSteps();
		waitForElementToDisplay(OR.Shipment_wait, 20);
		String ShipmentNum=getText(OR.Shipment_FirstShipmentNum).toUpperCase();
		System.out.println(ShipmentNum);
		clickOn(OR.Shipment_FirstShipmentNum);
		waitForElementToDisplay(OR.Shipment_wait, 20);
		String DetailPageText=getText(OR.Shipment_ShipmentDetailPAge);
		System.out.println(DetailPageText);
		if(DetailPageText.trim().contains("SHIPMENT# "+ ShipmentNum))
		{
			testLogPass("Successfully matched the Text and User is redirected to Shipment detail page");
		}
		else
		{
			testLogFail("Could not match the Text and User is not redirected to Shipment detail page");
		}
		
	}

	@Test(priority=18)
	public void Tc_018() throws InterruptedException
	{
		testStarts("Tc_SH_04", "Verify that �Receive� button appears for pending and Partial received shipments.�.");
		NavigateUrl(DashBoardURL);
		Shipmentpage.InitialSteps();
		selectFromDropdown(OR.Shipment_StatusDropDown, "Pending");
		Shipmentpage.searchAndWait();
		waitForElementToDisplay(OR.Shipment_wait, 20);
		clickOn(OR.Shipment_FirstShipmentNum);
		Shipmentpage.receiveTextandCloseButton();
		waitForElementToDisplay(OR.Shipment_wait, 60);
		selectFromDropdown(OR.Shipment_StatusDropDown, "Partial Receive");
		Shipmentpage.searchAndWait();
		waitForElementToDisplay(OR.Shipment_wait, 20);
		clickOn(OR.Shipment_FirstShipmentNum);
		Shipmentpage.receiveTextandCloseButton();
		closeBrowser();
	}
	@Test(priority=19)
	public void Tc_019()
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

	@Test(priority=20)
	public void Tc_020()
	{
		testStarts("Tc_SPO_02", "Verify that �Add Service� pop up appears when user clicks on Add New Service option. ");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Request_MenuLink);
		waitForElementToDisplay(OR.Request_drillDown, 20);
		clickOn(OR.Request_drillDown);
		clickOn(OR.Request_createNewService);
		verifyElementText(OR.Request_createNewServicePopUpText, "Add Service");
		clickOn(OR.Request_cancelPopUP);
		

	}
	@Test(priority=21)
	public void Tc_022()
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
	
	@Test(priority=23)
	public void Tc_023()
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

	@Test(priority=24)
	public void Tc_024() throws InterruptedException
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
		closeBrowser();
	}

	@Test(priority=25)
	public void Tc_025()
	{
		testStarts("Tc_RECDET_01_02", "Verify that �Notes for PO #� window appears when user clicks on notes icon with count."+"Verify that �Add Note for PO #� pop up appears when user clicks on �Add New Note�");
		Loginpage.OpenBrowserAndLogin();		
		ReceivePageObject.pageLinkandwait();
		//ReceivePageObject.selectByDefaultFacility();
		waitForElementToDisplay(OR.Receive_firstPONum, 10);
		clickOn(OR.Receive_firstPONum);
		waitForElementToDisplay(OR.Receive_NotesLinkPODetail, 10);
		clickOn(OR.Receive_NotesLinkPODetail);
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Notes for PO # ");
		clickOn(OR.Receive_AddNewNotesLink);
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Add Note for PO # ");
		clickOn(OR.Receive_PrintCloseclose);
		waitTime(2);
		clickOn(OR.Receive_PrintCloseclose);	
		waitTime(3);
		
		
	}
	
//	@Test(priority=2)
//	public void Tc_RECDET_02()
//	{
//		testStarts("Tc_RECDET_02", "Verify that �Add Note for PO #� pop up appears when user clicks on �Add New Note�");
//		NavigateUrl(DashBoardURL);	
//		ReceivePageObject.pageLinkandwait();
//		waitForElementToDisplay(OR.Receive_firstPONum, 10);
//		clickOn(OR.Receive_firstPONum);
//		waitForElementToDisplay(OR.Receive_NotesLinkPODetail, 10);
//		clickOn(OR.Receive_NotesLinkPODetail);
//		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
//		verifyElementText(OR.Receive_NotesLinkText, "Notes for PO # ");
//		clickOn(OR.Receive_AddNewNotesLink);
//		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
//		verifyElementText(OR.Receive_NotesLinkText, "Add Note for PO # ");
//		clickOn(OR.Receive_PrintCloseclose);
//		waitTime(3);
//		
//	}
	
	@Test(priority=26)
	public void Tc_026() throws InterruptedException
	{
		testStarts("Tc_RECDET_03_04()", "Verify that �PO # XXXX11 Documents� pop up appears when clicks on Documents icon with count +"
				+ "Verify that select file from computer window opens when user clicks on Upload File button.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();
		String s=getText(OR.Receive_firstPONum);
		clickOn(OR.Receive_firstPONum);
		clickOn(OR.Receive_DocsLinkPODetail);
		verifyElementText(OR.Receive_DocsLinkText, "PO # " +s+ " Documents");		
		WebElement elem=driver.findElement(By.xpath("//*[@type='file']"));
		String projectPath = System.getProperty("user.dir");
		elem.sendKeys(projectPath + "/assets/images.jpeg");
		waitTime(3);
		waitForElementToDisplay(OR.Invoice_UploadDocName, 10);
		String nameOfFile_Expected="images.jpeg";
		String s2=getText(OR.Invoice_UploadDocName).trim();
		if(s2.equalsIgnoreCase(nameOfFile_Expected))
		{
			testLogPass("Name of the image uploaded  matched");
			WebElement wholeElem=driver.findElement(By.xpath("//*[@class='col-sm-18']"));
			WebElement trashIcon=wholeElem.findElement(By.xpath("(//a[@class='a-with-icon pull-right ng-scope']/i[@class='fa fa-trash-o'])[1]"));
			trashIcon.click();
			clickOn(OR.Receive_confirmButton);
			waitTime(2);
		}
		else
		{			
			testLogFail("Name of the image uploaded is not matched");			
		}	
		clickOn(OR.Receive_PrintCloseclose);		
		waitTime(3);
	}	
	
	//MAY FAIL DUE TO WAIT FUNCTION
	@Test(priority=27)
	public void Tc_027() 
	{
		testStarts("Tc_RECDET_05()", "Verify that items in PO gets added to cart when user clicks on �Add Items to cart� option");
		NavigateUrl(DashBoardURL);	
		clickOn(OR.MyCart);
		MycartPage.checkIfCartIsEmpty();
		ReceivePageObject.pageLinkandwait();
		waitTime(3);
		clickOn(OR.Receive_firstPONum);
		waitForElementToDisplay(OR.Receive_DrillDownIcon, 10);
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_AddOrderItemsTOCartDetaipPage,10);
		clickOn(OR.Receive_AddOrderItemsTOCartDetaipPage);
		//waitForElementToDisplay(OR.Receive_firstPOHeadingText, 10);
		//boolean result=false;
		List<String> allItemNames=new ArrayList<String>();		
		List<WebElement> itemNames=driver.findElements(By.xpath("//table[@class='table table-body-striped ng-scope']//item-info[@class='ng-isolate-scope']"));		
		System.out.println(itemNames.size());
		for(int i=1; i<=itemNames.size(); i++)
		{			
			String ProductName=getAttributeValue(OR.Receive_itemNames, "name");						
			allItemNames.add(ProductName);	
		}
		clickOn(OR.MyCart);	
		//waitForElementToDisplay(OR.Receive_itemsNamesInCart, 20);
		List<WebElement> itemNamesInCart=driver.findElements(By.xpath("//*[@value='name']"));
		System.out.println(itemNamesInCart.size());
		boolean prodNotFoundInCart=true; 		
		for(int i=1;i<=allItemNames.size();i++)
		{
			prodNotFoundInCart = false;
			for(int j=1;j<=itemNamesInCart.size();j++)
			{
				String ProductNameInCart=getText(OR.Receive_itemsNamesInCart);		
				if(allItemNames.get(i-1).trim().equalsIgnoreCase(ProductNameInCart))
				{
					prodNotFoundInCart=false;
					break;
				}
				else
					prodNotFoundInCart=true;
			}			
			if(prodNotFoundInCart)
			{
				testLogFail("Both the lists do not have same items");
				break;
			}
			else
			{
				testLogPass("Both the lists have same items");				
			}
		}		
		
	}

	@Test(priority=28)
	public void Tc_028()
	{
		testStarts("Tc_RECDET_06()", "Verify that �Print PO� preview window appears when user clicks on Print Po option in action dropdown");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();
		waitForElementToDisplay(OR.Receive_firstPONum,10);
		clickOn(OR.Receive_firstPONum);
		//waitForElementToDisplay(OR.Receive_DrillDownPODetailPage, 10);
		clickOn(OR.Receive_DrillDownPODetailPage);
		clickOn(OR.Receive_PrintPOLinkPoDetail);
		waitForElementToDisplay(OR.Receive_PrintPOText, 10);		
		verifyElementText(OR.Receive_PrintPOText, " Print PO");
		clickOn(OR.Receive_PrintPReviewclose);
		waitTime(3);
		
	}
	
	@Test(priority=29)
	public void Tc_029() 
	{
		testStarts("Tc_RECDET_07()", "Verify that �Print Window� appears on clicking Print button..");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();
		waitForElementToDisplay(OR.Receive_firstPONum,10);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		clickOn(OR.Receive_printItemsDetailPage);
		verifyElementText(OR.Receive_printPOText, "Order Items");
		clickOn(OR.Receive_PrintCloseclose);
		waitTime(3);
	
	}
	
	@Test(priority=30)
	public void Tc_030() 
	{
		testStarts("Tc_RECDET_08()", "Verify that user gets redirected to �INVOICE FOR PO #� page, on clicking �Add Invoice� option.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_firstPONum,10);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
//		waitForElementToDisplay(OR.Receive_PODetailAddInvoice, 60);
		clickOn(OR.Receive_PODetailAddInvoice);
		verifyElementText(OR.Receive_PODetailInvoiceText, "INVOICE FOR PO #:");
		
	}
	//Had to use waitTime instead of dynamic
	@Test(priority=31)
	public void Tc_031() 
	{
		testStarts("Tc__RECDET_9()", "Verify that �INVOICES FOR PO #� page appears when user clicks on All Invoices option.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		//waitForElementToDisplay(OR.Receive_firstPONum,10);
		waitTime(3);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		clickOn(OR.Receive_AllInvoiceDetailPage);
		String s=getText(OR.Order_PO_first_AllInvoice_Header);
		System.out.println(s);
		if(s.contains("INVOICES FOR PO #"))
		{			
			testLogPass("All Invoices Page is opened");
		}
		else
		{
			testLogPass("All Invoices Page is not opened");			
		}
		
	
	}
	//Had to use waitTime instead of dynamic
	@Test(priority=32)
	public void Tc_032() {
		testStarts("Tc_RECDET_10",
				"Verify that �PO AUDIT LOGS FOR PO #� pop up window appears when user clicks on Po Log option in the dropdown.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		//waitForElementToDisplay(OR.Receive_firstPONum,10);
		waitTime(3);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_POLogDetailPage, 10);
		clickOn(OR.Receive_POLogDetailPage);	
		waitForElementToDisplay(OR.Receive_POLOgText,10);
		verifyElementText(OR.Receive_POLOgText, "PO AUDIT LOGS FOR PO #");		
		clickOn(OR.Receive_PrintCloseclose);
		waitTime(3);
	}
	
	@Test(priority=33)
	public void Tc_033() {
		testStarts("Tc_RECDET_11",
				"Verify that �PO APPROVAL LOGS FOR PO # � pop up appears when user clicks on PO Approval Log option");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_POApprovalLogs, 10);
		clickOn(OR.Receive_POApprovalLogs);	
		waitForElementToDisplay(OR.Receive_POApprovalLogsPopUpText,10);
		verifyElementText(OR.Receive_POApprovalLogsPopUpText, "PO APPROVAL LOGS FOR PO #");		
		clickOn(OR.Receive_PrintCloseclose);
		waitTime(3);
	}
	
	@Test(priority=34)
	public void Tc_034() {
		testStarts("Tc_RECDET_12",
				"Verify that �Notes for PO #� window appears when user clicks on notes icon with count.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_PODetailNotesLinkDrillDown, 10);
		clickOn(OR.Receive_PODetailNotesLinkDrillDown);	
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Notes for PO # ");	
		clickOn(OR.Receive_PrintCloseclose);
		waitTime(3);
	}
	//MAY FAIL DUE TO IF 'Receive_PrintCloseclose' IS NOT FOUND WITHIN TIME
	@Test(priority=35)
	public void Tc_035() {
		testStarts("Tc_RECDET_13",
				"Verify that �Add Note for PO #� pop up appears when user clicks on �Add New Note�.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_PODetailNotesLinkDrillDown, 10);
		clickOn(OR.Receive_PODetailNotesLinkDrillDown);	
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Notes for PO # ");
		clickOn(OR.Receive_AddNewNotesLink);
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Add Note for PO # ");		
		clickOn(OR.Receive_PrintCloseclose);
		//waitForElementToDisplay(OR.Receive_PrintCloseclose, 10);
		clickOn(OR.Receive_PrintCloseclose);
		waitTime(3);
	}
	
	
	@Test(priority=36)
	public void Tc_036() {
		testStarts("Tc_RECDET_14",
				"Verify that notes gets deleted when user clicks on �Delete� button for the added note.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_PODetailNotesLinkDrillDown, 10);
		clickOn(OR.Receive_PODetailNotesLinkDrillDown);	
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Notes for PO # ");
		clickOn(OR.Receive_AddNewNotesLink);
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Add Note for PO # ");
		String testNote="TestNote";
		typeIn(OR.Receive_AddNewNote, testNote);		
		clickOn(OR.Receive_AddNewNoteButton);
		typeIn(OR.Receive_searchBox, testNote);
		//String addedNOte=getText(OR.Receive_searchedfirstNote);
		if(getText(OR.Receive_searchedfirstNote).equalsIgnoreCase(testNote))
		{
			testLogPass("Note is added");
			waitForElementToDisplay(OR.Receive_NotesDeleteIcon, 10);
			clickOn(OR.Receive_NotesDeleteIcon);
			clickOn(OR.Receive_confirmButton);
		}
		else
		{
			testLogFail("Note is not added");
		}
		typeIn(OR.Receive_searchBox, testNote);
		verifyElementText(OR.Receive_NoNOteavailable, "No note available.");
		clickOn(OR.Receive_PrintCloseclose);
		waitTime(3);
				
	}
		
	
	@Test(priority=37)
	public void Tc_037() throws InterruptedException
	{
		testStarts("Tc_RECDET_15_16()", "Verify that �PO # XXXX11 Documents� pop up appears when clicks on Documents icon with count +"
				+ "Verify that select file from computer window opens when user clicks on Upload File button.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		String s=getText(OR.Receive_firstPONum);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_PODocsDrillDown, 10);
		clickOn(OR.Receive_PODocsDrillDown);			
		verifyElementText(OR.Receive_DocsLinkText, "PO # " +s+ " Documents");		
		WebElement elem=driver.findElement(By.xpath("//*[@type='file']"));
		String projectPath = System.getProperty("user.dir");
		elem.sendKeys(projectPath + "/assets/images.jpeg");
		waitTime(5);
		String nameOfFile_Expected="images.jpeg";
		String s2=getText(OR.Invoice_UploadDocName).trim();
		if(s2.contains(nameOfFile_Expected))
		{
			testLogPass("Name of the image uploaded  matched");
			WebElement wholeElem=driver.findElement(By.xpath("//*[@class='col-sm-18']"));
			WebElement trashIcon=wholeElem.findElement(By.xpath("(//a[@class='a-with-icon pull-right ng-scope']/i[@class='fa fa-trash-o'])[1]"));
			trashIcon.click();
			clickOn(OR.Receive_confirmButton);
			waitTime(3);
		}
		else
		{			
			testLogFail("Name of the image uploaded is not matched");			
		}
		clickOn(OR.Receive_PrintCloseclose);
		waitTime(3);
	}
	
	
	@Test(priority=38)
	public void Tc_038() {
		testStarts("Tc_RECDET_17",
				"Verify that Mark as Receive only option appears.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_PODetailNotesLinkDrillDown, 10);
		verifyElementText(OR.Receive_ReceiveOnlyText, "Mark as Receive Only");
		
	}
	
	@Test(priority=39)
	public void Tc_039() {
		testStarts("Tc_RECDET_18",
				"Verify that show tour pops appear when user clicks on show tour option in dropdown next to refresh button.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_PODetailNotesLinkDrillDown, 10);
		clickOn(OR.Receive_ShowTour);
		verifyElementText(OR.Receive_ShowTourText, "Order: PO Details");	
			
	}
	
	@Test(priority=40)
	public void Tc_040() {
		testStarts("Tc_RECDET_20",
				"Verify that �Departments� pop up appears when user clicks on Attach department hyperlink.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		verifyElementText(OR.Receive_Depatment, "Department:");
		
	}
	
	@Test(priority=41)
	public void Tc_041() {
		testStarts("Tc_RECDET_21",
				"Verify that �Physicians� pop up appears when user clicks on �Attach Physician� hyperlink.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		verifyElementText(OR.Receive_Physician, "Physician:");
		
	}
	
	@Test(priority=42)
	public void Tc_042() throws InterruptedException 
	{
		testStarts("Tc_RECDET_22_23",
				"Verify that Partial Receive and Complete buttons appear for corresponding PO number whose status is Confirmed/Assigned/Partial Received."
				+ "Verify that Order detail screen gets closed when user clicks on Close option.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);
		verifyElementText(OR.Receive_patialButtonText, "Partial Receive");
		verifyElementText(OR.Receive_completeButtonText, "Complete");
		clickOn(OR.Receive_CloseButtonText);
		waitForElementToDisplay(OR.Receive_SearchTextBox, 10);
		verifyElementText(OR.Receive_OrdersListingPageText, "ORDERS TO RECEIVE");
		closeBrowser();
	}

	@Test(priority=43)
	public void Tc_043()
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
	
	@Test(priority=44)
	public void Tc_044()
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
	
	@Test(priority=45)
	public void Tc_045()
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
	
	
	@Test(priority=46)
	public void Tc_046()
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
		closeBrowser();
		}

	@Test(priority=47)
	public void Tc_047()
		{
		testStarts("Tc_prefCard_01" , "Verify that 'Add Preference Card' button appears on page.");
		Loginpage.OpenBrowserAndLogin();	
		PrefcardPageObject.prefCardPagePageLinkandwait();
		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
		verifyElement(OR.prefCard_AddPrefCard);
		}
	
	@Test(priority=48)
	public void Tc_048()
		{
		testStarts("Tc_prefCard_02" , "Create Preference Card > Verify that Card Name and Physician are mandatory fields.");
		NavigateUrl(DashBoardURL);	
		PrefcardPageObject.prefCardPagePageLinkandwait();
		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
		clickOn(OR.prefCard_AddPrefCard);
		if(getAttributeValue(OR.prefCard_CardName, "required")!=null)
		{
		testLogPass("Card Name is mandatory field");
		}
		if(getAttributeValue(OR.prefCard_PhysicianName, "required")!=null)
		{
		testLogPass("Physician Name is mandatory field");
		}
		
	}

	@Test(priority=49)
	public void Tc_049()
		{
		testStarts("Tc_prefCard_03" , "Create Preference Card > Verify that 'Add Stage' pop-up appears when user click Add Stage button..");
		NavigateUrl(DashBoardURL);	
		PrefcardPageObject.prefCardPagePageLinkandwait();
		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
		clickOn(OR.prefCard_AddPrefCard);
		clickOn(OR.prefCard_addStage);
		verifyElementText(OR.prefCard_addStageText, "Add Stage");
		if(getAttributeValue(OR.prefCard_addStageName, "required")!=null)
		{
		testLogPass("Add Stage Name is mandatory field");
		}
		else
		{
			testLogFail("Add Stage Name is not a mandatory field");
		}
		clickOn(OR.prefCard_CanelBUtton);
		waitTime(2);	
		}
	
//	@Test(priority=4)
//	public void Tc_prefCard_04()
//		{
//		testStarts("Tc_prefCard_04" , "Create Preference Card > Add Stage > Verify that Name field is mandatory.");
//		NavigateUrl(DashBoardURL);	
//		PrefcardPageObject.prefCardPagePageLinkandwait();
//		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
//		clickOn(OR.prefCard_AddPrefCard);
//		clickOn(OR.prefCard_addStage);
//		if(getAttributeValue(OR.prefCard_addStageName, "required")!=null)
//		{
//		testLogPass("Add Stage Name is mandatory field");
//		}
//		else
//		{
//			testLogFail("Add Stage Name is not a mandatory field");
//		}
//		
//		
//		}
	
//	@Test
//	public void Tc_prefCard_05()
//		{
//		testStarts("Tc_prefCard_05" , "Create Preference Card > Verify that user is able to reorder stages by clicking Reorder Stage button.");
//		NavigateUrl(DashBoardURL);	
//		PrefcardPageObject.prefCardPagePageLinkandwait();
//		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
//		clickOn(OR.prefCard_AddPrefCard);
//		clickOn(OR.prefCard_addStage);
//		typeIn(OR.prefCard_addStageName, "TestStage001");
//		waitForElementToDisplay(OR.prefCard_addStageButton, 20);
//		clickOn(OR.prefCard_addStageButton);
//		clickOn(OR.prefCard_addStage);
//		typeIn(OR.prefCard_addStageName, "TestStage002");
//		waitForElementToDisplay(OR.prefCard_addStageButton, 20);
//		clickOn(OR.prefCard_addStageButton);
//		clickOn(OR.prefCard_reorderStageButton);
//		String firstStageBeforeDrag=getText(OR.prefCard_firstStage);
//		System.out.println(firstStageBeforeDrag);
//		String secondStageBeforeDrag=getText(OR.prefCard_secondStage);
//		System.out.println(secondStageBeforeDrag);
//		WebElement From = driver.findElement(By.xpath("//ul[@dnd-list='Ctrl.prefCardStages']/li[1]//p"));		  
//		WebElement To = driver.findElement(By.xpath("//ul[@dnd-list='Ctrl.prefCardStages']/li[2]//p"));
//		dragAndDrop2(From,To);
//		
//		//dragAndDrop(OR.prefCard_firstStage, OR.prefCard_secondStage);
//		String firstStageAfterDrag=getText(OR.prefCard_firstStage);
//		System.out.println(firstStageAfterDrag);
//		String secondStageAfterDrag=getText(OR.prefCard_secondStage);
//		System.out.println(secondStageAfterDrag);
////		if(firstStageBeforeDrag.equals(secondStageAfterDrag)&&secondStageBeforeDrag.equals(firstStageAfterDrag))
////		{
////			testLogPass("Stages are Re-ordered");
////		}
////		else
////		{
////			testLogFail("Stages are not Re-ordered");
////		}
//		
//		}
	
	@Test(priority=50)
	public void Tc_050()
		{
		testStarts("Tc_prefCard_06" , "Create Preference Card > Stage tab > Verify that user is able to search item on basis of Name, alias, SKU and MFR.");
		NavigateUrl(DashBoardURL);	
//		cartPage.addNewItem();
		PrefcardPageObject.prefCardPagePageLinkandwait();
		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
		clickOn(OR.prefCard_AddPrefCard);
		clickOn(OR.prefCard_addStage);
		setProperty("Prefcard_StageName", "abcdef");
		String sName=getProperty("Prefcard_StageName");
		typeIn(OR.prefCard_addStageName, sName);
		clickOn(OR.prefCard_addStageButton);		
		MycartPage.searchItem();
				
		}
	
	@Test(priority=51)
	public void Tc_051()
		{
		testStarts("Tc_prefCard_07" , "Create Preference Card > Stage tab > Verify that item category appears above item name.");
		NavigateUrl(DashBoardURL);
		//cartPage.addNewItem();
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.Admin_ItemCatalog);
		waitForElementToDisplay(OR.ItemCatalog_AddItem, 20);
		String itemDesc=getProperty("ItemDesc");
		typeIn(OR.ItemCatalog_searchTextBox, itemDesc);
		clickOn(OR.ItemCatalog_searchButtoncommon);	
		waitTime(4);
		String firstCategory=null;
		if(getText(OR.ItemCatalog_firstitemName).equals(itemDesc))
		{
		clickOn(OR.ItemCatalog_firstEDitButton);		
		firstCategory = getDropDownText(OR.ItemCatalog_itemCategory,1);
		setProperty("firstCategory", firstCategory);
		clickOn(OR.ItemCatalog_SaveButton);
		waitTime(3);
		}
		else
		{
			testLogFail("Search is not working properly on Item catalog page");
		}		
		PrefcardPageObject.addStageAndItem();		
		if(firstCategory!=null)
		{
			String temp=getText(OR.prefCard_catName);
			if(temp.equalsIgnoreCase(firstCategory))
			{
				testLogPass("Category" +firstCategory+ " is shown on the top of Item Name. ");				
			}			
		}
		else 
		{
			String temp=getText(OR.prefCard_catName);
			if(temp.equalsIgnoreCase("No category"))
			{
				testLogPass("Category name is Blank, so 'No category' is shown");				
			}
		}
		
		}
	
	@Test(priority=52)
	public void Tc_052()
		{
		testStarts("Tc_prefCard_08" , "Create Preference Card > Stage tab > Verify that item category appears above item name.");
		NavigateUrl(DashBoardURL);
		PrefcardPageObject.addStageAndItem();
		String s=getText(OR.prefCard_InitialopenQty);
		int intialOpenQty=Integer.parseInt(s);		
		String s1=getText(OR.prefCard_IntialHoldQty);
		int intialcloseQty=Integer.parseInt(s1);
		clickOn(OR.prefCard_plusIconOpenQTY);		
		String s3=getText(OR.prefCard_InitialopenQty);
		int afterOpenQty=Integer.parseInt(s3);		
		clickOn(OR.prefCard_plusIconHoldQTY);
		String s4=getText(OR.prefCard_InitialopenQty);
		int aftercloseQty=Integer.parseInt(s4);
		if(afterOpenQty==(intialOpenQty+1) && aftercloseQty==(intialcloseQty+1))
		{
		testLogPass("Quantities are Changing on clicking + Icon of Open and Close Quantities");	
		}
		else
		{
			testLogFail("Quantities are not Changing on clicking + Icon of Open and Close Quantities");	
		}
		
		}
	
	@Test(priority=53)
	public void Tc_053()
		{
		testStarts("Tc_prefCard_09" , "Create Preference Card > Stage tab > Verify that corresponding item gets deleted when user clicks Delete button.");
		NavigateUrl(DashBoardURL);
		PrefcardPageObject.addStageAndItem();
		clickOn(OR.prefCard_deleteQty);
		clickOn(OR.prefCard_confirmButton);
		verifyElementText(OR.prefCard_NoItemText ," No item in this stage.");
		
		}
	@Test(priority=54)
	public void Tc_054()
		{
		testStarts("Tc_prefCard_10" , "Create Preference Card > Stage tab > Verify that corresponding stage gets deleted when user clicks Remove Stage button.");
		NavigateUrl(DashBoardURL);
		PrefcardPageObject.prefCardPagePageLinkandwait();
		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
		clickOn(OR.prefCard_AddPrefCard);
		clickOn(OR.prefCard_addStage);
		String sName=getProperty("Prefcard_StageName");
		typeIn(OR.prefCard_addStageName, sName);
		clickOn(OR.prefCard_addStageButton);
		clickOn(OR.prefCard_removeStage);
		clickOn(OR.prefCard_confirmButton);
		if(!isElementDisplayed(OR.prefCard_removeStage, 10))
		{
			testLogPass("Stage is deleted Successfully");
		}
		else
		{
			testLogFail("Stage is deleted Successfully");			
		}
	
		}
	
	@Test(priority=55)
	public void Tc_055()
		{
		testStarts("Tc_prefCard_12" , "Create Preference Card > Verify that user can copy stage's from other prefcards using copy stages button. ");
		NavigateUrl(DashBoardURL);
		PrefcardPageObject.prefCardPagePageLinkandwait();
		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
		clickOn(OR.prefCard_AddPrefCard);
		clickOn(OR.prefCard_copyStages);
		waitForElementToDisplay(OR.prefCard_copyStagesPopUpText,50);
		selectFromDropdown(OR.prefCard_copyPredCardDrpDown, 1);
		waitTime(2);
		String stageName=getDropDownText(OR.prefCard_copyPredCardStageDroppDown, 1);
		System.out.println(stageName);
		clickOn(OR.prefCard_addButtton);
		clickOn(OR.prefCard_copyButtton);
		String temp=getText(OR.prefCard_stageName);
		System.out.println(temp);
		if(temp.contains(stageName))
		{
			testLogPass("Stage is copied from other Preference Card");
		}
		else
		{
			testLogFail("Stage is not copied from other Preference Card");
		}
		
		}
	
	@Test(priority=56)
	public void Tc_056()
		{
		testStarts("Tc_prefCard_14" , "Verify that corresponding preference card gets deleted when user clicks Delete button if corresponding card has no stages in it.");
		NavigateUrl(DashBoardURL);
		PrefcardPageObject.prefCardPagePageLinkandwait();
		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
		clickOn(OR.prefCard_AddPrefCard);
		String cardName = "cardName"+ApplicationKeyword.randomAlphaNumeric(2);
		typeIn(OR.prefCard_CardName, cardName);
		selectFromDropdown(OR.prefCard_PhysicianName, 1);
		waitTime(2);
		clickOn(OR.prefCard_saveButton);
		waitForElementToDisplay(OR.prefCard_searchTextBox, 10);
		typeIn(OR.prefCard_searchTextBox, cardName);
		waitForElementToDisplay(OR.prefCard_wait, 10);
		waitTime(3);
		if(getText(OR.prefCard_firstprefcard).contains(cardName))
		{
			clickOn(OR.prefCard_removePrefCard);
			clickOn(OR.prefCard_confirmButton);
			typeIn(OR.prefCard_searchTextBox, cardName);
			verifyElementText(OR.prefCard_NoPrefCardText, "No Preference cards Found.");			
		}
		else
		{
			testLogFail("Either preference card is not created or here is some issue in search on this page");
		}
		
		}
	
	@Test(priority=57)
	public void Tc_057()
		{
		testStarts("Tc_prefCard_15" , "Verify that user can copy any existing prefcard using �Copy Preference Card.� icon.");
		NavigateUrl(DashBoardURL);
		PrefcardPageObject.prefCardPagePageLinkandwait();
		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
		clickOn(OR.prefCard_copyCard);
		verifyElementText(OR.prefCard_copyCardPopUpText, "Copy Preference Card.");	
		closeBrowser();
		}

	@Test
	public void Tc_058()
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
	public void Tc_059()
	{
		testStarts("Tc_Cases_02", "Verify that Case preview page open when user clicks on Print.");
		NavigateUrl(DashBoardURL);			
		PrefcardPageObject.casesPageLinkandwait();
		selectFromDropdown(OR.Cases_SelectDateDropDown, "-- All Dates --");
		clickOn(OR.Cases_SearchButton);
		waitForElementToDisplay(OR.Cases_Printbutton, 20);
		clickOn(OR.Cases_Printbutton);
		verifyElementText(OR.Cases_PreviewPopUp, "Download case");
		closeBrowser();
	}

	@Test
	public void Tc_060()
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

	@Test
	public void Tc_061()
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

	@Test
	public void Tc_062()
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

	@Test(priority=63)
	public void Tc_063()
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

	@Test(priority=64)
	public void Tc_064()
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
	@Test
	public void Tc_065()
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
	@Test
	public void Tc_066()
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
	@Test
	public void Tc_067()
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

	@Test
	public void Tc_068()
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

	@Test
	public void Tc_069()
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

	@Test
	public void Tc_070()
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


	@Test
	public void Tc_071()
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
	@Test
	public void Tc_072()
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

	@Test
	public void Tc_073()
	{
		testStarts("Tc_Manufacturers_01" , "Verify that Manufacturers page appears when user clicks on manufacturer option.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.manufacturerPage();
		verifyElementText(OR.manufacturer_headerText, "MANUFACTURERS");
		

	}
	@Test
	public void Tc_074()
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

	@Test
	public void Tc_075()
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

	@Test
	public void Tc_076()
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
	public void Tc_077()
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
	@Test
	public void Tc_078()
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

	@Test
	public void Tc_079()
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
	
	@Test
	public void Tc_080()
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
	closeBrowser();
	}

	@Test
	public void Tc_081() throws InterruptedException
	{
		testStarts("Tc_Planner_01", "Verify that user can add patient using Add Patient option.");
		Loginpage.OpenBrowserAndLogin();
		PrefcardPageObject.plannerPageLinkandwait();
		String facility_Name=getText(OR.Patient_getfacilityName);
		clickOn(OR.Planner_createPatient);		
		//verifyElementText(OR.Planner_popUpText, "Create Patient ");
		String firstName="Test1";
		String lastName="Patient1";		
		typeIn(OR.Patient_firstName, firstName);
		//typeIn(OR.Patient_middleName, "Pat");
		typeIn(OR.Patient_lastName, lastName);
		typeIn(OR.Patient_mrnNumber, "00001");
		typeIn(OR.Patient_accNumber, "465000");
		typeIn(OR.Patient_dob, "11112017");
		clickOn(OR.Patient_facDropDown);
		WebElement elem=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//span[text()='"+facility_Name+"']"));
		elem.click();
		clickOn(OR.Planner_AddPatient);
		Thread.sleep(4000);
		PrefcardPageObject.patientsPageLinkandwait();
		typeIn(OR.Patient_searchTextBox, firstName+" "+lastName);
		clickOn(OR.Patient_searchbutton);
		waitForElementToDisplay(OR.Patient_wait, 20);
		verifyElement(OR.Patient_firstPatient);
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
	
	@Test
	public void Tc_082()
	{
		testStarts("Tc_Planner_02", "Verify that case can be created by clicking on the calendar vie.");
		NavigateUrl(DashBoardURL);
		PrefcardPageObject.plannerPageLinkandwait();
		clickOn(OR.Planner_datatime630);
		verifyElementText(OR.Planner_createCaseHeading, "Schedule Case -");
		closeBrowser();
	}
	@Test
	public void Tc_083()
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

	@Test
	public void Tc_084()
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

	@Test
	public void Tc_085()
	{
		testStarts("Tc_Patients_07", "Drill-down > Verify that edit case page opens when user clicks Edit button.");
	NavigateUrl(DashBoardURL);	
		PrefcardPageObject.patientsPageLinkandwait();
		clickOn(OR.Patient_EditPAtient);
		verifyElementText(OR.Patient_EditPAtientPopUp, "Edit Patient ");
		clickOn(OR.Patient_EditPAtientPopUpCancel);
	}


	//May fail in case there is no case attached to first Patient
	@Test
	public void Tc_086()
	{
		testStarts("Tc_Patients_07", "Drill-down > Verify that case preview opens when user clicks Print case button..");
	NavigateUrl(DashBoardURL);	
		PrefcardPageObject.patientsPageLinkandwait();
		clickOn(OR.Patient_plusIcon);
		clickOn(OR.Patient_PrintIcon);
		verifyElementText(OR.Patient_previewpopup, "Download case");
		clickOn(OR.Patient_ClosePreview);
		
	}

	@Test
	public void Tc_087() throws InterruptedException
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
//		clickOn(OR.Patient_EditPAtientPopUpCancel);
	}

	@Test
	public void Tc_088()
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
		clickOn(OR.Patient_EditPAtientPopUpCancel);
		
	}
	
	
	@Test
	public void Tc_089()
	{			
		testStarts("Tc_Patients_11", "Verify that �Apply Preference Card To� pop up appears when user clicks on Apply Preference Card icon.");
	NavigateUrl(DashBoardURL);	
		PrefcardPageObject.patientsPageLinkandwait();			
		waitForElementToDisplay(OR.Patient_firstPatient, 10);
		clickOn(OR.Patient_Addprefcard);
		//System.out.println(getText(OR.Patient_AddprefcardPopUpText));
		verifyElementText(OR.Patient_AddprefcardPopUpText, " Apply Preference Card To");
		closeBrowser();	
		
	}
	
	@Test
	public static void Tc_090()
	{	
		testStarts("TC_ORG_01_02_03_04", "Verify that Organization Details page appears when user clicks on organization option in admin dropdown.");
		Loginpage.OpenBrowserAndLogin();
		clickOn(OR.DashBoard_AdminDropdown);
		verifyElementText(OR.orgsetting_AdminMenuText, "Admin");		 
		clickOn(OR.Shop_orgpage);
		waitForElementToDisplay(OR.orgsetting_orgPageTextt, 10);
		verifyPageTitle("Organization");
		verifyElementText(OR.orgsetting_orgPageTextt, "ORGANIZATION DETAILS");		 
		clickOn(OR.Shop_orgFeatures2Page); 
		waitForElementToDisplay(OR.orgsetting_orgFeaturesPageText, 10);
		verifyElementText(OR.orgsetting_orgFeaturesPageText,"INVENTORY");
		clickOn(OR.DashBoard_AdminDropdown);
		clickOn(OR.orgsetting_orgBudgetPage);
		waitForElementToDisplay(OR.orgsetting_orgBudgetPageText, 10);
		verifyPageTitle("Organization");
		//System.out.println(getText(OR.orgsetting_orgBudgetPageText));
		verifyElementText(OR.orgsetting_orgBudgetPageText, "MANAGE ORGANIZATION BUDGET");		 
		closeBrowser();
	} 

	@Test
	public void Tc_091()
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
		closeBrowser();
	}
	@Test
	public void Tc_092() throws InterruptedException
	{
		testStarts("Tc_INV_01", "Verify that following dropdown filters(Vendor,Status,Search By ,Due Date,Facility,Departments,Ordered By	) appear on page");
		Loginpage.OpenBrowserAndLogin();	
		waitForElementToDisplay(OR.Shop_Menu, 60);
		//get the By default Facility Name of USer	
		clickOn(OR.Orders_Link);		
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		String s=getText(OR.conditionForNoOrder);
		if (s.trim().equalsIgnoreCase("No Order Found"))
		{
			testLogPass("Assigned State has no orders");
			clickOn(OR.Order_status_dropdown);
			clickOn(OR.Order_PO_StatusDropdown_Confirmed);
			if(s.trim().equalsIgnoreCase("No Order Found"))
			{
				testLogPass("Confirmed State has no orders");
				clickOn(OR.Order_status_dropdown);
				clickOn(OR.Order_PO_StatusDropdown_PartialReceived);
				if(s.trim().equalsIgnoreCase("No Order Found"))
				{
					testLogPass("PartialReceived State has no orders");
					clickOn(OR.Order_status_dropdown);
					clickOn(OR.Order_PO_StatusDropdown_Completed);
					if(s.trim().equalsIgnoreCase("No Order Found"))
					{
						testLogPass("Completed State has no orders");
						testLogFail("There are no orders for which the Invoice could be generated");
					}
				}
				else
				{		
					CreateInvoice();
				}
			}
			else
			{		
				CreateInvoice();
			}
		}
		else
		{		
			CreateInvoice();
		}
		
	}
	
	public void CreateInvoice() throws InterruptedException
	{
		waitForElementToDisplay(OR.Invoice_wait, 20);
		clickOn(OR.Order_PO_first_dropdown);
		waitForElementToDisplay(OR.Invoice_wait, 60);
		WebElement element = driver.findElement(By.xpath("(//a[text()='Invoices'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		waitForElementToDisplay(OR.Order_PO_first_Addinvoice, 60);
		clickOn(OR.Order_PO_first_Addinvoice);
		waitForElementToDisplay(OR.Order_PO_Invoice_Header, 60);
		String PoNum=getText(OR.Invoice_InvoicePONum);
		String invoiceName="INV"+ApplicationKeyword.randomAlphaNumeric(3);
		setProperty("invoiceName", invoiceName);
		typeIn(OR.Invoice_InvoiceDesc, invoiceName);
		clickOn(OR.Invoice_SaveButton);
		clickOn(OR.Invoice_ConfirmButton);
		testLogPass("Second Test case starts");
		String PONUmberHeader=getText(OR.Invoice_PONUmInHeader).trim();
		System.out.println(PONUmberHeader);
		clickOn(OR.Invoice_SelectFirstInvoice);
		String header1=getText(OR.Invoice_PageHeadTextIncludingPONum).trim();
		System.out.println(header1);
		if(header1.contains(PONUmberHeader))
		{
			testLogPass("Edit PO page is opened");
		}
		else
		{
			testLogFail("Edit PO page is not opened");
		}
		testLogPass("Third Test case starts");		
		clickOn(OR.Invoice_DocButton);
		//waitForElementToDisplay(OR.Invoice_clickOnUploadFIle,12);
		WebElement elem=driver.findElement(By.xpath("//*[@type='file']"));
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path --- " + projectPath);
		elem.sendKeys(projectPath + "/assets/images.jpeg");
		String nameOfFile_Expected="images.jpeg";
		String s2=getText(OR.Invoice_UploadDocName).trim();
		if(s2.contains(nameOfFile_Expected))
		{
			System.out.println("Name matched");
		}
		clickOn(OR.Invoice_closeButton);
		clickOn(OR.Request_InvoicePageLink);	
		typeIn(OR.Invoice_SearchInInvoiceTextBox, PoNum);
		clickOn(OR.Invoice_SearchButton);
		Thread.sleep(4000);
		verifyElementText(OR.Invoice_SelectFirstPONUmFromInvoicePage, PoNum);		
		clearEditBox(OR.Invoice_SearchInInvoiceTextBox);
		typeIn(OR.Invoice_SearchInInvoiceTextBox, invoiceName);
		clickOn(OR.Invoice_SearchButton);
		Thread.sleep(4000);
		String abc=getText(OR.Invoice_SelectFirstInvoiceFromInvoicePage);
		if(abc.equalsIgnoreCase(invoiceName))
		{
			testLogPass("Invoice is searched with PO number");								
		}
		else
		{
			testLogFail("Invoice is not searched with PO number");	
		}
		clearEditBox(OR.Invoice_SearchInInvoiceTextBox);
		typeIn(OR.Invoice_SearchInInvoiceTextBox, nameOfFile_Expected);
		clickOn(OR.Invoice_SearchButton);
		Thread.sleep(4000);
		verifyElementText(OR.Invoice_SelectFirstPONUmFromInvoicePage , PoNum);
		
	}
	@Test
	public void Tc_093() throws InterruptedException
	{
		testStarts("Tc_INV_09 and 10", "Verify that Invoice PDF gets downloaded when user clicks Download PDF button"
				+ " Verify that Invoice Print preview appears when user clicks Print button.");
		NavigateUrl(DashBoardURL);
		Invoicespage.typeAndSearchInvoice();
		waitForElementToDisplay(OR.Invoice_wait, 10);
		clickOn(OR.Invoice_DownoadPDF);	
		waitForElementToDisplay(OR.Invoice_wait, 10);
		clickOn(OR.Invoice_PrintPDF);
		//waitForElementToDisplay(OR.Invoice_PrintPReviewWindow,10);
		String IvnoiceText=getText(OR.Invoice_PrintPReviewWindow);
		if(IvnoiceText.contains("Print Invoice"))
		{
			testLogPass("Print Preview window opens");			
		}
		else
		{
			testLogFail("Print Preview window doesnot opens");
		}
		clickOn(OR.Invoice_PrintPReviewclose);
		
	}
	
	@Test
	public void Tc_094() throws InterruptedException
	{
		testStarts("Tc_INV_02", "Verify that following dropdown(VendorStatusSearch By Due Date ,Facility, Departments, Ordered By  ) filters appear on page.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Request_InvoicePageLink);		
		//Vendor DropDpwn		
	    verifyElementText(OR.Invoice_vendorDropDownLabels, "Vendor:");
	    if(isElementDisplayed(OR.Invoice_vendorDropDowns, 10))
	    {
	    	testLogPass("Vendor DropDownPresent");
	    }
	    else
	    {
	    	testLogFail("Vendor DropDown not Present");
	    }	    
	  //Status DropDpwn	    
	    verifyElementText(OR.Invoice_statusDropDownLabel, "Status:");
	    if(isElementDisplayed(OR.Invoice_statusDropDowns, 10))
	    {
	    	testLogPass("Status DropDownPresent");
	    }
	    else
	    {
	    	testLogFail("Status DropDown not Present");
	    }
	    //Search By Due date DropDpwn	    
	    verifyElementText(OR.Invoice_SearchBYDueDateLabel, "Search by Due-Date");
	    if(isElementDisplayed(OR.Invoice_SearchBYDueDateDropDowns, 10))
	    {
	    	testLogPass("Search by Due Date DropDownPresent");
	    }
	    else
	    {
	    	testLogFail("Search by Due Date DropDown not Present");
	    }	    
	    //Search By Facility DropDpwn	    
	    verifyElementText(OR.Invoice_FacilityLabel, "Facility");
	    if(isElementDisplayed(OR.Invoice_facilityDateDropDowns, 10))
	    {
	    	testLogPass("Search by Facility DropDown Present");
	    }
	    else
	    {
	    	testLogFail("Search by Facility DropDown not Present");
	    }	    
	    //Search By Department DropDpwn	    
	    verifyElementText(OR.Invoice_departmentLabel, "Departments");
	    if(isElementDisplayed(OR.Invoice_departmentDropDown, 10))
	    {
	    	testLogPass("Search by Department Drop Down Present");
	    }
	    else
	    {
	    	testLogFail("Search by Department Drop Down not Present");
	    }	    	    
	    //Search By Ordered DropDpwn	    
	    verifyElementText(OR.Invoice_OrderedByLabel, "Ordered By:");
	    if(isElementDisplayed(OR.Invoice_OrderedByDropDown, 10))
	    {
	    	testLogPass("Search by Due Date DropDownPresent");
	    }
	    else
	    {
	    	testLogFail("Search by Due Date DropDown not Present");
	    }
	   
	}
	
	@Test
	public void Tc_095() throws InterruptedException
	{
		testStarts("Tc_INV_03", "Edit Invoice > Verify that �Send to accounting� button appears on top right side of page.");
		NavigateUrl(DashBoardURL);
		if(!Invoicespage.IntactAndQucikBook_Toggle())
		{
			Invoicespage.typeAndSearchInvoice();
			clickOn(OR.Invoice_SelectFirstInvoice);
			verifyElementText(OR.Invoice_SentToAccounting, "Send To Accounting");
			testLogPass("Sent to Accounting button is present");
		}
		else
		{
			testLogPass("Either of the toggles 'Intact' or 'QuickBook' is enabled as a result Sent to Accounting button is not present");
		}
		
	}
	
	@Test
	public void Tc_096()
	{
		testStarts("Tc_INV_04", "Verify that invoice gets deleted on clicking corresponding Delete button.");
		NavigateUrl(DashBoardURL);
		Invoicespage.typeAndSearchInvoice();
		waitForElementToDisplay(OR.Invoice_wait, 10);
		String invoiceName=getProperty("invoiceName");
		if(getText(OR.Invoice_firstInvoice).equalsIgnoreCase(invoiceName))
		{
			waitForElementToDisplay(OR.Invoice_Delete, 10);
			clickOn(OR.Invoice_Delete);
			clickOn(OR.Invoice_confirmButton);
			waitForElementToDisplay(OR.Invoice_wait, 10);
			Invoicespage.typeAndSearchInvoice();
			if(!getText(OR.Invoice_firstInvoice).equalsIgnoreCase(invoiceName))
			{
				testLogPass("Invoice is deleted successfully");
			}
			else
			{
				testLogFail("Invoice is not deleted");
			}
		}
		else
		{
			testLogFail("Invoice was not seacrhed or added properly");
		}
//		clickOn(OR.Invoice_confirmButton);
//		verifyElementText(OR.Invoice_NoInvoiceFoundMessage, "No Invoice Found");	
closeBrowser();		
	}

	@Test
	public void Tc_097()
	{
		testStarts("Tc_manageInv_01" , "Verify that user can search items on the basis of Item Name, alias, MFR, SKU ands Crosswalk Id ");
		Loginpage.OpenBrowserAndLogin();		
		ManageInventoryPOpage.PageLinkAndWait("inventory", OR.manageInv_pageLink);
		String itemDesc=getProperty("ItemDesc");
		typeIn(OR.manageInv_searchTextBox, itemDesc);
		clickOn(OR.manageInv_searchButton);
		waitForElementToDisplay(OR.manageInv_wait2, 20);
		String ItemName=getText(OR.manageInv_firstItemDesc);
		if(ItemName.equalsIgnoreCase(itemDesc))
		{
			testLogPass("Item is searched with " + ItemName);
		}
		else
		{
			testLogFail("Item is not searched with " + ItemName);			
		}
		clearEditBox(OR.manageInv_searchTextBox);
		waitForElementToDisplay(OR.manageInv_wait2, 20);
		String Sku=getProperty("Sku");
		typeIn(OR.manageInv_searchTextBox,Sku);
		clickOn(OR.manageInv_searchButton);
		waitForElementToDisplay(OR.manageInv_wait2, 20);
		String getskuName=getText(OR.manageInv_firstItemSku);
		System.out.println(getskuName);
		waitForElementToDisplay(OR.manageInv_wait2, 15);
		if(getskuName.equalsIgnoreCase(Sku))
		{
			testLogPass("Item is searched with " + Sku);
		}
		else
		{
			testLogFail("Item is not searched with " + Sku);			
		}	
		clearEditBox(OR.manageInv_searchTextBox);
		waitForElementToDisplay(OR.manageInv_wait2, 15);
		String mfr=getProperty("ItemMfr");
		typeIn(OR.manageInv_searchTextBox,mfr);
		clickOn(OR.manageInv_searchButton);
		waitForElementToDisplay(OR.manageInv_wait2, 15);
		if(ItemName.equalsIgnoreCase(itemDesc))
		{
			testLogPass("Item is searched with " + mfr);
		}
		else
		{
			testLogFail("Item is not searched with " + mfr);			
		}	
		clearEditBox(OR.manageInv_searchTextBox);
		waitForElementToDisplay(OR.manageInv_wait2, 15);
		String alias=getProperty("alias");
		typeIn(OR.manageInv_searchTextBox,alias);
		clickOn(OR.manageInv_searchButton);
		waitForElementToDisplay(OR.manageInv_wait2, 15);
		if(ItemName.equalsIgnoreCase(itemDesc))
		{
			testLogPass("Item is searched with " + alias);
		}
		else
		{
			testLogFail("Item is not searched with " + alias);			
		}
	}


	@Test
	public void Tc_098()
	{
		testStarts("Tc_manageInv_03" , "Edit Item > Verify that changes made get saved successfully, on clicking SAVE button");
		NavigateUrl(DashBoardURL);	
		//Loginpage.OpenBrowserAndLogin();		
		ManageInventoryPOpage.PageLinkAndWait("inventory", OR.manageInv_pageLink);
		String itemDesc=getProperty("ItemDesc");
		typeIn(OR.manageInv_searchTextBox, itemDesc);
		clickOn(OR.manageInv_searchButton);
		waitTime(4);
		waitForElementToDisplay(OR.manageInv_wait2, 20);
		String ItemName=getText(OR.manageInv_firstItemDesc);
		if(ItemName.equalsIgnoreCase(itemDesc))
		{
			clickOn(OR.manageInv_EditIem);
			waitForElementToDisplay(OR.manageInv_EditIemHeaderText, 20);
			clickOn(OR.manageInv_VendorsTab);
			String temp=getDropDownText(OR.manageInv_EditStockStatus, 1);	
			clearEditBox(OR.manageInv_EditItem_GPoContractPrice);
			typeIn(OR.manageInv_EditItem_GPoContractPrice, "60");
			clickOn(OR.manageInv_EditItem_SaveButton);
			clearEditBox(OR.manageInv_searchTextBox);
			typeIn(OR.manageInv_searchTextBox, itemDesc);
			clickOn(OR.manageInv_searchButton);
			waitForElementToDisplay(OR.manageInv_wait2, 20);
			String ItemName2=getText(OR.manageInv_firstItemDesc);
			if(ItemName2.equalsIgnoreCase(itemDesc))
			{
				clickOn(OR.manageInv_EditIem);
				waitForElementToDisplay(OR.manageInv_EditIemHeaderText, 20);
				clickOn(OR.manageInv_VendorsTab);
				String temp1=getAttributeValue(OR.manageInv_EditStockStatus, "value");
				if(temp1.contains(temp))
				{
					testLogPass("Item is Edited Successfully");
				}
				else
				{
					testLogFail("Item is not Edited Successfully");
				}
			}
			else
			{
				testLogFail("Seach is not working properly");
			}
		}
		else
		{
			testLogFail("Seach is not working properly");
		}	

		
	}

	@Test
	public void Tc_099()
	{
		testStarts("Tc_manageInv_04" , "Delete > Verify that corresponding item gets deleted from page, on clicking �Delete� button");
		NavigateUrl(DashBoardURL);	
	   //Loginpage.OpenBrowserAndLogin();	
		ManageInventoryPOpage.PageLinkAndWait("inventory", OR.manageInv_pageLink);
		String itemDesc=getProperty("ItemDesc");
		typeIn(OR.manageInv_searchTextBox, itemDesc);
		clickOn(OR.manageInv_searchButton);
		waitForElementToDisplay(OR.manageInv_wait2, 20);
		String ItemName=getText(OR.manageInv_firstItemDesc);
		char quotes='"';
		if(ItemName.equalsIgnoreCase(itemDesc))
		{
			clickOn(OR.manageInv_DeleteIcon);
			verifyElementText(OR.manageInv_DeletePoUp, ("Do you really want to delete the item " + quotes + itemDesc +quotes+ "?" ));
		}
		else
		{
			testLogFail("Search is not working properly");
		}
		clickOn(OR.manageInv_cancelPopUP);
		
	}


	@Test
	public void Tc_100()
	{
		testStarts("Tc_manageInv_05_06" , "Verify that �Print Preview� button with total number of items to print appear on page, on clicking �Print button� for any item "
				+ "+Verify that Print popup window opens on clicking Print Preview button.");
		NavigateUrl(DashBoardURL);
		ManageInventoryPOpage.PageLinkAndWait("inventory", OR.manageInv_pageLink);
		String itemDesc=getProperty("ItemDesc");
		typeIn(OR.manageInv_searchTextBox, itemDesc);
		clickOn(OR.manageInv_searchButton);
		waitTime(4);
		String ItemName=getText(OR.manageInv_firstItemDesc);				
		if(ItemName.equalsIgnoreCase(itemDesc))
		{
			clickOn(OR.manageInv_PrintIcon);
			clickOn(OR.manageInv_PrintPreviewButton);
			verifyElementText(OR.manageInv_PrintPopUpText, "Selected Items");
			List<WebElement> allItemrows=driver.findElements(By.xpath("//*[@id='populate_items_pdf']/tr"));
			for(int i=1;i<=allItemrows.size();i++)
			{
				String we=(allItemrows.get(i-1)).findElement(By.xpath("//td["+i+"]")).getText();		
				System.out.println(we);
				if(we.contains(itemDesc))
				{
					testLogPass("Item found in the Print Pop up");
					break;
				}								
			}
		}
		clickOn(OR.manage_PrintCloseclose);
		
	}

	@Test
	public void Tc_101()
	{
		testStarts("Tc_manageInv_07" , "Verify that Transfer Inventory popup appears on clicking �Transfer� button.");
		NavigateUrl(DashBoardURL);	
		ManageInventoryPOpage.PageLinkAndWait("inventory", OR.manageInv_pageLink);
		clickOn(OR.manageInv_TransferIcon);
		verifyElementText(OR.manageInv_TransferPopUpText, "Transfer Inventory");
		waitTime(2);
		clickOn(OR.manage_PrintCloseclose);
		
	}

	@Test
	public void Tc_102()
	{
		testStarts("Tc_manageInv_02_09" , "Verify that on selecting any Inventory + and � buttons are appearing +"
				+ "Verify that  Cycle Count option appears on page");
		NavigateUrl(DashBoardURL);	
		ManageInventoryPOpage.PageLinkAndWait("inventory", OR.manageInv_pageLink);    
		selectFromDropdown(OR.manageInv_InventoryDropDown, 1);
		clickOn(OR.manageInv_searchButton);
		waitForElementToDisplay(OR.manageInv_wait2, 20);
		if(isElementDisplayed(OR.manageInv_minusSign, 10))
		{
			testLogPass("Minus sign is present on the Page");
		}
		else
		{
			testLogFail("Minus sign is not present on the Page");
		}
		if(isElementDisplayed(OR.manageInv_plusSign, 10))
		{
			testLogPass("Plus sign is present on the Page");
		}
		else
		{
			testLogFail("Plus sign is not present on the Page");
		}
		if(isElementDisplayed(OR.manageInv_cycleCount, 10))
		{
			testLogPass("Cycle Count Button is displayed on the page");			
		}
			closeBrowser();
	}
	
	@Test
	public void Tc_103()
	{
		testStarts("Tc_ItemCat_01_02_03", "Verify that user is able to add New category"
				+ "+Add/ Edit Category > Verify that 'Save' button appears Active, only if values are entered in mandatory fields + "
				+ "Add/ Edit Category > Verify that changes get saved, when user clicks 'Save' button");
		Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.AdminAndItemCat();
		clickOn(OR.ItemCat_AddCategoryButton);
		waitForElementToDisplay(OR.ItemCat_CatName, 20);
		if(getAttributeValue(OR.ItemCat_SAveButton, "disabled")!=null)
		{
			testLogPass("Save button is disabled if Name field is not filled");
		}
		else
		{
			testLogFail("Save button is not disabled if Name field is not filled");
		}
		ItemCatName = "Itemcat"+ApplicationKeyword.randomAlphaNumeric(2);
		typeIn(OR.ItemCat_CatName, ItemCatName);
		if(getAttributeValue(OR.ItemCat_SAveButton, "disabled")==null)
		{
			testLogPass("Save button is enabled if Name field is filled");
		}
		else
		{
			testLogFail("Save button is not enabled if Name field is filled");
		}
		clickOn(OR.ItemCat_SAveButton);
		waitForElementToDisplay(OR.ItemCat_wait, 10);
		typeIn(OR.ItemCat_SearchTextBox, ItemCatName);
		clickOn(OR.ItemCat_SearchButton);
		waitForElementToDisplay(OR.ItemCat_wait, 10);
		compareExactText(OR.ItemCat_firstCatName,"New Category is created", "New Category is not created", ItemCatName);
		
	}


	@Test
	public void Tc_104()
	{
		testStarts("Tc_ItemCat_04", "Verify that Category gets deleted, when user clicks 'Delete' button");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndItemCat();
		clickOn(OR.ItemCat_AddCategoryButton);
		waitForElementToDisplay(OR.ItemCat_CatName, 20);
		ItemCatName = "Itemcat"+ApplicationKeyword.randomAlphaNumeric(2);
		typeIn(OR.ItemCat_CatName, ItemCatName);
		clickOn(OR.ItemCat_SAveButton);
		waitForElementToDisplay(OR.ItemCat_wait, 10);
		typeIn(OR.ItemCat_SearchTextBox, ItemCatName);
		clickOn(OR.ItemCat_SearchButton);
		waitForElementToDisplay(OR.ItemCat_wait, 10);
		waitTime(5);
		if(getText(OR.ItemCat_firstCatName).equals(ItemCatName))
		{
			clickOn(OR.ItemCat_DeleteButton);
			clickOn(OR.ItemCat_ConfirmButton);
			clickOn(OR.ItemCat_SearchButton);
			waitForElementToDisplay(OR.ItemCat_wait, 10);
			waitTime(4);
			compareText(OR.ItemCat_NoRecordFoundText, "Item Category is deleted", "Item Category is not deleted", "No records found");
		}
		else
		{
			testLogFail("Either Category is not created or search is not working properly");
		}
		
	}

	//Print Barcodes/QR Codes

	@Test
	public void Tc_105()
	{
		testStarts("Tc_PrintBarcode_01_02", "Verify that �Select Facility� popup appears when user clicks on facility name with �PRINT BARCODES/QRCODES FOR� label."
				+ "Verify that �selected� button appears as disabled for the facility which is displayed on Shop page.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Shop_Menu);
		waitForElementToDisplay(OR.Shop_SHopfor_getfacilityName, 10);
		String facility_Name=getText(OR.Shop_SHopfor_getfacilityName);
		System.out.println(facility_Name);
		Organisation_settingspage.AdminAndPrintBarcodeLink();
		verifyElementText(OR.PrintBarcodes_text, "PRINT BARCODES/QRCODES FOR");
		clickOn(OR.PrintBarcodes_SelectFAc);
		verifyElementText(OR.PrintBarcodes_SelectFac_title, "Select Facility");
		int one = driver.findElements(By.xpath("//*[@class='table table-responsive table-striped table-bordered']/tbody/tr[@class='ng-scope']")).size();
		boolean facFound=false;
		String xpath;
		String selectedFacility;
		WebElement btn;
		for(int i=1; i<=one; i++)
		{
			xpath="(//table[@class='table table-responsive table-striped table-bordered']/tbody/tr["+i+"]";
			selectedFacility=driver.findElement(By.xpath(xpath+"/td)")).getText();
			System.out.println(selectedFacility);

			if(selectedFacility.equals(facility_Name))
			{  
				facFound=true;
				btn= driver.findElement(By.xpath(xpath+"/td[2]/div/button)"));
				if(btn.getAttribute("disabled")!=null)
				{
					testLogPass("Got the text '"+selectedFacility+ "' And it Matches with selected Facility" );
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

	@Test
	public void Tc_106()
	{
		testStarts("Tc_PrintBarcode_03", "Verify that user can search item on basis of �Item Name, alias, SKU, MFR#�.");		
		NavigateUrl(DashBoardURL);
		ManageInventoryPOpage.addItemInInventory();
		Organisation_settingspage.AdminAndPrintBarcodeLink();	
		waitForElementToDisplay(OR.PrintBarcodes_wait, 10);
		String ItemDesc=getProperty("ItemDesc");
		typeAndSearch(OR.PrintBarcodes_searchTextBox, ItemDesc, OR.PrintBarcodes_searchButton);
		waitForElementToDisplay(OR.PrintBarcodes_wait, 10);
		compareExactText(OR.PrintBarcodes_firstItem, "Item is searched with Description", "Item is not searched with Description", ItemDesc);
		String Sku=getProperty("Sku");
		clearEditBox(OR.PrintBarcodes_searchTextBox);
		typeAndSearch(OR.PrintBarcodes_searchTextBox, Sku, OR.PrintBarcodes_searchButton);
		waitForElementToDisplay(OR.PrintBarcodes_wait, 10);
		compareExactText(OR.PrintBarcodes_skuName, "Item is searched with sku", "Item is not searched with sku", Sku);	
		String alias=getProperty("alias");
		clearEditBox(OR.PrintBarcodes_searchTextBox);
		typeAndSearch(OR.PrintBarcodes_searchTextBox, alias, OR.PrintBarcodes_searchButton);
		waitForElementToDisplay(OR.PrintBarcodes_wait, 10);
		compareExactText(OR.PrintBarcodes_firstItem, "Item is searched with alias", "Item is not searched with alias", ItemDesc);
		String ItemMfr=getProperty("ItemMfr");
		clearEditBox(OR.PrintBarcodes_searchTextBox);
		typeAndSearch(OR.PrintBarcodes_searchTextBox, ItemMfr, OR.PrintBarcodes_searchButton);		
		waitTime(10);
		compareExactText(OR.PrintBarcodes_firstItem, "Item is searched with Mfr#", "Item is not searched with Mfr#", ItemDesc);		
		
	}

	@Test
	public void Tc_107()
	{
		testStarts("Tc_PrintBarcode_04", "Verify that Vendor and inventory dropdown appears on page.");		
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndPrintBarcodeLink();
		if(isElementDisplayed(OR.PrintBarcodes_vendordropdown, 10))
		{
			testLogPass("Vendor DropDown is Present on the Page");
		}
		else
		{
			testLogFail("Vendor DropDown is not Present on the Page");
		}
		if(isElementDisplayed(OR.PrintBarcodes_Inventorydropdown, 10))
		{
			testLogPass("Inventory DropDown is Present on the Page");
		}
		else
		{
			testLogFail("Inventory DropDown is not Present on the Page");
		}
		
	}
	@Test
	public void Tc_108()
	{
		testStarts("Tc_PrintBarcode_06", "Verify that �Print items Barcode/QRcode� pop up appears with print with options.");		
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.AdminAndPrintBarcodeLink();
		clickOn(OR.PrintBarcodes_PrintCodesButton);
		verifyElementText(OR.PrintBarcodes_PrintCodesPopUp, "Print items Barcode/QRcode");
		

	}
	@Test
	public void Tc_109()
	{
		testStarts("Tc_PrintBarcode_07", "Verify that following options appear('Print Sheet With', 'Generate PDF', 'Print With', 'Print Pages.')");		
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndPrintBarcodeLink();
		clickOn(OR.PrintBarcodes_PrintCodesButton);
		verifyElementText(OR.PrintBarcodes_PrintCodesPopUp, "Print items Barcode/QRcode");
		verifyElementText(OR.PrintBarcodes_text01, "Print sheet With :");
		verifyElementText(OR.PrintBarcodes_text02, "Generate PDF :");
		verifyElementText(OR.PrintBarcodes_text03, "Print With :");
		verifyElementText(OR.PrintBarcodes_text04, "Print Pages :");
		

	}
	@Test
	public void Tc_110()
	{
		testStarts("Tc_PrintBarcode_08", "Verify that user can print items on all pages or can select specific page numbers. ");		
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndPrintBarcodeLink();
		clickOn(OR.PrintBarcodes_PrintCodesButton);
		if(!getAttributeValue(OR.PrintBarcodes_PrintPagesAllRadioButton, "class").contains("ng-valid-parse"))
		{
			testLogPass("All option is selected");
			clickOn(OR.PrintBarcodes_SelectPagesRadioButton);
			typeIn(OR.PrintBarcodes_PrintPagesTextBox, "1");
			String s=getAttributeValue(OR.PrintBarcodes_PrintPagesTextBox, "value");
			System.out.println(s);
			if(s.equals("1"))
			{
				testLogPass("User can typeIn Print Pages TextBOx");
			}
			else
			{
				testLogPass("User can not typeIn Print Pages TextBOx");
			}
		}
		else
		{
			testLogPass("All option is not selected");
		}
		
	}
	@Test
	public void Tc_111()
	{
		testStarts("Tc_PrintBarcode_10", "Verify that user can print items on all pages or can select specific page numbers. ");		
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndPrintBarcodeLink();
		clickOn(OR.PrintBarcodes_PrintCodesButton);
		clickOn(OR.PrintBarcodes_PrintButton);
		waitForElementToDisplay(OR.PrintBarcodes_PreviewPDFTEXT, 50);
		verifyElementText(OR.PrintBarcodes_PreviewPDFTEXT, "Preview PDF");
		clickOn(OR.Invoice_PrintPReviewclose);
		
	}

	//PO Special Instructions

	@Test
	public void Tc_112()
	{
		testStarts("Tc_SI_01_02", "Verify that user can search special instruction using search text field+"
				+ "Verify that user can add special instructions on clicking Add button.");		
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.SIPage();
		waitForElementToDisplay(OR.SI_AddButton, 20);
		clickOn(OR.SI_AddButton);		
		verifyElementText(OR.SI_AddPageHeaderText, "Add PO Special Instructions");
		SI = "SI"+ApplicationKeyword.randomAlphaNumeric(4);
		setProperty("SI", SI);
		typeIn(OR.SI_AddSITextBox, SI);
		clickOn(OR.SI_SAveButton);
		//waitForElementToDisplay(OR.SI_TableWait, 15);
		typeIn(OR.SI_SearchTextBox, SI);
		clickOn(OR.SI_SearchButton);
		if(getText(OR.SI_firstElem).trim().equals(SI))
		{

			testLogPass("SI is added and searched ");
		}
		else
		{
			testLogFail("SI is not searched from search Box ");
		}	

	}
	@Test
	public void Tc_113()
	{
		testStarts("Tc_SI_03", "Verify that user can edit the special instruction using the Edit button.");		
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.SIPage();
		String SI=getProperty("SI");
		typeIn(OR.SI_SearchTextBox, SI);
		clickOn(OR.SI_SearchButton);
		waitForElementToDisplay(OR.SI_firstRow, 15);
		//waitTime(3);
		if(getText(OR.SI_firstElem).trim().equals(SI))
		{
			clickOn(OR.SI_EditItem);
			verifyElementText(OR.SI_AddPageHeaderText, "Edit PO Special Instructions");
		}
		else
		{
			testLogFail("SI is not searched from search Box");
		}
			
	}

	@Test
	public void Tc_114()
	{
		testStarts("Tc_SI_04", "Verify that Special instruction gets deleted on clicking Delete button.");		
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.SIPage();
		String SI=getProperty("SI");
		typeIn(OR.SI_SearchTextBox, SI);
		clickOn(OR.SI_SearchButton);
		waitForElementToDisplay(OR.SI_firstRow, 15);
		if(getText(OR.SI_firstElem).trim().equals(SI))
		{
			clickOn(OR.SI_DeleteButton);
			clickOn(OR.SI_ConfirmButton);
			waitForElementToDisplay(OR.SI_firstRow, 15);
			typeIn(OR.SI_SearchTextBox, SI);
			clickOn(OR.SI_SearchButton);
			waitForElementToDisplay(OR.SI_firstRow, 10);
			if(!getText(OR.SI_firstElem).trim().equals(SI))
			{
				testLogPass("SI is deleted successfully."); 			 
			}		 
			else
			{
				testLogFail("SI is not deleted or is not searchable");
			}
		}
		else
		{
			testLogFail("SI is not searched from search Box or new SI is not added ");
		}		

	}

	//Invoice Payment Terms
	@Test
	public void Tc_115()
	{
		testStarts("Tc_PTerms_01_02", "Verify that can add payment term using �Add Payment Term� button."
				+ "+Verify that user can search payment terms with description, type, no. ,of days and discount/penalty percentage using the search text field.");		
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.InvoicePayementTermPage();
		clickOn(OR.Pterms_AddPTermButton);
		PT = "PT"+ApplicationKeyword.randomAlphaNumeric(4);	 	
		setProperty("PT", PT);
		typeIn(OR.Pterms_addDesc, PT);
		setProperty("DaysForPayementTerms", "2");
		typeIn(OR.Pterms_addDays, "2");	 	 	
		setProperty("PaymentTermsPercentange", "2");
		typeIn(OR.Pterms_addperc, "3");
		clickOn(OR.Pterms_saveButton);
		waitForElementToDisplay(OR.Pterms_waitForTable, 10);
		String PT=getProperty("PT");
		typeIn(OR.Pterms_searchTextBox, PT);
		waitForElementToDisplay(OR.Pterms_waitForTable, 15);
		String paymntTermDesc=getText(OR.Pterms_firstItem);
		if(paymntTermDesc.trim().equals(PT))
		{
			testLogPass("Payment Term is added and searched with the Payement Term Description");	
		}
		else
		{
			testLogFail("Payment Term is either not added or searched with the Payement Term Description is not working");	
		}
		clearEditBox(OR.Pterms_searchTextBox);
		typeIn(OR.Pterms_searchTextBox, "2");
		List<WebElement> allAttri=driver.findElements(By.xpath("(//table[@class='table table-striped']//tbody/tr[1]/td)"));
		for(int i=1;i<=allAttri.size(); i++)
		{
			String attribute=allAttri.get(i-1).getText();
			if(attribute.trim().contains("2"))
			{
				testLogPass("Payment Term is searched with No.Of days");
				break;
			}	 		
		}
		clearEditBox(OR.Pterms_searchTextBox);
		typeIn(OR.Pterms_searchTextBox, "3");
		for(int j=1;j<=allAttri.size(); j++)
		{
			String attribute=allAttri.get(j-1).getText();
			if(attribute.trim().contains("3"))
			{
				testLogPass("Payment Term is searched with prcentage");
				break;
			}

		}
		
	}


	@Test
	public void Tc_116()
	{
		testStarts("Tc_PTerms_03_04", "Verify that user can update the corresponding payment terms using the Edit button."
				+ "Verify that user can delete the payment term using Delete button. ");		
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.InvoicePayementTermPage();
		String PT=getProperty("PT");
		typeIn(OR.Pterms_searchTextBox, PT);
		waitForElementToDisplay(OR.Pterms_waitForTable, 15);
		String paymntTermDesc=getText(OR.Pterms_firstItem);
		if(paymntTermDesc.trim().equals(PT))
		{
			clickOn(OR.Pterms_EditIcon);
			verifyElementText(OR.Pterms_headerTextEdit, "Update Payment Term");
			clickOn(OR.Pterms_closePopup);	
			clickOn(OR.Pterms_Deleteicon);
			clickOn(OR.Pterms_confirmButton);
			clickOn(OR.Pterms_OKbutton);
			waitForElementToDisplay(OR.Pterms_waitForTable, 15);
			if(!getText(OR.Pterms_firstItem).equals(PT))
			{
				testLogPass("Created Payment Term is successully Deleted");
			}
			else
			{
				testLogFail("Created Payment Term is not successully Deleted");
			}
		}
		else
		{
			testLogFail("Payment Term is either not added or searched with the Payement Term Description is not working");	
		}
		
	}

	//News Part
	@Test
	public void Tc_117()
	{
		testStarts("Tc_News_01_02", "Verify that user can search news by title using search text field and status dropdown."
				+ "Verify that User can create news using Add button. ");		
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.newsPage();
		clickOn(OR.News_addNews);
		NewsTitle = "NewsTitle"+ApplicationKeyword.randomAlphaNumeric(4);	 	
		setProperty("NewsTitle", NewsTitle);
		typeIn(OR.News_addTitle, NewsTitle);
		typeIn(OR.News_addDec, "This is a Test News");	 	
		clickOn(OR.News_saveButton);
		waitForElementToDisplay(OR.News_waitForNews, 15);
		typeIn(OR.News_searchTextBox, NewsTitle);
		clickOn(OR.News_searchButton);
		waitForElementToDisplay(OR.News_waitForNews, 15);
		compareExactText(OR.News_firstNews, "Created News is created and Searched", "Created News is not Searched", NewsTitle);
				
	}

	@Test
	public void Tc_118()
	{
		testStarts("Tc_News_03_04", "Verify that User can set the status as active/inactive using the toggle button."
				+ "Verify that news can be updated using the edit button.");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.newsPage();
		String newsAdded=getProperty("NewsTitle");
		typeIn(OR.News_searchTextBox,newsAdded);
		clickOn(OR.News_searchButton);
		waitForElementToDisplay(OR.News_waitForNews, 15);
		if(getText(OR.News_firstNews).trim().equals(newsAdded))
		{
			clickOn(OR.News_EditNews);		 				
			if(getText(OR.News_EditNewsText).equalsIgnoreCase("Edit News"))
			{
				testLogPass("Edit News Page is opened");
			}
			else
			{
				testLogPass("Edit News Page is not opened");
			}
			clickOn(OR.News_ActiveInactiveClass);
			if(getAttributeValue(OR.News_ActiveInactiveClass, "class").contains("-off"))
			{
				testLogPass("Toggle is set to InACTIVE");
				clickOn(OR.News_InActiveButtn);	
				if(getAttributeValue(OR.News_ActiveInactiveClass, "class").contains("-on"))
				{
					testLogPass("Toggle is set to ACTIVE"); 		
				}
				else
				{
					testLogFail("Toggle is set to OFF");
				}
			}
			else
			{
				testLogFail("Toggle is set to ON");
			}
		}		 	
		
	}

	//Vendor Test Cases
	@Test(priority=17)
	public void Tc_Vendor_01()
	{
		testStarts("Tc_Vendor_01", "Verify that vendor can be searched by name and the status filter.");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.vendorsPage();
		String firstVendor=getText(OR.Vendor_firstItem);
		typeIn(OR.Vendor_searchTextBox, firstVendor);
		clickOn(OR.Vendor_searchButton);
		waitForElementToDisplay(OR.Vendor_waitForVendor, 10);	
		if(getText(OR.Vendor_firstItem).equalsIgnoreCase(firstVendor)&&getText(OR.Vendor_status).equalsIgnoreCase("Active"))
		{
			testLogPass("Vendor could be searched with Name and Status");
		}
		else
		{
			testLogPass("Vendor could not be searched with Name and Status");
		}

		

	}
	@Test
	public void Tc_119()
	{
		testStarts("Tc_Vendor_02_03", "Edit Vendor > Verify that changes get saved, on clicking Save button."
				+"Verify that following tabs( Vendor, address, Interface , UOM, Customer support, Sales Representative) appear on Edit vendor pop up.");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.vendorsPage();
		clickOn(OR.Vendor_EditButton);
		waitForElementToDisplay(OR.Vendor_waitForTabs, 10);
		verifyElementText(OR.Vendor_EditVendorPopup, "Edit Vendor");
		List<String> tabs=new ArrayList<String>(Arrays.asList("Vendor", "Address" , "Interface", "UOM", "Customer Support", "Sales Representative"));
		List<WebElement> actualtabs = driver.findElements
				(By.xpath("//ul[@class='nav nav-tabs']/li"));
		if(tabs.size()==actualtabs.size())
		{
			for(int i=0; i<tabs.size(); i++)
			{	
				if(tabs.get(i).equals(actualtabs.get(i).getText()))
				{	
					testLogPass(tabs.get(i)+" Option matched with " +actualtabs.get(i).getText());					
				}
				else
				{
					testLogFail(tabs.get(i)+" Option doesnot matched with " +actualtabs.get(i).getText());					
				}
			}
		}
		else
		{
			testLogFail("No. of tabs is not equal");
		}		 	
				 	
	}
	//Price Tier Setup Test CAses


	@Test
	public void Tc_120()
	{
		testStarts("Tc_PriceTier_01_02", "Verify that user can create new price tier using Add button."
				+"Verify that user can search price tier with name.");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.priceTierPage();
		clickOn(OR.priceTier_addPriceTier);
		String Name = "PTier"+ApplicationKeyword.randomAlphaNumeric(3);	 
		setProperty("PriceTierName", Name);
		typeIn(OR.priceTier_addName, Name);
		typeIn(OR.priceTier_addpercentage, "2");
		clickOn(OR.priceTier_saveButton);
		waitForElementToDisplay(OR.priceTier_searchTextBox, 10);
		typeIn(OR.priceTier_searchTextBox, Name);
		waitForElementToDisplay(OR.priceTier_waitfor, 10);
		System.out.println(getText(OR.priceTier_firstItem));
		if(getText(OR.priceTier_firstItem).equalsIgnoreCase(Name))
		{
			testLogPass("Price Tier is created and Searched");
		}
		else
		{
			testLogFail("Price Tier is not created and Searched");
		}	 		
		
	}	

	
	@Test
	public void Tc_121()
	{
		testStarts("Tc_PriceTier_03_05", "Verify that price tier can be updated by Edit button."
				+"Verify that user can assign price tier for vendor for any facility using the Vendor Price Tier Setup button..");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.priceTierPage();
		String Name=getProperty("PriceTierName");
		typeIn(OR.priceTier_searchTextBox, Name);
		waitForElementToDisplay(OR.priceTier_waitfor, 10);
		if(getText(OR.priceTier_firstItem).equalsIgnoreCase(Name))
		{
			clickOn(OR.priceTier_EditButton);
			clearEditBox(OR.priceTier_addName);
			String changedName = "PTier"+ApplicationKeyword.randomAlphaNumeric(3);	 
			setProperty("PriceTierName", changedName);
			typeIn(OR.priceTier_addName, changedName);
			clickOn(OR.priceTier_saveButton);
			waitForElementToDisplay(OR.priceTier_waitfor, 10);
			clearEditBox(OR.priceTier_searchTextBox);
			typeIn(OR.priceTier_searchTextBox, changedName);
			waitForElementToDisplay(OR.priceTier_waitfor, 10);
			if(getText(OR.priceTier_firstItem).equalsIgnoreCase(changedName))
			{
				testLogPass("Price Tier is Edited and Searched");
			}
			else
			{
				testLogFail("Price Tier is not Edited and Searched");
			}
			clickOn(OR.priceTier_vendorSetup);
			verifyElementText(OR.priceTier_vendorPriceSetup, "Vendor Price Tier Setup");	 			
		}
		else
		{
			testLogFail("Search is not working on the Price Tier Page");
		}
		
	}

	
	@Test
	public void Tc_122()
	{
		testStarts("Tc_PriceTier_04", "Verify that user can delete price tier using the delete button.");
		//NavigateUrl(DashBoardURL);
		Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.priceTierPage();
		String Name=getProperty("PriceTierName");
		typeIn(OR.priceTier_searchTextBox, Name);
		waitForElementToDisplay(OR.priceTier_waitfor, 10);
		if(getText(OR.priceTier_firstItem).equalsIgnoreCase(Name))
		{
			clickOn(OR.priceTier_DeleteButton);
			clickOn(OR.priceTier_confirmButton);
			waitForElementToDisplay(OR.priceTier_waitfor, 10);
			clearEditBox(OR.priceTier_searchTextBox);
			typeIn(OR.priceTier_searchTextBox, Name);
			compareExactText(OR.priceTier_firstItem, "Price Tier is successfully Deleted", "Price Tier is not successfully Deleted", "No data available in table");			
		}
		else
		{
			testLogFail("Item is either not successfully searched or not added properly");
		}
		
	}

	//Item Catalog	 	
	@Test
	public void Tc_123()
	{
		testStarts("Tc_ItemCatalog_01_02", "Verify that user is able to add new item by clicking Add Item button."
				+"Verify that user can search items on the basis of Item Name, alias, MFR#, SKU crosswalk id.");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		CartPage.addNewItem();

		//With Item Description
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.ItemCatalog_searchTextBox, ItemDescription );
		clickOn(OR.ItemCatalog_searchbutton);
		waitForElementToDisplay(OR.ItemCatalog_wait, 10);
		compareExactText(OR.ItemCatalog_firstItemDesc, "Item is searched with Item Description " + ItemDescription, "Item is not searched with Description" + ItemDescription, ItemDescription);

		//search with SKU
		clearEditBox(OR.ItemCatalog_searchTextBox);
		String SkuName=getProperty("Sku");
		typeIn(OR.ItemCatalog_searchTextBox,SkuName);
		clickOn(OR.ItemCatalog_searchbutton);
		waitForElementToDisplay(OR.ItemCatalog_wait, 15);
		compareExactText(OR.ItemCatalog_firstItemSku, "Item is searched with " + SkuName, "Item is not searched with " + SkuName, SkuName);	
		clearEditBox(OR.ItemCatalog_searchTextBox);			
		//With Mfr#

		String ItemMfrNumber=getProperty("ItemMfr");
		typeIn(OR.ItemCatalog_searchTextBox,ItemMfrNumber);
		clickOn(OR.ItemCatalog_searchbutton);
		waitForElementToDisplay(OR.ItemCatalog_wait, 15);
		compareExactText(OR.ItemCatalog_firstItemMfr, "Item is searched with " + ItemMfrNumber, "Item is not searched with " + ItemMfrNumber, ItemMfrNumber);					

		// with alias Name
		clearEditBox(OR.ItemCatalog_searchTextBox);			
		String alias=getProperty("alias");
		typeIn(OR.ItemCatalog_searchTextBox,alias);
		clickOn(OR.ItemCatalog_searchbutton);
		waitForElementToDisplay(OR.ItemCatalog_wait, 15);
		compareExactText(OR.ItemCatalog_firstItemDesc, "Item is searched with AliasName" + alias, "Item is not searched with AliasName" + alias, ItemDescription);	 		
		
	}
	@Test
	public void Tc_124()
	{
		testStarts("Tc_ItemCatalog_03", "Add Item > Verify that validation message appears for blank mandatory fields or fields with invalid data, on clicking Save button.");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.itemCatalogPage();
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.ItemCatalog_searchTextBox, ItemDescription );
		clickOn(OR.ItemCatalog_searchbutton);
		waitForElementToDisplay(OR.ItemCatalog_wait, 10);
		if(getText(OR.ItemCatalog_firstItemDesc).equals(ItemDescription))
		{
			clickOn(OR.ItemCatalog_EditItem);
			clearEditBox(OR.ItemCatalog_AddItem_ItemDetails_Description);
			ItemDescription = "ItemDes"+ApplicationKeyword.randomAlphaNumeric(3);	
			setProperty("ItemDesc",ItemDescription);
			typeIn(OR.ItemCatalog_AddItem_ItemDetails_Description,ItemDescription);
			clickOn(OR.ItemCatalog_AddItem_Man_Save);
		}
		else
		{
			testLogFail("Either item was not added successfully earlier or search is not working");
		}
		clearEditBox(OR.ItemCatalog_searchTextBox);
		typeIn(OR.ItemCatalog_searchTextBox, ItemDescription );
		clickOn(OR.ItemCatalog_searchbutton);
		waitForElementToDisplay(OR.ItemCatalog_wait, 10);
		if(getText(OR.ItemCatalog_firstItemDesc).equals(ItemDescription))
		{
			testLogPass("Item is edited successfully");
		}
		else
		{
			testLogFail("Item is not edited successfully");
		}
			 	
	}

	@Test
	public void Tc_125()
	{
		testStarts("Tc_ItemCatalog_04", "Verify that user can map items to the facility using Map Facility/Update Price button. ");
		NavigateUrl(DashBoardURL);
		//Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.itemCatalogPage();
		clickOn(OR.ItemCatalog_MApFac_UpdatePriceButton);
		verifyElementText(OR.ItemCatalog_headTextEDit_Update, "Map Facility for");
		
	}

	//Inventory Locations
	@Test
	public void Tc_126()
	{
		testStarts("Tc_InventoryLoc_01_02", "Add Inventory Location > Verify that all fields are mandatory."
				+"Verify that user is able to create new inventory by clicking Add Inventory Location button.");
		//NavigateUrl(DashBoardURL);
		Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.InvLocationPage();
		clickOn(OR.InvLoc_AddINvLOc);
		if(Organisation_settingspage.ifButtonDisabled(OR.InvLoc_InactiveCreateButton))
		{
			testLogPass("Create button is disabled if all the field are not filled");	 			
		}
		else
		{
			testLogFail("Create button is not disabled if all the field are not filled");
		}
		String InvLoc = "InvLoc"+ApplicationKeyword.randomAlphaNumeric(3);	 
		setProperty("InventoryLocation", InvLoc);
		waitTime(2);
		typeIn(OR.InvLoc_addName, InvLoc);
		String userfac=getProperty("userdefaultFac");
		selectFromDropdown(OR.InvLoc_addFacility, userfac);
		waitTime(2);
		if(Organisation_settingspage.ifButtonDisabled(OR.InvLoc_InactiveCreateButton))
		{	 			
			testLogFail("Create button is not Enabled if all the field are filled");
		}
		else
		{
			testLogPass("Create button is Enabled if all the field are filled");
		}	
		clickOn(OR.InvLoc_InactiveCreateButton);
		waitForElementToDisplay(OR.InvLoc_waitfortable, 10);
		typeIn(OR.InvLoc_searchTextBox, InvLoc);
		waitForElementToDisplay(OR.InvLoc_waitfortable, 10);
		compareText(OR.InvLoc_firstInventoryLoc, "Item is searched with InvName", "Item is not searched with InvName", userfac);	 		
		
	}
	//Depends upon Tc_InventoryLoc_01_02()
	@Test
	public void Tc_127()
	{
		testStarts("Tc_InventoryLoc_01", "Edit Inventory Location >Verify that changes get saved, on clicking Save button."
				+"Verify that inventory gets deleted on clicking Delete button.");
		//NavigateUrl(DashBoardURL);
		Loginpage.OpenBrowserAndLogin();
		Organisation_settingspage.InvLocationPage();	
		WebElement favLi=Organisation_settingspage.matchFac_Inv();
		if(favLi!=null)	 		
		{	
			favLi.findElement(By.xpath(".//i[@class='fa fa-edit']")).click();
			waitTime(3);
			clearEditBox(OR.InvLoc_addName);
			//overriding the previous INvLoc
			String InvLocNew = "InvLoc"+ApplicationKeyword.randomAlphaNumeric(3);	 
			setProperty("InventoryLocation", InvLocNew);
			typeIn(OR.InvLoc_addName, InvLocNew);
			clickOn(OR.InvLoc_InactiveCreateButton);
			waitForElementToDisplay(OR.InvLoc_waitfortable, 10);
			favLi=Organisation_settingspage.matchFac_Inv();
			if(favLi!=null)	 		
			{
				testLogPass("Inventory is Edited successfully");
				favLi.findElement(By.xpath(".//*[@class='fa fa-trash-o']")).click();
				clickOn(OR.InvLoc_confirmButton);
				waitTime(3);
				//clearEditBox(OR.InvLoc_addName);
				if(Organisation_settingspage.matchFac_Inv()==null)	 		
				{
					testLogPass("Inventory is deleted successfully");		 						
				}
				else
				{
					testLogFail("Inventory is not deleted.");
				}
			}	
			else
			{
				testLogFail("Inventory is not Edited.");
			}	 			 			 			 		
		}
		else
		{
			testLogFail("Facility is not matching in which we added the inventory");
		}			 		
		
	}


	@Test
	public void Tc_128() {
		System.out.println("CameTo Dashboard");
		testStarts("Tc_Dashboard_001, Tc_Dashboard_003",
				"Verify that recent notifications appear on clicking Notification (bell) icon button.\n"
						+ "Verify that user gets redirected to �My Notifications� page, on clicking �View All� button\"");
		openBrowser(URL);
		Loginpage.login(UserName, Password);
		waitForElementToDisplay(OR.Notification_Bell, 60);
		clickOn(OR.Notification_Bell);
		Dashboardpage.NotificationCount();
	}

	@Test
	public void Tc_129() {
		testStarts("Tc_Dashboard_002",
				"Verify that user gets redirected to �Notification Settings� page, on clicking �Notification Settings� button");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		waitForElementToDisplay(OR.User_NotificationSetting, 60);
		clickOn(OR.User_NotificationSetting);
		Dashboardpage.verificationNotificationPage();
		
	}

	@Test
	public void Tc_130() {
		testStarts("Tc_Dashboard_004",
				"Verify that user is redirected to Cart page on clicking Shopping Cart icon button.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.MyCart, 60);
		MycartPage.verificationMycartPage();
	}

	@Test
	public void Tc_131() {
		testStarts("Tc_Dashboard_005",
				"Verify that user gets redirected to �Profile� page, on clicking Profile button.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		waitForElementToDisplay(OR.User_Profile, 60);
		clickOn(OR.User_Profile);
		ProfilePage.profileverification();
	}

	@Test
	public void Tc_132() {
		testStarts("Tc_Dashboard_006", "Verify that chat window opens up when user clicks on 'view chat' icon.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.Chat, 60);
		ChatPage.VerifyChatPage();
	}

	@Test
	public void Tc_133() {
		testStarts("Tc_Dashboard_007",
				"Verify that user gets redirected to user alerts page on clicking  alerts option..");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		waitForElementToDisplay(OR.User_alert, 60);
		clickOn(OR.User_alert);
		Alertpage.VerifyAlert();
	}

	@Test
	public void Tc_134() {
		testStarts("Tc_Dashboard_008",
				"Verify that �Pending Survey� pop up appears when user clicks on survey option.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		clickOn(OR.User_Survey);
		SurveyPage.VerifySurvey();
	}

	@Test
	public void Tc_135() {
		testStarts("Tc_Dashboard_009",
				"Verify that �Change Password� pop up appears when user clicks on Change Password option.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		clickOn(OR.User_ChangePassword);
		Changepasswordpage.VerifyChangePassword();
	}

	@Test
	public void Tc_136() {
		testStarts("Tc_Dashboard_010",
				"Verify that �Select user to login� screen appears when user clicks on switch user option.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		clickOn(OR.User_SwitchUser);
		SwitchUserPage.VerifySwitchUser();
	}

	@Test
	public void Tc_137() {
		testStarts("Tc_Dashboard_011",
				"Verify that user gets redirected to corresponding Report on clicking View More Reports button.");
		NavigateUrl(DashBoardURL);
		Reportspage.VerifyViewReport();
	}

	@Test
	public void Tc_138() {
		testStarts("Tc_Dashboard_012",
				"Verify that relevant data appears under Monthly Purchase Order Value, Number of Backorders and Vendor Performance sections");
		NavigateUrl(DashBoardURL);
		Dashboardpage.MonthlyReport();
		Dashboardpage.PartialReviews();
		Dashboardpage.Avgperformance();
	}

	@Test
	public void Tc_140() {
		testStarts("Tc_Dashboard_013, Tc_Dashboard_014, Tc_Dashboard_015",
				"Verify that �Recent Orders� and �News & Events� sections appear on Page.\n"
						+ "Verify that user gets redirected to corresponding news on clicking any news.\r\n"
						+ "Verify that user gets redirected to corresponding PO details on clicking any PO link under \"�Recent Orders� section,\r\n"
						+ "");
		NavigateUrl(DashBoardURL);
		Dashboardpage.PartialReviews();
		Dashboardpage.Avgperformance();
		Dashboardpage.VerifyOrders();
		driver.navigate().back();
		Dashboardpage.News();
		closeBrowser();
	}
	@Test
	public void Tc_141() {
		testStarts("Tc_Order_001", "Verify that user can search PO on basis PO#, Sku, Notes or Documents.");
		Loginpage.OpenBrowserAndLogin();
		clickOn(OR.Order);
		typeIn(OR.Order_Search_Input, "00");
		clickOn(OR.Order_PO_SearchBtn);
		typeIn(OR.Order_Search_Input, "sky464");
		clickOn(OR.Order_PO_SearchBtn);
		typeIn(OR.Order_Search_Input, "Notes");
		clickOn(OR.Order_PO_SearchBtn);
		
	}

	@Test
	public void Tc_142() {
		testStarts("Tc_Order_002",
				"Verify that following dropdown filters appear on page. Vendor, Status, Type, Facility, Department and Created By .");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Order);
		OrderPage.VerifyOrder();
		
	}

	@Test
	public void Tc_143() {
		testStarts("Tc_Order_003", "Verify that sorting works  for PO#, Requested Cost, Created At columns.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Order);
		clickOn(OR.Order_PO_Title);
		ArrayList<String> BeforeSorting = new ArrayList<String>();
		ArrayList<String> afterSorting = new ArrayList<String>();
		waitTime(10);
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

	@Test
	public void Tc_144() {
		testStarts("Tc_Order_003, Tc_Order_004, Tc_Order_014, Tc_Order_005, Tc_Order_015, Tc_Order_019",
				"Verify that 'Notes for PO #' window appears when user clicks on notes icon with count."
						+ "Verify that 'Add Note for PO #' pop up appears when user clicks on �Add New Note'"
						+ "Verify that 'Notes for PO #' window appears when user clicks on notes icon with count.");
		NavigateUrl(DashBoardURL);
		waitTime(5);
		waitForElement(OR.Order, 40);
		clickOn(OR.Order);
		String one =getText(OR.OrderNotes_PO_NotesCount);
		clickOn(OR.OrderNotes_PO_First);
		OrderPage.viewNotesForPO();
		OrderPage.AddNotes();
		waitForElement(OR.OrderNotes_PO_NotesClose, 40);
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

	@Test
	public void Tc_145() {
		testStarts("Tc_Order_006, Tc_Order_017, Tc_Order_020",
				"Verify that 'PO # XXXX11 Documents' pop up appears when clicks on Documents icon with count. ");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Order);
		clickOn(OR.OrderDoc_PO_First);
		OrderPage.viewDocumentForPO();
	}

	@Test
	public void Tc_146() {
		testStarts("Tc_Order_007, Tc_Order_018",
				"Verify that select file from computer window opens when user clicks on Upload File button.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Order);
		clickOn(OR.OrderDoc_PO_First);
		typeIn(OR.Order_PO_Doc_UploadFiles, "C:\\Users\\Acer\\Desktop\\file\\pwd.txt");
		verifyElement(OR.Order_PO_Doc_AfterUploadFiles);
		waitForElement(OR.OrderNotes_PO_NotesClose, 60);
		clickOn(OR.OrderNotes_PO_NotesClose);
	}

	@Test
	public void Tc_147() {
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
		clickOn(OR.OrderNotes_PO_NotesClose);
		
	}

	@Test
	public void Tc_148() {
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

	@Test
	public void Tc_149() {
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

	@Test
	public void Tc_150() {
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
		clickOn(OR.OrderNotes_PO_NotesClose);
		
	}

	@Test
	public void Tc_151() {
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
		closeBrowser();
	}

	@Test
	public void Tc_152() {
		testStarts("Tc_OrderDetails_001, Tc_OrderDetails_002,Tc_OrderDetails_012, Tc_OrderDetails_014",
				"Verify that �Add Note for PO #� pop up appears when user clicks on �Add New Note�"
				+"Verify that notes gets deleted when user clicks on �Delete� button for the added note.");

		Loginpage.OpenBrowserAndLogin();
		clickOn(OR.Order);
		clickOn(OR.Order_PO_ID_First);
		OrderDetailsPage.viewNotesForPO();
		OrderDetailsPage.AddNotes();
		waitForElement(OR.Order_PO_Notes_DeleteBtn);
		clickOn(OR.Order_PO_Notes_DeleteBtn);
		waitForElement(OR.Order_PO_Notes_Deletevalidation, 60);
		verifyElementText(OR.Order_PO_Notes_Deletevalidation, "Are you sure you want to delete this note?");
		clickOn(OR.Order_PO_Notes_Delete_yes);
		clickOn(OR.OrderNotes_PO_NotesClose);
	}
	
	
	@Test
	public void Tc_153() {
		testStarts("Tc_OrderDetails_003, Tc_OrderDetails_015",
				"Verify that �PO # XXXX11 Documents� pop up appears when clicks on Documents icon with count.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Order);
		clickOn(OR.Order_PO_ID_First);
		waitTime(5);
		clickOn(OR.Order_PO_Doc_Icon);
		OrderDetailsPage.viewDocumentForPO();
		clickOn(OR.OrderNotes_PO_NotesClose);
	}

	@Test
	public void Tc_154() {
		testStarts("Tc_OrderDetails_004, Tc_OrderDetails_016",
				"Verify that select file from computer window opens when user clicks on Upload File button.");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Order);
		waitTime(5);
		clickOn(OR.Order_PO_ID_First);
		waitTime(5);
		clickOn(OR.Order_PO_Doc_Icon);
		String one =System.getProperty("user.dir") + "/file.txt";
		driver.findElement(By.xpath("//*[@type='file']")).sendKeys(one);
		//typeIn(OR.Order_PO_Doc_UploadFiles, one);
		verifyElement(OR.Order_PO_Doc_AfterUploadFiles);
		clickOn(OR.OrderNotes_PO_NotesClose);
	}

	
	@Test
	public void Tc_155() {
		testStarts("Tc_OrderDetails_005","Verify that items in PO gets added to cart when user clicks on �Add Items to cart� option");
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

	@Test
	public void Tc_156() {
		testStarts("Tc_OrderDetails_006",
				"Verify that 'Print PO' preview window appears when user clicks on Print Po option in action dropdown ");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Order);
		waitForElement(OR.Order_PO_ID_First);
		waitTime(6);
		clickOn(OR.Order_PO_ID_First);
		waitTime(6);
		clickOn(OR.OrderDetails_HeaderDropdown);
		waitTime(6);
		clickOn(OR.Order_PO_first_PoLog);
		getText(OR.Order_PO_PoLogHead);
		clickOn(OR.OrderNotes_PO_NotesClose);
	}
	
	
	@Test
	public void Tc_157() {
		testStarts("Tc_OrderDetails_007","Verify that Print popup window opens on clicking Print Items button.");
		NavigateUrl(DashBoardURL);
		waitTime(6);
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
		waitTime(5);
		clickOn(OR.Order_PO_ID_First);
		waitTime(5);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_PrintItems);
		verifyElementText(OR.OrderDetails_OrderItems, "Order Items");
		clickOn(OR.OrderNotes_PO_NotesClose);
	}
	
	@Test
	public void Tc_158() {
		testStarts("Tc_OrderDetails_008","Verify that �INVOICES FOR PO #� page appears when user clicks on All Invoices option.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.MyCart);
		waitTime(4);
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
		waitTime(6);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(5);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		waitTime(6);
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
			waitTime(6);		
			clickOn(OR.Order_status_dropdown);
			clickOn(OR.Order_PO_StatusDropdown_Confirmed);
			clickOn(OR.Order_PO_SearchBtn);
			waitTime(5);
			clickOn(OR.Order_PO_first_dropdown);
			waitTime(5);
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
	
	@Test
	public void Tc_159() {
		testStarts("Tc_OrderDetails_009","Verify that �INVOICES FOR PO #� page appears when user clicks on All Invoices option.");
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
		waitTime(5);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(5);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		waitTime(5);
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
			waitTime(5);
			clickOn(OR.Order_PO_first_dropdown);
			waitTime(5);
			clickOn(OR.OrderDetails_HeaderDropdown);
			waitTime(5);
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
//		waitTime(5);
//		clickOn(OR.Order_PO_first_dropdown);
//		clickOn(OR.Order_PO_first_printPo);
//		getText(OR.Order_PO_printPo_PDF);
		
	}

	
//	@Test(groups="OrderDetails", priority=5)
//	public void Tc_OrderDetails_008d() {
//		testStarts("Tc_OrderDetails_008","Verify that Print popup window opens on clicking Print Items button.");
//		NavigateUrl(DashBoardURL);
//		
//		clickOn(OR.MyCart);
//		if(driver.findElements(By.xpath("//*[@value='name']")).size()!=0)
//		{
//			clickOn(OR.OrderDetails_Btn_GeneratePo);
//			clickOn(OR.OrderDetails_Btn_ClearAllCart);
//			clickOn(OR.OrderDetails_DeleteConfirmion);
//			verifyElementText(OR.OrderDetails_DeleteConfirmion, "Are you sure you want to clear cart?");
//			clickOn(OR.Order_PO_Notes_Delete_yes);
//			verifyElementText(OR.OrderDetails_DeleteSucessConfirmion, "Cart cleared successfully.");
//			clickOn(OR.Confirmionok);
//		}
//		
//		clickOn(OR.Order);
//		waitForElement(OR.Order_PO_ID_First);
//		clickOn(OR.Order_PO_ID_First);
//		clickOn(OR.OrderDetails_HeaderDropdown);
//		clickOn(OR.OrderDetails_PrintItems);
//		verifyElementText(OR.OrderDetails_OrderItems, "Order Items");
//		
//	}

	@Test
	public void Tc_160() {
		testStarts("Tc_OrderDetails_010",
				"Verify that �PO AUDIT LOGS FOR PO #� pop up window appears when user clicks on Po Log option in the dropdown.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(5);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		waitTime(5);
		clickOn(OR.Order_PO_first_PoLog);
		//clickOn(OR.OrderDetails_POApprovalLog);
		getText(OR.Order_PO_PoLogHead);
		clickOn(OR.OrderNotes_PO_NotesClose);
		
	}
	
	@Test
	public void Tc_161() {
		testStarts("Tc_OrderDetails_011",
				"Verify that �PO APPROVAL LOGS FOR PO # � pop up appears when user clicks on PO Approval Log option");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(5);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_POApprovalLog);
		getText(OR.Order_PO_PoLogHead);
		clickOn(OR.OrderNotes_PO_NotesClose);
		
	}
	
	@Test
	public void Tc_162() {
		testStarts("Tc_OrderDetails_012",
				"Verify that �PO APPROVAL LOGS FOR PO # � pop up appears when user clicks on PO Approval Log option");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(5);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_POApprovalLog);
		getText(OR.Order_PO_PoLogHead);
		clickOn(OR.OrderNotes_PO_NotesClose);
		
	}
	
	@Test
	public void Tc_163() {
		testStarts("Tc_OrderDetails_018","Verify that Delete PO option appears.\r\n");
		NavigateUrl(DashBoardURL);
		clickOn(OR.Order);
		waitForElement(OR.Order_PO_ID_First);
		waitTime(2);
		clickOn(OR.Order_PO_ID_First);
		waitTime(3);
		clickOn(OR.OrderDetails_HeaderDropdown);
		verifyElement(OR.OrderDetails_DeletePO);
		
	}
	
	@Test
	public void Tc_164() {
		testStarts("Tc_OrderDetails_019",
				"Verify that show tour pops appear when user clicks on show tour option in dropdown next to refresh button.");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		waitTime(5);
		waitForElement(OR.Order_status_dropdown);
		clickOn(OR.Order_status_dropdown);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(5);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		clickOn(OR.OrderDetails_HeaderDropdown);
		clickOn(OR.OrderDetails_ShowTour);
		getText(OR.Order_PO_PoLogHead);
		
	}
	

	@Test
	public void Tc_165() {
		testStarts("Tc_OrderDetails_020","Verify that �Departments� pop up appears when user clicks on Attach  department hyperlink.\r\n");
		NavigateUrl(DashBoardURL);
		
		clickOn(OR.Order);
		waitTime(5);
		waitForElement(OR.Order_status_dropdown);
		clickOn(OR.Order_status_dropdown);
		waitTime(5);
		clickOn(OR.Order_PO_StatusDropdown_Assigned);
		clickOn(OR.Order_PO_SearchBtn);
		waitTime(6);
		waitForElement(OR.Order_PO_ID_First);
		clickOn(OR.Order_PO_ID_First);
		//clickOn(OR.OrderDetails_Dept);
		//getAttributeValue(OR.OrderDetails_Dept, "title");
		waitTime(5);
	    WebElement element = driver.findElement(By.xpath("//vendor-info//*[@ng-mouseenter='fetchData();']"));
		   Actions action = new Actions(driver);
		   
		   action.moveToElement(element);

	        
	    JavascriptExecutor jse = (JavascriptExecutor)driver;

	    //jse.executeScript("return arguments[0].text", element)
	    
		System.out.println("Hari --------->"+jse.executeScript("return arguments[0].text", element));
	    //getText(OR.Order_PO_PoLogHead);
		
		
	}
		
		@Test
		public void Tc_166() {
			testStarts("Tc_OrderDetails_021","Verify that �Physicians� pop up appears when user clicks on �Attach Physician� hyperlink.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Order);
			waitForElement(OR.Order_status_dropdown);
			waitTime(5);
			clickOn(OR.Order_status_dropdown);
			waitTime(5);
			clickOn(OR.Order_PO_StatusDropdown_Assigned);
			clickOn(OR.Order_PO_SearchBtn);
			waitTime(7);
			waitForElement(OR.Order_PO_ID_First);
			clickOn(OR.Order_PO_ID_First);
			verifyElement(OR.OrderDetails_Physision);
			verifyElement(OR.OrderDetails_Dept1);
			
		}
		
		@Test
		public void Tc_167() {
			testStarts("Tc_OrderDetails_022","Verify that Order detail screen gets closed when user clicks on Close option.");
			NavigateUrl(DashBoardURL);
			
			clickOn(OR.Order);
			waitTime(5);
			waitForElement(OR.Order_status_dropdown);
			clickOn(OR.Order_status_dropdown);
			clickOn(OR.Order_PO_StatusDropdown_Assigned);
			clickOn(OR.Order_PO_SearchBtn);
			waitTime(5);
			waitForElement(OR.Order_PO_ID_First);
			clickOn(OR.Order_PO_ID_First);
			clickOn(OR.OrderDetails_Close);
			waitTime(5);;
			verifyElement(OR.OrderDetails_MyOrderTxt);
			closeBrowser();
		}


		@Test
		public void Tc_168() {
			testStarts("Tc_Template_001, Tc_Template_002,Tc_Template_005, Tc_Template_006, Tc_Template_008","Verify that user is able to add template by clicking Add Template button.");
			//NavigateUrl(DashBoardURL);
			Loginpage.OpenBrowserAndLogin();
			clickOn(OR.Templates);
			waitTime(5);
			Template.AddDeleteScheduleTemplate();
			closeBrowser();
			}
		
		@Test
		public void Tc_169() {
			testStarts("Tc_Template_003","Verify that template can be used by every user if �Yes� is selected in toggle button");
			Loginpage.OpenBrowserAndLogin();
			clickOn(OR.Templates);
			waitTime(5);
			Template.AddTemplate();
			isElementDisplayed(OR.Template_ShareYes);
			isElementDisplayed(OR.Template_ShareNo);
		}
		
		@Test
		public void Tc_170() {
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

		@Test
		public void Tc_171() {
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
			closeBrowser();
		}
		
		@Test
		public void Tc_172() {
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

	@AfterTest
	public void endReport() {
		// 
		extent.flush();
	}
}
