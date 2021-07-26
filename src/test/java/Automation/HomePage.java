package Automation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.HomeObjects;
import PageObjects.MPPObjects;
import TestData.DataStorage;
import resources.Base;

public class HomePage extends Base {
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	public WebDriver driver;
	String className = "HomePage";
	String methodName = "navApplication"; //provide the method name where u have use the dataProvider
	DataStorage DS = new DataStorage();
	
	@Test(dataProvider="data_navApplication")
	public void navApplication(String URL, String Email, String Password) throws IOException
	{
		
		/*DataStorage DS = new DataStorage();
		String URL = DS.getData(methodName, className, "URL");
		String Email = DS.getData(methodName, className, "EmailID");
		String Password = DS.getData(methodName, className, "Password");*/
		this.driver = invokeBrowser();
		driver.get(URL);
		log.info("Navigated to the URL" + URL);
		driver.manage().window().maximize();
		HomeObjects HO= new HomeObjects(driver);
		HO.Login().click();
		MPPObjects mpp= new MPPObjects(driver);
		mpp.Email().sendKeys(Email);
		mpp.Password().sendKeys(Password);
		mpp.SignIn().click();
		log.info("Passing Email ID :" + Email + "& Password :" + Password +" And submitting for 2 diff invalied credentials");
	}
	
	@DataProvider
	public Iterator<String[]> data_navApplication() throws IOException
	{
		ArrayList<String[]> data = new ArrayList<String[]>();
		int v = 1;
		
		while(true)
		{
			String URL = DS.getDataArray(methodName, className, "URL", v);
			String Email = DS.getDataArray(methodName, className, "EmailID", v);
			String Password = DS.getDataArray(methodName, className, "Password", v);
			if(URL==null)
			{
				break;
			}
			String obj[] = {URL, Email, Password};
			data.add(obj);
			v++;
		}
		return data.iterator();
	}
	
	@AfterMethod
	public void Fn_Close_Browser()
	{
		driver.close();
	}

}
