package com.sda.mentoring.assignments.Day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Task1 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Parameters("searchKeyword")
    @Test
    public void testAmazonSearch(String keyword) {
        // 1. Open Amazon
        driver.get("https://www.amazon.com/");

        // 2. If "Continue shopping" button appears, click it
        try {
            WebElement continueBtn = driver.findElement(By.xpath("//button[@type='submit' and text()='Continue shopping']"));
            continueBtn.click();
        } catch (Exception e) {
            System.out.println("Continue shopping button not found, moving on...");
        }

        // 3. Enter keyword in search bar
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchBox.submit();

        // 4. Assert results contain the searched word
        WebElement resultText = driver.findElement(By.cssSelector("span.a-color-state.a-text-bold"));
        String result = resultText.getText();
        System.out.println("Result text: " + result);

        Assert.assertTrue(result.toLowerCase().contains(keyword.toLowerCase()),
                "Result does not contain the searched keyword!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
