package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MPPObjects {
	WebDriver driver;
	//All objects locators of the Home Page
	By EmailID = By.id("user_email");//
	By PasswordID = By.id("user_password");
	By SignInButton = By.cssSelector("input[value='Log In']");
	
	public MPPObjects(WebDriver driverTest) {
		this.driver = driverTest;
	}
	
	//All the Functions for the above locators
	public WebElement Email()
	{
		WebElement Email = driver.findElement(EmailID);
		return Email;
	}
	public WebElement Password()
	{
		WebElement Password = driver.findElement(PasswordID);
		return Password;
	}
	public WebElement SignIn()
	{
		WebElement SignIn = driver.findElement(SignInButton);
		return SignIn;
	}

}
