package fr.titouanschotte.cook.constructors;

import fr.titouanschotte.cook.Main;
import net.minecraft.item.Item;

public class ItemMod extends Item {
    public ItemMod(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
