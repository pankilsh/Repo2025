package seleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class ForgotPasswordPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "h3.card-title")
	WebElement cardTitle;
	
	@FindBy(css = "input[type='email']")
	WebElement email;
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
	
	@FindBy(css = "button[type='submit']")
	WebElement saveNewPassword;
	
	@FindBy(linkText = "Login")
	WebElement login;
	
	@FindBy(linkText = "Register")
	WebElement register;

	public RegisterPage clickRegisterLink() {
		register.click();
		return new RegisterPage(driver);
	}
	
}
