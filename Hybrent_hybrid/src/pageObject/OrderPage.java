package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class OrderPage extends ApplicationKeyword{
    
	public static void VerifyOrder()
    {
		waitForElementToDisplay(OR.Order_Myorder_txt, 60);
    	verifyElementText(OR.Order_Myorder_txt, "MY ORDERS");
    	verifyElementText(OR.Order_Vendor_txt, "Vendor:");
    	verifyElement(OR.Order_Vendor_dropdown);
    	verifyElementText(OR.Order_status_txt, "Status:");
    	verifyElement(OR.Order_status_dropdown);
    	verifyElementText(OR.Order_type_txt, "Type:");
    	verifyElement(OR.Order_type_dropdown);
    	verifyElementText(OR.Order_facility_txt, "Facility:");
    	verifyElement(OR.Order_facility_dropdown);
    	verifyElementText(OR.Order_creater_txt, "Created By:");
    	verifyElement(OR.Order_creater_dropdown);
    }
	
	public static void viewNotesForPO()
	{
		getText(OR.Order_PO_Header);
		clickOn(OR.Order_PO_Notes_AddIcon_btn);
		getText(OR.Order_PO_Notes_HeaderTxt);
		verifyElement(OR.Order_PO_Notes_AddBtn);
	}
	
	public static void AddNotes()
	{
		clickOn(OR.Order_PO_Notes_AddIcon_btn);
		getText(OR.Order_PO_Notes_HeaderTxt);
		verifyElement(OR.Order_PO_Notes_AddBtn);
		verifyElement(OR.Order_PO_Notes_CloseBtn);
		getText(OR.Order_PO_Notes_Add_HeaderTxt);
		typeIn(OR.Order_PO_Notes_Input,"Test");
		clickOn(OR.Order_PO_Notes_AddBtn);
		
	}
	
	public static void viewDocumentForPO()
	{
		getText(OR.Order_PO_Doc_HeaderTxt);
		verifyElement(OR.Order_PO_Doc_UploadFile);
		verifyElement(OR.Order_PO_Doc_CloseBtn);
		
	}
	
	public static void LogPo()
	{
		getText(OR.Order_PO_PoLogHead);
		
	}
}

