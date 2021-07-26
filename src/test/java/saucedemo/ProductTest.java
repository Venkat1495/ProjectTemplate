package saucedemo;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import SaucedemoObjects.ProductsObjects;
import TestData.DataStorage;
import junit.framework.Assert;
import resources.Base;

public class ProductTest {
	
	DataStorage ds = new DataStorage();
	SoftAssert sf = new SoftAssert();
	LoginTest lt = new LoginTest();
	
	//public WebDriver driver = lt.driver;
	
	String className = "ProductTest";
	String MethodName1 = "Fn_Products_Validation_Adding";
	
	public static Logger log = LogManager.getLogger(Base.class.getName()); 
	@Test//(dependsOnMethods= {"Fn_Login_Validation"})
	public void Fn_Products_Validation_Adding() throws IOException
	{
		int k = 0;
		ProductsObjects po = new ProductsObjects(lt.driver);
		
		List<WebElement> productName = po.Fn_Product_Name();
		List<WebElement> productDesc = po.Fn_Product_Desc();
		List<WebElement> productPrice = po.Fn_Product_Price();
		List<WebElement> productButton = po.Fn_Product_Add_Button();
		
		String productNamesEx = ds.getData(MethodName1, className, "ProductNames");
		String ProductDescsEX = ds.getData(MethodName1, className, "ProductDescs");
		String ProductPricesEX = ds.getData(MethodName1, className, "ProductPrices");
		
		String[] pn=  productNamesEx.split(",");
		String[] pd=  ProductDescsEX.split("/");
		String[] pr=  ProductPricesEX.split("/");
		int productLength = pn.length;
		
		log.info("Navgated to the products page");
		
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
			sf.assertTrue(false);
		}else
		{
			sf.assertTrue(false);
		}
		
		log.info("Adding the mentioned product to cart and validating the price and discritption");
		
	}

}
