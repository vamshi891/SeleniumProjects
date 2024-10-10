package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus; 

import commonFunctions.FunctionLibrary; 
import utilities.ExcelFileUtil;

public class DriverScript extends FunctionLibrary {

	String inputPath = "./FileInput/rdata.xlsx" ;
	String outPutPath = "./FileOutput/result.xlsx " ; 
	ExtentReports reports ;
	ExtentTest logger ;

@Test
public void startTest() throws Throwable {
	//create object for excel file util class
	reports = new ExtentReports("./ExtentReports/Login.html") ;
	ExcelFileUtil xl = new ExcelFileUtil(inputPath);
	//count no of rows 
	int rc = xl.rowCount("Login");
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++) {
		logger = reports.startTest("Login Test");
		String userName = xl.getCellData("Login",i,0) ;
		String passWord = xl.getCellData("Login",i,1) ;
		logger.log(LogStatus.INFO, userName+" "+passWord) ;
		//call login method
		boolean res = FunctionLibrary.adminLogin(userName, passWord) ;
		if(res) { 
			xl.setCellData("Login", i, 2, "Valid Username and Password", outPutPath);
			xl.setCellData("Login", i, 3, "pass", outPutPath);
			logger.log(LogStatus.PASS, "Valid Username and Password") ;
		}
		else {
			//Take Screen shot
			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./ScreenShot/"+i+"LoginPage.png")) ;
			xl.setCellData("Login", i, 2, "Invalid Username and Password", outPutPath); 
			xl.setCellData("Login", i, 3, "Fail", outPutPath); 
			logger.log(LogStatus.FAIL, "Invalid Username and Password") ;
		} 
		reports.endTest(logger); 
		reports.flush();
		
		
	}
}



}
