package com.phanipramod.automation.core.pages;

import com.phanipramod.automation.core.wait.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected void click(WebElement element) {
        Waits.clickable(driver, element).click();
    }

    protected void type(WebElement element, String value) {
        WebElement visibleElement = Waits.visible(driver, element);
        visibleElement.clear();
        visibleElement.sendKeys(value);
    }

    protected String textOf(WebElement element) {
        return Waits.visible(driver, element).getText();
    }

    protected boolean isVisible(WebElement element) {
        return Waits.visible(driver, element).isDisplayed();
    }
}
