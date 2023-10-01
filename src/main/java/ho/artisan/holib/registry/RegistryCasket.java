package ho.artisan.holib.registry;

import net.minecraft.util.Identifier;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class RegistryCasket<T> implements Supplier<T> {
    private final T object;
    private final Identifier id;

    public RegistryCasket(T object, Identifier id) {
        this.object = object;
        this.id = id;
    }

    public boolean is(T object) {
        return this.object == object;
    }

    @Override
    public T get() {
        return object;
    }

    public <K> K act(Function<T, K> function) {
        return function.apply(object);
    }

    public <K> Supplier<K> supplier(Function<T, K> function) {
        return () -> function.apply(object);
    }

    public Identifier id() {
        return id;
    }

    @Override
    public String toString() {
        return object.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistryCasket<?> that)) return false;
        return Objects.equals(object, that.object) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, id);
    }
}
