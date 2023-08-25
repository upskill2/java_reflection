package exercise.session3_class_fields.json_serializer.jsonwriter;

import exercise.session3_class_fields.json_serializer.data.Address;
import exercise.session3_class_fields.json_serializer.data.Company;
import exercise.session3_class_fields.json_serializer.data.Person;

import static exercise.session3_class_fields.json_serializer.jsonwriter.Utils.objectToJson;

public class Main {

    public static void main (String[] args) throws IllegalAccessException {

        Company company = new Company ("Google", "Mountain View", new Address ("Main Street", (short) 123));

        Address address = new Address ("Main Street", (short) 123);
        Person person = new Person ("John", 30, true, 1000.555f, address, company);

   //     System.out.println (objectToJson (address, 0));
        System.out.println (objectToJson (person, 0));

        //{"street":"Main Street","apartment":123}
    }


}
