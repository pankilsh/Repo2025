package seleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class ProductCatalougePage extends AbstractComponent {

	WebDriver driver;

	public ProductCatalougePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.card")
	List<WebElement> products;
	
	@FindBy(css = "div.card-body")
	WebElement productCard;
	
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	
	@FindBy(id = "toast-container")
	WebElement toastMessage;
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productCard);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement product = getProductList().stream()
				.filter(prod -> prod.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addToCartBy).click();
		waitForElementToAppear(toastMessage);
	}

}