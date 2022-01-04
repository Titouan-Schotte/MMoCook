package fr.titouanschotte.cook.constructors;
import fr.titouanschotte.cook.Main;
import fr.titouanschotte.cook.References;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemCustomFood extends ItemFood {

    public ItemCustomFood(String name, int amount, float saturation, boolean isWolfFood, boolean isSpecialFood, PotionEffect potionEffect) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.creativeTab);
        if(isSpecialFood){
            setPotionEffect(potionEffect, 1F);
        }
    }
}