import config.TestConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static constants.Constants.Actions.*;
import static constants.Constants.Path.JMART_PATH;
import static constants.Constants.Servers.JMART_URL;
import static io.restassured.RestAssured.given;

public class AzatTest extends TestConfig  {

    @Test
    public void PostAuthSignIn () {

        String postJsonBody = "{\n" +
                "    \"login\": \"dev_test_admin@email.com\",\n" +
                "    \"password\": \"Test_4dmin_Jmart\"\n" +
                "}";
        Response response =
        given().
                body(postJsonBody).
                log().body().
        when().
                post(JMART_URL + JMART_PATH + JMART_AUTHSIGHNIN_POST ).
        then().
                spec(responseSpecificationForPost).
                log().body().extract().response();

        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void PostAuthSignInByOtp () {

        String postJsonBody = "{\n" +
                "    \"mobile_phone\": \"+7(777)055-13-64\"\n" +
                "}";
        Response response =
                given().
                        body(postJsonBody).
                        log().body().
                when().
                        post(JMART_URL + JMART_PATH + JMART_AUTHSIGHNIN_BY_OTP_POST ).
                then().
                        spec(responseSpecificationForPost).
                        log().body().
                        extract().response();

        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void PostAuthSignInByOtpVerify () {

        String postJsonBody = "{\n" +
                "    \"mobile_phone\": \"+7(777)055-13-64\",\n" +
                "    \"otp\": \"1950\"\n" +
                "}";

        Response response =
                given().
                        body(postJsonBody).
                        log().body().
                when().
                        post(JMART_URL + JMART_PATH + JMART_AUTHSIGHNIN_BY_OTP_VERIFY_POST ).
                then().
                        spec(responseSpecificationForPost).
                        log().body().
                        extract().response();

        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }


}
