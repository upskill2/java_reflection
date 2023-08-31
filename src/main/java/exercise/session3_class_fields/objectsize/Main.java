package exercise.session3_class_fields.objectsize;

public class Main {

    public static void main (String[] args) throws IllegalAccessException {

        AccountSummary accountSummary = new AccountSummary ("John", "Smith", (short) 20, 100_000);
        Long result = new ObjectSizeCalculator ().sizeOfObject (accountSummary);
        System.out.println (result);
    }


}
