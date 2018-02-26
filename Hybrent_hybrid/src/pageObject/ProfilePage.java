package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class ProfilePage extends ApplicationKeyword{
	 public static void profileverification()
     {
         verifyElementText(OR.Profile_Label_Name, "Name*");
         verifyElementText(OR.Profile_Label_Email, "Email*");
         verifyElementText(OR.Profile_Label_phone, "Phone");
         verifyElementText(OR.Profile_Label_Photo, "Photo:");
         verifyElementText(OR.Profile_Label_Facility, "Facility");
         verifyElementText(OR.Profile_Label_Dept, "Department:");
         verifyElementText(OR.Profile_Label_Inventory, "Inventory");
         verifyElementText(OR.Profile_Label_Journeydate, "Joining Date:");
         verifyElementText(OR.Profile_Label_QuickBook, "Quick Books:");
         verifyPageTitle("User Profile");
     }
    
}
