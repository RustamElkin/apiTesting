package UserService.Auth;

import config.TestConfig;
import connectionMySQLdb.ConnectionMySQLdb;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static constants.Constants.Actions.*;
import static constants.Constants.Path.JMART_AUTHSIGHNIN_PATH;
import static constants.Constants.Servers.JMART_URL;
import static io.restassured.RestAssured.given;

public class authTest extends TestConfig {

    private ConnectionMySQLdb connectionMySQLdb;


    @Test
    public void PostAuthSignIn() {

        String postJsonBody = "{\n" +
                "    \"login\": \"dev_test_admin@email.com\",\n" +
                "    \"password\": \"Test_4dmin_Jmart\"\n" +
                "}";
        given().
                body(postJsonBody).
                log().body().
        when().
                post(JMART_URL + JMART_AUTHSIGHNIN_PATH + JMART_AUTHSIGHNIN_POST).
        then().
                spec(responseSpecificationForPost).
                log().body();

    }

    @Test
    public void PostAuthSignInByOtp() {

        String postJsonBody = "{\n" +
                "    \"mobile_phone\": \"+7(777)055-13-64\"\n" +
                "}";
        given().
                body(postJsonBody).
                log().body().
        when().
                post(JMART_URL + JMART_AUTHSIGHNIN_PATH + JMART_AUTHSIGHNIN_BY_OTP_POST).
        then().
                spec(responseSpecificationForPost).
                log().body();

    }

    public void SMSCode() throws SQLException, InterruptedException {
//        connectionMySQLdb = new ConnectionMySQLdb();

        ConnectionMySQLdb.GetPhoneSQL();
//        ConnectionMySQLdb.getSmsCode(); // ниже в Json-e вызываем.
        Thread.sleep(2000);
    }

    @Test
    public void PostAuthSignInByOtpVerify() throws SQLException, InterruptedException {
        SMSCode();
        String postJsonBody = "{\n" +
                "    \"mobile_phone\": \"+7(777)055-13-64\",\n" +
//                "    \"otp\": \"5656\"\n" +
                "    \"otp\":" + "\"" + ConnectionMySQLdb.getSmsCode() + "\"\n" +
                "}";

        given().
                body(postJsonBody).
                log().body().
        when().
                post(JMART_URL + JMART_AUTHSIGHNIN_PATH + JMART_AUTHSIGHNIN_BY_OTP_VERIFY_POST).
        then().
                spec(responseSpecificationForPost).
                log().body();
    }


}
