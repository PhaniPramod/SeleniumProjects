package com.phanipramod.automation.core.pages;

import com.phanipramod.automation.core.wait.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected void click(WebElement element) {
        Waits.clickable(driver, element).click();
    }

    protected void click(By locator) {
        Waits.clickable(driver, locator).click();
    }

    protected String textOf(By locator) {
        return Waits.visible(driver, locator).getText();
    }
}
