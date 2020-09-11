package me.connlost.totemplus.mixin;

import me.connlost.totemplus.util.IPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends Entity implements IPlayerEntity {

    public MixinPlayerEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    private BlockPos[] lastOnGroundPos = new BlockPos[2];


    @Override
    public BlockPos[] getLastBlockPos(){
        if (lastOnGroundPos[0] == null)
            lastOnGroundPos[0] = this.getBlockPos();
        if (lastOnGroundPos[1] == null)
            lastOnGroundPos[1] = lastOnGroundPos[0];
        return lastOnGroundPos;
    }

    @Override
    public void setLastBlockPos(BlockPos pos) {
        if (!pos.equals(lastOnGroundPos[0])){
            lastOnGroundPos[1] = lastOnGroundPos[0];
            lastOnGroundPos[0] = pos;
        }
    }
}
