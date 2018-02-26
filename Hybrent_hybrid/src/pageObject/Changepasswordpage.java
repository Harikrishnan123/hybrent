package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Changepasswordpage extends ApplicationKeyword {
    public static void VerifyChangePassword()
    {
        verifyElement(OR.Cpwd_btn_submit);
        verifyElement(OR.Cpwd_txt_changepassword);
        verifyElement(OR.Cpwd_txt_confirmpassword);
        verifyElement(OR.Cpwd_txt_oldpassword);
        verifyElement(OR.Cpwd_txt_newpassword);
        
    }
}
