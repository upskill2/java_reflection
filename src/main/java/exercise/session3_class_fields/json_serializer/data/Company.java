package exercise.session3_class_fields.json_serializer.data;

public class Company {
    private final String name;
    private final String city;
    private final Address address;

    public Company (String name, String city, Address address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
