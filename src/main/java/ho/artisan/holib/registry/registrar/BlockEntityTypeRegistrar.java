package ho.artisan.holib.registry.registrar;

import ho.artisan.holib.registry.Registrar;
import ho.artisan.holib.registry.RegistryCasket;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;

public class BlockEntityTypeRegistrar extends Registrar<BlockEntityType<?>> {
    public BlockEntityTypeRegistrar(String modid) {
        super(modid, Registries.BLOCK_ENTITY_TYPE);
    }


    public <T extends BlockEntity> RegistryCasket<BlockEntityType<T>> register(
            String id,
            FabricBlockEntityTypeBuilder.Factory<T> factory,
            Block... blocks) {
        return register(id, FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }
}
