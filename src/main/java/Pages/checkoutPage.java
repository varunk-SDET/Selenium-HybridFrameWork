package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class checkoutPage extends AbstractComponents {
	WebDriver driver;
	WebElement product;
	Actions a;

	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		a = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[contains(text(),'Shopping Cart')]")
	WebElement title;

	@FindBy(xpath = "//*[@data-role='opc-continue']")
	WebElement continueButton;

	public void selectShippingmethod(String shippingMethod) {
		List<WebElement> col = driver.findElements(By.xpath("//td[contains(@class,'col-method')]"));
		super.waitUntilPageLoad();
		a.sendKeys(Keys.END).perform();
		for (WebElement value : col) {
			String text = value.getText().trim();
			if (text.equalsIgnoreCase(shippingMethod)) {
				value.findElement(By.xpath("./parent::tr/td[1]")).click();
			}
		}
		super.waitUntilPageLoad();

	}

	public payment goToPayment() {

		continueButton.click();
		payment objpayment = new payment(driver);
		return objpayment;
	}
}