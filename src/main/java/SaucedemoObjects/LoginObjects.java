package SaucedemoObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginObjects {
	WebDriver driver;
	//All objects locators of the Home Page
	By userID = By.id("user-name");
	By password = By.id("password");
	By logInButton = By.id("login-button");
	
	public LoginObjects(WebDriver driverTest) {
		this.driver = driverTest;
	}
	
	//All the Functions for the above locators
	public WebElement Fn_Email()
	{
		WebElement userIDWE = driver.findElement(userID);
		return userIDWE;
	}
	public WebElement Fn_Password()
	{
		WebElement passwordWE = driver.findElement(password);
		return passwordWE;
	}
	public WebElement Fn_LognIn()
	{
		WebElement logInButtonWE = driver.findElement(logInButton);
		return logInButtonWE;
	}

}
