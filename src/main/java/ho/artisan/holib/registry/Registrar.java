package ho.artisan.holib.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Registrar {
    private final String modid;
    private final List<ItemStack> itemGroup;

    public Registrar(String modid) {
        this.modid = modid.toLowerCase();
        this.itemGroup = new ArrayList<>();
    }

    public <T extends Item> void register(String id, T item) {
        itemGroup.add(new ItemStack(item));
        register(Registries.ITEM, id, item);
    }

    public <T extends Block> void register(String id, T block) {
        register(Registries.BLOCK, id, block);
    }

    public <T extends BlockEntityType<? extends BlockEntity>> void register(String id, T type) {
        register(Registries.BLOCK_ENTITY_TYPE, id, type);
    }

    public <T extends ScreenHandlerType<? extends ScreenHandler>> void register(String id, T type) {
        register(Registries.SCREEN_HANDLER, id, type);
    }

    public ItemGroup createTab(String id, Item item) {
        return register(Registries.ITEM_GROUP, id, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup." + modid + "." + id))
                .entries((displayContext, entries) -> entries.addAll(itemGroup))
                .icon(() -> new ItemStack(item))
                .build());
    }

    public <T> T register(Registry<? super T> registry, String id, T object) {
        return Registry.register(registry, createID(id), object);
    }

    public Identifier createID(String path) {
        return new Identifier(modid, path);
    }

    public String getModid() {
        return modid;
    }
}
