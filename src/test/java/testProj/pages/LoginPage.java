package testProj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import testProj.TestBase;

import java.io.IOException;

/**
 * Login page class
 * Created by Iakov Volf on 5/21/2015.
 */
public class LoginPage extends Page {

  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
  public WebElement header;

  @FindBy(id = "Name")
  WebElement nameField;

  @FindBy(id = "Password")
  WebElement passwordField;

  @FindBy(xpath = "//button[@type='button' and contains(text(),'Login')]")
  WebElement loginButton;

  public LoginPage(WebDriver driver) {
    super(driver);
    this.PAGE_URL = TestBase.baseUrl;
    PageFactory.initElements(driver, this);
  }
  public LoginPage openLoginPage() {
    driver.get(PAGE_URL);
    return this;
  }

  public LoginPage fillNameField(String name) {
    setElementText(nameField, name);
    return this;
  }

  public LoginPage fillPasswordField(String password) {
    setElementText(passwordField, password);
    return this;
  }

  public void pressLoginButton(){
    clickElement(loginButton);
  }

  public LoginPage waitUntilLoginPageIsLoaded () throws IOException, InterruptedException {
    waitUntilIsLoaded(loginButton);
    return this;
  }

public void login (String name, String password) throws IOException, InterruptedException {
  openLoginPage();
  waitUntilLoginPageIsLoaded();
  fillNameField(name);
  fillPasswordField(password);
  pressLoginButton();
}


}
