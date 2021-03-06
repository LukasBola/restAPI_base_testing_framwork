package pl.javastart.restassured.tests.pet;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.rop.CreatePetEndpoint;
import pl.javastart.restassured.main.rop.DeletePetEndpoint;
import pl.javastart.restassured.main.test.data.PetDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;


public class CreatePetTests extends SuiteTestBase {

    private Pet actualPet;

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        Pet pet = new PetDataGenerator().generatePet();
        actualPet = new CreatePetEndpoint().setPet(pet).sendRequest().assertRequestSuccess().getResponseModel();

//        pet.setName("Diego"); // <<== celowo psuję test by uzyskać błąd i zademonstrować działanie asercji

        Assertions.assertThat(actualPet).describedAs("Send Pet was different than received by API").usingRecursiveComparison().isEqualTo(pet);
    }

    @AfterMethod
    public void cleanUpAfterTest() {

        ApiResponse apiResponse = new DeletePetEndpoint().setPetId(actualPet.getId()).sendRequest().assertRequestSuccess().getResponseModel();
        ApiResponse expectedApiResponse = new ApiResponse(HttpStatus.SC_OK, "unknown", actualPet.getId().toString());

        Assertions.
                assertThat(apiResponse).describedAs("API Response from system was not as expected").
                usingRecursiveComparison().
                isEqualTo(expectedApiResponse);
    }

}
