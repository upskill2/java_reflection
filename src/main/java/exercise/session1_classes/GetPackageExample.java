package exercise.session1_classes;

public class GetPackageExample {

    public static void main(String[] args) {
        Class<?> intClass = int.class;
        Class<?> doubleArrayClass = double[].class;

        Package intPackage = intClass.getPackage();
        Package doubleArrayPackage = doubleArrayClass.getPackage();

        System.out.println("Package for int: " + intPackage); // Outputs: Package for int: null
        System.out.println("Package for double[]: " + doubleArrayPackage); // Outputs: Package for double[]: null
    }
}
