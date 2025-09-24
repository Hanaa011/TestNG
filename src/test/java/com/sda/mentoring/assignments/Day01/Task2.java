package com.sda.mentoring.assignments.Day01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task2 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void negativeLoginTest() {
        SoftAssert softAssert = new SoftAssert();

        // 1. Navigate to the login page
        driver.get("https://claruswaysda.github.io/signIn.html");

        // 2. Enter wrong username
        driver.findElement(By.id("username")).sendKeys("wronguser");

        // 3. Enter wrong password
        driver.findElement(By.id("password")).sendKeys("wrongpass");

        // 4. Click submit
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // 5. Soft assertions
        String alertText = driver.switchTo().alert().getText();
        softAssert.assertTrue(alertText.contains("Incorrect username or password"),
                "Alert text is incorrect!");

        // call assertAll at the end
        softAssert.assertAll();
    }
}
