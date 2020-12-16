package ru.geekmarket.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.geekmarket.DriverInitializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;

@Ignore
public class RegisterSteps extends TestHelper {

    private WebDriver webDriver;

    @Given("^I open web browser$")
    public void iOpenChromeBrowser() {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I open home page and navigate to register page$")
    public void iNavigateToUserRegisterHtmlPage() throws InterruptedException {
        webDriver.get(DriverInitializer.getProperty("homepage.url"));
        Thread.sleep(500);
        expandMenuIfNeeded(webDriver);
        Thread.sleep(500);
        WebElement accountDropDownMenu = webDriver.findElement(By.cssSelector("ul.nav.navbar-nav.menu_nav.ml-auto.mr-auto > li:nth-child(4) > a"));
        accountDropDownMenu.click();
        WebElement registerMenuLink = webDriver.findElement(By.cssSelector("li.nav-item.submenu.dropdown.show > ul > li:nth-child(1) > a"));
        registerMenuLink.click();
        Thread.sleep(500);
        WebElement registerPageTitle = webDriver.findElement(By.className("register_form_inner"));
        registerPageTitle.isDisplayed();
    }

    @When("^I provide username as \"([^\"]*)\", emailAddress as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsEmailAsAndPasswordAs(String username, String email, String password) throws Throwable {
        WebElement usernameInput = webDriver.findElement(By.id("username"));
        WebElement emailInput = webDriver.findElement(By.id("email"));
        WebElement passwordInput = webDriver.findElement(By.id("password"));
        WebElement confPasswordInput = webDriver.findElement(By.id("confirmPassword"));
        usernameInput.sendKeys(username);
        Thread.sleep(500);
        emailInput.sendKeys(email);
        Thread.sleep(500);
        passwordInput.sendKeys(password);
        Thread.sleep(500);
        confPasswordInput.sendKeys(password);
        Thread.sleep(500);
    }

    @When("^I click on register button$")
    public void clickRegisterButton() {
        WebElement RegisterButtonElement = webDriver.findElement(By.className("button-register"));
        Actions builder = new Actions(webDriver);
        builder.moveToElement(RegisterButtonElement).click(RegisterButtonElement);
        builder.perform();
        //RegisterButtonElement.click();
    }

    @Then("^name should be \"([^\"]*)\" and email should be \"([^\"]*)\" on user page$")
    public void nameShouldBeOnProfilePage(String name, String email) throws Throwable {
        Thread.sleep(500);
        WebElement userProfileSubmitButton = webDriver.findElement(By.className("button-contactForm"));
        userProfileSubmitButton.isDisplayed();
        expandMenuIfNeeded(webDriver);
        Thread.sleep(500);
        WebElement usernameInMenu = webDriver.findElement(By.cssSelector(".dropdown-toggle>span>span"));
        Thread.sleep(500);
        assertThat(usernameInMenu.getText()).isEqualToIgnoringCase(name);

        WebElement usernameInput = webDriver.findElement(By.cssSelector(".form-group>input#username"));
        assertThat(usernameInput.getAttribute("value")).isEqualTo(name);

        WebElement emailInput = webDriver.findElement(By.cssSelector(".form-group>input#email"));
        assertThat(emailInput.getAttribute("value")).isEqualTo(email);
        webDriver.quit();
    }

    @Then("^delete test user with name \"([^\"]*)\"$")
    public void deleteTestUser(String name) throws InterruptedException {
        WebDriver webDriverForAdminPage = DriverInitializer.getDriver();
        webDriverForAdminPage.get(DriverInitializer.getProperty("admin.login.url"));
        Thread.sleep(1000);
        WebElement webElement = webDriverForAdminPage.findElement(By.id("inp-username"));
        webElement.sendKeys("admin");
        Thread.sleep(500);
        webElement = webDriverForAdminPage.findElement(By.id("inp-password"));
        webElement.sendKeys("admin");
        Thread.sleep(500);
        WebElement loginButton = webDriverForAdminPage.findElement(By.id("btn-login"));
        loginButton.click();
        Thread.sleep(500);
        webDriverForAdminPage.get(DriverInitializer.getProperty("admin.users.url"));
        Thread.sleep(500);
        WebElement deleteLastUserButton = webDriverForAdminPage.findElement(By.cssSelector("#users > tbody > tr:last-child > td:last-child > form > button"));
        WebElement lastUserName = webDriverForAdminPage.findElement(By.cssSelector("#users > tbody > tr:last-child> td:nth-child(2)"));
        if (lastUserName.getText().equals(name)) {
            deleteLastUserButton.click();
        } else System.out.println("User with name: " + name + ", was not found.");
        Thread.sleep(1000);
        assertNotEquals(webDriverForAdminPage.findElement(By.cssSelector("#users > tbody > tr:last-child> td:nth-child(2)")).getText(), name);
        webDriverForAdminPage.quit();
    }
}
