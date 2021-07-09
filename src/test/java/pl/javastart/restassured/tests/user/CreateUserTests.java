package pl.javastart.restassured.tests.user;

import org.assertj.core.api.Assertions;
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

        ApiResponse apiResponse = given().contentType("application/json")
                .body(user)
                .when().post("user")
                .then().statusCode(200).extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(200);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getId().toString());

        apiResponse.setMessage("invalid_message_test"); // <<== celowo psuję test by uzyskać błąd i zademonstrować działanie asercji
        Assertions.
                assertThat(apiResponse).describedAs("Send Pet was different than received by API").
                usingRecursiveComparison().
                isEqualTo(expectedApiResponse);
    }

    @AfterMethod
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = given().contentType("application/json")
                .when().delete("user/{userName}", user.getUsername())
                .then().statusCode(200).extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(200);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getUsername());

        Assertions.
                assertThat(apiResponse).describedAs("Send user was different than received by API").
                usingRecursiveComparison().
                isEqualTo(expectedApiResponse);
    }
}
