package com.enuygun.base;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;


public final class OptionsFactory {

    private static final String KIOSK = "--kiosk";
    private static final String DISABLE_NOTIFICATIONS = "--disable-notifications";
    private static final String START_FULLSCREEN = "--start-fullscreen";

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(KIOSK,DISABLE_NOTIFICATIONS,START_FULLSCREEN);

        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(KIOSK,DISABLE_NOTIFICATIONS,START_FULLSCREEN);

        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(KIOSK,DISABLE_NOTIFICATIONS,START_FULLSCREEN);
        return options;
    }

    private OptionsFactory() {}



}