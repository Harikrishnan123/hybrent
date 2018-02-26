package pageObject;

import org.openqa.selenium.By;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Shipmentpage extends ApplicationKeyword{
    public static void InitialSteps()
    {
		waitForElementToDisplay(OR.Shop_Menu, 60); 
		clickOn(OR.Shipment_PageLink);
		verifyPageTitle("Shipment");
    }
    
    public static void searchAndWait()
    {
    	clickOn(OR.Shipment_SubmitButton);
    	//waitForElementToDisplay(OR.Shipment_FirstShipmentNum, 60);
    	waitForElementToDisplay(OR.Shipment_wait,20);
    }
    public static void clearAndWait()
    {
    	clearEditBox(OR.Shipment_SearchTextBox);
    	waitForElementToDisplay(OR.Shipment_FirstShipmentNum, 60);
    }
    public static void receiveTextandCloseButton()
    {
    	//clickOn(OR.Shipment_FirstShipmentNum);
    	//
    	//{
    	 	//System.out.println(getText(OR.Shipment_ReceiveButtonText));
    	 //	testLogInfo("Element is present");    		
    	//}
    	
    	verifyElementText(OR.Shipment_ReceiveButtonText, "Receive");
		clickOn(OR.Shipment_CloseButton);
    }

}
