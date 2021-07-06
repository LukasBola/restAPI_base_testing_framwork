package pl.javastart.restassured.tests.pet;

import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.pet.Category;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.pojo.pet.Tag;
import pl.javastart.restassured.main.test.data.PetDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests extends SuiteTestBase {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        PetDataGenerator petDataGenerator = new PetDataGenerator();
        Pet pet = petDataGenerator.generatePet();

        Pet actualPet = given().log().all().body(pet).contentType("application/json")
                .when().post("pet")
                .then().statusCode(200).log().all().extract().as(Pet.class);


        assertEquals(actualPet.getId(), pet.getId(), "Pet id");
        assertEquals(actualPet.getName(), pet.getName(), "Pet name");

    }
}
