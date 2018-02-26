package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Itempage extends ApplicationKeyword {
	
	public static String UserName = "admin";
    public static String Password = "goouser";
    public static String URL = "https://qa4.test.hybrent.com/b/#/login/";
	
    public static void FirstItemDropdownPriceHistory()
    {
    	verifyElementText(OR.Shop_ItemNameDropDownPricechangeHistory_First, "Price Change History");
    	clickOn(OR.Shop_ItemNameDropDownPricechangeHistory_First);
    	verifyElementText(OR.PriceChangeDetails_text, "Price Change Details");
    	verifyElementText(OR.PriceChangeDetails_current_text, "Current Price");
    	clickOn(OR.PriceChangeDetails_Close);
    }
    
    
    public static void FirstItemDropdownEdit()
    {
    	getText(OR.Shop_ItemNameDropDownEdit_First);
    	verifyElementText(OR.Shop_ItemNameDropDownEdit_First, "Edit");
    	clickOn(OR.Shop_ItemNameDropDownEdit_First);
    	verifyElementText(OR.EditItem_text, "Edit Item");
    	verifyElementText(OR.ItemCatalog_AddPage_ItemDetails, "Item Detail");
    	clickOn(OR.ItemCatalog_AddItem_Map_Closepage);
    }
   
    
    public static void FirstItemDropdownPurchaseHistory()
    {
    	verifyElementText(OR.Shop_ItemNameDropDownPurchaceHistory_First, "Purchase History");
    	clickOn(OR.Shop_ItemNameDropDownPurchaceHistory_First);
    	verifyElementText(OR.purchaseHistory_itemPurchace_text, "Item Purchase History");
    	clickOn(OR.PriceChangeDetails_Close);
    }
   
    
    public static void AddItemPage()
    {
        verifyElementText(OR.ItemCatalog_AddPage_AddItemText, "Add Item");
        verifyElementText(OR.ItemCatalog_AddPage_ItemDetails, "Item Detail");



        verifyElementText(OR.ItemCatalog_Vendors, "Vendors");
        verifyElementText(OR.ItemCatalog_Vendors, "Vendors");
        verifyElementText(OR.Profile_Label_phone, "Phone");
        verifyElementText(OR.Profile_Label_Photo, "Photo:");
        verifyElementText(OR.Profile_Label_Facility, "Facility");
        verifyElementText(OR.Profile_Label_Dept, "Department:");
        verifyElementText(OR.Profile_Label_Inventory, "Inventory");
        verifyElementText(OR.Profile_Label_Journeydate, "Joining Date:");
        verifyElementText(OR.Profile_Label_QuickBook, "Quick Books:");
        verifyPageTitle("User Profile");
    }
    
    public static void invalidValidation()
    {
        clickOn(OR.ItemCatalog_AddItem);
        typeIn(OR.ItemCatalog_AddItem_ItemDetails_Description, "Hari-Test");
        typeIn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetails, "mrf-Testing-101");
        clickOn(OR.ItemCatalog_AddItem_ItemDetails_MfrDetailsDropdown);
        clickOn(OR.ItemCatalog_AddItem_Man_Select);
        clickOn(OR.ItemCatalog_AddItem_Man_Save);
    }
    
    

}
