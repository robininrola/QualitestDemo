package testscript.stepdefs;

import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.TestScriptDemoWishListPage;

public class TestScriptDemoWishListStepDef{

	TestScriptDemoWishListPage testScriptDemoWishListPage = new TestScriptDemoWishListPage(); 
	
	
	@When("I view my wishlist table")
	public void  I_view_my_wishlist_table() {
		testScriptDemoWishListPage.verifyWishListPage();
	}
	
	@Then("I find total four selected items in my Wishlist")
	public void I_find_total_four_selected_items_in_my_Wishlist() {
		Assert.assertEquals(4,testScriptDemoWishListPage.countWishListItems());;
	}
	
	@When("I search for lowest price product")
	public void  I_search_for_lowest_price_product() {
		
		testScriptDemoWishListPage.checlLowestPriceItemInWishList();
	}
	
	@And("I am able to add the lowest price item to my cart")
	public void  I_am_able_to_add_the_lowest_price_item_to_my_cart() {
		testScriptDemoWishListPage.clickLowestPriceAddToCart();
	}
	

}
