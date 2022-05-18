package pages;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import wrappers.SeleniumWrapper;

public class TestScriptDemoHomePage extends SeleniumWrapper{
	
	RemoteWebDriver driver;
	 
    @FindBy(xpath = "(//a[@title='Wishlist'])[2]")
    WebElement wishList;
 
    @FindBy(xpath = "(//a[@title='Cart'])[2]")
    WebElement cart;

    
    String wishListClassPath = "add_to_wishlist single_add_to_wishlist";
    String wishListAddedItems = "//*[@class='product-name']";
    
    public TestScriptDemoHomePage() {
        this.driver = getDriver();
        PageFactory.initElements(driver, this);
    }
 
    public void clickAddToWishList() {
    	WebDriverWait wait = new WebDriverWait(driver, 40);
    	List<WebElement> wishListElement = driver.findElementsByClassName(wishListClassPath);
    	for(int i = 1; i >= 4; i++) {
    		wishListElement.get(i).click();
    		WebElement productAdded = driver.findElementByXPath("//span[contains(.,'Product added!')])["+i+"]");
    		wait.until(ExpectedConditions.visibilityOf(productAdded));
    	}
    }
 
    public void clickWishList() {
    	wishList.click();
    }
 
    
    public void clickCart() {
    	cart.click();
    }

}
