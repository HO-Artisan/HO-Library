package ho.artisan.holib;

import ho.artisan.holib.init.*;
import ho.artisan.holib.registry.Registrar;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HOLibMod implements ModInitializer {
    public static final String MODID = "holib";
    public static final String MOD_NAME = "HO-Library";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final Registrar REGISTRAR = new Registrar(MODID);

    @Override
    public void onInitialize() {
        HOBlocks.register();
        HOBlockEntityTypes.register();
        HOScreenHandlerTypes.register();
        HOItems.register();
        HOItemGroups.register();

        LOGGER.info(MOD_NAME + " has set up!");
    }
}
