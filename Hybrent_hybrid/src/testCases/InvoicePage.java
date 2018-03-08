package testCases;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Invoicespage;
import pageObject.Loginpage;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class InvoicePage extends ApplicationKeyword
{
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Invoice.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
			
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}

	@Test
	public void Tc_INV_01() throws InterruptedException
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
	@Test(priority=2)
	public void Tc_INV_09_10() throws InterruptedException
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
	
	@Test(priority=3)
	public void Tc_INV_02() throws InterruptedException
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
	
	@Test(priority=4)
	public void Tc_INV_03() throws InterruptedException
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
	
	@Test(priority=5)
	public void Tc_INV_04()
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
		
	}
	@AfterTest
	public void endReport(){
		closeBrowser();
		extent.flush();
	}		
}		
		

		

		
		
	
		

