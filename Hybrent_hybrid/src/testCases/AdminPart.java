package testCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Loginpage;
import pageObject.ManageInventoryPOpage;
import pageObject.Organisation_settingspage;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class AdminPart  extends ApplicationKeyword
{
	public static Properties prop;
	public static String receive_facility_Name;
	public static String vendor_Name;
	public static String ItemDescription = "ItemDesTest001";
	public static String ItemMfrNumber = "ItemMfrNumber001";
	public static String ItemTestID = "ItemTestID001";
	public static String SkuName = "sku001";
	public static String ItemCatName;
	public static String SI;
	public static String PT;
	public static String Days;
	public static String Percentage;
	public static String NewsTitle;
	public static String NewsDesc;

	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Admin.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Harikrishnan");
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	//ITEM CATEGORIES
	@Test(priority=1)
	public void Tc_ItemCat_01_02_03()
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


	@Test(priority=2)
	public void Tc_ItemCat_04()
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

	@Test(priority=3)
	public void Tc_PrintBarcode_01_02()
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

	@Test(priority=4)
	//Will work on newly added item only. 
	public void Tc_PrintBarcode_03()
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

	@Test(priority=5)
	public void Tc_PrintBarcode_04()
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
	@Test(priority=6)
	public void Tc_PrintBarcode_06()
	{
		testStarts("Tc_PrintBarcode_06", "Verify that �Print items Barcode/QRcode� pop up appears with print with options.");		
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndPrintBarcodeLink();
		clickOn(OR.PrintBarcodes_PrintCodesButton);
		verifyElementText(OR.PrintBarcodes_PrintCodesPopUp, "Print items Barcode/QRcode");
		

	}
	@Test(priority=7)
	public void Tc_PrintBarcode_07()
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
	@Test(priority=8)
	public void Tc_PrintBarcode_08()
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
	@Test(priority=9)
	public void Tc_PrintBarcode_10()
	{
		testStarts("Tc_PrintBarcode_10", "Verify that user can print items on all pages or can select specific page numbers. ");		
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.AdminAndPrintBarcodeLink();
		clickOn(OR.PrintBarcodes_PrintCodesButton);
		clickOn(OR.PrintBarcodes_PrintButton);
		waitForElementToDisplay(OR.PrintBarcodes_PreviewPDFTEXT, 50);
		verifyElementText(OR.PrintBarcodes_PreviewPDFTEXT, "Preview PDF");
		
	}

	//PO Special Instructions

	@Test(priority=10)
	public void Tc_SI_01_02()
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
	@Test(priority=11)
	public void Tc_SI_03()
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

	@Test(priority=12)
	public void Tc_SI_04()
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
	@Test(priority=13)
	public void Tc_PTerms_01_02()
	{
		testStarts("Tc_PTerms_01_02", "Verify that can add payment term using �Add Payment Term� button."
				+ "+Verify that user can search payment terms with description, type, no. ,of days and discount/penalty percentage using the search text field.");		
		NavigateUrl(DashBoardURL);
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


	@Test(priority=14)
	public void Tc_PTerms_03_04()
	{
		testStarts("Tc_PTerms_03_04", "Verify that user can update the corresponding payment terms using the Edit button."
				+ "Verify that user can delete the payment term using Delete button. ");		
		NavigateUrl(DashBoardURL);
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
	@Test(priority=15)
	public void Tc_News_01_02()
	{
		testStarts("Tc_News_01_02", "Verify that user can search news by title using search text field and status dropdown."
				+ "Verify that User can create news using Add button. ");		
		NavigateUrl(DashBoardURL);
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

	@Test(priority=16)
	public void Tc_News_03_04()
	{
		testStarts("Tc_News_03_04", "Verify that User can set the status as active/inactive using the toggle button."
				+ "Verify that news can be updated using the edit button.");
		NavigateUrl(DashBoardURL);
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
	@Test(priority=18)
	public void Tc_Vendor_02_03()
	{
		testStarts("Tc_Vendor_02_03", "Edit Vendor > Verify that changes get saved, on clicking Save button."
				+"Verify that following tabs( Vendor, address, Interface , UOM, Customer support, Sales Representative) appear on Edit vendor pop up.");
		NavigateUrl(DashBoardURL);
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


	@Test(priority=19)
	public void Tc_PriceTier_01_02()
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

	@Test(priority=20)
	public void Tc_PriceTier_03_05()
	{
		testStarts("Tc_PriceTier_03_05", "Verify that price tier can be updated by Edit button."
				+"Verify that user can assign price tier for vendor for any facility using the Vendor Price Tier Setup button..");
		NavigateUrl(DashBoardURL);
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

	@Test(priority=21)
	public void Tc_PriceTier_04()
	{
		testStarts("Tc_PriceTier_04", "Verify that user can delete price tier using the delete button.");
		NavigateUrl(DashBoardURL);
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
	@Test(priority=22)
	public void Tc_ItemCatalog_01_02()
	{
		testStarts("Tc_ItemCatalog_01_02", "Verify that user is able to add new item by clicking Add Item button."
				+"Verify that user can search items on the basis of Item Name, alias, MFR#, SKU crosswalk id.");
		NavigateUrl(DashBoardURL);
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
	@Test(priority=23)
	public void Tc_ItemCatalog_03()
	{
		testStarts("Tc_ItemCatalog_03", "Add Item > Verify that validation message appears for blank mandatory fields or fields with invalid data, on clicking Save button.");
		NavigateUrl(DashBoardURL);
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

	@Test(priority=24)
	public void Tc_ItemCatalog_04()
	{
		testStarts("Tc_ItemCatalog_04", "Verify that user can map items to the facility using Map Facility/Update Price button. ");
		NavigateUrl(DashBoardURL);
		Organisation_settingspage.itemCatalogPage();
		clickOn(OR.ItemCatalog_MApFac_UpdatePriceButton);
		verifyElementText(OR.ItemCatalog_headTextEDit_Update, "Map Facility for");
		
	}

	//Inventory Locations
	@Test(priority=25)
	public void Tc_InventoryLoc_01_02()
	{
		testStarts("Tc_InventoryLoc_01_02", "Add Inventory Location > Verify that all fields are mandatory."
				+"Verify that user is able to create new inventory by clicking Add Inventory Location button.");
		NavigateUrl(DashBoardURL);
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
	@Test(priority=26)
	public void Tc_InventoryLoc_04_05()
	{
		testStarts("Tc_InventoryLoc_01", "Edit Inventory Location >Verify that changes get saved, on clicking Save button."
				+"Verify that inventory gets deleted on clicking Delete button.");
		NavigateUrl(DashBoardURL);
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

	@AfterTest
	public void endReport()
	{
		closeBrowser();
		extent.flush();
	}



}
