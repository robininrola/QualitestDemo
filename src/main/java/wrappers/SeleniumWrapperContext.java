package wrappers;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This is an interface for testing framework.
 */
public interface SeleniumWrapperContext {

    RemoteWebDriver getDriver();

    void setDriver(SeleniumWrapper wrappers);

    RemoteWebDriver invokeApp();
}
