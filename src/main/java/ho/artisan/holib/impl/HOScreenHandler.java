package ho.artisan.holib.impl;

import ho.artisan.holib.common.blockentity.data.DataList;
import ho.artisan.holib.common.blockentity.data.StoreList;
import ho.artisan.holib.common.screen.HScreenHandler;
import ho.artisan.holib.init.HOScreenHandlerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public class HOScreenHandler extends HScreenHandler {

    public HOScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(HOScreenHandlerTypes.HO_SCREEN_HANDLER, syncId, playerInventory, new StoreList(9), new DataList(2));
    }

    public HOScreenHandler(int syncId, PlayerInventory playerInventory, @Nullable StoreList storeList, DataList dataList) {
        super(HOScreenHandlerTypes.HO_SCREEN_HANDLER, syncId, playerInventory, storeList, dataList);
    }

    @Override
    public void addSlots(StoreList storeList) {
        int m;
        int l;

        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 3; ++l) {
                this.addSlot(new Slot(storeList, l + m * 3, 62 + l * 18, 17 + m * 18));
            }
        }
    }
}
