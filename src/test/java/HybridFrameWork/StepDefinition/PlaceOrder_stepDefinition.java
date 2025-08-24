package HybridFrameWork.StepDefinition;

import java.io.IOException;

import HybridFrameWork.TestComponents.baseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductDetailsPage;
import Pages.ProductListPage;
import Pages.checkoutPage;
import Pages.payment;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaceOrder_stepDefinition extends baseTest {

	public HomePage objHomePage;
	public LoginPage objLoginPage;
	public ProductListPage objProductListPage;
	public ProductDetailsPage objProductDetailsPage;
	public CartPage objCartPage;
	public checkoutPage objcheckoutPage;
	public payment objpayment;

	@Given("I have landed on ecommerce site")
	public void I_have_landed_on_ecommerce_site() throws IOException {

		objHomePage = launchApplication();

	}

	@Given("Log in to the site using (.+) and (.+)")
	public void login_to_site(String username, String password) {
		objLoginPage = objHomePage.clickSigninBtn();
		objLoginPage.login(username, password);
	}

	@When("I add (.+) (.+) (.+) to cart")
	public void add_to_cart(String name, String size, String color) throws InterruptedException {

		objHomePage.homePageCheck();
		objProductListPage = objHomePage.selectcategory();

		objProductListPage.findProduct(name);

		objProductDetailsPage = objProductListPage.selectProduct();

		Thread.sleep(20000L);
		objProductDetailsPage.selectSizeOption(size);
		objProductDetailsPage.selectColorOption(color);
		objProductDetailsPage.addProductToCart();
		objProductDetailsPage.verifyAddSuccessMsg(name);

	}

	@And("Navigate to checkout page and place order (.+)")
	public void navigate_to_checkout_and_place_order(String shippingMethod) throws InterruptedException {
		objCartPage = objProductDetailsPage.goToShoppingCart();
		objcheckoutPage = objCartPage.goToCheckout();
		Thread.sleep(20000L);
		objcheckoutPage.selectShippingmethod(shippingMethod);

		objpayment = objcheckoutPage.goToPayment();

		objpayment.placeOrder();
	}

	@Then(" order placed message is displayed")
	public void verify_order_message() {
		String orderNum = objpayment.getOrderNumber();
		System.out.println(orderNum);
	}

	@Then("login error message is displayed")
	public void login_error_message_validation() {
		objLoginPage.checkLoginFailure();
	}
}
