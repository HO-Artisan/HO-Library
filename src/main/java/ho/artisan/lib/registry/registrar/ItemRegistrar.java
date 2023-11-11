package ho.artisan.lib.registry.registrar;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 实现物品注册的注册器
 */
public class ItemRegistrar extends VanillaRegistrar<Item> {
    private Item.Settings templateSettings = new Item.Settings();
    private Function<Item.Settings, ? extends Item> templateItem = Item::new;

    public ItemRegistrar(String modid) {
        super(modid, Registry.ITEM);
    }

    /**
     * 简化注册简单物品
     */
    public Item factory(String id) {
        return func(id, templateItem);
    }

    public Item factory(String id, Consumer<Item.Settings> consumer) {
        return func(id, settings -> {
            consumer.accept(settings);
            return new Item(settings);
        });
    }

    public List<Item> factory(String... id) {
        return Arrays.stream(id).map(this::factory).toList();
    }

    public List<Item> factory(Consumer<Item.Settings> consumer, String... id) {
        return Arrays.stream(id).map(this::factory).toList();
    }

    /**
     * 简化注册方块物品
     */
    public BlockItem blockItem(String id, Supplier<Block> block) {
        return func(id, settings -> new BlockItem(block.get(), settings));
    }

    /**
     * 通过函数式接口来实现简化注册
     */
    public <K extends Item> K func(String id, @NotNull Function<Item.Settings, K> function) {
        return register(id, function.apply(templateSettings));
    }

    /**
     * 切换 {@code func()} 所使用的 {@code Item.Setting}
     * @see ho.artisan.lib.registry.registrar.ItemRegistrar#func(String, Function)
     */
    @SuppressWarnings("UnusedReturnValue")
    public Item.Settings templateSettings(Item.Settings settings) {
        this.templateSettings = settings;
        return this.templateSettings;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Function<Item.Settings, ? extends Item> templateItem(Function<Item.Settings, ? extends Item> template) {
        this.templateItem = template;
        return this.templateItem;
    }

    public void clearTemplate() {
        templateSettings = new Item.Settings();
        templateItem = Item::new;
    }
}
