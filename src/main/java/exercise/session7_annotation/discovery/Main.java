package exercise.session7_annotation.discovery;

import exercise.session7_annotation.discovery.annotations.ScanPackages;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

import static exercise.session7_annotation.discovery.utils.Utils.initialize;

@ScanPackages (packages = {"exercise.session7_annotation.discovery.databases", "exercise.session7_annotation.discovery"
        , "exercise.session7_annotation.discovery.http", "exercise.session7_annotation.discovery.configs"})
public class Main {

    public static void main (String[] args) throws Throwable {
        initialize ();
    }
}
