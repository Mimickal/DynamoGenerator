package mimickal.mc.dynamo.common;

import mimickal.mc.dynamo.DynamoMod;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {

    private static final String CONFIG_NAME = "config.cfg";
    private static final String CATEGORY = "config";

    private static final float ENERGY_MULTIPLIER_DEFAULT = 1.0f;
    private static final float ENERGY_MULTIPLIER_MIN = 0f;
    private static final float ENERGY_MULTIPLIER_MAX = 20.0f;

    public static float energyMultiplier = ENERGY_MULTIPLIER_DEFAULT;

    public static void load() {
        File configFile = new File("config/" + DynamoMod.MOD_ID + "/" + CONFIG_NAME);
        Configuration config = new Configuration(configFile);

        config.load();

        energyMultiplier = config.getFloat(
                "energy_multiplier", CATEGORY,
                ENERGY_MULTIPLIER_DEFAULT, ENERGY_MULTIPLIER_MIN, ENERGY_MULTIPLIER_MAX,
                "Value to scale energy output by"
        );

        config.save();
    }

}
