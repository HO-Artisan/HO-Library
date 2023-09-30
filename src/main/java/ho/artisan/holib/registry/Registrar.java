package ho.artisan.holib.registry;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Registrar<T> {
    private final Registry<T> registry;
    public final String modid;

    public Registrar(String modid, Registry<T> registry) {
        this.registry = registry;
        this.modid = modid;
    }

    public Registry<T> getRegistry() {
        return this.registry;
    }

    public <K extends T> RegistryObject<K> register(String id, K object) {
        return new RegistryObject<>(Registry.register(registry, id, object), genID(id));
    }

    public Identifier genID(String path) {
        return new Identifier(modid, path);
    }
}
