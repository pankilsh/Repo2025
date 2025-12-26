package cucumber;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameworkDesign.TestComponents.BaseTest;
import seleniumFrameworkDesign.pageObjects.CartPage;
import seleniumFrameworkDesign.pageObjects.CheckoutPage;
import seleniumFrameworkDesign.pageObjects.LandingPage;
import seleniumFrameworkDesign.pageObjects.ProductCatalougePage;

public class StepDefinations extends BaseTest {
	
	LandingPage landingPage;
	ProductCatalougePage productCatalogue;
	CheckoutPage checkout;

	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void gogged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginToApplication(username, password);
	}
	
	@When("^I Add the product (.+)$")
	public void i_Add_the_product(String productName) throws InterruptedException {
		productCatalogue.getProductList();
		productCatalogue.getProductByName(productName);
		productCatalogue.addProductToCart(productName);
		Thread.sleep(1000);
	}
	
	@And("^Checkout (.+) and submit order$")
	public void Checkout_and_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.productPresentInCart(productName));
		checkout = cartPage.buyProductNow(productName);
	}
	
	@Then("{string} is displayed")
	public void message_is_displayed(String message) {
		System.out.println(message);
	}
	
	
	
}
