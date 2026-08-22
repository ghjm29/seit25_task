package io.cucumber.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestEnvironment {

  private TestEnvironment() {
  }

  public static String get(String key) {
    Properties properties = new Properties();

    try (FileInputStream input = new FileInputStream(".env")) {
      properties.load(input);
    } catch (IOException e) {
      throw new RuntimeException("Unable to read .env file", e);
    }

    String value = properties.getProperty(key);

    if (value == null || value.isBlank()) {
      throw new RuntimeException("Value not found in .env: " + key);
    }

    return value;
  }
}
