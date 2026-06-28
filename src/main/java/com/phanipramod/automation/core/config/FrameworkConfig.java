package com.phanipramod.automation.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class FrameworkConfig {
    private static final Properties PROJECT_PROPERTIES = loadProjectProperties();

    private FrameworkConfig() {
    }

    public static String browser() {
        return value("browser", "chrome");
    }

    public static String execution() {
        return value("execution", "local");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(value("headless", "false"));
    }

    public static String baseUrl() {
        return value("base.url", "https://example.com");
    }

    public static String apiBaseUrl() {
        return value("api.base.url", "https://jsonplaceholder.typicode.com");
    }

    public static String gridUrl() {
        return value("grid.url", "http://localhost:4444/wd/hub");
    }

    public static int timeoutSeconds() {
        return Integer.parseInt(value("timeout.seconds", "20"));
    }

    public static String projectName() {
        return value("project.name", "template");
    }

    public static String value(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }

        String projectValue = PROJECT_PROPERTIES.getProperty(key);
        if (projectValue != null && !projectValue.isBlank()) {
            return projectValue.trim();
        }

        return defaultValue;
    }

    private static Properties loadProjectProperties() {
        Properties properties = new Properties();
        String projectName = System.getProperty("project.name", "template");
        String resourcePath = "projects/" + projectName + "/config.properties";

        try (InputStream stream = FrameworkConfig.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (stream != null) {
                properties.load(stream);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load project configuration: " + resourcePath, exception);
        }

        return properties;
    }
}
