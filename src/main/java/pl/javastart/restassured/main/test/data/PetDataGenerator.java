package pl.javastart.restassured.main.test.data;

import pl.javastart.restassured.main.pojo.pet.Category;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.pojo.pet.Tag;
import pl.javastart.restassured.main.test.data.pet.PetCategory;
import pl.javastart.restassured.main.test.data.pet.PetStatus;
import pl.javastart.restassured.main.test.data.pet.PetTags;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PetDataGenerator extends TestDataGenerator{

    public Pet generatePet(){
        Category category = new Category();
        PetCategory randomPetCategory = randomPetCategory();
        category.setId(randomPetCategory.getId());
        category.setName(randomPetCategory.getCategoryName());

        Tag tag = new Tag();
        PetTags randomPetTag = randomPetTag();
        tag.setId(randomPetTag.getTagId());
        tag.setName(randomPetTag.getTagName());

        Pet pet = new Pet();
        pet.setId(faker().number().numberBetween(1, 9999));
        pet.setCategory(category);
        pet.setName(faker().funnyName().name());
        pet.setPhotoUrls(Collections.singletonList(faker().internet().url()));
        pet.setTags(Collections.singletonList(tag));
        PetStatus randomPetStatus = randomPetStatus();
        pet.setStatus(randomPetStatus.getStatus());
        return pet;
    }

    private PetStatus randomPetStatus() {
        int pick= new Random().nextInt(PetStatus.values().length);
        return PetStatus.values()[pick];
    }

    private PetCategory randomPetCategory() {
        int pick = new Random().nextInt(PetCategory.values().length);
        return PetCategory.values()[pick];
    }

    private PetTags randomPetTag() {
        int pick = new Random().nextInt(PetTags.values().length);
        return PetTags.values()[pick];
    }
}
