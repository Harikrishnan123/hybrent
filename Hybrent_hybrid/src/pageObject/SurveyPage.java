package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class SurveyPage extends ApplicationKeyword{
    public static void VerifySurvey()
    {
    	waitForElementToDisplay(OR.Survey_txt_Survey, 60);
    	String one = getText(OR.Survey_txt_Survey);
    	verifyElementText(OR.Survey_txt_Survey, one);
        
    }
}
