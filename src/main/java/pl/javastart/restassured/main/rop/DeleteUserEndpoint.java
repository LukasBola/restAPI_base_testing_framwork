package pl.javastart.restassured.main.rop;

import org.apache.http.HttpStatus;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.user.User;
import pl.javastart.restassured.main.request.configuration.RequestConfigurationBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeleteUserEndpoint extends BaseEndpoint<DeleteUserEndpoint, ApiResponse> {

    private String userName;

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Override
    public DeleteUserEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().delete("user/{userName}", userName);
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public DeleteUserEndpoint setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
