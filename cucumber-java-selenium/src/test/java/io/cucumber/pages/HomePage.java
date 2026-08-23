package io.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

  public HomePage(ChromeDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//h2[text()='Available Examples']")
  private WebElement availExampleHeadingEli;

  @FindBy(xpath = "//*[@id='content']/ul/li")
  private List<WebElement> exampleLinksEli;

  @FindBy(xpath = "//*[@id='content']/ul/li/a")
  private List<WebElement> exampleLinkAnchorsEli;

  public void navigateToHomePage(String url){
    driver.get(url);
    waitUntilElementVisible(availExampleHeadingEli);
  }

  public List<String> getExampleLinkTexts() {
    return exampleLinksEli.stream()
            .map(WebElement::getText)
            .toList();
  }

  public void openExample(String exampleName) {
    WebElement exampleLink = exampleLinkAnchorsEli.stream()
            .filter(link -> link.getText().contains(exampleName))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Example link not found: " + exampleName));

    wait.until(ExpectedConditions.elementToBeClickable(exampleLink)).click();
  }

  public void openBasicAuth(String exampleName, String username, String password) {
    ((HasAuthentication) driver).register(UsernameAndPassword.of(username, password));
    openExample(exampleName);
  }
}
