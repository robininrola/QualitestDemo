package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import wrappers.SeleniumWrapper;

public class TestScriptDemoAddToCartPage extends SeleniumWrapper {
	
	RemoteWebDriver driver;
	 
	@FindBy(className = "single-title")
	WebElement cartLog;
	
	String cartItemCount = "//table[@class='shop_table shop_table_responsive cart woocommerce-cart-form__contents']/tbody/tr";

    
    public TestScriptDemoAddToCartPage() {
        this.driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    
    public void verifyCartPage() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(cartLog));
	}
    

    public int countCartItems() {
        return driver.findElementsByXPath(cartItemCount).size() - 1;
    }
    
    
    
}
