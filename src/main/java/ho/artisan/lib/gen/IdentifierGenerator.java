package ho.artisan.lib.gen;


import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class IdentifierGenerator {
    private final String namespace;

    public IdentifierGenerator(String namespace) {
        this.namespace = namespace;
    }

    public Identifier gen(String path) {
        return new Identifier(namespace, path);
    }

    public List<Identifier> gen(String... paths) {
        return Arrays.stream(paths).map(this::gen).toList();
    }
}
