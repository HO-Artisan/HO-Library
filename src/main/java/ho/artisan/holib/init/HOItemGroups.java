package ho.artisan.holib.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;

import static ho.artisan.holib.HOLibMod.REGISTRAR;

public class HOItemGroups {
    public static ItemGroup main;

    public static void register() {
        main = REGISTRAR.createTab("main", Items.DIAMOND);
    }
}
