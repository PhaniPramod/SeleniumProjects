package com.phanipramod.automation.projects.firstproject.steps;

import com.phanipramod.automation.core.driver.DriverLifecycle;
import com.phanipramod.automation.core.driver.DriverManager;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class GreenKartHooks {
    @Before
    public void setUpBrowser() {
        DriverLifecycle.startDriver();
    }

    @After
    public void tearDownBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            attachScreenshot();
        }
        DriverLifecycle.quitDriver();
    }

    private void attachScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("failure-screenshot", new ByteArrayInputStream(screenshot));
        } catch (RuntimeException ignored) {
            Allure.addAttachment("failure-context", "No browser screenshot was available for this failure.");
        }
    }
}
