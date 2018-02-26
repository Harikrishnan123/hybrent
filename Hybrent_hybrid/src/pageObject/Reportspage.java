package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AutomationFramework.ApplicationKeyword;

import AutomationFramework.OR;


public class Reportspage extends ApplicationKeyword {

	 public static void VerifyViewReport()
     {
         waitForElementToDisplay(OR.ViewMoreReport, 60);

         clickOn(OR.ViewMoreReport);
         int sClass = driver.findElements(By.xpath("//*[@role='menuitem']")).size();

         for(int j =3;j <=sClass; j++)
         {
             WebElement one = driver.findElement(By.xpath("(//*[@role='menuitem'])[" + j + "]"));
             
             testLogPass("Report is "+one.getText());
             if(one.getText()!=null)
             {
                 one.click();;
                 waitTime(10);
                 testLogPass("selected report item  page is" + driver.getTitle());
                 break;
             }
             break;
         }

     }
    
}
