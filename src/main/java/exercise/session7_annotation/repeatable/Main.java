package exercise.session7_annotation.repeatable;

import exercise.session7_annotation.discovery.annotations.ScanPackages;

import java.io.IOException;
import java.net.URISyntaxException;

import static exercise.session7_annotation.repeatable.utils.Utils.schedule;

@ScanPackages (packages = {"exercise.session7_annotation.repeatable.loaders"})
public class Main {

    public static void main (String[] args) throws URISyntaxException, IOException {
        schedule ();
    }

}
