package exercise.session1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise {

    public static void main (String[] args) {
        List<String> list = new ArrayList<> ();

        findAllImplementedInterfaces (list.getClass ());
    }

    /**
     * Returns all the interfaces that the current input class implements.
     * Note: If the input is an interface itself, the method returns all the interfaces the
     * input interface extends.
     */
    public static Set<Class<?>> findAllImplementedInterfaces (Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<> ();

        Class<?>[] inputInterfaces = input.getInterfaces ();
        for (Class<?> currentInterface : inputInterfaces) {
            allImplementedInterfaces.add (currentInterface);
            if (currentInterface.isInterface ()) {
                allImplementedInterfaces.addAll (findAllImplementedInterfaces (currentInterface));
            }

            /** Complete this code **/
        }

        System.out.println (allImplementedInterfaces);
        return allImplementedInterfaces;
    }
}
