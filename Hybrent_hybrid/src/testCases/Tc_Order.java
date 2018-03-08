package testCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Loginpage;
import pageObject.MycartPage;
import pageObject.OrderPage;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;
import AutomationFramework.Generickeywords;

public class Tc_Order extends ApplicationKeyword{
	
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

			extent = new ExtentReports(OutputDirectory+"/order.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}


	@Test(groups="Order", priority=1)
	public void Tc_Order_001() {
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

	@Test(groups="Order", priority=5)
	public void Tc_Order_005() {
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
		typeIn(OR.Order_PO_Doc_UploadFiles, "C:\\Users\\Acer\\Desktop\\file\\pwd.txt");
		verifyElement(OR.Order_PO_Doc_AfterUploadFiles);
		waitForElement(OR.OrderNotes_PO_NotesClose, 60);
		clickOn(OR.OrderNotes_PO_NotesClose);
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
		clickOn(OR.OrderNotes_PO_NotesClose);
		
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
		clickOn(OR.OrderNotes_PO_NotesClose);
		
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

	@AfterTest
	public void endReport(){
		closeBrowser();
		extent.flush();
	}
}


