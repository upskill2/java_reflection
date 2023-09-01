package exercise.session4_fields_modification.configloader;

import exercise.session4_fields_modification.data.GameConfig;
import exercise.session4_fields_modification.data.UserInterfaceConfig;
import exercise.session4_fields_modification.utils.ConfigParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

import static exercise.session4_fields_modification.utils.ConfigParser.createConfigObject;

public class Main {

    private static final Path CONFIG_FILE = Path.of ("src/main/resources/game-properties.cfg");
    private static final Path UI_CONFIG_FILE = Path.of ("src/main/resources/user-interface.cfg");

    public static void main (String[] args) throws IOException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        GameConfig config = createConfigObject (GameConfig.class, CONFIG_FILE);
        System.out.println (config);

        System.out.println ("---------------------------");
        UserInterfaceConfig userInterfaceConfig = createConfigObject (UserInterfaceConfig.class, UI_CONFIG_FILE);
        System.out.println (userInterfaceConfig);

    }
}
