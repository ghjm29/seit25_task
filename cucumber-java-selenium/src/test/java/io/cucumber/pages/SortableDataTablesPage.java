package io.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SortableDataTablesPage extends BasePage {

  public SortableDataTablesPage(ChromeDriver driver) {
    super(driver);
  }

  public List<List<String>> getTableResults(String tableName) {

    By tableRows = By.xpath("//h4[text()='" + tableName + "']/following-sibling::table[@id='table1']/tbody/tr");
    List<WebElement> tableRowsEli = wait.until(
        ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));

    return tableRowsEli.stream()
            .map(row -> {
              List<WebElement> cells = row.findElements(By.xpath("./td"));

              List<WebElement> cellsWithoutActions =
                      cells.subList(0, cells.size() - 1);

              return cellsWithoutActions.stream()
                      .map(WebElement::getText)
                      .toList();
            })
            .toList();
  }
}
