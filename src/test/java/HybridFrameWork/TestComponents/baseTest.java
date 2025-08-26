package HybridFrameWork.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {

	WebDriver driver;
	public HomePage objHomePage;

	public WebDriver initializeDriver() throws IOException, URISyntaxException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//configProp//config.properties");
		prop.load(fis);

		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			
			ChromeOptions options = new ChromeOptions();

	        driver = new RemoteWebDriver(
	                new URL("http://localhost:4444/wd/hub"), options);
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("WebDriver.edge.driver", "C:\\Hybrid_Framework_Components");
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public HomePage launchApplication() throws IOException, URISyntaxException {
		driver = initializeDriver();
		objHomePage = new HomePage(driver);
		return objHomePage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws IOException {
		driver.close();

	}

	

	public List<HashMap<String, String>> getJsonDataToMap(String file) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(file), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(null);
		File des = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, des);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

}