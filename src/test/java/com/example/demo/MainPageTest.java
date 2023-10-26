package com.example.demo;

import org.junit.jupiter.api.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://market.yandex.ru/");
    }

    public class ThreadSleep {

        public static void main(String[] args) throws InterruptedException {
            long start = System.currentTimeMillis();


            System.out.println("Sleep time in ms = " + (System.currentTimeMillis() - start));
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {

        String input = "ожерелье солнце";

        WebElement searchField = driver.findElement(By.xpath("//input[@id='header-search']"));
        searchField.click();
        searchField.sendKeys(input);
        WebElement searchPageField = driver.findElement(By.xpath("//button[@type='submit']"));
        searchPageField.click();

        WebElement searchPageFieldTrue = driver.findElement(By.xpath("//input[@id='header-search']"));
        assertEquals(input, searchPageFieldTrue.getAttribute("value"));

    }
    @Test
    public void search2() throws InterruptedException {

            WebElement searchField = driver.findElement(By.xpath("//input[@id='header-search']"));
            searchField.click();
            searchField.sendKeys("ожерелье солнце");
            WebElement searchPageField = driver.findElement(By.xpath("//button[@type='submit']"));
            searchPageField.click();
            WebElement searchPage = driver.findElement(By.xpath("//a[contains(@data-node-cache-key, 'product-image')][1]"));
            searchPage.click();

            assertTrue(driver.findElement(By.xpath("//h1['олнце']")).isDisplayed(),
                    "Запрос не обнаружен в выдаче");
            sleep(5000);
    }
}