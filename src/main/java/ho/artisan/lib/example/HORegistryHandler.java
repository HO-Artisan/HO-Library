package ho.artisan.lib.example;

import ho.artisan.lib.common.item.FuelItem;
import ho.artisan.lib.registry.registrar.BlockRegistrar;
import ho.artisan.lib.registry.registrar.ItemRegistrar;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

public class HORegistryHandler {
    public static final Block IRON_COAL_BLOCK;

    public static final BlockItem IRON_COAL_BLOCK_ITEM;

    public static final FuelItem IRON_COAL;
    public static final FuelItem GOLD_COAL;
    public static final FuelItem DIAMOND_COAL;

    public static final Item COPPER_SWORD;
    public static final Item TIN_SWORD;
    public static final Item LEAD_SWORD;

    public static void load() {}

    static {
        BlockRegistrar registrar = new BlockRegistrar("ho");

        registrar.templateSettings(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK));
        IRON_COAL_BLOCK = registrar.factory("iron_coal_block");
    }

    static {
        ItemRegistrar registrar = new ItemRegistrar("ho");

        IRON_COAL = registrar.register("iron_coal", new FuelItem(new Item.Settings().maxCount(16), 400));

        IRON_COAL_BLOCK_ITEM = registrar.blockItem("iron_coal_block", () -> IRON_COAL_BLOCK);

        registrar.templateSettings(new Item.Settings().maxCount(16));
        GOLD_COAL = registrar.func("gold_coal",
                settings -> new FuelItem(settings, 800));
        DIAMOND_COAL = registrar.func("diamond_coal",
                settings -> new FuelItem(settings.rarity(Rarity.EPIC), 1600));
        registrar.clearTemplate();

        registrar.templateSettings(new Item.Settings().rarity(Rarity.UNCOMMON));
        registrar.templateItem(settings -> new SwordItem(ToolMaterials.WOOD, 0, 0, settings));
        COPPER_SWORD = registrar.factory("copper_sword", settings -> settings.maxDamageIfAbsent(200));
        TIN_SWORD = registrar.factory("tin_sword", settings -> settings.maxDamageIfAbsent(150));
        LEAD_SWORD = registrar.factory("lead_sword", settings -> settings.maxDamageIfAbsent(100));
        registrar.clearTemplate();

        registrar.factory("copper_dust", "tin_dust", "lead_dust");
    }
}
