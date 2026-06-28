package com.phanipramod.automation.projects.firstproject.pages;

import com.phanipramod.automation.core.pages.BasePage;
import com.phanipramod.automation.core.wait.Waits;
import io.qameta.allure.Allure;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class GreenKartPage extends BasePage {
    private static final By PRODUCT_CARD = By.cssSelector(".products .product");
    private static final By PRODUCT_NAME = By.cssSelector(".product-name");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector(".product-action button");

    @FindBy(css = "a.cart-icon")
    private WebElement cartIcon;

    @FindBy(xpath = "//button[normalize-space()='PROCEED TO CHECKOUT']")
    private WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//button[normalize-space()='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(tagName = "select")
    private WebElement countryDropdown;

    @FindBy(css = ".chkAgree")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//button[normalize-space()='Proceed']")
    private WebElement proceedButton;

    @FindBy(css = ".wrapperTwo")
    private WebElement successMessage;

    public GreenKartPage(WebDriver driver) {
        super(driver);
    }

    public void openStore(String url) {
        Allure.step("Open GreenKart store: " + url, () -> {
            open(url);
            Waits.until(driver, ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_CARD));
        });
    }

    public void addProductsToCart(List<String> productNames) {
        Allure.step("Add products to cart: " + productNames, () -> {
            for (String productName : productNames) {
                Allure.step("Add product to cart: " + productName, () -> {
                    WebElement productCard = productCardFor(productName);
                    click(productCard.findElement(ADD_TO_CART_BUTTON));
                });
            }
        });
    }

    public void proceedToCheckout() {
        Allure.step("Open cart and proceed to checkout", () -> {
            click(cartIcon);
            click(proceedToCheckoutButton);
        });
    }

    public void placeOrder() {
        Allure.step("Place order from cart summary", () -> click(placeOrderButton));
    }

    public void selectCountry(String country) {
        Allure.step("Select country: " + country, () -> {
            Waits.visible(driver, countryDropdown);
            new Select(countryDropdown).selectByVisibleText(country);
        });
    }

    public void agreeToTermsAndConditions() {
        Allure.step("Agree to terms and conditions", () -> {
            if (!termsCheckbox.isSelected()) {
                click(termsCheckbox);
            }
        });
    }

    public void proceed() {
        Allure.step("Proceed with order", () -> click(proceedButton));
    }

    public String successMessage() {
        Allure.step("Read order success message");
        return textOf(successMessage);
    }

    private WebElement productCardFor(String expectedName) {
        return Waits.until(driver, ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_CARD))
                .stream()
                .filter(card -> productName(card).contains(normalized(expectedName)))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found: " + expectedName));
    }

    private String productName(WebElement productCard) {
        return normalized(productCard.findElement(PRODUCT_NAME).getText().split("-")[0].trim());
    }

    private String normalized(String value) {
        return value.toLowerCase(Locale.ROOT);
    }
}
