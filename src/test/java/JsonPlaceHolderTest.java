import config.TestConfig;
import org.testng.annotations.Test;

import static constants.Constants.Actions.JSON_PLACEHOLDER_GET;
import static io.restassured.RestAssured.given;

public class JsonPlaceHolderTest extends TestConfig { // этот класс тоже экстендится от TestConfig
    // JsonPlaceHolder это ресурс, где есть возможность чекнуть все виды запросов
    // Далее в константах добавляем URL, PATH и эндпоинты для наших запросов (Actions - константы запросов)

    @Test
    public void getTest() {
        given().queryParams("postId", 1).
                log().uri().
        when().
                get( JSON_PLACEHOLDER_GET). // здесь добавляем передачу параметра с помощью вызова queryParam
        then().
                log().body().statusCode(200);
    }

}
