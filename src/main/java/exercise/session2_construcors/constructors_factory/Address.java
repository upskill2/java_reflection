package exercise.session2_construcors.constructors_factory;

public class Address {

    private final String street;
    private final int houseNumber;

    public Address (String street, int houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString () {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}
