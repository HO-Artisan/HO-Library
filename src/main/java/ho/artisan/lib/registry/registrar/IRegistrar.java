package ho.artisan.lib.registry.registrar;

import ho.artisan.lib.registry.Casket;
import net.minecraft.util.Identifier;


/**
 * 实现注册器的规范接口
 *
 * @param <T> 实现注册的类型
 */
public interface IRegistrar<T> {

    /**
     * 注册条目的共同命名空间
     */
    String namespace();

    /**
     * 简化生成ID
     */
    Identifier id(String id);

    /**
     * 注册条目
     *
     * @param id 注册条目的ID
     * @param object 注册条目的实例
     */
    @SuppressWarnings("UnusedReturnValue")
    <K extends T> K register(String id, K object);


    /**
     * 注册条目并返回封装
     *
     * @param id 注册条目的ID
     * @param object 注册条目的实例
     */
    @SuppressWarnings("UnusedReturnValue")
    default <K extends T> Casket<K> casket(String id, K object) {
        return new Casket<>(register(id, object), id(id));
    }



}