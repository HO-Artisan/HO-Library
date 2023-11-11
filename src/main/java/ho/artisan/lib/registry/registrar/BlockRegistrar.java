package ho.artisan.lib.registry.registrar;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockRegistrar extends VanillaRegistrar<Block> {
    private AbstractBlock.Settings templateSettings = AbstractBlock.Settings.of(Material.AIR);
    private Function<AbstractBlock.Settings, ? extends Block> templateBlock = Block::new;

    public BlockRegistrar(String modid) {
        super(modid, Registry.BLOCK);
    }

    public <K extends Block> K func(String id, @NotNull Function<AbstractBlock.Settings, K> function) {
        return register(id, function.apply(templateSettings));
    }

    public Block factory(String id) {
        return func(id, templateBlock);
    }

    public Block factory(String id, Consumer<AbstractBlock.Settings> consumer) {
        return func(id, settings -> {
            consumer.accept(settings);
            return templateBlock.apply(settings);
        });
    }

    public List<Block> factory(String... id) {
        return Arrays.stream(id).map(this::factory).toList();
    }

    public List<Block> factory(Consumer<AbstractBlock.Settings> consumer, String... id) {
        return Arrays.stream(id).map(this::factory).toList();
    }

    @SuppressWarnings("UnusedReturnValue")
    public AbstractBlock.Settings templateSettings(AbstractBlock.Settings settings) {
        this.templateSettings = settings;
        return this.templateSettings;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Function<AbstractBlock.Settings, ? extends Block> templateBlock(Function<AbstractBlock.Settings, ? extends Block> template) {
        this.templateBlock = template;
        return this.templateBlock;
    }
}
