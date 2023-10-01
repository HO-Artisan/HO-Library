package ho.artisan.holib.registry.registration;

import ho.artisan.holib.registry.registration.annotation.ItemObject;
import ho.artisan.holib.registry.registration.annotation.Modid;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

@Modid("holib")
public class RegistrationExample {
    @ItemObject
    public static final Item i = new Item(new Item.Settings().rarity(Rarity.EPIC));
}
