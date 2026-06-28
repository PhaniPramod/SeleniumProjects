package com.phanipramod.automation.projects.firstproject.tests;

import com.phanipramod.automation.base.BaseUiTest;
import com.phanipramod.automation.core.config.FrameworkConfig;
import com.phanipramod.automation.core.data.JsonDataReader;
import com.phanipramod.automation.projects.firstproject.pages.GreenKartPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GreenKartCheckoutTest extends BaseUiTest {
    @Feature("GreenKart checkout")
    @Story("Place order successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Adds products to cart, checks out, selects Canada, accepts terms, places the order, and verifies the success message.")
    @Test(groups = {"ui", "smoke"})
    public void shouldPlaceGreenKartOrderSuccessfully() {
        GreenKartOrderData data = JsonDataReader.read(
                "projects/first project/data/order-data.json",
                GreenKartOrderData.class
        );
        GreenKartPage greenKartPage = new GreenKartPage(driver());

        greenKartPage.openStore(FrameworkConfig.baseUrl());
        greenKartPage.addProductsToCart(data.products());
        greenKartPage.proceedToCheckout();
        greenKartPage.placeOrder();
        greenKartPage.selectCountry(data.country());
        greenKartPage.agreeToTermsAndConditions();
        greenKartPage.proceed();

        Allure.step("Verify order success message contains: " + data.expectedSuccessMessage(), () ->
                Assert.assertTrue(
                        greenKartPage.successMessage().contains(data.expectedSuccessMessage()),
                        "Order success message was not displayed."
                )
        );
    }
}
