package com.phanipramod.automation.projects.firstproject.tests;

import java.util.List;

public record GreenKartOrderData(
        List<String> products,
        String country,
        String expectedSuccessMessage
) {
}
