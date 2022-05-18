package testscript.stepdefs;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import pages.TestScriptDemoAddToCartPage;
import pages.TestScriptDemoHomePage;

public class TestScriptDemoCartStepDef{
	
	TestScriptDemoHomePage testScriptDemoHomePage = new TestScriptDemoHomePage(); 
	TestScriptDemoAddToCartPage testScriptDemoAddToCartPage = new TestScriptDemoAddToCartPage(); 
	

	@Then("I am able to verify the item in my cart")
	public void  I_am_able_to_verify_the_item_in_my_cart() {
		testScriptDemoHomePage.clickCart();
		Assert.assertEquals(1, testScriptDemoAddToCartPage.countCartItems());
		
	}

}
