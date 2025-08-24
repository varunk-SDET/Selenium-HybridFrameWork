package HyridFrameWork.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/HybridFrameWork/cucumber",glue = "HybridFrameWork.StepDefinition", monochrome=true, plugin= {"html: target/cucumber.html"}, tags="@TC_001")
public class testCase_PlaceOrder extends AbstractTestNGCucumberTests {

}
