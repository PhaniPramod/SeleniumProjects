package com.phanipramod.automation.projects.template.steps;

import com.phanipramod.automation.core.config.FrameworkConfig;
import com.phanipramod.automation.core.driver.DriverLifecycle;
import com.phanipramod.automation.core.driver.DriverManager;
import com.phanipramod.automation.projects.template.pages.TemplateHomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class TemplateSteps {
    private TemplateHomePage homePage;

    @Before
    public void startBrowser() {
        DriverLifecycle.startDriver();
        homePage = new TemplateHomePage(DriverManager.getDriver());
    }

    @After
    public void stopBrowser() {
        DriverLifecycle.quitDriver();
    }

    @Given("the configured application is opened")
    public void theConfiguredApplicationIsOpened() {
        homePage.openApplication(FrameworkConfig.baseUrl());
    }

    @Then("the page heading should be {string}")
    public void thePageHeadingShouldBe(String expectedHeading) {
        Assert.assertEquals(homePage.headingText(), expectedHeading);
    }
}
