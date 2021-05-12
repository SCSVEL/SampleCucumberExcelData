package SampleCucumber;

import cucumber.api.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import utils.CucumberCustomRunner;
import utils.CustomFeature;
import utils.CustomHooks.AfterSuite;
import utils.CustomHooks.BeforeSuite;

@RunWith(CucumberCustomRunner.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/ModifiedFeatures",
        glue = "SampleCucumber/StepDefs",
        tags = {"@SHAN"}
)
public class RunCucumberTest {
    public static WebDriver driver;
    static CustomFeature feature;

    @BeforeClass
    public static void before() {
//        System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
//        driver = new InternetExplorerDriver();
    }

    @AfterClass
    public static void after() {
//        driver.close();
//        driver.quit();
    }

    @BeforeSuite
    public static void updateFeaturesWithData() {
        feature = new CustomFeature();
        feature.deleteFiles(); //clean old modified features if any

        feature.getFeaturesFiles();
        feature.updateAllFiles();
    }

    @AfterSuite
    public static void cleanTempFiles() {
        feature.deleteFiles();
    }
}
