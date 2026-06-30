package com.phanipramod.automation.projects.firstproject.steps;

import java.util.List;

public record GreenKartOrderData(
        List<String> products,
        String country,
        String expectedSuccessMessage
) {
}
