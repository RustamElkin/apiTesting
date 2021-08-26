package config;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import static constants.Constants.RunVariable.path;
import static constants.Constants.RunVariable.server;

// Создание конфигурационного класса
// TestConfig содержит пред установку необходимых действий

public class TestConfig {

// чтобы этот метод выполнялся перед каждым тестовым методом добавляем аннотацию @BeforeClass из testng
// и в самом тесте наследуемся от TestConfig - public class FirstTest extends TestConfig {...

    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = server;
        RestAssured.basePath = path;

    }

}
