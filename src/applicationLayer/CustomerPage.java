package applicationLayer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;


public class CustomerPage {
	
WebDriver driver ;
	//write constructor to access web driver methods
	public CustomerPage(WebDriver driver) 
	{
		this.driver = driver ;
	}
	
//define repository
	@FindBy(xpath = "(//a[text()='Customers'])[2]") 
	WebElement ObjclickCustomerlink ;
	@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
	WebElement ObjclickAddIcon ;
	@FindBy(name="x_Customer_Number")
	WebElement ObjCustomerNumber ;
	@FindBy(name="x_Customer_Name")
	WebElement ObjCustomerName ;
	@FindBy(name="x_Address") 
	WebElement ObjAddress;
	@FindBy(name="x_City")
	WebElement Objcity ;
	@FindBy(name="x_Country")
	WebElement ObjCountry ;
	@FindBy(name="x_Contact_Person")
	WebElement ObjContactperson ;
	@FindBy(name="x_Phone_Number")
	WebElement ObjphoneNumber ;
	@FindBy(name="x__Email")
	WebElement ObjEmail ;
	@FindBy(name="x_Mobile_Number")
	WebElement ObjMobileNumber ;
	@FindBy(name="x_Notes")
	WebElement ObjNotes ;
    @FindBy(id="btnAction")
    WebElement ObjClickAdd ;
	@FindBy(xpath="//button[normalize-space()='OK!']")
	WebElement ObjClickconfirmbutton ;
	@FindBy(xpath="(//button[contains(text(),'OK')])[6]")
	WebElement ObjClickalertok ;
	@FindBy(xpath="//button[@data-caption='Search Panel']")
	WebElement ObjClickSearchPanel ;
	@FindBy(xpath="//input[@id='psearch']")
	WebElement ObjsearchTextbox ;
	@FindBy(xpath="//button[@id='btnsubmit']")
	WebElement Objclicksearchbutton ;
	@FindBy(xpath="//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement webtable ; 
	
	//method for adding customer
public boolean addCustomer(String customername,String Address,String City,String Country,String contactperson,
		String phonenumber,String Email,String mobilenumber,String Notes) throws Throwable 
{
	Actions ac = new Actions(driver);
	ac.moveToElement(this.ObjclickCustomerlink).click().perform();
	ac.pause(5000);
	ac.moveToElement(this.ObjclickAddIcon).click().perform();
	Thread.sleep(2000);
	String Exp_Data = this.ObjCustomerNumber.getAttribute("value");
	this.ObjCustomerName.sendKeys(customername);
	this.ObjAddress.sendKeys(Address);
	this.Objcity.sendKeys(City);
	this.ObjCountry.sendKeys(Country);
	this.ObjContactperson.sendKeys(contactperson);
	this.ObjphoneNumber.sendKeys(phonenumber);
	this.ObjEmail.sendKeys(Email);
	this.ObjMobileNumber.sendKeys(mobilenumber);
	this.ObjNotes.sendKeys(Notes);
	this.ObjClickAdd.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	ObjClickconfirmbutton.click();
	Thread.sleep(2000);
	this.ObjClickalertok.click();
	//if search text box is already displayed dont click 
	if(!ObjsearchTextbox.isDisplayed()) {
		ObjClickSearchPanel.click();
	}
	ObjsearchTextbox.clear();
	ObjsearchTextbox.sendKeys(Exp_Data);
	Objclicksearchbutton.click();
	Thread.sleep(3000);
	String Act_Data = webtable.getText();
	if(Exp_Data.equals(Act_Data)) {
		Reporter.log("Customer Add is success "+Exp_Data+" "+Act_Data,true); 
		return true; 
	}
	else {
		Reporter.log("Customer Add is fail "+Exp_Data+" "+Act_Data,true);
		return false; 
	}
}
	
	
	
	
}
