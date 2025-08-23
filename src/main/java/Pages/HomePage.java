package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.AbstractComponents;



public class HomePage extends AbstractComponents {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//li[@class='authorization-link']/a[contains(text(),'Sign In')])[1]")
	WebElement signInBtn;

	public LoginPage clickSigninBtn() {

		waitUntilClickable(signInBtn);
		signInBtn.click();
		LoginPage objLoginPage = new LoginPage(driver);
		return objLoginPage;
	}

	public void homePageCheck() {
		driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/']")).isDisplayed();
	}

	public ProductListPage selectcategory() {
		driver.findElement(By.xpath(
				"//li[contains(@class,'category-item') and contains(@class, 'level0')]//span[contains(text(),'Men')]"))
				.click();
		waitUntilPageLoad();
		driver.findElement(By.xpath(
				"//*[@href=\"https://magento.softwaretestingboard.com/men/tops-men.html\" and contains(text(),'Tops')]"))
				.click();
		ProductListPage objProductListPage = new ProductListPage(driver);
		return objProductListPage;

	}

}
