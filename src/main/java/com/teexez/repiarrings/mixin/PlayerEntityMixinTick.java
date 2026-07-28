package com.teexez.repiarrings.mixin;

import com.teexez.repiarrings.ItemRingRepair;
import com.teexez.repiarrings.PlayerEquipUtil;
import com.teexez.repiarrings.RingsOfRepair;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixinTick extends LivingEntity {
    protected PlayerEntityMixinTick(EntityType<? extends LivingEntity> type, Level world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void repairTick(CallbackInfo ci) {
        if (!(this.level() instanceof ServerLevel)) return;
        Player player = (Player) (Object) this;

        if (PlayerEquipUtil.hasItemInInventory(player, RingsOfRepair.RING_OF_REPAIR) ||
                PlayerEquipUtil.hasItemInEnderchest(player, RingsOfRepair.RING_OF_REPAIR)) {
            ItemRingRepair.repairItems(this.level(), player);
        }
    }
}