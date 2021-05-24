import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WebFormTest {

    @BeforeEach
    void testInit() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestWithCorrectForm() {
        $("[data-test-id=name] input").setValue("Михаил Салтыков-Щедрин");
        $("[data-test-id=phone] input").setValue("+79513574532");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
     }

    @Test
    void shouldTestWithIncorrectName() {
        $("[data-test-id=name] input").setValue("Mikhail Petrov");
        $("[data-test-id=phone] input").setValue("+79513574532");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestWithIncorrectPhone() {
        $("[data-test-id=name] input").setValue("Михаил Петров");
        $("[data-test-id=phone] input").setValue("+795135745");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestWithEmptyName() {
        $("[data-test-id=phone] input").setValue("+79513576265");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestWithEmptyPhone() {
        $("[data-test-id=name] input").setValue("Михаил Петров");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestWithEmptyNameAndPhone() {
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestWithoutAgreement() {
            $("[data-test-id=name] input").setValue("Макс Фрай");
            $("[data-test-id='phone'] input").setValue("+79513576525");
            $(".button__content").click();
        boolean exists = $("[data-test-id=agreement].input_invalid .checkbox__text").exists();
        }

}