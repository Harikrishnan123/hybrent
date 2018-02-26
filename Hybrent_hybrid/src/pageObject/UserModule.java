package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class UserModule extends ApplicationKeyword{
	public static String UserName = "admin";
	public static String Password = "goouser";
	public static String URL = "https://qa4.test.hybrent.com/b/#/login/";

	 public static void adminAndUserPage()
     {	
		 	clickOn(OR.DashBoard_AdminDropdown);
			clickOn(OR.Users_page);
			waitForElementToDisplay(OR.Users_FirstUserName, 10);					 
     }
	 public static void searchAndUserWait()
     {	
	 
		 clickOn(OR.Users_SearchButton);
		 waitForElementToDisplay(OR.Users_EditFirstUser, 10);
	 
     }
}
