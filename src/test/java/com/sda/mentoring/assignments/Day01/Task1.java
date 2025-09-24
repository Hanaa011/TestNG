package com.sda.mentoring.assignments.Day01;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task1 {

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
    public void positiveLoginTest() {
        // 1. Navigate11
        driver.get("https://claruswaysda.github.io/signIn.html");

        // 2. Enter username
        driver.findElement(By.id("username")).sendKeys("admin");

        // 3. Enter password
        driver.findElement(By.id("password")).sendKeys("123");

        // 4. Click submit
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // 5. Hard assertions
        String expectedUrl = "https://claruswaysda.github.io/signIn.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL did not match after login!");

        WebElement tableHeader = driver.findElement(By.tagName("h2"));
        Assert.assertTrue(tableHeader.getText().contains("Employee Table"), "Page does not contain Employee Table!");
    }
}
