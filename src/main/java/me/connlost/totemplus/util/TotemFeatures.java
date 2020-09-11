package me.connlost.totemplus.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class TotemFeatures {
    public static void reviveFromVoid(Entity player) {
        player.setVelocity(0, 0, 0);
        player.refreshPositionAfterTeleport(findSavePos(player));
        if (player instanceof LivingEntity) {
            ((LivingEntity)player).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 1));
            ((LivingEntity) player).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 1));
        }
    }

    public static Vec3d findSavePos(Entity player) {
        if (player instanceof PlayerEntity) {
            BlockPos[] blockPoses = ((IPlayerEntity) player).getLastBlockPos();
            BlockState blockState0 = player.world.getBlockState(blockPoses[0].down());
            BlockState blockState1 = player.world.getBlockState(blockPoses[1].down());
            Vec3d lastSafePos;

            if (blockState0.isAir()) {
                if (blockState1.isAir()){
                    System.out.println("in");
                    BlockPos pos0= blockPoses[0];
                    pos0 = pos0.up(4);
                    for (int i = 8; i > 0; i--) {
                        BlockState state = player.world.getBlockState(pos0.up(1));
                        if (state.isAir()) {
                            break;
                        }
                        pos0 = pos0.down();
                    }
                }
                lastSafePos = new Vec3d(blockPoses[1].getX(),blockPoses[1].getY(),blockPoses[1].getZ());

            } else {
                lastSafePos = new Vec3d(blockPoses[0].getX(),blockPoses[0].getY(),blockPoses[0].getZ());
            }

            return lastSafePos;
        }
        return new Vec3d(0, 70, 0);
    }
}
