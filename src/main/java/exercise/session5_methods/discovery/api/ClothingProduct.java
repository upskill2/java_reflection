package exercise.session5_methods.discovery.api;

public class ClothingProduct extends Product {

    public Size getSize () {
        return size;
    }

    public void setSize (Size size) {
        this.size = size;
    }

    private Size size;
    private String color;


    public String getColor () {
        return color;
    }


    public void setColor (String color) {
        this.color = color;
    }
}
