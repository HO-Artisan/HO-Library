package ho.artisan.holib.common.blockentity.extra;

import net.minecraft.nbt.NbtCompound;

public interface ISaveToBlock {
    NbtCompound saveToBlock(NbtCompound nbt);

    boolean hasToSave();
}
