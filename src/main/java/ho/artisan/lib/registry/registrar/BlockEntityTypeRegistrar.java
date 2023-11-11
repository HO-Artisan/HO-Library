package ho.artisan.lib.registry.registrar;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BlockEntityTypeRegistrar extends VanillaRegistrar<BlockEntityType<?>> {
    public BlockEntityTypeRegistrar(String modid) {
        super(modid, Registry.BLOCK_ENTITY_TYPE);
    }

    public
    <T extends BlockEntity> BlockEntityType<T>
    register(String id,
             FabricBlockEntityTypeBuilder.Factory<T> factory,
             Block... blocks) {
        return register(id, FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }
}
