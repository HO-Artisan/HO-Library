package ho.artisan.holib.init;

import ho.artisan.holib.impl.HOBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;

import static ho.artisan.holib.HOLibMod.REGISTRAR;

public class HOBlocks {
    public static final HOBlock HO_BLOCK = new HOBlock(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK));

    public static void register() {
        REGISTRAR.register("ho", HO_BLOCK);
    }
}
