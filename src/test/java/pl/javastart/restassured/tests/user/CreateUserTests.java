package pl.javastart.restassured.tests.user;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.user.User;
import pl.javastart.restassured.main.rop.CreateUserEndpoint;
import pl.javastart.restassured.main.test.data.UserDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends SuiteTestBase {

    private User user;

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        user = new UserDataGenerator().generateUser();

        ApiResponse apiResponse = new CreateUserEndpoint().setUser(user).sendRequest().assertRequestSuccess().getResponseModel();

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(HttpStatus.SC_OK);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getId().toString());

//        apiResponse.setMessage("invalid_message_test"); // <<== celowo psuję test by uzyskać błąd i zademonstrować działanie asercji
        Assertions.
                assertThat(apiResponse).describedAs("Send Pet was different than received by API").
                usingRecursiveComparison().
                isEqualTo(expectedApiResponse);
    }

    @AfterMethod
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = given().contentType("application/json")
                .when().delete("user/{userName}", user.getUsername())
                .then().statusCode(HttpStatus.SC_OK).extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(HttpStatus.SC_OK);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getUsername());

        Assertions.
                assertThat(apiResponse).describedAs("Send user was different than received by API").
                usingRecursiveComparison().
                isEqualTo(expectedApiResponse);
    }
}
