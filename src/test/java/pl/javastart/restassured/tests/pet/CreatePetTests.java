package pl.javastart.restassured.tests.pet;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.Category;
import pl.javastart.restassured.main.pojo.Pet;
import pl.javastart.restassured.main.pojo.Tag;
import pl.javastart.restassured.main.properties.EnvironmentConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests {

    @BeforeMethod
    public void beforeMethod() {
        EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

        RestAssured.baseURI = environmentConfig.baseUri();
        RestAssured.basePath = environmentConfig.basePath();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

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
