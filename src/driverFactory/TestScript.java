package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.CustomerPage;
import config.AppUtil2;
import utilities.ExcelFileUtil;

public class TestScript extends AppUtil2 {
	
String input = "./FileInput/pomData.xlsx" ;	
String output = "./FileOutput/FinalData.xlsx" ; 
String TCSheet = "customer" ;
ExtentReports report ;
ExtentTest logger ;
	
@Test
public void startTest() throws Throwable
{
  //create object for excel file util class
	report = new ExtentReports("./Reports/Customer.html") ;
	ExcelFileUtil xl = new ExcelFileUtil(input);
	//count no of rows
	int rc = xl.rowCount(TCSheet);
	Reporter.log("No of rows are "+rc,true);
	for(int i=1;i<=rc;i++) {
		logger = report.startTest("Customer") ;
		logger.assignAuthor("Arun") ;
		//read cell data
		String cname = xl.getCellData(TCSheet, i, 0);
		String Address = xl.getCellData(TCSheet, i, 1);
		String city = xl.getCellData(TCSheet, i, 2);
		String country = xl.getCellData(TCSheet, i, 3) ;
		String cperson = xl.getCellData(TCSheet, i, 4); 
		String pnumber = xl.getCellData(TCSheet, i, 5) ; 
		String email = xl.getCellData(TCSheet, i, 6) ;
		String mnumber = xl.getCellData(TCSheet, i, 7) ;
		String notes = xl.getCellData(TCSheet, i, 8) ;
		logger.log(LogStatus.INFO, cname+" "+Address+" "+city+" "+country+" "+cperson+" "+pnumber+" "+email+" "+mnumber+" "+notes);
		CustomerPage Customeradd = PageFactory.initElements(driver,CustomerPage.class);
		boolean res = Customeradd.addCustomer(cname, Address, city, country, cperson, pnumber, email, mnumber, notes);
		if(res) {
			xl.setCellData(TCSheet, i, 9, "Pass", output);
			logger.log(LogStatus.PASS,"Customer Added Successfully") ;
		}
		else {
			xl.setCellData(TCSheet, i, 9, "Fail", output);
			logger.log(LogStatus.FAIL,"Customer is not Added");
		}
		report.endTest(logger);
		report.flush();
	}
	
	
}
	
	

}
