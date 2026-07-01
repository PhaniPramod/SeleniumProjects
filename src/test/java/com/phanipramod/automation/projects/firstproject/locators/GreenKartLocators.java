package com.phanipramod.automation.projects.firstproject.locators;

import org.openqa.selenium.By;

public class GreenKartLocators {
    public static final By PRODUCT_CARD = By.cssSelector(".products .product");
    public static final By PRODUCT_NAME = By.cssSelector(".product-name");
    public static final By ADD_TO_CART_BUTTON = By.cssSelector(".product-action button");
    public static final By CART_ICON = By.cssSelector("a.cart-icon");
    public static final By PROCEED_TO_CHECKOUT_BUTTON = By.xpath("//button[normalize-space()='PROCEED TO CHECKOUT']");
    public static final By PLACE_ORDER_BUTTON = By.xpath("//button[normalize-space()='Place Order']");
    public static final By COUNTRY_DROPDOWN = By.tagName("select");
    public static final By TERMS_CHECKBOX = By.cssSelector(".chkAgree");
    public static final By PROCEED_BUTTON = By.xpath("//button[normalize-space()='Proceed']");
    public static final By SUCCESS_MESSAGE = By.cssSelector(".wrapperTwo");
}
