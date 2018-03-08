package pageObject;

import org.openqa.selenium.By;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class Template extends ApplicationKeyword
{
	public static String CreateTemplateName = "Test"+randomAlphaNumeric(16);
	public static String EditTemplateName = "EdiTest";
	
	public static void AddDeleteScheduleTemplate()
	{
		try
		{
		waitForElement(OR.Templates_AddBtn, 60);
		clickOn(OR.Templates_AddBtn);
		verifyElementText(OR.Templates_AddTemplatetxt, "Add Template");
		verifyElement(OR.Templates_Cancel);
		typeIn(OR.Templates_Name, CreateTemplateName);
		clickOn(OR.Templates_CreateTemplate);
		String TemplateName ="Test";
		typeIn(OR.Templates_SearchScan,TemplateName);
		clickOn(OR.Template_Add_First);
		getAttributeValue(OR.Template_Color, "background-color");
		clickOn(OR.Template_Schedule);
		clickOn(OR.Template_AddSchedule);
		//String one = getAttributeValue(OR.Template_Schedule_Every, "selected");
		waitForElement(OR.Template_Schedule_Savebtn, 60);
		clickOn(OR.Template_Schedule_Savebtn);
		waitTime(5);
		clickOn(OR.Template_Schedule_Deletebtn);
		verifyElementText(OR.Template__Deletevalidation, "Are you sure?");
		verifyElementText(OR.Template__Deletevalidation1, "Are you sure you want to delete this schedule ?");
		clickOn(OR.Template_Yes);
		System.out.println("test");
		waitTime(15);
		clickOn(OR.Template_Yes);
		}
		catch(Exception e)
		{
			if(isElementDisplayed(OR.Template_Yes))
			{
				clickOn(OR.Template_Yes);
			}
			NavigateUrl(DashBoardURL);
		}
	}
	
	public static void AddTemplate()
	{
		try
		{
		waitForElement(OR.Templates_AddBtn, 60);
		clickOn(OR.Templates_AddBtn);
		verifyElementText(OR.Templates_AddTemplatetxt, "Add Template");
		verifyElement(OR.Templates_Cancel);
		typeIn(OR.Templates_Name, CreateTemplateName);
		clickOn(OR.Templates_CreateTemplate);
		}
		catch(Exception e)
		{
			if(isElementDisplayed(OR.Template_Yes))
			{
				clickOn(OR.Template_Yes);
			}
			NavigateUrl(DashBoardURL);
		}
	}
	
	public static void AddScehdule()
	{
		try
		{
		clickOn(OR.Template_Schedule);
		clickOn(OR.Template_AddSchedule);
		//String one = getAttributeValue(OR.Template_Schedule_Every, "selected");
		waitForElement(OR.Template_Schedule_Savebtn, 60);
		clickOn(OR.Template_Schedule_Savebtn);
		}
		catch(Exception e)
		{
			if(isElementDisplayed(OR.Template_Yes))
			{
				clickOn(OR.Template_Yes);
			}
			NavigateUrl(DashBoardURL);
		}
	}
	public static void AddItemTemplate()
	{
		String TemplateName ="Test";
		typeIn(OR.Templates_SearchScan,TemplateName);
		clickOn(OR.Template_Add_First);
	}
	
	public static void EditTemplate()
	{
		waitTime(10);
		clickOn(OR.Templates_Edit);
		driver.findElement(By.xpath("//form/div/input")).clear();
		typeIn(OR.Templates_EditName,"EdiTest");
		clickOn(OR.Templates_EditSubmit);
	}
	
	public static void verify()
	{
		verifyElementText(OR.Templates_Editview, EditTemplateName.toUpperCase());
	}
	
	public static void searchTemplate()
	{
		typeIn(OR.Template_Search_Input,CreateTemplateName);
		clickOn(OR.Template_SearchBtn);
	}
}
