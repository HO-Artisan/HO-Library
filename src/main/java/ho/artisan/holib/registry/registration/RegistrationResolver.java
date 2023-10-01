package ho.artisan.holib.registry.registration;

import ho.artisan.holib.registry.registration.annotation.BlockObject;
import ho.artisan.holib.registry.registration.annotation.Identity;
import ho.artisan.holib.registry.registration.annotation.ItemObject;
import ho.artisan.holib.registry.registration.annotation.Modid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class RegistrationResolver {
    public static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResolver.class);

    public static void process(Class<?> rClass) {
        try {
            if (rClass.isAnnotationPresent(Modid.class)) {
                String modid = rClass.getAnnotation(Modid.class).value();
                for (Field field : rClass.getFields()) {
                    register(field, ItemObject.class, Registries.ITEM, modid);
                    register(field, BlockObject.class, Registries.BLOCK, modid);
                }
            }else {
                LOGGER.error(rClass + " has no @Modid annotation.");
            }
        }catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected static <T> void register(Field field, Class<? extends Annotation> annotation, Registry<T> registry, String modid) throws IllegalAccessException {
        Identifier identity = new Identifier(modid, field.getName().toLowerCase());
        if (field.isAnnotationPresent(Identity.class)) {
            identity = new Identifier(modid, field.getAnnotation(Identity.class).value());
        }
        if (field.isAnnotationPresent(annotation)) {
            Registry.register(registry, identity, (T) field.get(null));
        }
    }
}
