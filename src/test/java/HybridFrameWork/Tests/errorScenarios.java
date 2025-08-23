package HybridFrameWork.Tests;

import java.io.IOException;
import Pages.LoginPage;
import org.testng.annotations.Test;
import HybridFrameWork.TestComponents.*;

public class errorScenarios extends baseTest {

	static String sizeOption = "M";
	static String productName = "Atlas Fitness Tank";
	static String colorOption = "Blue";
	static String shippingMethod = "Fixed";

	@Test (retryAnalyzer=Retry.class)

	public void invalidLogin() throws InterruptedException, IOException {
		objHomePage.homePageCheck();

		LoginPage objLoginPage = objHomePage.clickSigninBtn();
		objLoginPage.login("Vk@mail.com", "^^^^^iQc#fRS377n8j_9");
		objLoginPage.checkLoginFailure();
		
	}

	
}
