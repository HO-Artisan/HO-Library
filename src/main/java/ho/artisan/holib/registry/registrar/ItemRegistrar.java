package ho.artisan.holib.registry.registrar;

import ho.artisan.holib.registry.Registrar;
import ho.artisan.holib.registry.RegistryObject;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemRegistrar extends Registrar<Item> {
    public final List<RegistryObject<? extends Item>> list = new ArrayList<>();

    public ItemRegistrar(String modid) {
        super(modid, Registries.ITEM);
    }

    @Override
    public <K extends Item> RegistryObject<K> register(String id, K object) {
        var item = super.register(id, object);
        list.add(item);
        return item;
    }

    public <T extends Item> RegistryObject<T> function(String id, Function<Item.Settings, T> itemFunction) {
        return register(id, itemFunction.apply(new Item.Settings()));
    }

    public RegistryObject<Item> simple(String id) {
        return function(id, Item::new);
    }

    public RegistryObject<BlockItem> blockItem(String id, Supplier<Block> block, Item.Settings settings) {
        return register(id, new BlockItem(block.get(), settings));
    }

    public RegistryObject<BlockItem> blockItem(String id, Supplier<Block> block) {
        return function(id, (settings -> new BlockItem(block.get(), settings)));
    }

}
