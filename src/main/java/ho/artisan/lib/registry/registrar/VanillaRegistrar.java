package ho.artisan.lib.registry.registrar;

import ho.artisan.lib.gen.IdentifierGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

/**
 * 实现原版注册的简易注册器
 *
 * @param <T> 实现注册的类型
 */
public class VanillaRegistrar<T> implements IRegistrar<T> {
    private final String modid;
    private final Registry<T> registry;
    public final IdentifierGenerator idGenerator;

    /**
     * @param modid 模组ID
     * @param registry 原版注册表实例
     */
    public VanillaRegistrar(String modid, @NotNull Registry<T> registry) {
        this.modid = modid;
        this.registry = registry;
        this.idGenerator = new IdentifierGenerator(modid);
    }

    @Override
    public String namespace() {
        return modid;
    }

    /**
     * 简单地使用原版注册方法来注册条目
     *
     * @param id     注册条目的ID
     * @param object 注册条目的实例
     */
    @Override
    public <K extends T> K register(String id, K object) {
        return Registry.register(registry, id(id), object);
    }

    @Override
    public Identifier id(String path) {
        return idGenerator.gen(path);
    }
}
