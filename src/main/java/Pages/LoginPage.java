package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	WebElement email;

	@FindBy(id = "pass")
	WebElement pass;

	@FindBy(xpath = "//button[@id='send2' and contains(@class,'primary')]")
	WebElement signInBtn;
	
	@FindBy(xpath = "//*[@data-ui-id='message-error']")
	WebElement loginFailureMessage;

	public void login(String mail, String password) {
		email.sendKeys(mail);
		pass.sendKeys(password);
		waitUntilClickable(signInBtn);
		signInBtn.click();
		
	}
	
	public void checkLoginFailure() {
		waitUntilVisible(loginFailureMessage);
		loginFailureMessage.isDisplayed();
	}
}
