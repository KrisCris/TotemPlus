package me.connlost.totemplus.util;

import net.minecraft.util.math.BlockPos;

public interface IPlayerEntity {
    public BlockPos[] getLastBlockPos();
    public void setLastBlockPos(BlockPos pos);
}
