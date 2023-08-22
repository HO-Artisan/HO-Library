package ho.artisan.holib;

import ho.artisan.holib.impl.HOScreen;
import ho.artisan.holib.init.HOScreenHandlerTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class HOLibClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(HOScreenHandlerTypes.HO_SCREEN_HANDLER, HOScreen::new);
    }
}
