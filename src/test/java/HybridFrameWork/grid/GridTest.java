package HybridFrameWork.grid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GridTest {
    @Test
    public void runOnGrid() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        // Connect to Selenium Grid Hub
        WebDriver driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"), options);

        driver.get("https://www.google.com");
        System.out.println("Title is: " + driver.getTitle());

        driver.quit();
    }
}
