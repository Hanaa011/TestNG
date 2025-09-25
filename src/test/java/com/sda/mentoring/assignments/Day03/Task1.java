package com.sda.mentoring.assignments.Day03;

import com.sda.utilities.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task1 extends TestBase {

    @Test(dataProvider = "invalidCredentials")
    public void negativeLoginTest(String username, String password) throws InterruptedException {

        // Navigate to login page
        driver.get("https://claruswaysda.github.io/signIn.html");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate web elements
        WebElement userBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit']")));

        // Enter credentials
        userBox.clear();
        userBox.sendKeys(username);
        passBox.clear();
        passBox.sendKeys(password);

        // Click sign in button
        signInBtn.click();
        Thread.sleep(2000);

        // Handle different error scenarios
        boolean testPassed = false;

        // Check for Alert (incorrect credentials)
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert message: " + alertText);

            Assert.assertTrue(alertText.contains("Incorrect"));
            alert.accept();
            testPassed = true;

        } catch (TimeoutException e) {
            // Check for validation messages (empty fields)
            JavascriptExecutor js = (JavascriptExecutor) driver;

            if (username.isEmpty()) {
                String usernameValidation = (String) js.executeScript("return arguments[0].validationMessage;", userBox);
                if (usernameValidation != null && !usernameValidation.isEmpty()) {
                    Assert.assertEquals(usernameValidation, "Please fill out this field.");
                    testPassed = true;
                }
            }

            if (password.isEmpty() && !testPassed) {
                String passwordValidation = (String) js.executeScript("return arguments[0].validationMessage;", passBox);
                if (passwordValidation != null && !passwordValidation.isEmpty()) {
                    Assert.assertEquals(passwordValidation, "Please fill out this field.");
                    testPassed = true;
                }
            }

            if (!testPassed) {
                Assert.fail("No error feedback found");
            }
        }
    }

    @DataProvider(name = "invalidCredentials")
    public static Object[][] getData() {
        return new Object[][]{
                {"", ""},                 // Empty username and password
                {"validUser", ""},        // Empty password
                {"", "validPass"},        // Empty username
                {"wrongUser", "wrongPass"}, // Wrong credentials
                {"validUser", "wrongPass"}, // Wrong password
                {"wrongUser", "validPass"}  // Wrong username
        };
    }
}