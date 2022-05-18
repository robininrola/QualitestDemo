package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import wrappers.SeleniumWrapper;

public class TestScriptDemoWishListPage extends SeleniumWrapper{
	
	RemoteWebDriver driver;
	 
	@FindBy(className = "single-title")
	WebElement wishListLog;
	
	@FindBy(xpath = "//div[contains(text(),'Product added to cart successfully')]")
	WebElement productAddedSuccessfully;
	
	String wishListAddedItems = "//*[@class='product-name']";
	
	String wishListPriceCheck = "(//ul[@class='shop_table cart wishlist_table wishlist_view responsive mobile  ']//li[listValue]//*[@class='woocommerce-Price-amount amount'])";
	
	String wishListItems = "(//ul[@class='shop_table cart wishlist_table wishlist_view responsive mobile  ']//li//*[@class='product-name'])";
	
	String productAddToCart = "(//ul[@class='shop_table cart wishlist_table wishlist_view responsive mobile  ']//li//*[@class='product-add-to-cart'])";
	
	static int count = 0;
    
    public TestScriptDemoWishListPage() {
        this.driver = getDriver();
        PageFactory.initElements(driver, this);
    }
	
	public void verifyWishListPage() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(wishListLog));
	}
	

    public int countWishListItems() {
        return driver.findElementsByXPath(wishListAddedItems).size();
    }
	
	public void checlLowestPriceItemInWishList() {
		
		List<WebElement> wishListItemsWebElements = driver.findElementsByXPath(wishListItems);
		
		int price = 0;
		
		for(int i = 1; i <= wishListItemsWebElements.size() ; i++) {
			
			String priceCheckXpath = wishListPriceCheck.replace("listValue", String.valueOf(i));
			
			List<WebElement> priceCheckWebElements = driver.findElementsByXPath(priceCheckXpath);
 		
			for(int j = 1; j <= priceCheckWebElements.size() ; j++) {
				if(price < Integer.valueOf(priceCheckWebElements.get(j).getText())) {
					count = i;
					break;
				}
			}
			
		}
		
		
		
	}
	
	public void clickLowestPriceAddToCart() {
		
		driver.findElementByXPath(productAddToCart+"["+count+"]").click();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(productAddedSuccessfully));
		
	}
	

}
