package exercise.session7_annotation.discovery.configs;

import exercise.session7_annotation.discovery.annotations.InitializerClass;
import exercise.session7_annotation.discovery.annotations.InitializerMethod;

@InitializerClass
public class ConfigsLoader {

    @InitializerMethod
    public void loadAllConfigs () {
        System.out.println ("Loading all configs...");
    }
}
