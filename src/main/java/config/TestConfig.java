package config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static constants.Constants.RunVariable.path;
import static constants.Constants.RunVariable.server;
import static constants.Constants.Servers.JSON_PLACEHOLDER_URL;
import static constants.Constants.Servers.REQUESTBIN_URL;

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

    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = server;
        RestAssured.basePath = path;

        RequestSpecification requestSpecJson = new RequestSpecBuilder().
                addHeader("Content-Type", "application/json").
                addCookie("testCookieJson").
                build();
        RestAssured.requestSpecification = requestSpecJson;

    }

}
