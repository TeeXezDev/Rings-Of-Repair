package com.teexez.repiarrings;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import java.util.function.Consumer;

public class ItemRingRepair extends Item {
    private static final TagKey<Item> DURABILITY_TAG = TagKey.create(
            Registries.ITEM, Identifier.fromNamespaceAndPath("minecraft", "enchantable/durability"));

    public ItemRingRepair(Properties settings) {
        super(settings);
    }

    public static void repairItems(Level world, Player player) {
        if (world.isClientSide()) return;

        ServerPlayer serverPlayer = (ServerPlayer) player;

        if (serverPlayer.tickCount % ModConfig.getConfig().ringRepairInterval != 0) return;

        for (int i = 0; i < serverPlayer.getInventory().getContainerSize(); i++) {
            ItemStack stack2 = serverPlayer.getInventory().getItem(i);

            if (stack2.is(TagInit.RING_REPAIR_BLACKLIST)) continue;
            if (!stack2.is(DURABILITY_TAG)) continue;
            if (stack2.isEmpty()) continue;
            if (stack2 == serverPlayer.getMainHandItem()) continue;
            if (!stack2.isDamaged()) continue;

            stack2.setDamageValue(stack2.getDamageValue() - 1);
            break;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipAdder, TooltipFlag type) {
        tooltipAdder.accept(Component.translatable("item." + RingsOfRepair.MOD_ID + ".ring_of_repair.tip1").withStyle(ChatFormatting.DARK_GRAY));
        tooltipAdder.accept(Component.translatable("item." + RingsOfRepair.MOD_ID + ".ring_of_repair.tip2").withStyle(ChatFormatting.DARK_GREEN));
    }
}