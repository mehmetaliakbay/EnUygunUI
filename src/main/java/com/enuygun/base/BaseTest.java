package com.enuygun.base;

import com.enuygun.utils.TestListener;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;

@Listeners(TestListener.class)
public class BaseTest {


    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
     protected final String WEB_URL="https://www.enuygun.com/";

    @BeforeMethod(alwaysRun = true)
    public void setup() throws MalformedURLException {
        PropertyConfigurator.configure("log4j.properties");
        WebDriver driver=DriverFactory.getRemoteDriver("CHROME");
        driverThreadLocal.set(driver);
        getDriver().get(WEB_URL);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver()!=null)
            getDriver().quit();

    }

    @AfterClass
    public void terminate() {
        if (driverThreadLocal!=null){
            driverThreadLocal.remove();
        }
    }

}
