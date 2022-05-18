package wrappers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Reporter;


/**
 * This is a wrapper for selenium framework and can be used for other framework./SeleniumWrapperContext
 */
public class SeleniumWrapper extends Reporter implements SeleniumWrapperContext {

    protected static final ThreadLocal<SeleniumWrapper> driverThreadLocal = new ThreadLocal<SeleniumWrapper>();
    public static String appURL;
    public static String apiHostName;
    protected static String environmentType;
    public RemoteWebDriver driver;
    protected Properties prop;
    private String browserName;
    private String executionType;
    
    public SeleniumWrapper() {
        try {
            prop = new Properties();
            InputStream inputStream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream("runnerconfig/DriverConfig.properties");
            prop.load(inputStream);
            browserName = prop.getProperty(WrapperConstants.BROWSER_NAME);
            appURL = prop.getProperty(WrapperConstants.APPLICATION_URL);
            executionType = prop.getProperty(WrapperConstants.EXECUTION_TYPE);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public RemoteWebDriver getDriver() {
        return driverThreadLocal.get().driver;
    }

    public void setDriver(SeleniumWrapper wrappers) {
        driverThreadLocal.set(wrappers);
    }
    
    public synchronized RemoteWebDriver invokeApp() {

    
           if ("chrome".equalsIgnoreCase(browserName)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else {
            	WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
        
        SeleniumWrapper seleniumWrapper = new SeleniumWrapper();
        seleniumWrapper.driver = driver;
        setDriver(seleniumWrapper);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get(appURL);
        return driver;
    }
   
}
