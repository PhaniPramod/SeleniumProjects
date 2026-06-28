package com.phanipramod.automation.projects.template.pages;

import com.phanipramod.automation.core.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplateHomePage extends BasePage {
    @FindBy(css = "h1")
    private WebElement heading;

    public TemplateHomePage(WebDriver driver) {
        super(driver);
    }

    public void openApplication(String url) {
        open(url);
    }

    public String headingText() {
        return textOf(heading);
    }
}
