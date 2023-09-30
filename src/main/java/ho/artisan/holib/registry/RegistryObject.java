package ho.artisan.holib.registry;

import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.Supplier;

public class RegistryObject<T> implements Supplier<T> {
    private final T object;
    private final Identifier id;

    public RegistryObject(T object, Identifier id) {
        this.object = object;
        this.id = id;
    }

    @Override
    public T get() {
        return object;
    }

    public <K> K act(Function<T, K> function) {
        return function.apply(object);
    }

    public Identifier id() {
        return id;
    }
}
