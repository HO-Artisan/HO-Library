package ho.artisan.holib.init;

import ho.artisan.holib.impl.HOScreenHandler;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

import static ho.artisan.holib.HOLibMod.REGISTRAR;

public class HOScreenHandlerTypes {

    public static final ScreenHandlerType<HOScreenHandler> HO_SCREEN_HANDLER =
            new ScreenHandlerType<>(HOScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES);

    public static void register() {
        REGISTRAR.register("ho", HO_SCREEN_HANDLER);
    }
}
