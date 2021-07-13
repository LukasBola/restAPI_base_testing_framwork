package pl.javastart.restassured.tests.pet;

import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.rop.DeletePetEndpoint;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

public class DeletePetTests extends SuiteTestBase {

    int nonExistingPetId;

    @BeforeMethod
    public void beforeTest(){
        nonExistingPetId = new Faker().number().numberBetween(10000, 100000);
        new DeletePetEndpoint().setPetId(nonExistingPetId).sendRequest();
    }

    @Test
    public void givenNonExistingPetWhenDeletingPetThenPetNotFoundTest() {
        new DeletePetEndpoint().setPetId(nonExistingPetId).sendRequest().assertStatusCode(HttpStatus.SC_NOT_FOUND);
    }
}
