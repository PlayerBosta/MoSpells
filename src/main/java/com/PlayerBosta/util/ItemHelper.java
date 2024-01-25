package com.PlayerBosta.util;

import net.minecraft.world.item.Item;

public class ItemHelper {
    public static Item.Properties equipment() {
        return new Item.Properties().tab(CreativeTab.SPELL_EQUIPMENT_TAB);
    }

    public static Item.Properties equipment(int stackSize) {
        return equipment().stacksTo(stackSize);
    }

    public static Item.Properties material() {
        return new Item.Properties().tab(CreativeTab.SPELL_MATERIALS_TAB);
    }

    public static Item.Properties material(int stackSize) {
        return material().stacksTo(stackSize);
    }

    public static Item.Properties hidden() {
        return new Item.Properties();
    }

    public static Item.Properties hidden(int stackSize) {
        return hidden().stacksTo(stackSize);
    }
}
