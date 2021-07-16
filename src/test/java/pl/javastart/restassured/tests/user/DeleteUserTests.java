package pl.javastart.restassured.tests.user;

import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.rop.DeletePetEndpoint;
import pl.javastart.restassured.main.rop.DeleteUserEndpoint;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

public class DeleteUserTests extends SuiteTestBase {

 private String nonExistingUsername;

    @BeforeMethod
    public void beforeTest(){
        nonExistingUsername = new Faker().name().username();
        new DeleteUserEndpoint().setUserName(nonExistingUsername).sendRequest();
    }

    @Test
    public void givenNonExistingPetWhenDeletingPetThenPetNotFoundTest() {
        new DeleteUserEndpoint().setUserName(nonExistingUsername).sendRequest().assertStatusCode(HttpStatus.SC_OK);
    }

}
