package seleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class RegisterPage extends AbstractComponent{
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[type='firstName']")
	WebElement firstName;
	
	@FindBy(css = "input[type='lastName']")
	WebElement lastName;
	
	@FindBy(css = "input[type='email']")
	WebElement email;
	
	@FindBy(id = "userMobile")
	WebElement userMobile;
	
	@FindBy(css ="[select[formcontrolname='occupation']")
	WebElement occupation;
	
	
	public void selectOccupationByName(String occupationName) {
		Select select = new Select(occupation);
		select.selectByVisibleText(occupationName);
	}
	
	public void selectOccupationByIndex(int index) {
		Select select = new Select(occupation);
		select.selectByIndex(index);
	}

}
