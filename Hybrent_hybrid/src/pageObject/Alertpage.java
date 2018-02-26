package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Alertpage extends ApplicationKeyword{
    public static void VerifyAlert()
    {
    	waitForElementToDisplay(OR.Alert_txt_userAlert, 60);
    	verifyElementText(OR.Alert_txt_userAlert, "USER ALERT");
        verifyElement(OR.Alert_Btn_cancel);
        verifyElement(OR.Alert_Btn_Update);
    }
    

}
