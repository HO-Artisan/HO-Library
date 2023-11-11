package ho.artisan.lib.common.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;


/**
 * 使用FabricAPI注册燃烧时间的燃料类物品
 */
public class FuelItem extends Item {
    public FuelItem(Settings settings, int burnTime) {
        super(settings);
        FuelRegistry.INSTANCE.add(this, burnTime);
    }

    public int getBurnTime() {
        return FuelRegistry.INSTANCE.get(this);
    }
}
