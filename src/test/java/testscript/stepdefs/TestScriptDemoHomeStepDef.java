package testscript.stepdefs;

import cucumber.api.java.en.Given;
import pages.TestScriptDemoHomePage;

public class TestScriptDemoHomeStepDef{
	
	TestScriptDemoHomePage testScriptDemoHomePage = new TestScriptDemoHomePage(); 
	
	
	@Given("I add four different products to my wish list")
	public void  I_add_four_different_products_to_my_wish_list() {
		testScriptDemoHomePage.clickAddToWishList();
		testScriptDemoHomePage.clickWishList();
	}
	
	
}
