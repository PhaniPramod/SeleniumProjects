package com.phanipramod.automation.projects.template.tests;

import com.phanipramod.automation.base.BaseUiTest;
import com.phanipramod.automation.core.config.FrameworkConfig;
import com.phanipramod.automation.core.data.JsonDataReader;
import com.phanipramod.automation.projects.template.pages.TemplateHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TemplateUiTest extends BaseUiTest {
    @Test(groups = {"ui", "smoke"})
    public void shouldOpenConfiguredApplication() {
        TemplateData data = JsonDataReader.read("projects/template/data/template-data.json", TemplateData.class);
        TemplateHomePage homePage = new TemplateHomePage(driver());

        homePage.openApplication(FrameworkConfig.baseUrl());

        Assert.assertEquals(homePage.headingText(), data.expectedHeading());
    }
}
