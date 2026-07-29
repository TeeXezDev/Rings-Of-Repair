package com.teexez.repiarrings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RingsOfRepair implements ModInitializer {
    public static final String MOD_ID = "teexezringsrepiar";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Identifier RING_OF_REPAIR_ID = Identifier.fromNamespaceAndPath(MOD_ID, "ring_of_repair");
    public static final ResourceKey<Item> RING_OF_REPAIR_KEY = ResourceKey.create(Registries.ITEM, RING_OF_REPAIR_ID);
    public static Item RING_OF_REPAIR;

    @Override
    public void onInitialize() {
        ModConfig.setup();

        RING_OF_REPAIR = new ItemRingRepair(new Item.Properties()
                .stacksTo(1)
                .setId(RING_OF_REPAIR_KEY)
                .component(DataComponents.ITEM_MODEL, Identifier.fromNamespaceAndPath(MOD_ID, "item/ring_of_repair")));

        Registry.register(
                BuiltInRegistries.ITEM,
                RING_OF_REPAIR_KEY,
                RING_OF_REPAIR
        );

        TabInit.addTab();

        ServerTickEvents.START_WORLD_TICK.register(world -> {
            for (Player player : world.players()) {
                if (PlayerEquipUtil.hasItemInInventory(player, RING_OF_REPAIR) ||
                        PlayerEquipUtil.hasItemInEnderchest(player, RING_OF_REPAIR)) {
                    ItemRingRepair.repairItems(world, player);
                }
            }
        });
    }
}