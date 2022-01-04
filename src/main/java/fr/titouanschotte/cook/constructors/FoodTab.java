package fr.titouanschotte.cook.constructors;

import fr.titouanschotte.cook.init.FirstInit;
import fr.titouanschotte.cook.init.ItemsMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import static fr.titouanschotte.cook.init.ItemsMod.*;

public class FoodTab extends CreativeTabs {

    public FoodTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(FirstInit.icon);
    }

}