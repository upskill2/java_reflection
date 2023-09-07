package exercise.session5_methods.discovery;

import exercise.session5_methods.discovery.api.ClothingProduct;
import exercise.session5_methods.discovery.api.Product;

import static exercise.session5_methods.discovery.utils.Helper.testGetters;
import static exercise.session5_methods.discovery.utils.Helper.testSetters;

public class Main {

    public static void main (String[] args) {
   //     testGetters(Product.class);
        testSetters (ClothingProduct.class);
        testGetters (ClothingProduct.class);
    }
}
