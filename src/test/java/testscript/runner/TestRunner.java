package testscript.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import wrappers.CucumberWrapper;
import wrappers.TestNgWrapper;

@CucumberOptions(features = {"src/test/resources/features/testscriptDemo/"},
        glue = {"testscript.stepdefs", "wrappers", "pages"},
        monochrome = true,
        plugin = {"html:cucumberResults/cucumber-html-report", "json:cucumberResults/cucumber.json"},
        tags = "@addToCart"
)

public class TestRunner extends CucumberWrapper {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "scenarios")
    public void runFeature(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
	        testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
    }

    @DataProvider(name = "scenarios", parallel = false)
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();

    }

}
