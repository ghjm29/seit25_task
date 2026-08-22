package io.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

  public HomePage(ChromeDriver driver) {
    super(driver);
  }

  @FindBy(css = "h1")
  private WebElement title;

  @FindBy(xpath = "//h2[text()='Available Examples']")
  private WebElement availExampleHeadingEli;

  @FindBy(xpath = "//*[@id='content']/ul/li/a")
  private List<WebElement> exampleLinksEli;

  public void navigateToHomePage(String url){
    driver.get(url);
    waitUntilElementVisible(availExampleHeadingEli);
  }

  public List<String> getExampleLinkTexts() {
    return exampleLinksEli.stream()
            .map(WebElement::getText)
            .toList();
  }

  public WebElement getTitle() {
    return title;
  }

  public void refresh() {
    driver.navigate().refresh();
    System.out.println("Refreshed page");
  }
}
