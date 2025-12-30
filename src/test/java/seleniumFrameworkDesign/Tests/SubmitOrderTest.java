package seleniumFrameworkDesign.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import seleniumFrameworkDesign.TestComponents.BaseTest;
import seleniumFrameworkDesign.pageObjects.CartPage;
import seleniumFrameworkDesign.pageObjects.ProductCatalougePage;

public class SubmitOrderTest extends BaseTest {

	@DataProvider(name = "getDataFromJson")
	public Object[][] getDataFromJson() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap("PurchaseOrder");

		Object[][] object = new Object[data.size()][1];

		for (int i = 0; i < data.size(); i++) {
			object[i][0] = data.get(i);
		}

		return object;

	}

	@DataProvider(name = "getDataFromExcel")
	public Object[][] getDataFromExcel() throws InvalidFormatException, IOException {
		List<HashMap<String, String>> data = getExcelDataToMapMethodTwo("loginData", "ExcelTestData");

		Object[][] object = new Object[data.size()][1];

		for (int i = 0; i < data.size(); i++) {
			object[i][0] = data.get(i);
		}

		return object;
	}

	@DataProvider(name = "getDataFromDB")
	public Object[][] getDataFromDB() throws IOException, SQLException {
		List<HashMap<String, String>> dbData = getDBDataToMap("logindetails");

		Object[][] data = new Object[dbData.size()][1];

		for (int i = 0; i < dbData.size(); i++) {
			data[i][0] = dbData.get(i);
		}

		return data;
	}

	@Test(dataProvider = "getDataFromJson", groups = "purchase")
	public void addProductToCart(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalougePage productCatalogue = landingPage.loginToApplication(input.get("email"),
				input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("productName"));
		productCatalogue.addProductToCart(input.get("productName"));
		Thread.sleep(2000);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.productPresentInCart(input.get("productName")));
		cartPage.buyProductNow(input.get("productName"));
		// CheckoutPage checkout = cartPage.buyProductNow(productName);

	}

	@Test(dataProvider = "getDataFromExcel")
	public void test(HashMap<String, String> input) throws InterruptedException {
		landingPage.loginToApplication(input.get("email"), input.get("password"));
		// System.out.println(input.get("password"));
	}

	@Test(dataProvider = "getDataFromDB")
	public void testDB(HashMap<String, String> input) {
		for (String key : input.keySet()) {
			String value = input.get(key);
			System.out.println("Username : " + key + " and Password : " + value);
		}
	}
}
