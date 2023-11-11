package ho.artisan.lib;

import ho.artisan.lib.example.HORegistryHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HOLibrary implements ModInitializer {
    public static final String MOD_ID = "holib";
    public static final String MOD_NAME = "HOLibrary";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            HORegistryHandler.load();
        }
        LOGGER.info(MOD_NAME + " has set up!");
    }
}
