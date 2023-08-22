package ho.artisan.holib.common.screen;

import ho.artisan.holib.common.blockentity.data.DataList;
import ho.artisan.holib.common.blockentity.data.StoreList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.NotNull;

public abstract class HScreenHandler extends ScreenHandler {
    private final StoreList storeList;
    public final DataList dataList;

    public HScreenHandler(@NotNull ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, StoreList storeList, DataList dataList) {
        super(type, syncId);
        this.storeList = storeList;
        this.dataList = dataList;

        storeList.onOpen(playerInventory.player);

        this.addProperties(dataList.delegate());

        this.addPlayerSlots(playerInventory);
        this.addSlots(storeList);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot < this.storeList.size()) {
                if (!this.insertItem(itemStack2, this.storeList.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 0, this.storeList.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.storeList.canPlayerUse(player);
    }

    public void addPlayerSlots(PlayerInventory playerInventory) {
        int m;
        int l;

        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }

        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }

    public abstract void addSlots(StoreList storeList);
}
