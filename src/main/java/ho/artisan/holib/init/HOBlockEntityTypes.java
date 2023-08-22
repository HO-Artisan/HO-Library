package ho.artisan.holib.init;

import ho.artisan.holib.impl.HOTile;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

import static ho.artisan.holib.HOLibMod.REGISTRAR;

public class HOBlockEntityTypes {

    public static final BlockEntityType<HOTile> HO_BLOCK_ENTITY =
            FabricBlockEntityTypeBuilder.create(HOTile::new, HOBlocks.HO_BLOCK).build();

    public static void register() {
        REGISTRAR.register("ho", HO_BLOCK_ENTITY);
    }
}
