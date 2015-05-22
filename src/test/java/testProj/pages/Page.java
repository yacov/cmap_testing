package testProj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 * Created by @Iakov Volf on 5/21/2015.
 */
public abstract class Page {

    protected WebDriver driver;
    public String PAGE_URL;
    public String PAGE_TITLE;

    /*
     * Constructor injecting the WebDriver interface
     *
     * @param webDriver
     */
    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void loadPage() {
        driver.get(getPageUrl());
        //assertEquals(getTitle(), getPageTitle());
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getPageUrl() {
        return PAGE_URL;
    }

    public String getPageTitle() {
        return PAGE_TITLE;
    }

    public void setElementText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        Assert.assertEquals(element.getAttribute("value"), text);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void waitUntilIsLoaded(WebElement element) throws IOException, InterruptedException {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }

    public void selectValueInDropdown(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public boolean verifyElementIsPresent(WebElement element) {
        try {
            element.getTagName();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public boolean verifyTextBoolean(WebElement element, String text) {
        return text.equals(element.getText());
    }


    public boolean exists(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException ignored) {
            return false;
        }
    }

    public void waitUntilElementIsLoaded(WebElement element) throws IOException, InterruptedException {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }
    public void waitUntilListIsLoaded(List<WebElement> list) throws IOException, InterruptedException {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(list));
    }

    public void waitForElement(WebDriverWait wait, String element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


}
