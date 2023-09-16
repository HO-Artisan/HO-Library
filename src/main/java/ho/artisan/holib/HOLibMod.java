package ho.artisan.holib;

import ho.artisan.holib.tool.Registrar;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HOLibMod implements ModInitializer {
    public static final String MODID = "holib";
    public static final String MOD_NAME = "HO-Library";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    private static final Registrar REGISTRAR = new Registrar(MODID);

    @Override
    public void onInitialize() {
        LOGGER.info(MOD_NAME + " has set up!");
    }
}
