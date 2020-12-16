package ru.geekmarket.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelper {

    public boolean isClickable(WebDriver webDriver, WebElement webElement ) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    void expandMenuIfNeeded(WebDriver webDriver) throws InterruptedException {
        Thread.sleep(500);
        WebElement navBarToggle = webDriver.findElement(By.className("navbar-toggler"));
        if (isClickable(webDriver, navBarToggle)) navBarToggle.click();
    }
}
