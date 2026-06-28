package com.phanipramod.automation.core.driver;

import com.phanipramod.automation.core.config.FrameworkConfig;
import java.net.MalformedURLException;
import java.net.URI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class DriverFactory {
    private DriverFactory() {
    }

    public static WebDriver createDriver() {
        AbstractDriverOptions<?> options = createOptions();

        if ("remote".equalsIgnoreCase(FrameworkConfig.execution())) {
            try {
                return new RemoteWebDriver(URI.create(FrameworkConfig.gridUrl()).toURL(), options);
            } catch (MalformedURLException exception) {
                throw new IllegalArgumentException("Invalid Selenium Grid URL: " + FrameworkConfig.gridUrl(), exception);
            }
        }

        return createLocalDriver(options);
    }

    private static AbstractDriverOptions<?> createOptions() {
        String browser = FrameworkConfig.browser().toLowerCase();
        boolean headless = FrameworkConfig.headless();

        switch (browser) {
            case "firefox": {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("-headless");
                }
                return options;
            }
            case "edge": {
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                return options;
            }
            case "chrome": {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                options.addArguments("--disable-gpu", "--window-size=1440,900");
                return options;
            }
            default:
                throw new IllegalArgumentException("Unsupported browser: " + FrameworkConfig.browser());
        }
    }

    private static WebDriver createLocalDriver(AbstractDriverOptions<?> options) {
        String browser = FrameworkConfig.browser().toLowerCase();

        switch (browser) {
            case "firefox":
                return new FirefoxDriver((FirefoxOptions) options);
            case "edge":
                return new EdgeDriver((EdgeOptions) options);
            case "chrome":
                return new ChromeDriver((ChromeOptions) options);
            default:
                throw new IllegalArgumentException("Unsupported browser: " + FrameworkConfig.browser());
        }
    }
}
