package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	WebElement product;
	Actions a;
	checkoutPage objcheckoutPage; 

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		a = new Actions(driver);
	}

	@FindBy(xpath = "//*[contains(text(),'Shopping Cart')]")
	WebElement title;

	@FindBy(xpath = "//*[@data-role='proceed-to-checkout']")
	WebElement proceedToCheckout;

	public checkoutPage goToCheckout() {
		title.isDisplayed();
		proceedToCheckout.click();
		objcheckoutPage = new checkoutPage(driver);
		return objcheckoutPage;
	}

}
