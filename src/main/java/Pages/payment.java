package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class payment extends AbstractComponents {
	WebDriver driver;
	WebElement product;


	public payment(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Place Order']")
	WebElement placeOrder;
	
	@FindBy(xpath = "//*[@data-ui-id='page-title-wrapper']")
	WebElement orderSuccessMsg;
	
	@FindBy(xpath = "//p[contains(text(),'Your order number is:')]/parent::div[@class='checkout-success']//strong")
	WebElement orderNumber;
	

	public void placeOrder() throws InterruptedException {
		waitUntilPageLoad();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrder);
		Thread.sleep(1000); // Give some time for any animations
		placeOrder.click();
	}
	
	public String getOrderNumber() {
		waitUntilVisible(orderSuccessMsg);
		String orderNum = orderNumber.getText();
		return orderNum;
	}
	
}