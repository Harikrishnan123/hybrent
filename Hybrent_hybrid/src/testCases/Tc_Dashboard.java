package testCases;

import java.io.File;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;
import pageObject.Alertpage;
import pageObject.Changepasswordpage;
import pageObject.ChatPage;
import pageObject.Dashboardpage;
import pageObject.Loginpage;
import pageObject.MycartPage;
import pageObject.ProfilePage;
import pageObject.Reportspage;
import pageObject.SurveyPage;
import pageObject.SwitchUserPage;

public class Tc_Dashboard  extends ApplicationKeyword
{

	public static String UserName = "admin";
	public static String Password = "goouser";
	public static String URL = "https://qa4.test.hybrent.com/b/#/login/";
	public static String DashBoardURL = "https://qa4.test.hybrent.com/b/#/dashboard";
	
	@BeforeTest
	public void startReport() {

		try {

			extent = new ExtentReports(OutputDirectory+"/DashBoard.html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("User Name", "Ravneet");
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		
		} catch (Exception e) {
			testLogFail("unable to generate the pass report " + e.toString());
		}
	}
	
	@Test(groups="Dashboard", priority=1)
	public void Tc_Dashboard_001() {
		System.out.println("CameTo Dashboard");
		testStarts("Tc_Dashboard_001, Tc_Dashboard_003",
				"Verify that recent notifications appear on clicking Notification (bell) icon button.\n"
						+ "Verify that user gets redirected to �My Notifications� page, on clicking �View All� button\"");
		openBrowser(URL);
		Loginpage.login(UserName, Password);
		waitForElementToDisplay(OR.Notification_Bell, 60);
		clickOn(OR.Notification_Bell);
		Dashboardpage.NotificationCount();
	}

	@Test(groups="Dashboard", priority=2)
	public void Tc_Dashboard_002() {
		testStarts("Tc_Dashboard_002",
				"Verify that user gets redirected to �Notification Settings� page, on clicking �Notification Settings� button");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		waitForElementToDisplay(OR.User_NotificationSetting, 60);
		clickOn(OR.User_NotificationSetting);
		Dashboardpage.verificationNotificationPage();
		
	}

	@Test(groups="Dashboard", priority=3)
	public void Tc_Dashboard_004() {
		testStarts("Tc_Dashboard_004",
				"Verify that user is redirected to Cart page on clicking Shopping Cart icon button.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.MyCart, 60);
		MycartPage.verificationMycartPage();
	}

	@Test(groups="Dashboard", priority=4)
	public void Tc_Dashboard_005() {
		testStarts("Tc_Dashboard_005",
				"Verify that user gets redirected to �Profile� page, on clicking Profile button.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		waitForElementToDisplay(OR.User_Profile, 60);
		clickOn(OR.User_Profile);
		ProfilePage.profileverification();
	}

	@Test(groups="Dashboard", priority=5)
	public void Tc_Dashboard_006() {
		testStarts("Tc_Dashboard_006", "Verify that chat window opens up when user clicks on 'view chat' icon.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.Chat, 60);
		ChatPage.VerifyChatPage();
	}

	@Test(groups="Dashboard", priority=6)
	public void Tc_Dashboard_007() {
		testStarts("Tc_Dashboard_007",
				"Verify that user gets redirected to user alerts page on clicking  alerts option..");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		waitForElementToDisplay(OR.User_alert, 60);
		clickOn(OR.User_alert);
		Alertpage.VerifyAlert();
	}

	@Test(groups="Dashboard", priority=7)
	public void Tc_Dashboard_008() {
		testStarts("Tc_Dashboard_008",
				"Verify that �Pending Survey� pop up appears when user clicks on survey option.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		clickOn(OR.User_Survey);
		SurveyPage.VerifySurvey();
	}

	@Test(groups="Dashboard", priority=8)
	public void Tc_Dashboard_009() {
		testStarts("Tc_Dashboard_009",
				"Verify that �Change Password� pop up appears when user clicks on Change Password option.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		clickOn(OR.User_ChangePassword);
		Changepasswordpage.VerifyChangePassword();
	}

	@Test(groups="Dashboard", priority=9)
	public void Tc_Dashboard_010() {
		testStarts("Tc_Dashboard_010",
				"Verify that �Select user to login� screen appears when user clicks on switch user option.");
		NavigateUrl(DashBoardURL);
		waitForElementToDisplay(OR.User, 60);
		clickOn(OR.User);
		clickOn(OR.User_SwitchUser);
		SwitchUserPage.VerifySwitchUser();
	}

	@Test(groups="Dashboard", priority=10)
	public void Tc_Dashboard_011() {
		testStarts("Tc_Dashboard_011",
				"Verify that user gets redirected to corresponding Report on clicking View More Reports button.");
		NavigateUrl(DashBoardURL);
		Reportspage.VerifyViewReport();
	}

	@Test(groups="Dashboard", priority=11)
	public void Tc_Dashboard_012() {
		testStarts("Tc_Dashboard_012",
				"Verify that relevant data appears under Monthly Purchase Order Value, Number of Backorders and Vendor Performance sections");
		NavigateUrl(DashBoardURL);
		Dashboardpage.MonthlyReport();
		Dashboardpage.PartialReviews();
		Dashboardpage.Avgperformance();
	}

	@Test(groups="Dashboard", priority=12)
	public void Tc_Dashboard_013() {
		testStarts("Tc_Dashboard_013, Tc_Dashboard_014, Tc_Dashboard_015",
				"Verify that �Recent Orders� and �News & Events� sections appear on Page.\n"
						+ "Verify that user gets redirected to corresponding news on clicking any news.\r\n"
						+ "Verify that user gets redirected to corresponding PO details on clicking any PO link under \"�Recent Orders� section,\r\n"
						+ "");
		NavigateUrl(DashBoardURL);
		Dashboardpage.PartialReviews();
		Dashboardpage.Avgperformance();
		Dashboardpage.VerifyOrders();
		driver.navigate().back();
		Dashboardpage.News();
		closeBrowser();
	}
	@AfterTest
	public void endReport() {
		closeBrowser();
		extent.flush();
	}

}
