import config.TestConfig;
import org.testng.annotations.Test;

import static constants.Constants.Actions.JMART_GET_SRA_FASTED_PRODUCTS_GET;
import static io.restassured.RestAssured.*;

public class FirstTest extends TestConfig {

    @Test
    public void myFirstTest() {
        given().
                log().ifValidationFails().

//        when().get("https://jtest4.jmart.kz/api/4.0/sra_faseted_products?category_id=1480").
//        Редактируем наш запрос, вместо полного пути, добавляем Action (эндпоинт куда будем идти) + "параметр"

        // После создания конфигурационного класса (TestConfig + Constants) добавляем Logging в запрос и ответ
                // Первым добавим логирование запроса
                        // После ключевого слова given(). добавим log().all(Все). или uri(пример запроса).
                                // Логирование ответа происходит после ключевого слова then(). + log().all(Все). или body(тело респонса).
                // Можем добавить логирование запроса при ошибке. После ключевого слова given(). + log().ifValidationFails().

        when().
                get( JMART_GET_SRA_FASTED_PRODUCTS_GET + "1480"). // в кавычках параметр, цифра айдишника, в данном случае номер категории
        then().
                statusCode(200);


        // Далее запрос методом GET с использованием query parameters,
        // чтобы можно было передавать параметр строкой запроса и отправлять на сервер
        // пример в JsonPlaceHolderTest

    }

}
