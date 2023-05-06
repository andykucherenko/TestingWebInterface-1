package ru.netology.rest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.text;



public class AppCardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void shouldTestSuccessResult() {
        String myDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").sendKeys(myDate);
        $("[data-test-id='name'] input").setValue("Иван Петров-Сидоров");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id='agreement']").click();
        $(".button__text").click();
        $("[data-test-id='notification']").should(text("Встреча успешно забронирована на " + myDate), Duration.ofSeconds(15));
    }

    @Test
    public void shouldTestValidateDateField() {
        $("[data-test-id='city'] input").val("Санкт-Петербург");
        $("[data-test-id='name'] input").val("Иван Петров");
        $("[data-test-id='phone'] input").val("+79012345678");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='date'] .input__sub").shouldHave(text("Неверно введена дата"));
    }

    @Test
    public void shouldTestValidPhone() {
        String myDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").val("Санкт-Петербург");
        $("[data-test-id='date'] input").sendKeys(myDate);
        $("[data-test-id='name'] input").val("Иван Петров");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldTestValidName() {
        String myDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").val("Санкт-Петербург");
        $("[data-test-id='date'] input").sendKeys(myDate);
        $("[data-test-id='phone'] input").val("+79012345678");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldTestValidCheckbox() {
        String myDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").val("Санкт-Петербург");
        $("[data-test-id='date'] input").sendKeys(myDate);
        $("[data-test-id='name'] input").val("Иван Петров");
        $("[data-test-id='phone'] input").val("+79012345678");
        $(".button").click();
        $("[data-test-id='agreement'] .checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки" +
                " и использования моих персональных данных"));
    }
}


