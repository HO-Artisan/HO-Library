package ho.artisan.holib.registry.registrar;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;

import java.util.function.Function;

public class BlockRegistrar extends Registrar<Block> {
    public BlockRegistrar(String modid) {
        super(modid, Registries.BLOCK);
    }

    public <T extends Block> RegistryObject<T> function(String id, Function<AbstractBlock.Settings, T> blockFunction, Block copy) {
        return super.register(id, blockFunction.apply(AbstractBlock.Settings.copy(copy)));
    }
}
