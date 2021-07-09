package pl.javastart.restassured.tests.pet;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.request.configuration.RequestConfigurationBuilder;
import pl.javastart.restassured.main.test.data.PetDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests extends SuiteTestBase {

    private Pet pet;

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        pet = new PetDataGenerator().generatePet();

        Pet actualPet = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .body(pet)
                .when().post("pet")
                .then().statusCode(HttpStatus.SC_OK).extract().as(Pet.class);

        pet.setName("Diego"); // <<== celowo psuję test by uzyskać błąd i zademonstrować działanie asercji

        Assertions.assertThat(actualPet).describedAs("Send Pet was different than received by API").usingRecursiveComparison().isEqualTo(pet);
    }

    @AfterMethod
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().delete("pet/{petId}", pet.getId())
                .then().statusCode(HttpStatus.SC_OK).extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(HttpStatus.SC_OK);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(pet.getId().toString());

        Assertions.
                assertThat(apiResponse).describedAs("API Response from system was not as expected").
                usingRecursiveComparison().
                isEqualTo(expectedApiResponse);
    }

}
