package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class ReceivePageObject extends ApplicationKeyword {

	 public static void pageLinkandwait()
	 	{
		 clickOn(OR.Receive_pageLink);
		 waitForElementToDisplay(OR.Receive_wait, 10);
	 	}
	 
	 public static void selectByDefaultFacility()
		{
		 	clickOn(OR.User);
		 	waitForElementToDisplay(OR.User_Profile, 60);
		 	clickOn(OR.User_Profile);
		 	String facNAme=getText(OR.Profile_defaultFacility);
		 	pageLinkandwait();
		 	String alreadySelectedFac=getText(OR.Receive_selectedFacInDropDown);
		 	boolean flag=false;
		 	boolean flaf_02=false;
		 	if(!alreadySelectedFac.equals(facNAme))
		 	{
		 		clickOn(OR.Receive_facilityDropdown);
				WebElement elem=driver.findElement(By.xpath("//*[text()='"+facNAme+"']"));
				elem.click();
				flag=true;
		 	}						
			String alreadySelectedUser=getText(OR.Receive_selectedUserInDropDown);
			if(!alreadySelectedUser.equals("All"))
			{
			clickOn(OR.Receive_UsersDropdown);
			WebElement elem2=driver.findElement(By.xpath("//*[text()='All']"));
			elem2.click();
			flaf_02=true;
			}
			if(flag||flaf_02)
			{
			clickOn(OR.Receive_searchButton);
			}
		}

	 

}
