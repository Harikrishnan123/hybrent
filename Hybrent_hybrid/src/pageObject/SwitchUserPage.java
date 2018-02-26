package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class SwitchUserPage extends ApplicationKeyword{

	 public static void VerifySwitchUser()
     {
         verifyElement(OR.SwitchUser_txt_Msg);
         verifyPageTitle("Select User");
     }
}
