package com.phanipramod.automation.core.api;

import static io.restassured.RestAssured.given;

import com.phanipramod.automation.core.config.FrameworkConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {
    private final String baseUrl;

    public ApiClient() {
        this(FrameworkConfig.apiBaseUrl());
    }

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response get(String path) {
        return request().get(path);
    }

    public Response post(String path, Object body) {
        return request().body(body).post(path);
    }

    public Response put(String path, Object body) {
        return request().body(body).put(path);
    }

    public Response patch(String path, Object body) {
        return request().body(body).patch(path);
    }

    public Response delete(String path) {
        return request().delete(path);
    }

    private RequestSpecification request() {
        return given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .accept("application/json")
                .log().ifValidationFails();
    }
}
