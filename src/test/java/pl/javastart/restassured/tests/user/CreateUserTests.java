package pl.javastart.restassured.tests.user;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.user.User;
import pl.javastart.restassured.main.rop.CreateUserEndpoint;
import pl.javastart.restassured.main.rop.DeleteUserEndpoint;
import pl.javastart.restassured.main.test.data.UserDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends SuiteTestBase {

    private User user;

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        user = new UserDataGenerator().generateUser();

        ApiResponse apiResponse = new CreateUserEndpoint().setUser(user).sendRequest().assertRequestSuccess().getResponseModel();
        ApiResponse expectedApiResponse = new ApiResponse(HttpStatus.SC_OK, "unknown", user.getId().toString());

//        apiResponse.setMessage("invalid_message_test"); // <<== celowo psuję test by uzyskać błąd i zademonstrować działanie asercji
        Assertions.
                assertThat(apiResponse).describedAs("Send Pet was different than received by API").
                usingRecursiveComparison().
                isEqualTo(expectedApiResponse);
    }

    @AfterMethod
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = new DeleteUserEndpoint().setUserName(user.getUsername()).sendRequest().assertRequestSuccess().getResponseModel();
        ApiResponse expectedApiResponse = new ApiResponse(HttpStatus.SC_OK, "unknown", user.getUsername());

        Assertions.
                assertThat(apiResponse).describedAs("Send user was different than received by API").
                usingRecursiveComparison().
                isEqualTo(expectedApiResponse);
    }
}
