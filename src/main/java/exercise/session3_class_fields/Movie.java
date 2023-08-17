package exercise.session3_class_fields;

public class Movie extends Product {

    public static final double MINIMUM_PRICE = 10.99;

    private boolean isReleased;
    private Category category;

    private double actualPrice;

    public Movie (String name, int year, boolean isReleased, Category category, double price) {
        super (name, year);
        this.isReleased = isReleased;
        this.category = category;
        this.actualPrice = Math.max (price, MINIMUM_PRICE);
    }
}
