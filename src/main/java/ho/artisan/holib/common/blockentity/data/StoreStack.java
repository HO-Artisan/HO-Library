package ho.artisan.holib.common.blockentity.data;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.Stack;
import java.util.stream.IntStream;

public class StoreStack implements SidedInventory {
    private final Stack<ItemStack> collection;

    public StoreStack() {
        this.collection = new Stack<>();
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return IntStream.range(0, collection.size() - 1).toArray();
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return collection.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(collection, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(collection, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (!stack.isEmpty() && slot < collection.size())
            collection.set(slot, stack);
    }

    public ItemStack popStack() {
        return collection.pop();
    }

    public void pushStack(ItemStack stack) {
        collection.push(stack);
    }

    public ItemStack peekStack() {
        return collection.peek();
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        collection.clear();
    }

    public void write(NbtCompound nbt) {
        NbtList nbtList = new NbtList();

        for(int i = 0; i < collection.size(); ++i) {
            ItemStack itemStack = collection.get(i);
            if (!itemStack.isEmpty()) {
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putByte("Slot", (byte)i);
                itemStack.writeNbt(nbtCompound);
                nbtList.add(nbtCompound);
            }
        }

        nbt.put("Items", nbtList);
    }

    public void read(NbtCompound nbt) {
        NbtList nbtList = nbt.getList("Items", 10);

        for(int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            int j = nbtCompound.getByte("Slot") & 255;
            if (j < collection.size()) {
                collection.set(j, ItemStack.fromNbt(nbtCompound));
            }
        }
    }
}
