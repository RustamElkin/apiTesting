package config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static constants.Constants.RunVariable.path;
import static constants.Constants.RunVariable.server;
import static constants.Constants.Servers.*;

// Создание конфигурационного класса
// TestConfig содержит пред установку необходимых действий

public class TestConfig {

// чтобы этот метод выполнялся перед каждым тестовым методом добавляем аннотацию @BeforeClass из testng
// и в самом тесте наследуемся от TestConfig - public class FirstTest extends TestConfig {...

    protected RequestSpecification requestSpecXML = new RequestSpecBuilder().
            addHeader("Content-Type", "application/xml").
            addCookie("testCookieXML").
            setBaseUri(REQUESTBIN_URL).
            build();
    // Если спецификация уникальна, выносим из @BeforeClass, в сам класс TestConfig, наполняем определенными полями и используем requestSpecXML в самом тесте.

    protected ResponseSpecification responseSpecificationForGet = new ResponseSpecBuilder().
            expectStatusCode(200).
            build();

    protected ResponseSpecification responseSpecificationForPost = new ResponseSpecBuilder().
            expectStatusCode(201).
            build();

    protected RequestSpecification requestSpecificationForPlaceholderTests = new RequestSpecBuilder().
            setBaseUri(JSON_PLACEHOLDER_URL).
            build();
// Assertions для ответов с Java библиотекой Hamcrest


    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = server;
        RestAssured.basePath = path;

        // ДО
//        RequestSpecification requestSpecJson = new RequestSpecBuilder().
//                addHeader("Content-Type", "application/json").
//                addCookie("testCookieJson").
//                build();
//        RestAssured.requestSpecification = requestSpecJson;

        // ПОСЛЕ
        RestAssured.requestSpecification = new RequestSpecBuilder().
                addHeader("Content-Type", "application/json").
                addCookie("testCookieJson").
                build();

        // тут если у всех дублируется параметры и будет применяться для каждого нашего теста

    }

}
