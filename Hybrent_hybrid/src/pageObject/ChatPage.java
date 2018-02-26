package pageObject;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class ChatPage extends ApplicationKeyword{
    
	public static void VerifyChatPage()
    {
    	waitForElementToDisplay(OR.Chat, 60);
    	clickOn(OR.Chat);
    	waitForElementToDisplay(OR.Chat_Customerservice_txt, 60);
    	verifyElementText(OR.Chat_Customerservice_txt, "Customer Service");
        verifyElement(OR.Chat_input);
        verifyElement(OR.Chat_SendBtn);
    }

}
