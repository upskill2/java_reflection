package exercise.session3_class_fields.json_serializer.data1;

public class Person {

    private final String name;
    private final int age;
    private final boolean employed;
    private final float salary;
    private final Address address;
    private final Company job;

    public Person (String name, int age, boolean employed, float salary, Address address, Company job) {
        this.name = name;
        this.age = age;
        this.employed = employed;
        this.salary = salary;
        this.address = address;
        this.job = job;
    }

}

