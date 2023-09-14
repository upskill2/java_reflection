package exercise.session7_annotation.discovery;

import exercise.session7_annotation.discovery.annotations.InitializerClass;
import exercise.session7_annotation.discovery.annotations.InitializerMethod;

@InitializerClass
public class AutoSaver {

    @InitializerMethod
    public void startAutoSavingThreads () {
        System.out.println ("Start automatic data saving to disk");
    }



}
