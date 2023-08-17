package exercise.session3_class_fields;

public class Movie extends Product {

    public static final double MINIMUM_PRICE = 10.99;

    public boolean isReleased;
    public Category category;

    public double actualPrice;

    public Movie (String name, int year, boolean isReleased, Category category, double price) {
        super (name, year);
        this.isReleased = isReleased;
        this.category = category;
        this.actualPrice = Math.max (price, MINIMUM_PRICE);
    }

    //Nested class
    public class MovieStats {
        public double timeWatched;

        public MovieStats (double timeWatched) {
            this.timeWatched = timeWatched;
        }

        public double getRevenue () {
            return actualPrice * timeWatched;
        }
    }
}
