package pageObject;

import org.openqa.selenium.By;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class ShopPage extends ApplicationKeyword{
    
	public static void VerifyShopPage()
    {
		waitForElementToDisplay(OR.Shop_SHopfor_Shopfaclity, 60);
        verifyElementText(OR.Shop_SHopfor_Shopfaclity, "Select Facility");
    }
	
	public static void MatchDropdown()
	{
		verifyElementText(OR.Shop_Match_Text, "Match");
		int one = driver.findElements(By.xpath("")).size();
		driver.findElement(By.xpath("//*[@id='matchselect']")).click();
		for(int i=0;i<=one;i++)
		{
			String two = driver.findElement(By.xpath("//*[@id='matchselect']/option["+i+"]")).getText();
			if(two.contains("Partial"))
			{
				testLogPass("Text is presend in the dropdown is "+two);
			}
			else if(two.contains("Exact"))
			{
				testLogPass("Text is presend in the dropdown is "+two);
			}
			else
			{
				testLogFail("text is not present in the dropdown "+two);
			}
				
		}
	}
	
	public static void fav()
	{
		//int ItemName = getMatchingXpathCount(OR.Shop_ItemName_Total);
		

	}
    

}
