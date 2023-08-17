package exercise.session2_construcors.constructors_factory;

import java.lang.reflect.InvocationTargetException;

import static exercise.session2_construcors.constructors_factory.Utils.createInstanceWithArguments;

public class Main {

    public static void main (String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        //    Utils.printConstructorData (Address.class);

        Address address = createInstanceWithArguments (Address.class, "Main Street", 1);

        Person person = createInstanceWithArguments (Person.class);
        System.out.println (person);

        Person person1 = createInstanceWithArguments (Person.class, "John", 25);
        System.out.println (person1);

        Person person2 = createInstanceWithArguments (Person.class, address, "Phil", 99);
        System.out.println (person2);

    }
}
