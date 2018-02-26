package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Dashboardpage extends ApplicationKeyword{
	  public static void NotificationCount()
      {
          try
          {  
        	  waitForElement(OR.Notification_Bell, 60);
        	  String one = getText(OR.Notification_First);
        	  String two = getText(OR.Notification_First_Time);
        	  System.out.println(one);
              testLogPass("Notification in popup ' "+ one + "'  - ' " +two  );
              waitforclick(OR.Notification_ViewAll);
              clickOn(OR.Notification_ViewAll);
              String two1 = getText(OR.NotficationPage_FirstNotificationText); 
              if (one.equals(two1))
              {
                  testLogPass("Notification in both matching ' " + one + "' == ' " + two1);
              }
              else
              {
                  testLogFail("Notification is not both  ' " + one + "' == ' " + two1);
              }
              verifyElement(OR.Notification_Mynotification);
              verifyElement(OR.Notification_Btn_Refresh);
          }
          catch(Exception e)
          {
              testLogFail("Notification  Error '" + e.toString()+ "'");
          }
      }

      public static void verificationNotificationPage()
      {
          verifyElement(OR.NotficationPage_Text);
          verifyPageTitle("Notifications Settings");
              
              verifyElementText(OR.NotficationPage_Text_Event, "Event");
              verifyElementText(OR.NotficationPage_Text_MobilePush, "Mobile Push");
              verifyElementText(OR.NotficationPage_Text_Email, "Email Notification");
              verifyElementText(OR.NotficationPage_Text_Web, "Web Notification");
      }

      public static void MonthlyReport()
      {
          getText(OR.DashBoard_Report_Monthly_Dolors);
          getText(OR.DashBoard_Report_Monthly_txt);
          getText(OR.DashBoard_Report_Montly);
          verifyElement(OR.DashBoard_Report_Monthly_Dolors);
          verifyElement(OR.DashBoard_Report_Monthly_txt);
          verifyElement(OR.DashBoard_Report_Montly);

      }

      public static void PartialReviews()
      {
          getText(OR.DashBoard_Report_PartialReviews);
          getText(OR.DashBoard_Report_PartialReviews_txt);
          verifyElement(OR.DashBoard_Report_PartialReviews);
          verifyElement(OR.DashBoard_Report_PartialReviews_txt);
      }

      public static void Avgperformance()
      {
          getText(OR.DashBoard_Report_Day);
          getText(OR.DashBoard_Report_Day_txt);
          getText(OR.DashBoard_Report_Hours);
          getText(OR.DashBoard_Report_Hours_txt);
          getText(OR.DashBoard_Report_avgventor_txt);
          verifyElement(OR.DashBoard_Report_Day);
          verifyElement(OR.DashBoard_Report_Day_txt);
          verifyElement(OR.DashBoard_Report_Hours);
          verifyElement(OR.DashBoard_Report_Hours_txt);
          verifyElement(OR.DashBoard_Report_avgventor_txt);
      }


      public static void VerifyOrders()
      {   
          int Header = driver.findElements(By.xpath("//*[@id='reqListing']//tr/th")).size();
          int OrderDetails = driver.findElements(By.xpath("//*[@id='reqListing']//tr/td")).size();

          for (int j = 1; j <= Header; j++)
          {
              WebElement OrderHeader = driver.findElement(By.xpath("//*[@id='reqListing']//tr/th[" + j + "]"));
              WebElement Order = driver.findElement(By.xpath("//*[@id='reqListing']//tr/td["+j+"]"));
              testLogPass("Order Header " + OrderHeader.getText());
              testLogPass("Order Details " + Order.getText());
              
          }

      }

      public static void News()
      {
          verifyElementText(OR.DashBoard_Report_NewsEvent_txt, "News & Events");
          verifyElement(OR.DashBoard_Report_NewsDate);
          verifyElement(OR.DashBoard_Report_NewsTime);
          getText(OR.DashBoard_Report_NewsDate);
          getText(OR.DashBoard_Report_NewsTime);
          getText(OR.DashBoard_Report_NewsEvent_txt);
          getText(OR.DashBoard_Report_News);
          clickOn(OR.DashBoard_Report_News);
          verifyPageTitle("News");
      }

}
