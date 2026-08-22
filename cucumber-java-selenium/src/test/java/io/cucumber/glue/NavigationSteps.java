package io.cucumber.glue;

import io.cucumber.java.en.Given;
import io.cucumber.core.Context;
import io.cucumber.core.Manager;

public class NavigationSteps extends Context {

  public NavigationSteps(Manager manager) {
    super(manager);
  }

  @Given("^the page under test is '(.+)'$")
  public void navToPage(String url) {
    manager.getDriver().get(url);
    stash("exampleKey1", "exampleValue1");
  }
}