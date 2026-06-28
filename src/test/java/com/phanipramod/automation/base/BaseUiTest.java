package com.phanipramod.automation.base;

import com.phanipramod.automation.core.driver.DriverLifecycle;
import com.phanipramod.automation.core.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseUiTest {
    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        DriverLifecycle.startDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        DriverLifecycle.quitDriver();
    }

    protected WebDriver driver() {
        return DriverManager.getDriver();
    }
}
