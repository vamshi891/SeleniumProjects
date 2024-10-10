package commonFunctions;

import java.time.Duration; 

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil; 

public class FunctionLibrary extends AppUtil {

	//method for login
	public static boolean adminLogin(String user,String pass) throws Throwable 
	{
		
		driver.get(conpro.getProperty("Url")); 
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		 
		driver.findElement(By.xpath(conpro.getProperty("Objreset"))).click();   
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		Thread.sleep(3000);
        String Expected = "dashboard" ;
        String Actual = driver.getCurrentUrl();
        
        if(Actual.contains(Expected)) {
        	//click logout link
        	driver.findElement(By.xpath(conpro.getProperty("Objlogout"))).click();
        	Reporter.log("Valid Username and password::"+Expected+" "+Actual,true);
        	return true;
        }
        else {
        	//Capture error message
        	String errorMessage = driver.findElement(By.xpath(conpro.getProperty("ObjAlertText"))).getText() ;
        	Thread.sleep(3000);
        	driver.findElement(By.xpath(conpro.getProperty("Objok"))).click(); 
        	Reporter.log(errorMessage+" "+Expected+" "+Actual,true);
        	return false ;
        }
	}
}
