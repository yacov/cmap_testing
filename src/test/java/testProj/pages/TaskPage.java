package testProj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import testProj.TestBase;

import java.io.IOException;
import java.util.List;

/**
 * Task page class
 * Created by Iakov Volf on 5/21/2015.
 */
public class TaskPage extends Page {

  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
  public WebElement header;

  @FindBy(id = "grid")
  WebElement tasksTable;

  @FindBy(id = "addTaskButton")
  WebElement addTaskButton;
// Typ Filter elements
    @FindBy(xpath = "//*[@id='grid']//th[9]//span")
    WebElement typFilterIcon;
    @FindBy(xpath = "//form[@class='k-filter-menu k-popup k-group k-reset k-state-border-up']")
    WebElement typFilterForm;
    @FindBy(xpath = "//form[@class='k-filter-menu k-popup k-group k-reset k-state-border-up']//li[3]//input")
    WebElement typFilterBugCheckbox;
    @FindBy(xpath = "//form[@class='k-filter-menu k-popup k-group k-reset k-state-border-up']//button[@class='k-button k-primary']")
    WebElement typFilterFilterButton;

  public TaskPage(WebDriver driver) {
    super(driver);
    this.PAGE_URL = TestBase.baseUrl+"/Task/Index";
    PageFactory.initElements(driver, this);
  }
  public TaskPage openTaskePage() {
    driver.get(PAGE_URL);
    return this;
  }

  public TaskPage openTypFilterForm () {
    clickElement(typFilterIcon);
    return this;
  }

    public TaskPage checkBugBox () {
        clickElement(typFilterBugCheckbox);
        return this;
    }

    public TaskPage pressFilterButton () {
        clickElement(typFilterFilterButton);
        return this;
    }

    public TaskPage waitUntilTaskPageIsLoaded () throws IOException, InterruptedException {
        waitUntilElementIsLoaded(typFilterIcon);
        return this;
    }

    public boolean isOnTaskPage (){
        return (exists(addTaskButton));
    }

//checking every row of the resulting table (after filtering)
    public TaskPage checkTypColumn() throws InterruptedException, IOException {

        List<WebElement> totalRows = driver.findElements(By.xpath("//*[@id='grid']//table//tr[*]/td[9]"));
        waitUntilListIsLoaded(totalRows);

        for (int row = 1; row <= totalRows.size(); row++) {
            // Fetch the text of 'typ' column
            String typ = driver.findElement(By.xpath("//*[@id='grid']//table//tr["+row+"]/td[9]")).getText();
            Assert.assertEquals(typ,"Bug");
            System.out.println("Row "+row+" data is "+typ);
        }

        return this;
    }


}
