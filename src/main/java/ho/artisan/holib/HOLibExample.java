package ho.artisan.holib;


import ho.artisan.holib.registry.RegistryCasket;
import ho.artisan.holib.registry.registrar.BlockEntityTypeRegistrar;
import ho.artisan.holib.registry.registrar.BlockRegistrar;
import ho.artisan.holib.registry.registrar.ItemGroupRegistrar;
import ho.artisan.holib.registry.registrar.ItemRegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static ho.artisan.holib.HOLibMod.MOD_ID;

public class HOLibExample {
    public static final RegistryCasket<Block> IRON_COAL_BLOCK;

    public static final RegistryCasket<BlockItem> IRON_COAL_BLOCK_ITEM;
    public static final RegistryCasket<Item> IRON_COAL;

    public static final ItemGroup MAIN;

    static {
        BlockRegistrar blockRegistrar = new BlockRegistrar(MOD_ID);
        IRON_COAL_BLOCK = blockRegistrar.function("iron_coal_block", Block::new, Blocks.IRON_BLOCK);

        BlockEntityTypeRegistrar blockEntityTypeRegistrar = new BlockEntityTypeRegistrar(MOD_ID);
        //blockEntityTypeRegistrar.register("iron_coal_block", IronCoalBlockEntity::new, IRON_COAL_BLOCK.get())

        ItemRegistrar itemRegistrar = new ItemRegistrar(MOD_ID);
        IRON_COAL_BLOCK_ITEM = itemRegistrar.blockItem("iron_coal_block", IRON_COAL_BLOCK);
        IRON_COAL = itemRegistrar.simple("iron_coal");

        ItemGroupRegistrar itemGroupRegistrar = new ItemGroupRegistrar(MOD_ID);

        MAIN = itemGroupRegistrar.register("main", itemRegistrar.list(), itemGroup -> {
            itemGroup.icon(IRON_COAL.supplier(ItemStack::new));
        }).get();
    }
}
