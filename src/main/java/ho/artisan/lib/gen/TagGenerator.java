package ho.artisan.lib.gen;

import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.List;

public class TagGenerator<T> {
    private final Registry<T> registry;

    public TagGenerator(Registry<T> registry) {
        this.registry = registry;
    }

    public TagKey<T> gen(Identifier id) {
        return TagKey.of(registry.getKey(), id);
    }

    public List<TagKey<T>> gen(Identifier... id) {
        return Arrays.stream(id).map(this::gen).toList();
    }
}
