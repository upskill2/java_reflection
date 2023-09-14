package exercise.session7_annotation.discovery.http;

import exercise.session7_annotation.discovery.annotations.InitializerClass;
import exercise.session7_annotation.discovery.annotations.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

    @InitializerMethod
    public void registerService () {
        System.out.println ("Service successfully registered");
    }
}
