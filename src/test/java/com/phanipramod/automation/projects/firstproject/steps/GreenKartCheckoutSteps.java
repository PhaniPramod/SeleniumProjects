package com.phanipramod.automation.projects.firstproject.steps;

import com.phanipramod.automation.core.config.FrameworkConfig;
import com.phanipramod.automation.core.data.JsonDataReader;
import com.phanipramod.automation.core.driver.DriverManager;
import com.phanipramod.automation.projects.firstproject.pages.GreenKartPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class GreenKartCheckoutSteps {
    private final GreenKartOrderData data = JsonDataReader.read(
            "projects/first project/data/order-data.json",
            GreenKartOrderData.class
    );
    private GreenKartPage greenKartPage;

    @Given("the GreenKart store is open")
    public void theGreenKartStoreIsOpen() {
        greenKartPage = new GreenKartPage(DriverManager.getDriver());
        greenKartPage.openStore(FrameworkConfig.baseUrl());
    }

    @When("I add configured products to the cart")
    public void iAddConfiguredProductsToTheCart() {
        greenKartPage.addProductsToCart(data.products());
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        greenKartPage.proceedToCheckout();
    }

    @When("I place the order from the cart summary")
    public void iPlaceTheOrderFromTheCartSummary() {
        greenKartPage.placeOrder();
    }

    @When("I select the configured country")
    public void iSelectTheConfiguredCountry() {
        greenKartPage.selectCountry(data.country());
    }

    @When("I agree to the terms and conditions")
    public void iAgreeToTheTermsAndConditions() {
        greenKartPage.agreeToTermsAndConditions();
    }

    @When("I proceed with the order")
    public void iProceedWithTheOrder() {
        greenKartPage.proceed();
    }

    @Then("the order success message should be displayed")
    public void theOrderSuccessMessageShouldBeDisplayed() {
        Assertions.assertTrue(
                greenKartPage.successMessage().contains(data.expectedSuccessMessage()),
                "Order success message was not displayed."
        );
    }
}
