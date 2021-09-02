import config.TestConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static constants.Constants.Actions.*;
import static constants.Constants.Path.JMART_PATH;
import static constants.Constants.Path.SWAPI_PATH;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest extends TestConfig {

    @Test
    public void myFirstTest() {
        given().
                log().ifValidationFails().

//        when().get("https://jtest3.jmart.kz/api/4.0/sra_faseted_products?category_id=1480").
//        Редактируем наш запрос, вместо полного пути, добавляем Action (эндпоинт куда будем идти) + "параметр"

        // После создания конфигурационного класса (TestConfig + Constants) добавляем Logging в запрос и ответ
                // Первым добавим логирование запроса
                        // После ключевого слова given(). добавим log().all(Все). или uri(пример запроса).
                                // Логирование ответа происходит после ключевого слова then(). + log().all(Все). или body(тело респонса).
                // Можем добавить логирование запроса при ошибке. После ключевого слова given(). + log().ifValidationFails().

        when().
                get(JMART_GET_SRA_FASTED_PRODUCTS_GET + "1480"). // в кавычках параметр, цифра айдишника, в данном случае номер категории
        then().
                statusCode(200);


        // Далее запрос методом GET с использованием query parameters,
        // чтобы можно было передавать параметр строкой запроса и отправлять на сервер
        // пример в JsonPlaceHolderTest

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Assertions для ответов с Java библиотекой Hamcrest

    @Test
    public void getSomeFieldInResponseAssertion() {
        given().
                spec(requestSpecificationForPlaceholderTests).log().uri().
        when().
                get(JSON_PLACEHOLDER_GET2).
        then().
                body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")).
                log().body();
        // С одним объектом понятно.
        // А если массив объектов приходит как тогда?
    }

    @Test
    public void getSomeFieldInResponseAssertion2() {
        // Если приходит массив в котором много объектов и есть вложенности
        given().
                spec(requestSpecificationForPlaceholderTests).log().uri().
        when().
                get(JSON_PLACEHOLDER_GET).
        then().
                body("name[0]", equalTo("id labore ex et quam laborum")).
                // тут я проверяю поле "name" которое лежит в массиве
                        // Если бы у поля был свой массив, то сперва пишется название поля, потом точка, нужное поле и в квадратных скобках номер объекта. Начинается с нуля.
                                // {
                                //      "results": [
                                //      {"name": "id labore ex et quam laborum"}
                                //      ]
                                // }
                                // body(results.name[0], equalTo("id labore ex et quam laborum")).
                log().body();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Извлечение body, headers, cookie и других данных из ответа.
    // Получение всего ответа в одну строку с помощью функции экстракт

    @Test
    public void getAllDataFromRequest() {
        // Перед этим воспользуемся классом response и в then().добавим методы extract().response();
        Response response =
                given().
                        spec(requestSpecificationForDataExtractTests).log().uri().
                when().
                        get(JMART_PATH + JMART_GET_SRA_FASTED_PRODUCTS_GET + "1480").
                then().
                        extract().response();

        // Далее создаем переменную типа String
        String jsonResponseAsString = response.asString();
        // И выведем значение нашего jsonResponseAsString
        System.out.println(jsonResponseAsString);

    }

    @Test
    public void getCookieFromResponse() {
        // Перед этим воспользуемся классом response и в then().добавим методы extract().response();
        Response response =
                given().
                        spec(requestSpecificationForCookieExtractTests).log().uri().
                when().
                        get(SWAPI_PATH).
                then().
                        extract().response();

        // Далее создаем переменную типа String
        Map<String, String> allCookie = response.getCookies();
        // И выведем значение нашего jsonResponseAsString
        System.out.println("allCookie -->" + allCookie);

//      Если придет куки, можно вывести конкретный куки
//      String someCookie = response.getCookie("....");
//      System.out.println("someCookie -->" + someCookie);

    }

    @Test
    public void getHeadersFromResponse() {
        // Перед этим воспользуемся классом response и в then().добавим методы extract().response();
        Response response =
                given().
                        spec(requestSpecificationForDataExtractTests).log().uri().
                when().
                        get(JMART_PATH + JMART_GET_SRA_FASTED_PRODUCTS_GET + "1480").
                then().
                        extract().response();
        Headers headers = response.getHeaders();
        System.out.println("headers --> " + headers);

        // Далее создаем переменную типа String
        String contentType = response.getContentType();
        // И выведем значение нашего jsonResponseAsString
        System.out.println("contentType --> " + contentType);

    }

}
