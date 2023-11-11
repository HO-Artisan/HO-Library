package ho.artisan.lib.registry;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Casket<T> implements Supplier<T> {
    private final T object;
    private final Identifier identifier;

    public Casket(@NotNull T object, @NotNull Identifier id) {
        this.object = object;
        this.identifier = id;
    }

    @Override
    public T get() {
        return object;
    }

    public <F> F func(Function<T, F> function) {
        return function.apply(object);
    }

    public Identifier id() {
        return identifier;
    }

    public String sid() {
        return identifier.getPath();
    }

    public String namespace() {
        return identifier.getNamespace();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casket<?> casket = (Casket<?>) o;
        return Objects.equals(object, casket.object) && Objects.equals(identifier, casket.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, identifier);
    }
}
