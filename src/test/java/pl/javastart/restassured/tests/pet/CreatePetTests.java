package pl.javastart.restassured.tests.pet;

import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.pet.Category;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.pojo.pet.Tag;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests extends SuiteTestBase {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        Category category = new Category();
        category.setId(778);
        category.setName("cat");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("cat-category");

        Tag tag1 = new Tag();
        tag1.setId(2);
        tag1.setName("kocur");
        List<Tag> tags = Arrays.asList(tag, tag1);

        Pet pet = new Pet();
        pet.setId(777);
        pet.setCategory(category);
        pet.setName("Filemon dziki kocur");
        pet.setPhotoUrls(Collections.singletonList("https://wallpaperaccess.com/full/278984.jpg"));
        pet.setTags(tags);
        pet.setStatus("sold");

        Pet actualPet = given().log().all().body(pet).contentType("application/json")
                .when().post("pet")
                .then().statusCode(200).log().all().extract().as(Pet.class);


        assertEquals(actualPet.getId(), pet.getId(), "Pet id");
        assertEquals(actualPet.getName(), pet.getName(), "Pet name");

    }
}
