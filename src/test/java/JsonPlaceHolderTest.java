import config.TestConfig;
import org.testng.annotations.Test;

import static constants.Constants.Actions.*;
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

    // Запрос PUT
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
                "\"userId\":1\n" +
                "}";
        given().
                body(putBodyJson).log().uri().
        when().
                put(JSON_PLACEHOLDER_PUT).
        then().
                log().body().statusCode(200); // У PUT запроса статус 200, 201 только у POST.
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Запрос DELETE.
    // Идём в описание, смотрим какой эндпоинт имеет DELETE запрос.
    // В текущем примере эндпоинт такой же, отличается метод, method: 'DELETE' .

    @Test
    public void DELETE() {

        given().log().all(). // Тело запроса не указываем.

        when().              // Создадим эндпоинт в Constants - public static String JSON_PLACEHOLDER_DELETE = "posts/1/";
                delete(JSON_PLACEHOLDER_DELETE).
        then().
                log().body().statusCode(200);

    }
    // В ответе будет пустое тело.

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Запрос POST с JSON body

    @Test
    public void PostWithJson() {

        String postJsonBody = "{\n" +
                "\"title\":\"foo\",\n" +
                "\"body\":\"bar\",\n" +
                "\"userId\":1\n" +
                "}";
        given().
                body(postJsonBody).
                log().all().
        when().
                post(JSON_PLACEHOLDER_POST).
        then().
                log().body().statusCode(201); //201 только у POST.

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Запрос POST с XML body
    // для XML нужен специальный сервис который поможет транслировать и дебажить API - requestbin.com/r/enhn7stoyd7v (https://pipedream.com/@ryolkin90/p_KwCwmMg/edit)


    @Test
    public void PostWithXML() {

        // XML взял для примера из онлайн редактора

        String postXMLBody = "<Company>\n" +
                "  <Employee>\n" +
                "      <FirstName>Tanmay</FirstName>\n" +
                "      <LastName>Patil</LastName>\n" +
                "      <ContactNo>1234567890</ContactNo>\n" +
                "      <Email>tanmaypatil@xyz.com</Email>\n" +
                "      <Address>\n" +
                "           <City>Bangalore</City>\n" +
                "           <State>Karnataka</State>\n" +
                "           <Zip>560212</Zip>\n" +
                "      </Address>\n" +
                "  </Employee>\n" +
                "</Company>";

        given().
                spec(requestSpecXML).
                body(postXMLBody).
                log().all().
        when().
                post(""). // Из конфига URL, ничего передавать не будем
        then().
                log().body().statusCode(200); //201 только у POST, но мы обращаемся через транслирующий сервис и объект по факту не создается. Ставим 200

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Знакомство с RequestSpecification
    // Возможность унифицировать все запросы где в одном месте вынесем общие свойства запроса.
    // Использует шаблон builder, requestSpecBuilder позволяет создавать новые объекты используя не ограниченное количество их свойств (Headers, Cookies, URL), Для того чтоб создать уникальные запросы.
    // В TestConfig создадим RequestSpecification requestSpec = new RequestSpecBuilder()...
    // RequestSpecification может использоваться как для одного запроса точечно, так и для всех запросов.

}
