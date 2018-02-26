package testCases;

import java.io.File;
import java.util.ArrayList;
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
import pageObject.ReceivePageObject;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class ReceiveDetails extends ApplicationKeyword
{
	
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/ReceiveDetails.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Harikrishnan");
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public void Tc_RECDET_01()
	{
		testStarts("Tc_RECDET_01", "Verify that �Notes for PO #� window appears when user clicks on notes icon with count.");
		Loginpage.OpenBrowserAndLogin();		
		ReceivePageObject.pageLinkandwait();
		//ReceivePageObject.selectByDefaultFacility();
		waitForElementToDisplay(OR.Receive_firstPONum, 10);
		clickOn(OR.Receive_firstPONum);
		waitForElementToDisplay(OR.Receive_NotesLinkPODetail, 10);
		clickOn(OR.Receive_NotesLinkPODetail);
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Notes for PO # ");
		
	}
	
	@Test(priority=2)
	public void Tc_RECDET_02()
	{
		testStarts("Tc_RECDET_02", "Verify that �Add Note for PO #� pop up appears when user clicks on �Add New Note�");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();
		waitForElementToDisplay(OR.Receive_firstPONum, 10);
		clickOn(OR.Receive_firstPONum);
		waitForElementToDisplay(OR.Receive_NotesLinkPODetail, 10);
		clickOn(OR.Receive_NotesLinkPODetail);
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Notes for PO # ");
		clickOn(OR.Receive_AddNewNotesLink);
		waitForElementToDisplay(OR.Receive_NotesLinkText, 10);
		verifyElementText(OR.Receive_NotesLinkText, "Add Note for PO # ");
		
	}
	
	@Test(priority=3)
	public void Tc_RECDET_03_04() throws InterruptedException
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
		
	}	
	@Test(priority=4)
	public void Tc_RECDET_05() 
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

	@Test(priority=5)
	public void Tc_RECDET_06()
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
		
	}
	
	@Test(priority=6)
	public void Tc_RECDET_07() 
	{
		testStarts("Tc_RECDET_07()", "Verify that �Print Window� appears on clicking Print button..");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();
		waitForElementToDisplay(OR.Receive_firstPONum,10);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		clickOn(OR.Receive_printItemsDetailPage);
		verifyElementText(OR.Receive_printPOText, "Order Items");
		
	
	}
	
	@Test(priority=8)
	public void Tc_RECDET_08() 
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
	@Test(priority=9)
	public void Tc_RECDET_09() 
	{
		testStarts("Tc__RECDET_9()", "Verify that �INVOICES FOR PO #� page appears when user clicks on All Invoices option.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_firstPONum,10);
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
	
	@Test(priority=10)
	public void Tc_RECDET_10() {
		testStarts("Tc_RECDET_10",
				"Verify that �PO AUDIT LOGS FOR PO #� pop up window appears when user clicks on Po Log option in the dropdown.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_firstPONum,10);
		clickOn(OR.Receive_firstPONum);		
		clickOn(OR.Receive_DrillDownPODetailPage);
		waitForElementToDisplay(OR.Receive_POLogDetailPage, 10);
		clickOn(OR.Receive_POLogDetailPage);	
		waitForElementToDisplay(OR.Receive_POLOgText,10);
		verifyElementText(OR.Receive_POLOgText, "PO AUDIT LOGS FOR PO #");		
				
	}
	
	@Test(priority=11)
	public void Tc_RECDET_11() {
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
				
	}
	
	@Test(priority=12)
	public void Tc_RECDET_12() {
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
				
	}
	@Test(priority=13)
	public void Tc_RECDET_13() {
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
				
	}
	
	
	@Test(priority=14)
	public void Tc_RECDET_14() {
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
		String testNote="TextNote";
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
				
	}
		
	
	@Test(priority=15)
	public void Tc_RECDET_15_16() throws InterruptedException
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
		elem.sendKeys(projectPath + "\\assets\\images.jpeg");
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
		
	}
	
	
	@Test(priority=16)
	public void Tc_RECDET_17() {
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
	
	@Test(priority=17)
	public void Tc_RECDET_19() {
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
		//System.out.println(getText(OR.Receive_NextButton));
		//System.out.println(getText(OR.Receive_EndButton));
		
	}
	
	@Test(priority=18)
	public void Tc_RECDET_20() {
		testStarts("Tc_RECDET_20",
				"Verify that �Departments� pop up appears when user clicks on Attach department hyperlink.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		verifyElementText(OR.Receive_Depatment, "Department:");
		
	}
	
	@Test(priority=19)
	public void Tc_RECDET_21() {
		testStarts("Tc_RECDET_21",
				"Verify that �Physicians� pop up appears when user clicks on �Attach Physician� hyperlink.");
		NavigateUrl(DashBoardURL);	
		ReceivePageObject.pageLinkandwait();		
		waitForElementToDisplay(OR.Receive_DrillDownIconwait,20);
		clickOn(OR.Receive_firstPONum);		
		verifyElementText(OR.Receive_Physician, "Physician:");
		
	}
	
	@Test(priority=20)
	public void Tc_RECDET_22_23() throws InterruptedException 
	{
		testStarts("Tc_RECDET_22_23",
				"Verify that Partial Receive and Complete buttons appear for corresponding PO number whose status is Confirmed/Assigned/Partial Received. and "
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
		
	}
	
	
	@AfterTest
	public void endReport(){
		closeBrowser();
		extent.flush();
	}

	
	
	
	
}
