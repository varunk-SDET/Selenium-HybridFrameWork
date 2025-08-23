package HybridFrameWork.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import HybridFrameWork.TestComponents.baseTest;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductDetailsPage;
import Pages.ProductListPage;
import Pages.checkoutPage;
import Pages.payment;

public class placeOrder extends baseTest {
	payment objpayment;

	@Test(dataProvider = "getData", groups = "Smoke")
//pipeline test
//	public void orderPlacement(String uname, String pass, String sizeOption, String productName, String colorOption,String shippingMethod) throws InterruptedException, IOException {
	// TODO Auto-generated method stub
	public void orderPlacement(HashMap<String, String> map) throws InterruptedException {
		objHomePage.homePageCheck();

		LoginPage objLoginPage = objHomePage.clickSigninBtn();
		objLoginPage.login(map.get("uname"), map.get("pass"));

		objHomePage.homePageCheck();
		ProductListPage objProductListPage = objHomePage.selectcategory();

		objProductListPage.findProduct(map.get("productName"));

		ProductDetailsPage objProductDetailsPage = objProductListPage.selectProduct();

		Thread.sleep(20000L);
		objProductDetailsPage.selectSizeOption(map.get("sizeOption"));
		objProductDetailsPage.selectColorOption(map.get("colorOption"));
		objProductDetailsPage.addProductToCart();
		objProductDetailsPage.verifyAddSuccessMsg(map.get("productName"));

		CartPage objCartPage = objProductDetailsPage.goToShoppingCart();

		checkoutPage objcheckoutPage = objCartPage.goToCheckout();
		Thread.sleep(20000L);
		objcheckoutPage.selectShippingmethod(map.get("shippingMethod"));

		objpayment = objcheckoutPage.goToPayment();

		objpayment.placeOrder();

	}

	@Test(dependsOnMethods = "orderPlacement")
	public void getOrderNumber() {
		objpayment.getOrderNumber();
	}

	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("uname", "VK@mail.com");
//		map.put("pass", "iQc#fRS377n8j_9");
//		map.put("sizeOption", "M");
//		map.put("productName", "Atlas Fitness Tank");
//		map.put("colorOption", "Blue");
//		map.put("shippingMethod", "Fixed");
		List<HashMap<String, String>>data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//HybridFrameWork//data//order_details.json");
		return new Object[][] {{data.get(0)}};
//		return new Object[][] {{"Vk@mail.com","iQc#fRS377n8j_9","M","Atlas Fitness Tank","Blue","Fixed"}};
	}
}
