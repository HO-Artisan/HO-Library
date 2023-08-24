package ho.artisan.holib.common.block;

import ho.artisan.holib.common.blockentity.extra.ISaveToBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Optional;

public abstract class HBlockWithEntity<T extends BlockEntity> extends BlockWithEntity {
    public HBlockWithEntity(Settings settings) {
        super(settings);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ISaveToBlock iSave) {
            if (!world.isClient && iSave.hasToSave()) {
                ItemStack drop = new ItemStack(state.getBlock());
                BlockItem.setBlockEntityNbt(drop, blockEntity.getType(), iSave.saveToBlock(new NbtCompound()));

                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, drop);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        ItemStack drop = super.getPickStack(world, pos, state);
        Optional<T> optional = world.getBlockEntity(pos, getBlockEntityType());
        if (optional.isPresent() && optional.get() instanceof ISaveToBlock iSave)
           BlockItem.setBlockEntityNbt(drop, optional.get().getType(), iSave.saveToBlock(new NbtCompound()));
        return drop;
    }

    public abstract BlockEntityType<T> getBlockEntityType();

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
