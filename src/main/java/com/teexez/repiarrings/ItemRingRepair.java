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
        int interval = ModConfig.getConfig().ringRepairInterval;

        if (serverPlayer.tickCount % interval != 0) return;

        int containerSize = serverPlayer.getInventory().getContainerSize();
        RingsOfRepair.LOGGER.info("repairItems: player={}, containerSize={}, tickCount={}, interval={}",
                player.getName().getString(), containerSize, serverPlayer.tickCount, interval);

        boolean repaired = false;
        for (int i = 0; i < containerSize; i++) {
            ItemStack stack2 = serverPlayer.getInventory().getItem(i);

            if (stack2.is(TagInit.RING_REPAIR_BLACKLIST)) continue;
            if (!stack2.is(DURABILITY_TAG)) continue;
            if (stack2.isEmpty()) continue;
            if (stack2 == serverPlayer.getMainHandItem()) continue;
            if (!stack2.isDamaged()) continue;

            RingsOfRepair.LOGGER.info("repairItems: repairing slot {} item {} damage {} -> {}",
                    i, stack2.getItem(), stack2.getDamageValue(), stack2.getDamageValue() - 1);
            stack2.setDamageValue(stack2.getDamageValue() - 1);
            repaired = true;
            break;
        }

        if (!repaired) {
            RingsOfRepair.LOGGER.info("repairItems: no damaged item found in {} slots", containerSize);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipAdder, TooltipFlag type) {
        tooltipAdder.accept(Component.translatable("item." + RingsOfRepair.MOD_ID + ".ring_of_repair.tip1").withStyle(ChatFormatting.DARK_GRAY));
        tooltipAdder.accept(Component.translatable("item." + RingsOfRepair.MOD_ID + ".ring_of_repair.tip2").withStyle(ChatFormatting.DARK_GREEN));
    }
}