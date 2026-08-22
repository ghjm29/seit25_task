package io.cucumber.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.core.TestEnvironment;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.pages.BasicAuthPage;
import io.cucumber.pages.HomePage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeSteps extends Context {

  private HomePage homePage;

  public HomeSteps(Manager manager) {
    super(manager);
  }

  @Given("an example Home Page step")
  public void exampleHomePageStep() {
    System.out.println("printing shared stash :" + getTestStash().toString());
  }

  @Given("the {string} homepage is open")
  public void homePageIsOpen(String url){
    homePage = new HomePage(manager.getDriver());
    homePage.navigateToHomePage(url);
  }

  @Then("homepage should display the following links")
  public void homepageShouldDisplayTheLinks(DataTable dt){
    List<String> expectedLinks = dt.asList(String.class);
    List<String> actualLinks = homePage.getExampleLinkTexts();
    System.out.println(expectedLinks);

    List<String> missingLinks = new ArrayList<>(expectedLinks);
    List<String> unexpectedLinks = new ArrayList<>(actualLinks);

    for (String actualLink : actualLinks) {
      missingLinks.remove(actualLink);
    }

    for (String expectedLink : expectedLinks) {
      unexpectedLinks.remove(expectedLink);
    }

    assertTrue(
            missingLinks.isEmpty() && unexpectedLinks.isEmpty(),
            "Missing links: " + missingLinks
                    + "; Unexpected links: " + unexpectedLinks
    );
  }

  @When("the {string} example is opened with username from {string} and password from {string}")
  public void basicAuthExampleIsOpenedWithUsernameAndPassword(
          String exampleName,
          String usernameKey,
          String passwordKey
  ) {
    String username = TestEnvironment.get(usernameKey);
    String password = TestEnvironment.get(passwordKey);
    homePage.openBasicAuth(exampleName, username, password);
  }

  @Then("{string} should be displayed")
  public void congratulationsShouldBeDisplayed(String expectedMsg) {
    BasicAuthPage basicAuthPage = new BasicAuthPage(manager.getDriver());

    assertTrue(
            basicAuthPage.waitForConfMsg().contains(expectedMsg),
            expectedMsg + " was not displayed"
    );
  }

}
