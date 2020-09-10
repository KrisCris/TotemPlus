package me.connlost.totemplus.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.Vec3d;

public class TotemFeatures {
    public static void reviveFromVoid(Entity player){
        player.setVelocity(0,0,0);
        player.refreshPositionAfterTeleport(findSavePos(player));
        if (player instanceof LivingEntity){
            ((LivingEntity)player).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200, 10));
            ((LivingEntity)player).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 10));
        }
    }

    public static Vec3d findSavePos(Entity player){
        Vec3d playerPos = player.getPos();
        //TODO find better pos
        return new Vec3d(playerPos.x, 0, playerPos.z);
    }
}
