package com.sda.mentoring.assignments.Day03;

import com.sda.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task1 extends TestBase {

    @Test(dataProvider = "invalidCredentials")
    public void negativeLoginTest(String username, String password) throws InterruptedException {
        System.out.println("Starting test with username: '" + username + "' and password: '" + password + "'");

        // Open the login page
        driver.get("https://claruswaysda.github.io/signIn.html");
        System.out.println("Page loaded: " + driver.getCurrentUrl());


        Thread.sleep(2000);

        // Wait for elements to be ready
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Locating username field...");
        WebElement userBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        System.out.println("Username field found.");

        System.out.println("Locating password field...");
        WebElement passBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        System.out.println("Password field found.");

        System.out.println("Locating sign-in button...");
        WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        System.out.println("Sign-in button found.");

        // Fill in credentials
        System.out.println("Filling in credentials...");
        userBox.clear();
        userBox.sendKeys(username);
        passBox.clear();
        passBox.sendKeys(password);

        // Click sign in
        System.out.println("Clicking sign-in...");
        signInBtn.click();

        // Verify error message appears
        System.out.println("Waiting for error message...");
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));
        System.out.println("Error message displayed: " + errorMsg.getText());

        Assert.assertTrue(errorMsg.isDisplayed(), "Error message is not displayed!");
        System.out.println("Test passed for invalid credentials.\n");
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] getData() {
        return new Object[][]{
                {"", ""},
                {"validUser", ""},
                {"", "validPass"},
                {"wrongUser", "wrongPass"},
                {"validUser", "wrongPass"},
                {"wrongUser", "validPass"}
        };
    }
}
