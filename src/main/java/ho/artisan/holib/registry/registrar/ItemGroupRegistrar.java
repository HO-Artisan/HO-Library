package ho.artisan.holib.registry.registrar;

import ho.artisan.holib.registry.Registrar;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

import java.util.List;

public class ItemGroupRegistrar extends Registrar<ItemGroup> {
    public ItemGroupRegistrar(String modid) {
        super(modid, Registries.ITEM_GROUP);
    }

    public ItemGroup.Builder builder(String id, List<Item> list) {
        return FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup." + this.modid + "." + id))
                .entries((displayContext, entries) -> list.forEach(item -> entries.add(new ItemStack(item))));
    }
}
