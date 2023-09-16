package ho.artisan.holib.tool;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
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
        boolean flag = true;
        if (item instanceof ExtraInGroup extra) {
            extra.expand(itemGroup);
            flag = extra.isSelfUsed();
        }
        if (flag)
            itemGroup.add(new ItemStack(item));
        register(Registries.ITEM, id, item);
    }

    public <T extends ItemGroup> void register(String id, T itemGroup) {
        register(Registries.ITEM_GROUP, id, itemGroup);
    }

    public <T extends Block> void register(String id, T block) {
        register(Registries.BLOCK, id, block);
    }

    public <T extends Fluid> void register(String id, T fluid) {
        register(Registries.FLUID, id, fluid);
    }

    public <C extends Recipe<?>, T extends RecipeSerializer<C>> void register(String id, T serializer) {
        register(Registries.RECIPE_SERIALIZER, id, serializer);
    }

    public <C extends Recipe<?>, T extends RecipeType<C>> void register(String id, T rType) {
        register(Registries.RECIPE_TYPE, id, rType);
    }

    public <T extends BlockEntityType<? extends BlockEntity>> void register(String id, T bType) {
        register(Registries.BLOCK_ENTITY_TYPE, id, bType);
    }

    public <T extends ScreenHandlerType<? extends ScreenHandler>> void register(String id, T sType) {
        register(Registries.SCREEN_HANDLER, id, sType);
    }

    public ItemGroup.Builder createTab(String id, Item item) {
        return FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup." + modid + "." + id))
                .entries((displayContext, entries) -> entries.addAll(itemGroup))
                .icon(() -> new ItemStack(item));
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

    public List<ItemStack> getModItemStacks() {
        return itemGroup;
    }

    public List<Item> getModItems() {
        List<Item> list = new ArrayList<>();
        itemGroup.forEach((itemStack) -> list.add(itemStack.getItem()));
        return list;
    }
}
