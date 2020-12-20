package ru.geekmarket.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekmarket.DriverInitializer;

import static org.assertj.core.api.Assertions.assertThat;

public class CartSteps extends TestHelper {

    private WebDriver webDriver;
    private String prodName;
    private String prodPrice;

    @Given("^I open web browser to main page$")
    public void iOpenToHomeHtmlPage() {
        webDriver = DriverInitializer.getDriver();
        webDriver.get(DriverInitializer.getProperty("homepage.url"));
    }

    @When("^I navigate first product$")
    public void iOpenNavigateFirstProductPage() throws InterruptedException {
        webDriver.findElement(By.cssSelector("#trending > div > div.row > div:nth-child(1) > div > div.card > h4 > a")).click();
        Thread.sleep(500);
        assertThat(webDriver.getCurrentUrl()).contains("/product/");
    }

    @When("^I click on add to cart button from main UI$")
    public void iClickOnAddToCartButton() {
        WebElement productElement = webDriver.findElement(By.cssSelector("#trending > div > div.row > div:nth-child(1) > div"));
        prodName = webDriver.findElement(By.cssSelector("#trending > div > div.row > div:nth-child(1) > div > div.card > h4")).getText();
        prodPrice = webDriver.findElement(By.cssSelector("#trending > div > div.row > div:nth-child(1) > div > div.card > p.card-product__price > span")).getText();
        Actions builder = new Actions(webDriver);
        builder.moveToElement(productElement).perform();
        WebElement addToCartElement = webDriver.findElement(By.cssSelector("#trending > div > div.row > div:nth-child(1) > div > div.card-product__img > ul > li:nth-child(2) > form > button"));
        builder.moveToElement(addToCartElement).click().perform();
    }

    @When("^I click on add to cart button from product page$")
    public void iClickOnAddToCartButtonOnProductPage() throws InterruptedException {
        Thread.sleep(500);
        prodName = webDriver.findElement(By.cssSelector("div > h3")).getText();
        prodPrice = webDriver.findElement(By.cssSelector("div > h2 > span")).getText();
        webDriver.findElement(By.cssSelector("div > form > button")).click();
    }

    @Then("^opens cart page with same product and price$")
    public void nameShouldBeOnCartPage() throws InterruptedException {
        Thread.sleep(1000);
        assertThat(webDriver.getCurrentUrl()).endsWith("cart");
        webDriver.findElement(By.cssSelector("div.media-body > a")).getText().equals(prodName);
        webDriver.findElement(By.cssSelector("td:nth-child(2) > h5")).getText().equals(prodPrice);
    }

    @When("^I click on delete button$")
    public void iClickOnDeleteButton() throws InterruptedException {
        Thread.sleep(500);
        WebElement deleteProdFromCartButton = webDriver.findElement(By.className("cart_quantity_delete"));
        deleteProdFromCartButton.click();
    }

    @Then("^cart goes empty$")
    public void cartGoesToEmpty() {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        By emptyCartMessageElement = By.cssSelector("h5");
        wait.until(ExpectedConditions.presenceOfElementLocated(emptyCartMessageElement));
        webDriver.findElement(emptyCartMessageElement).getText().equals("Empty cart");
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}
