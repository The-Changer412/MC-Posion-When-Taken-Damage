package com.the_changer.pwtd.mixin;


import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PosionDamage {
    @Inject(at = @At("RETURN"), method = "damage")
    public boolean damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() == true) {
            PlayerEntity player = ((PlayerEntity)(Object) this);
            if (!source.isMagic()) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 300, 0), player);
            }
        }
        return cir.getReturnValue();
    }
}