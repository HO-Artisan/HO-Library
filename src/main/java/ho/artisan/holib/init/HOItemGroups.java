package ho.artisan.holib.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;

import static ho.artisan.holib.HOLibMod.REGISTRAR;

public class HOItemGroups {
    public static final ItemGroup MAIN = REGISTRAR.createTab("main", Items.DIAMOND);

    public static void register() {

    }
}
