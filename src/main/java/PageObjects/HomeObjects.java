package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeObjects {
	
	WebDriver driver;
	//All objects locators of the Home Page
	By Login = By.cssSelector("a[href*='sign_in']");
	
	public HomeObjects(WebDriver driverTest) {
		this.driver = driverTest;
	}
	
	//All the Functions for the above locators
	public WebElement Login()
	{
		WebElement login = driver.findElement(Login);
		return login;
	}
	

}
