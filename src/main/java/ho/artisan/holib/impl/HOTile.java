package ho.artisan.holib.impl;

import ho.artisan.holib.common.blockentity.HBlockEntity;
import ho.artisan.holib.common.blockentity.data.DataList;
import ho.artisan.holib.common.blockentity.data.StoreList;
import ho.artisan.holib.common.blockentity.extra.ISaveToBlock;
import ho.artisan.holib.init.HOBlockEntityTypes;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class HOTile extends HBlockEntity implements NamedScreenHandlerFactory, ISaveToBlock {
    public final DataList data = new DataList(2);
    public final StoreList store = new StoreList(9);
    public final DataList.Value processTime = data.create(0, "ProcessTime");
    public final DataList.Value processTimeTotal = data.create(1, "ProcessTimeTotal");

    public final InventoryStorage inventoryWrapper = InventoryStorage.of(store, null);


    public HOTile(BlockPos pos, BlockState state) {
        super(HOBlockEntityTypes.HO_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void read(NbtCompound nbt, boolean isClient) {
        data.read(nbt);
        store.read(nbt);
    }

    @Override
    public void write(NbtCompound nbt, boolean isClient) {
        data.write(nbt);
        store.write(nbt);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new HOScreenHandler(syncId, playerInventory, store, data);
    }

    @Override
    public NbtCompound saveToBlock(NbtCompound nbt) {
        store.write(nbt);
        return nbt;
    }

    @Override
    public boolean hasToSave() {
        return !store.isEmpty();
    }
}
