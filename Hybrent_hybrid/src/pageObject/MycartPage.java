package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;
import AutomationFramework.Generickeywords;


public class MycartPage extends ApplicationKeyword{

	public static void verificationMycartPage()
	{
		clickOn(OR.MyCart);
		getText(OR.MyCart_cartFor);
		getText(OR.MyCart_cartFor_Details);
		verifyPageTitle("My Cart");
	}


	public static boolean boolcheckCartIsEmpty()
	{

		if(isElementDisplayed(OR.MyCart_drillDown, 15))
		{
			return false;
		}

		return true;

	}

	public static void checkIfCartIsEmpty()
	{	
		if(isElementDisplayed(OR.MyCart_drillDownDiv, 15))
		{
			clickOn(OR.MyCart_drillDown);
			//			//clickOn(OR.MyCart_clearCurrentCart);
			clickOn(OR.MyCart_clearAllCarts2);
			clickOn(OR.MyCart_yesInPopup);
			clickOn(OR.MyCart_OKInPopup);
		}
		else
		{
			testLogPass("NO item is present in Any cart");	
		}
	}

	public static void searchItem()
	{	
		String ItemDescription=getProperty("ItemDesc");
		typeIn(OR.MyCart_searchInCart, ItemDescription );
		waitForElementToDisplay(OR.MyCart_firstItemName, 15);
		String ItemName=getText(OR.MyCart_firstItemName);
		System.out.println(ItemName);
		waitForElementToDisplay(OR.MyCart_firstItemName, 15);
		if(ItemName.equalsIgnoreCase(ItemDescription))
		{
			testLogPass("Item is searched with " + ItemName);
		}
		else
		{
			testLogFail("Item is not searched with " + ItemName);			
		}
		clearEditBox(OR.MyCart_searchInCart);
		waitForElementToDisplay(OR.MyCart_searchInCart, 15);
		String SkuName=getProperty("Sku");
		typeIn(OR.MyCart_searchInCart,SkuName);
		waitForElementToDisplay(OR.MyCart_searchInCart, 15);
		String getskuName=getText(OR.MyCart_firstItemSkuName);
		System.out.println(getskuName);
		waitForElementToDisplay(OR.MyCart_firstItemName, 15);
		if(getskuName.equalsIgnoreCase(SkuName))
		{
			testLogPass("Item is searched with " + SkuName);
		}
		else
		{
			testLogFail("Item is not searched with " + SkuName);			
		}	
		clearEditBox(OR.MyCart_searchInCart);
		waitForElementToDisplay(OR.MyCart_searchInCart, 15);
		String ItemMfrNumber=getProperty("ItemMfr");
		typeIn(OR.MyCart_searchInCart,ItemMfrNumber);
		waitForElementToDisplay(OR.MyCart_searchInCart, 15);
		if(ItemName.equalsIgnoreCase(ItemDescription))
		{
			testLogPass("Item is searched with " + ItemMfrNumber);
		}
		else
		{
			testLogFail("Item is not searched with " + ItemMfrNumber);			
		}		

	}

	public static void showTourPopUP()
	{

		clickOn(OR.MyCart_drillDown);
		clickOn(OR.MyCart_showTourButton);
		String s=getText(OR.MyCart_showtourtextONPOPUP);	
	}

	public static void chkMan_level(String mfrToreterive)
	{	
		if(isElementDisplayed(OR.ItemCatalog_AddItem_ItemDetails_MfrDetails, 10))
		{
			String mfr=getProperty(mfrToreterive);
			typeIn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetails, mfr);
			clickOn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetailsDropdown);
			clickOn(OR.ItemCatalog_AddItem_Man_Select);
		}
	}

	public static String getFac()
	{
		clickOn(OR.Shop_Menu);		
		waitForElementToDisplay(OR.Shop_wait, 10);
		String facility_Name=getText(OR.Shop_SHopfor_getfacilityName);
		return facility_Name;
	}
	
	public static void matchFac(String facName)
	{	
	
	clickOn(OR.Shop_SHopfor_ShopfaclitySelect);
	waitForElementToDisplay(OR.Shop_SHopfor_Shopfaclity, 60);
	verifyElementText(OR.Shop_SHopfor_Shopfaclity, "Select Facility");
	waitForElementToDisplay(OR.Shop_countoffacilities, 60);
	int one = driver.findElements(By.xpath("//*[@style='border-right: none;vertical-align: middle;']")).size();
	boolean facFound=false;
	String xpath;
	String selectedFacility;
	WebElement btn;
	for(int i=1; i<=one; i++)
	{
		xpath="(//table[@class='table table-responsive table-striped table-bordered']/tbody/tr["+i+"]";
		selectedFacility=driver.findElement(By.xpath(xpath+"/td)")).getText();
		//System.out.println(selectedFacility);
		if(selectedFacility.equals(facName))
		{  
			facFound=true;
			btn= driver.findElement(By.xpath(xpath+"/td[2]/div/button)"));
			if(btn.getAttribute("disabled")!=null)
			{
				testLogPass("Go the text '"+selectedFacility+ "' Matches with selected Facility" );
			}
			else
			{
				testLogFail("'" + facName + "' is not selected in popup." );
			}
			break;
		}
	}
	if(!facFound)
	{
		testLogFail("Could not Got the text '"+facName+ "' Matches with selected Facility" );
	}
	clickOn(OR.Shop_SHopfor_cancelButtonPopup);
	}
}
