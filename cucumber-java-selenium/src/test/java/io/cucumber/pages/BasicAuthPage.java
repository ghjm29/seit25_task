package io.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class BasicAuthPage extends BasePage {

  public BasicAuthPage(ChromeDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//*[@id='content']//p")
  private WebElement confMsgEli;

  public String waitForConfMsg() {
    waitUntilElementVisible(confMsgEli);
    return confMsgEli.getText();
  }
}
