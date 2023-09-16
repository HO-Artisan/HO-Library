package ho.artisan.holib.tool;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface ExtraInGroup {
    void expand(List<ItemStack> itemGroup);

    default boolean isSelfUsed() {
        return true;
    }
}
