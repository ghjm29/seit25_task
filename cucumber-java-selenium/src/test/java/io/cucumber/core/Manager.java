package io.cucumber.core;

import org.openqa.selenium.chrome.ChromeDriver;

public class Manager {

  private ChromeDriver driver;

  public ChromeDriver getDriver() {
    return driver;
  }

  public void setDriver(ChromeDriver driver) {
    this.driver = driver;
  }
}