import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LoginTests {

    @Test
    void successfulLoginTest() {
        open("https://qa.guru/cms/system/login");
        $("[name=email]").setValue("qagurubot@gmail.com");
        $("[name=password]").setValue("qagurupassword");
        $(".btn-success").click();
        $(".main-header__login").click();
        $(".logined-form").shouldHave(text("QA_GURU_BOT"));
    }

    @Test
    void wrongPasswordLoginTest() {
        open("https://qa.guru/cms/system/login");
        $("[name=email]").setValue("qagurubot@gmail.com");
        $("[name=password]").setValue("12345").pressEnter();
        $(".btn-success").shouldHave(text("Неверный пароль"));
    }

    @Test
    void missingPasswordLoginTest() {
        open("https://qa.guru/cms/system/login");
        $("[name=email]").setValue("qagurubot@gmail.com").pressEnter();
        $(".btn-success").shouldHave(text("Не заполнено поле Пароль"));
    }

    @Test
    void missingEmailLoginTest() {
        open("https://qa.guru/cms/system/login");
        $("[name=email]").pressEnter();
        //$(".btn-success").click();
        $(".btn-success").shouldHave(text("Не заполнено поле E-Mail"));
    }
}