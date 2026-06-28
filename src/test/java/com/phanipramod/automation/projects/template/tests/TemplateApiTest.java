package com.phanipramod.automation.projects.template.tests;

import com.phanipramod.automation.base.BaseApiTest;
import com.phanipramod.automation.core.data.JsonDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TemplateApiTest extends BaseApiTest {
    @Test(groups = {"api", "smoke"})
    public void shouldReturnConfiguredApiStatusCode() {
        TemplateData data = JsonDataReader.read("projects/template/data/template-data.json", TemplateData.class);

        int statusCode = apiClient.get("/posts/1").statusCode();

        Assert.assertEquals(statusCode, data.expectedStatusCode());
    }
}
