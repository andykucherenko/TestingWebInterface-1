package ru.netology.rest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("a").click();
        $("[type=text]").setValue("4055 0100 0123 4613 8564");
        $("[type-tel]").setValue("+7 999 921 33 11");
        $(".button__text").click();
        $x("//p[contains(text(), 'Успешная авторизация')]").shouldBe(visible, Duration.ofMillis(8000));
        $x("//*[contains(text(), 'кабинет')]").should(appear, Duration.ofSeconds(6));
    }
}
