package SaucedemoObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsObjects {
	WebDriver driver;
	//All objects locators of the Home Page
	By productsLogo = By.cssSelector(".title");
	By product = By.cssSelector(".inventory_item_name");
	By productDesc = By.cssSelector(".inventory_item_desc");
	By productPrice = By.cssSelector(".inventory_item_price");//.inventory_item_label a div
	By productAddButton =By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
	
	
	public ProductsObjects(WebDriver driverTest) {
		this.driver = driverTest;
	}
	
	//All the Functions for the above locators
	public WebElement Fn_Products_Logo()
	{
		WebElement productsLogoWE = driver.findElement(productsLogo);
		return productsLogoWE;
	}
	
	public List<WebElement> Fn_Product_Name()
	{
		List<WebElement> productWE = driver.findElements(product);
		return productWE;
	}
	
	public List<WebElement> Fn_Product_Desc()
	{
		List<WebElement> productDescWE = driver.findElements(productDesc);
		return productDescWE;
	}
	
	public List<WebElement> Fn_Product_Price()
	{
		List<WebElement> productPriceWE = driver.findElements(productPrice);
		return productPriceWE;
	}
	
	public List<WebElement> Fn_Product_Add_Button()
	{
		List<WebElement> productAddButtonWE = driver.findElements(productAddButton);
		return productAddButtonWE;
	}

}
