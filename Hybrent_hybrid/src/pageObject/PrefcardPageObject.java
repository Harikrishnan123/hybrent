package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import AutomationFramework.ApplicationKeyword;
import AutomationFramework.OR;

public class PrefcardPageObject extends ApplicationKeyword {	
	public static void pageLinkandwait()
    {
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//a[@href='#/preference-card/operating-rooms']"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);
		clickOn(OR.OP_ORoomPageLink);
		waitForElementToDisplay(OR.OP_wait, 10);
	
    }
	 public static void patientsPageLinkandwait()
	    {	 
		 	JavascriptExecutor je = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//a[@href='#/preference-card/patients']"));
			je.executeScript("arguments[0].scrollIntoView(true);",element);
			clickOn(OR.Patient_PatientsPageLink);
			waitForElementToDisplay(OR.OP_wait, 10);
			 
	    }
	 
	 public static void casesPageLinkandwait()
	    {	 
		 	JavascriptExecutor je = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//a[@href='#/preference-card/cases']"));
			je.executeScript("arguments[0].scrollIntoView(true);",element);
			clickOn(OR.Cases_CasesPageLink);
			//cannot put wait on table elements bcoz no elemenet is present on page by default
			//waitForElementToDisplay(OR.Case_firstCase, 10);
			 
	    }
	 public static void procedurePageLinkandwait()
	    {	 
		 	JavascriptExecutor je = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@href='#/preference-card/procedures']"));
			je.executeScript("arguments[0].scrollIntoView(true);",element);
			clickOn(OR.Procedure_PageLink);
			//waitForElementToDisplay(OR.Procedure_wait, 10);
			 
	    }
	 public static void plannerPageLinkandwait()
	    {	 
		 	JavascriptExecutor je = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@href='#/preference-card/planner']"));
			je.executeScript("arguments[0].scrollIntoView(true);",element);
			clickOn(OR.Planner_PageLink);
			 
	    }
	 public static void prefCardPagePageLinkandwait()
	    {	 
		 	JavascriptExecutor je = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@href='#/preference-card/cards']"));
			je.executeScript("arguments[0].scrollIntoView(true);",element);
			clickOn(OR.prefCard_PageLink);
			waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
			 
	    }
	 public static void addStageAndItem()
	    {
	 
		PrefcardPageObject.prefCardPagePageLinkandwait();
		waitForElementToDisplay(OR.prefCard_AddPrefCard, 10);
		clickOn(OR.prefCard_AddPrefCard);
		clickOn(OR.prefCard_addStage);
		String sName=getProperty("Prefcard_StageName");
		typeIn(OR.prefCard_addStageName, sName);
		clickOn(OR.prefCard_addStageButton);
		String itemDesc=getProperty("ItemDesc");
		typeIn(OR.prefCard_searchInCart,itemDesc);
		waitForElementToDisplay(OR.prefCard_addItemInCart, 10);
		clickOn(OR.prefCard_addItemInCart);
	    }
	 
}
