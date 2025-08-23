package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstractComponents;

public class ProductDetailsPage extends AbstractComponents {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//div[@class='swatch-option text']")
	List<WebElement> sizeOptions;
	
	@FindBy(xpath = "//div[@class='swatch-option color']")
	List<WebElement> colorOptions;
	
	@FindBy(xpath = "//button[@type='submit' and @title='Add to Cart']")
	WebElement addToCart;
	
	@FindBy(xpath = "//div[@data-ui-id='message-success']")
	WebElement successMessage;
	
	@FindBy(linkText = "shopping cart")
	WebElement shoppingCart;
	
	
	public void selectSizeOption(String sizeOption) {
		
		waitUntilPageLoad();
	    for (WebElement size : sizeOptions) {
	        if (size.getText().trim().equalsIgnoreCase(sizeOption)) {
	        	
	            size.click();
	            break;
	        }
	    }
	}
	
	public void selectColorOption(String colorOption) {
	    for (WebElement color : colorOptions) {
	        if (color.getDomAttribute("option-label").trim().equalsIgnoreCase(colorOption)) {
	       
	        	color.click();
	            break;
	        }
	    }
	}
	
	public void addProductToCart() {
		
		addToCart.click();
	}
	
	public void verifyAddSuccessMsg(String productName) {
		waitUntilVisible(successMessage);
		assert successMessage.getText().equals("You added " + productName + " to your shopping cart.");

	}
	
	public CartPage goToShoppingCart() {
		shoppingCart.click();
		CartPage objCartPage = new CartPage(driver);
		return objCartPage;
	}

}
