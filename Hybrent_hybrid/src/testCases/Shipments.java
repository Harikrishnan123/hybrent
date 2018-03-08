package testCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import pageObject.Loginpage;
import pageObject.Shipmentpage;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Shipments  extends ApplicationKeyword
{
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/Shipments.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(priority=1)
	public void Tc_SH_01() throws InterruptedException
	{
		testStarts("Tc_SPO_01", "Verify that user can search shipments by Shipment#, Tracking # and PO# ");
		Loginpage.OpenBrowserAndLogin();
		Shipmentpage.InitialSteps();
		waitForElementToDisplay(OR.Shipment_wait, 20);
		String ShipmentNum=getText(OR.Shipment_FirstShipmentNum);	
		System.out.println(ShipmentNum);
		String PoNum=getText(OR.Shipment_ShipmentPONum);
		System.out.println(PoNum);
		typeIn(OR.Shipment_SearchTextBox, ShipmentNum); 
		Shipmentpage.searchAndWait();			
		verifyElementText(OR.Shipment_FirstShipmentNum, ShipmentNum);
		Shipmentpage.clearAndWait();
		typeIn(OR.Shipment_SearchTextBox, PoNum); 
		Shipmentpage.searchAndWait();
		verifyElementText(OR.Shipment_ShipmentPONum, PoNum );
		Shipmentpage.clearAndWait();
//		typeIn(OR.Shipment_SearchTextBox, TrackingNum); 
//		Shipmentpage.searchAndWait();
//		verifyElementText(OR.Shipment_TrackingNum, TrackingNum);
		
	}

	@Test(priority=2)
	public void Tc_SH_02() throws InterruptedException
	{
		testStarts("Tc_SH_02", "Verify that status dropdown appears with(Pending Shipped Partial Received Received) options");
	NavigateUrl(DashBoardURL);
		Shipmentpage.InitialSteps();			
		List<String> list = new ArrayList<String>( Arrays.asList("All", "Pending", "Shipped", "Partial Receive", "Received"));		
		List<WebElement> options = driver.findElements
				(By.xpath("//*[@id='sel1']/option"));	
		boolean textFound;
		if(options.size() == list.size())
		{
			for(String lm:list)
			{
				textFound=false;
				for(WebElement we:options)  
				{
					if(we.getText().trim().equals(lm))
					{
						textFound=true;
					}
				}
				if(textFound)
				{
					testLogPass("Text '" + lm + "' exists in dropdown.");
				}
				else
				{
					testLogFail("Text '" + lm + "' does not exist in dropdown.");
				}
			}
		}
		else
		{
			//fail because size is not equal
			testLogFail("Size of two arrayList is not equal");
		}
						
	}

	@Test(priority=3)
	public void Tc_SH_03() throws InterruptedException
	{
		testStarts("Tc_SH_03", "Verify that user gets redirected to shipment details screen on clicking �Shipment #�.");
	NavigateUrl(DashBoardURL);
		Shipmentpage.InitialSteps();
		waitForElementToDisplay(OR.Shipment_wait, 20);
		String ShipmentNum=getText(OR.Shipment_FirstShipmentNum).toUpperCase();
		System.out.println(ShipmentNum);
		clickOn(OR.Shipment_FirstShipmentNum);
		waitForElementToDisplay(OR.Shipment_wait, 20);
		String DetailPageText=getText(OR.Shipment_ShipmentDetailPAge);
		System.out.println(DetailPageText);
		if(DetailPageText.trim().contains("SHIPMENT# "+ ShipmentNum))
		{
			testLogPass("Successfully matched the Text and User is redirected to Shipment detail page");
		}
		else
		{
			testLogFail("Could not match the Text and User is not redirected to Shipment detail page");
		}
		
	}

	@Test(priority=4)
	public void Tc_SH_04() throws InterruptedException
	{
		testStarts("Tc_SH_04", "Verify that �Receive� button appears for pending and Partial received shipments.�.");
	NavigateUrl(DashBoardURL);
		Shipmentpage.InitialSteps();
		selectFromDropdown(OR.Shipment_StatusDropDown, "Pending");
		Shipmentpage.searchAndWait();
		waitForElementToDisplay(OR.Shipment_wait, 20);
		clickOn(OR.Shipment_FirstShipmentNum);
		Shipmentpage.receiveTextandCloseButton();
		waitForElementToDisplay(OR.Shipment_wait, 60);
		selectFromDropdown(OR.Shipment_StatusDropDown, "Partial Receive");
		Shipmentpage.searchAndWait();
		waitForElementToDisplay(OR.Shipment_wait, 20);
		clickOn(OR.Shipment_FirstShipmentNum);
		Shipmentpage.receiveTextandCloseButton();
		
	}



	@AfterTest
	public void endReport(){
		closeBrowser();
		extent.flush();
	}


}
