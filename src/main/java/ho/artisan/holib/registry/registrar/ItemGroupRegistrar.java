package ho.artisan.holib.registry.registrar;

import ho.artisan.holib.registry.Registrar;
import ho.artisan.holib.registry.RegistryCasket;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

import java.util.List;
import java.util.function.Consumer;

public class ItemGroupRegistrar extends Registrar<ItemGroup> {
    public ItemGroupRegistrar(String modid) {
        super(modid, Registries.ITEM_GROUP);
    }

    public RegistryCasket<ItemGroup> register(String id, List<RegistryCasket<? extends Item>> list) {
        var builder = FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup." + this.modid + "." + id))
                .entries((displayContext, entries) -> list.forEach(item -> entries.add(item::get)));
        return super.register(id, builder.build());
    }

    public RegistryCasket<ItemGroup> register(String id, List<RegistryCasket<? extends Item>> list, Consumer<ItemGroup.Builder> consumer) {
        var builder = FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup." + this.modid + "." + id))
                .entries((displayContext, entries) -> list.forEach(item -> entries.add(item::get)));
        consumer.accept(builder);
        return super.register(id, builder.build());
    }
}
