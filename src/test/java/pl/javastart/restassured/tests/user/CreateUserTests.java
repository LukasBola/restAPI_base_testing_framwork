package pl.javastart.restassured.tests.user;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.user.User;
import pl.javastart.restassured.main.test.data.UserDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class CreateUserTests extends SuiteTestBase {

    private User user;

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        UserDataGenerator userDataGenerator = new UserDataGenerator();
        user = userDataGenerator.generateUser();

////      I sposób
//        given().
//                body(user).
//                contentType("application/json").
//                when().post("user").
//                then().
//                assertThat().statusCode(200).
//                assertThat().body(
//                "code", equalTo(200),
//                "type", equalTo("unknown"),
//                "message", equalTo(Integer.toString(user.getId())));

//      II sposób z wykorzystaniem POJO
        ApiResponse apiResponse = given().contentType("application/json")
                .body(user)
                .when().post("user")
                .then().statusCode(200).extract().as(ApiResponse.class);

        assertEquals(apiResponse.getCode(), Integer.valueOf(200), "Code");
        assertEquals(apiResponse.getType(), "unknown", "Type");
        assertEquals(apiResponse.getMessage(), Integer.toString(user.getId()), "Message");
    }

    @AfterMethod
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = given().contentType("application/json")
                .when().delete("user/{userName}", user.getUsername())
                .then().statusCode(200).extract().as(ApiResponse.class);

        assertEquals(apiResponse.getCode(), Integer.valueOf(200), "Code");
        assertEquals(apiResponse.getType(), "unknown", "Type");
        assertEquals(apiResponse.getMessage(), user.getUsername(), "Message");
    }
}
