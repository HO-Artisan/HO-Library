package ho.artisan.holib.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import static ho.artisan.holib.HOLibMod.REGISTRAR;

public class HOItems {
    public static final Item HO_BLOCK = new BlockItem(HOBlocks.HO_BLOCK, new Item.Settings().rarity(Rarity.EPIC));

    public static void register() {
        REGISTRAR.register("ho", HO_BLOCK);
    }
}
