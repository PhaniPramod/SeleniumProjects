package com.phanipramod.automation.core.driver;

public final class DriverLifecycle {
    private DriverLifecycle() {
    }

    public static void startDriver() {
        DriverManager.setDriver(DriverFactory.createDriver());
    }

    public static void quitDriver() {
        try {
            DriverManager.getDriver().quit();
        } finally {
            DriverManager.removeDriver();
        }
    }
}
