package config;

import data.Map;
import iface.IConfiguration;
import iface.IReflection;

import java.util.Scanner;

public class Startup {

    private final IConfiguration config;

    public Startup(IConfiguration configuration) {
        if (configuration == null)
            throw new IllegalArgumentException(
                    "configuration must not be null."
            );
        this.config = configuration;
    }

    public void run() {
        while (true) {
            String input = new Scanner(System.in).nextLine();
            IReflection action = config.getMap().match(input);
            (action != null ? action : new IReflection() {
                @Override
                public void invoke(String input) {
                    config.getConsole().printerr(
                            "'" + input + "' is not recognized as an internal command."
                    );
                }
            }).invoke(input);
        }
    }


    public IConfiguration getConfig()
    {
        return config;
    }

    /**
     * <summary>
     *     Outputs all the keys from the main configuration IMap.
     * </summary>
     *
     * @return this object instance.
     */
    public Startup outputKeys() {
        ((Map) config.getMap()).outputKeys();
        return this;
    }

    /**
     * used for debugging.
     * @return this object instance.
     */
    public Startup outputNotFoundKeys() {
        ((Map) config.getMap()).outputNotFoundKeys();
        return this;
    }

}