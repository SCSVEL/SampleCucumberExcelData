package SampleCucumber.StepDefs;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Stepdefs {
    private WebDriver cDriver;
    private WebDriverWait cWait;
    private Scenario currScenario;

    @Before
    public void before(Scenario scenario) {
        currScenario = scenario;
    }


    @Given("^User searching for (.*)")
    public void search1(String txt) {
//        cDriver = RunCucumberTest.driver;
//        cWait = new WebDriverWait(cDriver, Duration.ofSeconds(10));
//        cDriver.get("https://google.com");
//
//        WebElement element = cDriver.findElement(By.name("q"));
//        cWait.until(ExpectedConditions.visibilityOf(element));
//        element.sendKeys("abc" + Keys.RETURN);
        currScenario.write("---------------------------------");
        currScenario.write("EXECUTED FOR: " + txt);
        currScenario.write("---------------------------------");
    }
}
