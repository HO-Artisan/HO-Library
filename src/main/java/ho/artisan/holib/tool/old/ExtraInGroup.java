package ho.artisan.holib.tool.old;

import net.minecraft.item.ItemStack;

import java.util.List;

@Deprecated
public interface ExtraInGroup {
    void expand(List<ItemStack> itemGroup);

    default boolean isSelfUsed() {
        return true;
    }
}
