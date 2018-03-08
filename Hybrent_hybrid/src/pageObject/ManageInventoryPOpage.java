package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;
//contains javascript function
public class ManageInventoryPOpage extends ApplicationKeyword
{
	public static void PageLinkAndWait(String pagepartialTitle, String fullLink)
    {
		JavascriptExecutor je = (JavascriptExecutor) driver;		
		WebElement element = driver.findElement(By.xpath("//a[@href='#/"+pagepartialTitle+"']"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);
		clickOn(fullLink);
		//waitForElementToDisplay(OR.manageInv_firstItemDesc, 10);
		
    }
	public static String getDefaultFac()
    {
		clickOn(OR.User);
		clickOn(OR.User_Profile);
		String fac=getText(OR.Profile_defaultFacility);
		setProperty("userdefaultFac", fac);
		return fac;
		
    }
//	public static void checkDef_Fac()
//    {		
//	String fac=getDefaultFac();
//	String actualFac=getText(OR.manageInv_selectedFac);
//	System.out.println(actualFac);	
//	if(!fac.equals(actualFac))
//	{
//		clickOn(OR.manageInv_selectedFac);
//		int one = driver.findElements(By.xpath("//table[@class='table table-responsive table-striped table-bordered']//td[@class='ng-binding']")).size();
//		System.out.println(one);
//		String xpath;
//		String selectedFacility;
//		for(int i=1; i<=one; i++)
//		{
//			xpath="(//table[@class='table table-responsive table-striped table-bordered']/tbody/tr["+i+"]";
//			selectedFacility=driver.findElement(By.xpath(xpath+"/td)")).getText();               
//			System.out.println(selectedFacility);
//			if(selectedFacility.equals(fac))
//			{  
//				clickOn(OR.manageInv_selectedButton);
//				waitTime(4);
//				testLogInfo("Changed the facility to Default one in the DropDown");
//			}
//			else
//			{
//				testLogFail("Could not find the Default facility in the DropDown");
//			}
//		}
//	}
//	else
//	{
//		testLogInfo("Default facility of user is already selected on this page");
//	}
//	
//    }
	
	public static void checkDef_Fac(String facLocator, String pageLink, String pagepartialTitle, String fullLink)
	{		
		String fac=getDefaultFac();
		System.out.println("FAC---"+fac);
		PageLinkAndWait(pagepartialTitle, fullLink);
		//PageLinkAndWait("inventory", OR.manageInv_pageLink);
		waitTime(3);
		String actualFac=getText(facLocator);	
		System.out.println("ACTUAL----"+actualFac);	
		if(!fac.equalsIgnoreCase(actualFac))
		{
			testLogFail("Default facility for this user is not selected.");
		}
		else
		{
			testLogPass("Default facility of user is already selected on this page");
		}

	}
		
	public static void addItemInInventory()
	{
		ManageInventoryPOpage.checkDef_Fac(OR.manageInv_actaulFac, OR.manageInv_pageLink, "inventory", OR.manageInv_pageLink);
		String itemDesc=getProperty("ItemDesc");
		typeIn(OR.manageInv_searchTextBox, itemDesc);
		clickOn(OR.manageInv_searchButton);
		waitForElementToDisplay(OR.manageInv_wait, 10);
		clickOn(OR.manageInv_editItem);
		clickOn(OR.ItemCatalog_VendorsTab);
		//clearEditBox(OR.ItemCatalog_gpo);
		typeIn(OR.ItemCatalog_gpo, "3");
		clickOn(OR.manageInv_InventoryTab);
		List<WebElement> checkBoxes=driver.findElements(By.xpath("//*[contains(@id,'tbl_add_vendor')]/tbody[2]//input[@type='checkbox']"));		
		System.out.println("CHK SIZE----"+checkBoxes.size());
		for(WebElement chk:checkBoxes)
		{
			chk.click();
			waitTime(2);
			int i=ApplicationKeyword.generateRandomInt(10);
			String s=String.valueOf(i);
			chk.findElement(By.xpath("../..//input[@ng-model='inventory.qoh']")).clear();
			chk.findElement(By.xpath("../..//input[@ng-model='inventory.qoh']")).sendKeys(s);
			waitTime(1);
		}
		clickOn(OR.manageInv_SaveButton);
		waitTime(2);
	}
}
