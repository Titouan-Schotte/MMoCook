package fr.titouanschotte.cook.init;

import fr.titouanschotte.cook.constructors.ItemCustomFood;
import fr.titouanschotte.cook.constructors.ItemMod;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import static fr.titouanschotte.cook.init.FirstInit.*;
import static net.minecraft.init.MobEffects.*;

public class Init {

    public static void init(){
        icon = new ItemMod("icon");

        //Truite
        truite_bad = new ItemCustomFood("truite_bad", 2, 1.F, false,false,null);
        truite_good = new ItemCustomFood("truite_good", 4, 2.F, false,false,null);

        //Steak
        steak_bad = new ItemCustomFood("steak_bad", 3, 5.5F, false,false,null);
        steak_good = new ItemCustomFood("steak_good", 6, 11.F, false,false,null);

        //Omelette
        omelet_bad = new ItemCustomFood("omelet_bad", 3, 5.5F, false,false, null);
        omelet_good = new ItemCustomFood("omelet_good", 6, 11.F, false,false, null);

        //Green Soupe
        greensoupe_bad = new ItemCustomFood("greensoupe_bad", 3, 5.5F, false,false, null);
        greensoupe_good = new ItemCustomFood("greensoupe_good", 3, 5.5F, false,false, null);

        //Confiture
        confiture_bad = new ItemCustomFood("confiture_bad", 3, 5.5F, false, false, null);
        confiture_good = new ItemCustomFood("confiture_good", 3, 5.5F, false, true, new PotionEffect(SPEED, 200, 1));

        //Brochette Viande
        brochette_viande_bad = new ItemCustomFood("brochette_viande_bad", 3, 5.5F, false,false,null);
        brochette_viande_good = new ItemCustomFood("brochette_viande_good", 3, 5.5F, false,true, new PotionEffect(STRENGTH, 400, 1));

        //Brochette Champignons
        brochette_champi_bad=new ItemCustomFood("brochette_champi_bad", 5, 7.5F, false,false,null);
        brochette_champi_good=new ItemCustomFood("brochette_champi_good", 10, 15.F, false,true, new PotionEffect(JUMP_BOOST, 500, 1));

        //Viande Soupe
        fishsoupe_bad=new ItemCustomFood("fishsoupe_bad", 5, 7.5F, false,false,null);
        fishsoupe_good=new ItemCustomFood("fishsoupe_good", 5, 7.5F, false,true, new PotionEffect(WATER_BREATHING, 500, 1));

        //Cake
        cake_bad=new ItemCustomFood("cake_bad", 5, 7.5F, false,false,null);
        cake_good=new ItemCustomFood("cake_good", 5, 7.5F, false,true, new PotionEffect(SATURATION, 800, 1));

        //Cookies
        cookie_bad=new ItemCustomFood("cookie_bad", 5, 7.5F, false,false,null);
        cookie_good=new ItemCustomFood("cookie_good", 5, 7.5F, false,true, new PotionEffect(ABSORPTION, 400, 1));


    }
}
