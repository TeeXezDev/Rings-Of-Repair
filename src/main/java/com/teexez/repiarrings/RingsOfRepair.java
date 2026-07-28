package com.teexez.repiarrings;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RingsOfRepair implements ModInitializer {
    public static final String MOD_ID = "teexezringsrepiar";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Item RING_OF_REPAIR;

    @Override
    public void onInitialize() {
        ModConfig.setup();

        Registry.register(
                BuiltInRegistries.ITEM,
                Identifier.of(MOD_ID, "ring_of_repair"),
                RING_OF_REPAIR
        );

        TabInit.addTab();
    }

    static {
        RING_OF_REPAIR = new ItemRingRepair(new Item.Properties().stacksTo(1));
    }
}