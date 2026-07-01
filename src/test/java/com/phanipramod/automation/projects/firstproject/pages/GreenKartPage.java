package com.phanipramod.automation.projects.firstproject.pages;

import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.ADD_TO_CART_BUTTON;
import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.CART_ICON;
import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.COUNTRY_DROPDOWN;
import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.PLACE_ORDER_BUTTON;
import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.PROCEED_BUTTON;
import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.PROCEED_TO_CHECKOUT_BUTTON;
import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.PRODUCT_CARD;
import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.PRODUCT_NAME;
import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.SUCCESS_MESSAGE;
import static com.phanipramod.automation.projects.firstproject.locators.GreenKartLocators.TERMS_CHECKBOX;

import com.phanipramod.automation.core.pages.BasePage;
import com.phanipramod.automation.core.wait.Waits;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class GreenKartPage extends BasePage {
    public GreenKartPage(WebDriver driver) {
        super(driver);
    }

    public void openStore(String url) {
        open(url);
        Waits.until(driver, ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_CARD));
    }

    public void addProductsToCart(List<String> productNames) {
        for (String productName : productNames) {
            WebElement productCard = productCardFor(productName);
            click(productCard.findElement(ADD_TO_CART_BUTTON));
        }
    }

    public void proceedToCheckout() {
        click(CART_ICON);
        click(PROCEED_TO_CHECKOUT_BUTTON);
    }

    public void placeOrder() {
        click(PLACE_ORDER_BUTTON);
    }

    public void selectCountry(String country) {
        WebElement countryDropdown = Waits.visible(driver, COUNTRY_DROPDOWN);
        new Select(countryDropdown).selectByVisibleText(country);
    }

    public void agreeToTermsAndConditions() {
        WebElement termsCheckbox = Waits.visible(driver, TERMS_CHECKBOX);
        if (!termsCheckbox.isSelected()) {
            click(termsCheckbox);
        }
    }

    public void proceed() {
        click(PROCEED_BUTTON);
    }

    public String successMessage() {
        return textOf(SUCCESS_MESSAGE);
    }

    private WebElement productCardFor(String expectedName) {
        List<WebElement> products = Waits.until(driver, ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_CARD));

        for (WebElement product : products) {
            String actualName = product.findElement(PRODUCT_NAME).getText().split("-")[0].trim();
            if (actualName.equalsIgnoreCase(expectedName)) {
                return product;
            }
        }

        throw new NoSuchElementException("Product not found: " + expectedName);
    }
}
