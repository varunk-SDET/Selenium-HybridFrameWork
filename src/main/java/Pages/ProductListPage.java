package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage {
	WebDriver driver;
	WebElement product;
	Actions a;
	@FindBy(xpath = "//li[contains(@class,'product-item')]")
	List<WebElement> products;

	public ProductListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		a = new Actions(driver);
	}

	public static String getPrice(WebElement e) {
		String value = e.findElement(By.xpath("following-sibling::td[1]")).getText();
		return value;

	}
	
	
	public void findProduct(String productName) {
		product = products.stream()
				.filter(s -> s.findElement(By.xpath(".//strong[contains(@class,'product-item-name')]/a")).getText()
						.equals(productName))
				.findFirst().orElse(null);

//		List<String> itemPrice;
//		do {
//			Thread.sleep(5000L);
//			List<WebElement> products = driver.findElements(By.xpath(".//strong[contains(@class,'product-item-name')]/a"));
//			itemPrice = products.stream().filter(s -> s.getText().equalsIgnoreCase("Almond")).map(s -> getPrice(s))
//					.collect(Collectors.toList());
//
//			itemPrice.forEach(a -> System.out.println(a));
//			if (itemPrice.size() < 1) {
//				driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
//			}
//
//		} while (itemPrice.size() < 1);

	}

	public ProductDetailsPage selectProduct() {
		a.scrollToElement(product).build().perform();
		a.moveToElement(product).perform();
		WebElement addToCart = product.findElement(By.xpath(".//button[@type='submit' and @title='Add to Cart']/span"));
		addToCart.click();

		ProductDetailsPage objProductDetailsPage = new ProductDetailsPage(driver);
		return objProductDetailsPage;
	}
}
