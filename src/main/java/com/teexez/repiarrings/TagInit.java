package com.teexez.repiarrings;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagInit {
    public static final TagKey<Item> RING_REPAIR_BLACKLIST = TagKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath(RingsOfRepair.MOD_ID, "ring_repair_blacklist")
    );
}