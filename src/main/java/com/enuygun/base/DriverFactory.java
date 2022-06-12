package com.enuygun.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public final class DriverFactory {

    private DriverFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static WebDriver getLocalWebDriver(String driverType) {
        switch (driverType){
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(OptionsFactory.getChromeOptions());
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(OptionsFactory.getFirefoxOptions());
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(OptionsFactory.getEdgeOptions());
            default:
                throw new IllegalArgumentException();
        }
    }

    public static WebDriver getRemoteDriver(String driverType) throws MalformedURLException {

        final String REMOTE_URL="http://localhost:4444";

        switch (driverType){
            case "CHROME":
                return new RemoteWebDriver(new URL(REMOTE_URL), OptionsFactory.getChromeOptions());
            case "FIREFOX":
                return new RemoteWebDriver(new URL(REMOTE_URL), OptionsFactory.getFirefoxOptions());
            case "EDGE":
                return new RemoteWebDriver(new URL(REMOTE_URL), OptionsFactory.getEdgeOptions());
            default:
                throw new IllegalArgumentException();
        }
    }




}
