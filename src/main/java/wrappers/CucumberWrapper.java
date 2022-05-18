package wrappers;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * This class represents the cucumber framework.
 */
public class CucumberWrapper extends TestNgWrapper {
	
	public static boolean flag;

    /**
     * This method is launch before the test case to just setup the environment.
     *
     * @param sc Scenarios object that will be executed.
     *
     * @throws InterruptedException This can be thrown when the test execution is interrupted.
     */
    @Before
    public void launchBrowser(Scenario sc) throws InterruptedException {
        testCaseName = sc.getName();
        invokeApp();
        startTestCase(sc.getName(), sc.getId(), environmentType);
        flag = true;
    }

    @After
    public void executeAfterScenario(Scenario sc) {
    	if(!sc.isFailed()) {
    		reportStep("Scenario is passed", Status.PASS);	
    	}else {
    		reportStep("Scenario is failed", Status.FAIL);
    	}
    	
    }
    
    
}
