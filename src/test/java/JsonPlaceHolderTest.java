import config.TestConfig;
import org.testng.annotations.Test;

import static constants.Constants.Actions.JSON_PLACEHOLDER_GET;
import static constants.Constants.Actions.JSON_PLACEHOLDER_PUT;
import static io.restassured.RestAssured.given;

public class JsonPlaceHolderTest extends TestConfig { // этот класс тоже экстендится от TestConfig

    // Тут будет пример GET запроса с использованием query parameters, чтобы можно было передавать параметр в строку запроса и отправлять на сервер.
    // JsonPlaceHolder это ресурс, где есть возможность чекнуть все виды запросов
    // Далее в константах добавляем URL, PATH и эндпоинты для наших запросов (Actions - константы запросов)

    @Test
    public void GET() {
        given().queryParams("postId", 1).
                log().uri().
        when().
                get( JSON_PLACEHOLDER_GET). // здесь добавляем передачу параметра с помощью вызова queryParam
        then().
                log().body().statusCode(200); // У GET запроса статус 200, у POST 201.
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Запросы PUT и DELETE
    // Если сравнить PUT и POST операциями в MySQL, то POST - это INSERT, а PUT - UPDATE or INSERT
    // Начнем с PUT запроса, у него отображается эндпоинт, реквестбоди в формате Json, и отображается ответ, который мы получаем после отправки запроса PUT.
    // Переходим в константы и добавляем PUT эндпоинт

    @Test
    public void PUT() {

        // Необходимо создать реквестбоди
        // Для этого нужно создать переменную String putBodyJson = "";
        // Далее копируем Json объект и на сайте json.parser.online.fr/beta/ объект немного редактируем, добавляя двойные кавычки. Интовые значения оставляем без кавычек.

        // ДО

        // {
        //    id: 1,
        //    title: 'foo',
        //    body: 'bar',
        //    userId: 1,
        //  }

        // ПОСЛЕ

        // {
        //  "id":1,
        //  "title":"foo",
        //  "body":"bar",
        //  "userId":1,
        // }

        // Скопировав отредактированный объект из json.parser.online.fr/beta/ , вносим в переменную putBodyJson.
        // Далее скопируем название переменной и добавим в body(putBodyJson).

        String putBodyJson = "{\n" +
                "\"id\":1,\n" +
                "\"title\":\"foo\",\n" +
                "\"body\":\"bar\",\n" +
                "\"userId\":1,\n" +
                "}";
        given().
                body(putBodyJson).log().uri().
        when().
                put(JSON_PLACEHOLDER_PUT).
        then().
                log().body().statusCode(200); // У PUT запроса статус 200, 201 только у POST.
    }


}
