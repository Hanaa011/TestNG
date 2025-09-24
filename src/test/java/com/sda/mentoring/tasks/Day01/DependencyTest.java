package com.sda.mentoring.tasks.Day01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class DependencyTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("Setting up WebDriver");
        driver = new ChromeDriver();
    }

    @Test
    public void openYahoo() {
        System.out.println("Opening Yahoo...");
        driver.get("https://www.yahoo.com");


        throw new RuntimeException("Intentional Failure in Yahoo test");
    }

    @Test(dependsOnMethods = "openYahoo")
    public void openBing() {
        System.out.println("Opening Bing..");
        driver.get("https://www.bing.com");
    }

    @Test(dependsOnMethods = "openBing")
    public void openDuckDuckGo() {
        System.out.println("Opening DuckDuckGo...");
        driver.get("https://www.duckduckgo.com");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Closing WebDriver");
        if (driver != null) {
            driver.quit();
        }
    }
}
