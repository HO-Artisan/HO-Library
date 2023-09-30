package ho.artisan.holib;

import ho.artisan.holib.registry.registrar.ItemRegistrar;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HOLibMod implements ModInitializer {
    public static final String MOD_ID = "holib";
    public static final String MOD_NAME = "HO-Library";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    private static final ItemRegistrar ITEM_REGISTRAR = new ItemRegistrar(MOD_ID);


    @Override
    public void onInitialize() {

        LOGGER.info(MOD_NAME + " has set up!");
    }
}
