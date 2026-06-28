package com.phanipramod.automation.core.reporting;

import com.phanipramod.automation.core.driver.DriverManager;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot();
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
