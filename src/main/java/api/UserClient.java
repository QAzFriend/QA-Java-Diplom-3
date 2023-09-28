package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends ApiBase {
    private final static String CREATE_USER_ENDPOINT = "api/auth/register";
    private final static String LOGIN_USER_ENDPOINT ="/api/auth/login";
    private static final String DELETE_USER_ENDPOINT ="/api/auth/user";

    @Step("Create new user. POST /api/auth/register")
    public ValidatableResponse createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(CREATE_USER_ENDPOINT)
                .then();
    }

    @Step("User login. POST /api/auth/login")
    public static ValidatableResponse loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_USER_ENDPOINT)
                .then();
    }

    @Step ("User delete. DELETE /api/auth/user")
    public static void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .when()
                .delete(DELETE_USER_ENDPOINT)
                .then();
    }
}
