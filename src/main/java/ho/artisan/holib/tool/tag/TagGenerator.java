package ho.artisan.holib.tool.tag;

import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagGenerator<T> {
    private final Registry<T> registry;

    public TagGenerator(Registry<T> registry) {
        this.registry = registry;
    }

    public TagKey<T> build(Identifier id) {
        return TagKey.of(registry.getKey(), id);
    }
}
