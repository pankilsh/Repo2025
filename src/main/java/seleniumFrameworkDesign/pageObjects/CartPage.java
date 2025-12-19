package seleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.infoWrap")
	List<WebElement> cartProducts;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutBtn;

	public WebElement getCartProduct(String productName) {
		WebElement cartProduct = cartProducts.stream().filter(prod -> prod
				.findElement(By.cssSelector("div.cartSection h3")).getText().trim().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return cartProduct;
	}

	public void removeCartProduct(String productName) {
		WebElement deleteBtn = getCartProduct(productName).findElement(By.cssSelector("button.btn-danger"));
		deleteBtn.click();
	}

	public CheckoutPage buyProductNow(String productName) {
		WebElement buyNowBtn = getCartProduct(productName).findElement(By.cssSelector("button.btn-primary"));
		buyNowBtn.click();
		return new CheckoutPage(driver);
	}

	public boolean productPresentInCart(String productName) {
		boolean match = cartProducts.stream().anyMatch(prod -> prod.findElement(By.cssSelector("div.cartSection h3"))
				.getText().trim().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage goToCheckout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}

}
