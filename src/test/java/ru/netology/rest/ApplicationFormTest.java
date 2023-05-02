package ru.netology.rest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationFormTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {

        WebDriverManager.chromedriver().setup();
       }
//    @BeforeAll
//    static void setUpAll() {
// убедитесь, что файл chromedriver.exe расположен именно в папке проекта
//        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
//    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    //@Test
    //void shouldTestSomething() {
    //    throw new UnsupportedOperationException();

    @Test
    void shouldPassTestWithAllCorrectFields() {
        driver.findElement(By.cssSelector("[data-test-id = name] input")).sendKeys("Классный Парень");
        driver.findElement(By.cssSelector("[data-test-id = phone] input")).sendKeys("+79112235566");
        driver.findElement(By.cssSelector("[data-test-id = agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id = order-success]")).getText().trim();
        assertEquals(expected, actual);
    }
}
