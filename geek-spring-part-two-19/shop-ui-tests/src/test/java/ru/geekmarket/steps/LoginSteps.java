package ru.geekmarket.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekmarket.DriverInitializer;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenFirefoxBrowser() {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I open to admin login page$")
    public void iNavigateToLoginHtmlPage() {
        webDriver.get(DriverInitializer.getProperty("admin.login.url"));
    }

    @When("^I open to user login page$")
    public void iNavigateToUserLoginHtmlPage() {
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() {
        WebElement webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();
    }

    @When("^I click on login button on user login page$")
    public void iClickOnLoginButtonOnUserLoginPage() {
        WebElement webElement = webDriver.findElement(By.className("button-login"));
        webElement.click();
    }


    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("inp-username"));
        webElement.sendKeys(username);
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("inp-password"));
        webElement.sendKeys(password);
        Thread.sleep(500);
    }

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\" on user login page$")
    public void iProvideUsernameAsAndPasswordAsOnUserLoginPage(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys(username);
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys(password);
        Thread.sleep(500);
    }

    @Then("^name should be \"([^\"]*)\"$")
    public void nameShouldBe(String name) {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        assertThat(webElement.getText()).isEqualTo(name);
    }

    @Then("^name should be \"([^\"]*)\" on user page$")
    public void nameShouldBeOnProfilePage(String name) throws Throwable {
        Thread.sleep(500);
        WebElement navBarToggle = webDriver.findElement(By.className("navbar-toggler"));
        if (isClickable(navBarToggle)) navBarToggle.click();
        Thread.sleep(500);
        WebElement webElement = webDriver.findElement(By.cssSelector(".dropdown-toggle>span>span"));
        Thread.sleep(500);

        assertThat(webElement.getText()).isEqualToIgnoringCase(name);

        webElement = webDriver.findElement(By.cssSelector(".form-group>input#username"));
        assertThat(webElement.getAttribute("value")).isEqualTo(name);
    }

    @Given("^any user logged in$")
    public void userLoggedIn() {
        webDriver.findElement(By.id("logged-in-username"));
    }

    @When("^Open dropdown menu$")
    public void openDropDownMenu() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.id("logged-in-username"));
        Thread.sleep(500);
        webElement.click();
        Thread.sleep(1000);
    }

    @When("^Open account dropdown menu on site and click logout$")
    public void openAccountDropDownMenu() {
        WebElement accountDropDownMenu = webDriver.findElement(By.cssSelector("li.nav-item.submenu.dropdown.active > a"));
        WebElement logoutMenuLink = webDriver.findElement(By.cssSelector("#logout > a"));
        accountDropDownMenu.click();
        logoutMenuLink.click();
    }


    @When("^click logout button$")
    public void clickLogoutButton() {
        WebElement webElement = webDriver.findElement(By.id("link-logout"));
        webElement.click();
    }

    @Then("^user logged out$")
    public void userLoggedOut() {
        webDriver.findElement(By.id("inp-username"));
        webDriver.findElement(By.id("inp-password"));
    }

    @Then("^user logged out from profile$")
    public void userLoggedOutFromProfile() {
        assertThat(webDriver.findElement(By.cssSelector(".alert-success")).getText()).isEqualTo("You have been logged out.");
        webDriver.findElement(By.id("name")).isDisplayed();
        webDriver.findElement(By.id("password")).isDisplayed();
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }

    public boolean isClickable(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
