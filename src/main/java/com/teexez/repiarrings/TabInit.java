package com.teexez.repiarrings;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TabInit {
    public static final ResourceKey<CreativeModeTab> RING_REPAIR_GROUP = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.of(RingsOfRepair.MOD_ID, "ring_repair_group")
    );

    public static void addTab() {
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                RING_REPAIR_GROUP,
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(RingsOfRepair.RING_OF_REPAIR))
                        .title(Component.translatable("itemGroup." + RingsOfRepair.MOD_ID + ".ring_repair_group"))
                        .displayItems((context, entries) -> {
                            entries.accept(RingsOfRepair.RING_OF_REPAIR);
                        })
                        .build()
        );
    }
}