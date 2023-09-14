package exercise.session7_annotation.discovery.databases;

import exercise.session7_annotation.discovery.annotations.InitializerClass;
import exercise.session7_annotation.discovery.annotations.InitializerMethod;

@InitializerClass
public class CacheLoader {

    @InitializerMethod
    public void loadCache () {
        System.out.println ("Loading data from cache");
    }

    @InitializerMethod
    public void reloadCache () {
        System.out.println ("Reloading cache");
    }

}
