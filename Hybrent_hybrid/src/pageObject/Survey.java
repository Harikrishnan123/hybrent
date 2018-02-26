package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Survey extends ApplicationKeyword{
    public static void VerifySurvey()
    {
        verifyElementText(OR.Survey_txt_Survey, "Pending Survey");
        verifyElement(OR.Survey_btn_Clickhere);
        
    }
}
