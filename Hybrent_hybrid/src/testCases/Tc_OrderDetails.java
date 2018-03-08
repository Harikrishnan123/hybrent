package testCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Loginpage;
import pageObject.MycartPage;
import pageObject.OrderDetailsPage;
import pageObject.OrderPage;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;
import AutomationFramework.Generickeywords;

public class Tc_OrderDetails extends ApplicationKeyword{
	
	public static String facility_Name;
	public static String vendor_Name;	
	public static String firstCategory;
	public static String ItemDescription ;
	public static String ItemMfrNumber;
	public static String ItemTestID ;
	public static String SkuName;
	public static String aliasName;


	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/orderDetails.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}

	
	@Test(groups="OrderDetails", priority=1)
	public void Tc_OrderDetails_002() {
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
	
	
	@Test(groups="OrderDetails", priority=2)
	public void Tc_OrderDetails_003() {
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

	@Test(groups="OrderDetails", priority=3)
	public void Tc_OrderDetails_004() {
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

	
	@Test(groups="OrderDetails", priority=4)
	public void Tc_OrderDetails_005() {
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

	@Test(groups="OrderDetails", priority=5)
	public void Tc_OrderDetails_006() {
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
	
	
	@Test(groups="OrderDetails", priority=6)
	public void Tc_OrderDetails_007() {
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
	
	@Test(groups="OrderDetails", priority=7)
	public void Tc_OrderDetails_008() {
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
	
	@Test(groups="OrderDetails", priority=8)
	public void Tc_OrderDetails_009() {
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

	@Test(groups="OrderDetails", priority=9)
	public void Tc_OrderDetails_010() {
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
	
	@Test(groups="OrderDetails", priority=10)
	public void Tc_OrderDetails_011() {
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
	
	@Test(groups="OrderDetails", priority=11)
	public void Tc_OrderDetails_012() {
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
	
	@Test(groups="OrderDetails", priority=12)
	public void Tc_OrderDetails_018() {
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
	
	@Test(groups="OrderDetails", priority=13)
	public void Tc_OrderDetails_019() {
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
	

	@Test(groups="OrderDetails", priority=14)
	public void Tc_OrderDetails_020() {
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
		
		@Test(groups="OrderDetails", priority=15)
		public void Tc_OrderDetails_021() {
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
		
		@Test(groups="OrderDetails", priority=16)
		public void Tc_OrderDetails_022() {
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
			
		}

	@AfterTest
	public void endReport(){
		closeBrowser();
		extent.flush();
	}
}


