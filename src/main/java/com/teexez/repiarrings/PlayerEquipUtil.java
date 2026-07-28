package com.teexez.repiarrings;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;

public final class PlayerEquipUtil {
    public static boolean hasItemInOffHand(Player player, Item item) {
        ItemStack offHand = player.getOffhandItem();
        return offHand.getItem() == item;
    }

    public static boolean hasItemInMainHand(Player player, Item item) {
        ItemStack mainHand = player.getMainHandItem();
        return mainHand.getItem() == item;
    }

    public static boolean hasItemInInventory(Player player, Item item) {
        Inventory inv = player.getInventory();
        int size = inv.getContainerSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack stack = inv.getItem(slot);
            if (stack.getItem() == item) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getItemInInventory(Player player, Item item) {
        Inventory inv = player.getInventory();
        int size = inv.getContainerSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack stack = inv.getItem(slot);
            if (stack.getItem() == item) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static boolean hasItemInEnderchest(Player player, Item item) {
        if (!(player instanceof ServerPlayer serverPlayer)) return false;
        Container inv = serverPlayer.getEnderChestInventory();
        int size = inv.getContainerSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack stack = inv.getItem(slot);
            if (stack.getItem() == item) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getItemStackInEnderchest(Player player, Item item) {
        if (!(player instanceof ServerPlayer serverPlayer)) return ItemStack.EMPTY;
        Container inv = serverPlayer.getEnderChestInventory();
        int size = inv.getContainerSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack stack = inv.getItem(slot);
            if (stack.getItem() == item) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}