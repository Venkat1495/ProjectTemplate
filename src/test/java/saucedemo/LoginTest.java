package saucedemo;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;

import SaucedemoObjects.LoginObjects;
import SaucedemoObjects.ProductsObjects;
import TestData.DataStorage;
import resources.Base;

public class LoginTest extends Base {
	
	public WebDriver driver;
	
	public static Logger log = LogManager.getLogger(Base.class.getName()); 
	DataStorage DS = new DataStorage();
	
	String className = "LoginTest";
	String MethodName1 = "Fn_SauceDemoURL";
	String MethodName2 = "Fn_Login_Validation";
	String MethodName3 = "Fn_Products_Validation_Adding";
	
	@Test
	public void Fn_SauceDemoURL() throws IOException
	{
		String URL = DS.getData(MethodName1, className, "URL");
		this.driver = invokeBrowser();
		LoginObjects lb = new LoginObjects(driver);
		driver.get(URL);
		WebElement user = lb.Fn_Email();
		Assert.assertTrue(user.isDisplayed());
		log.info("Navigated to URL" + URL);
	}
	
	@Test(dependsOnMethods= {"Fn_SauceDemoURL"})
	public void Fn_Login_Validation() throws IOException
	{
		String userID = DS.getData(MethodName2, className, "EmailID");
		String password = DS.getData(MethodName2, className, "Password");
		LoginObjects lb = new LoginObjects(driver);
		ProductsObjects po = new ProductsObjects(driver);
		
		lb.Fn_Email().sendKeys(userID);
		lb.Fn_Password().sendKeys(password);
		lb.Fn_LognIn().click();
		WebElement productsLogo = po.Fn_Products_Logo();
		Assert.assertTrue(productsLogo.isDisplayed());
		log.info("Passsing the UserID, Password and submitting");
	}
	
	@AfterTest
	public void Fn_Close_Browser()
	{
		driver.close();
		log.info("Closing the SauceDemo Browser");
		
	}
	
	@Test(dependsOnMethods= {"Fn_Login_Validation"})
	public void Fn_Products_Validation_Adding() throws IOException
	{
		int k = 0;
		ProductsObjects po = new ProductsObjects(driver);
		
		List<WebElement> productName = po.Fn_Product_Name();
		List<WebElement> productDesc = po.Fn_Product_Desc();
		List<WebElement> productPrice = po.Fn_Product_Price();
		List<WebElement> productButton = po.Fn_Product_Add_Button();
		
		String productNamesEx = DS.getData(MethodName3, className, "ProductNames");
		String ProductDescsEX = DS.getData(MethodName3, className, "ProductDescs");
		String ProductPricesEX = DS.getData(MethodName3, className, "ProductPrices");
		
		String[] pn=  productNamesEx.split(",");
		String[] pd=  ProductDescsEX.split("/");
		String[] pr=  ProductPricesEX.split("/");
		int productLength = pn.length;
		
		for(int i= 0;i<productLength;i++)
		{
			String productNameEX = pn[i];
			String ProductDescEX = pd[i];
			String ProductPriceEX = pr[i];
			
			for(int j= 0;j<=productName.size();j++)
			{
				if(productName.get(j).getText().equalsIgnoreCase(productNameEX))
				{
					if(productDesc.get(j).getText().equalsIgnoreCase(ProductDescEX))
					{
						if(productPrice.get(j).getText().equalsIgnoreCase(ProductPriceEX))
						{
						productButton.get(j).click();
						k++;
						break;
						}
					}
					
				}
			}
		}
		if(k==productLength)
		{
			Assert.assertTrue(true);
		}else
		{
			Assert.assertTrue(false);
		}
		
		
	}
	
	/*@AfterTest
	public void Fn_Close_Browser()
	{
		driver.close();
	}*/

}
