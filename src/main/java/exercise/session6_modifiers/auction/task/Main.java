package exercise.session6_modifiers.auction.task;

import static exercise.session6_modifiers.auction.task.JsonWriter.objectToJson;

public class Main {

    public static void main (String[] args) throws IllegalAccessException {
        Address address = new Address ("Street", (short) 1, "12345");
        System.out.println (objectToJson (address));
    }
}
