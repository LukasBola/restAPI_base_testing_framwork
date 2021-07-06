package pl.javastart.restassured.main.test.data.pet;

public enum PetCategory {
    DOGS(1, "dogs"),
    CATS(2, "cats"),
    OTHERS(3, "others");

    private int id;
    private String categoryName;

    PetCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
