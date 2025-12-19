package seleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement loginBtn;
	
	@FindBy(className = "forgot-password-link")
	WebElement forgotPass;
	
	@FindBy(css = "div[class*=flyInOut]")
	WebElement errorMessageEl;
	
	public ProductCatalougePage loginToApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		return new ProductCatalougePage(driver);
		
	}
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessageEl);
		return errorMessageEl.findElement(By.tagName("div")).getText();
	}
	
	
}
