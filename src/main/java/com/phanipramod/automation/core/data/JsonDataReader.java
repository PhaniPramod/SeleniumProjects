package com.phanipramod.automation.core.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public final class JsonDataReader {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonDataReader() {
    }

    public static <T> T read(String resourcePath, Class<T> targetType) {
        try (InputStream stream = JsonDataReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new IllegalArgumentException("Test data file not found: " + resourcePath);
            }
            return OBJECT_MAPPER.readValue(stream, targetType);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to read test data file: " + resourcePath, exception);
        }
    }
}
