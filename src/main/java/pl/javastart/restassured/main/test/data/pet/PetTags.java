package pl.javastart.restassured.main.test.data.pet;

public enum PetTags {

    YOUNG_PET(1, "young-pet"),
    ADULT_PET(2, "adult-pet");

    private int tagId;
    private String tagName;

    PetTags(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public int getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }
}
