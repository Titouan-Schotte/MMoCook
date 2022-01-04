package fr.titouanschotte.cook.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

import static fr.titouanschotte.cook.init.FirstInit.*;

public class RegisterItems {
    public static void register(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(icon,
                truite_bad, truite_good,
                steak_bad,steak_good,
                omelet_bad,omelet_good,
                greensoupe_bad,greensoupe_good,
                confiture_bad, confiture_good,
                brochette_viande_bad, brochette_viande_good,
                brochette_champi_bad,brochette_champi_good,
                fishsoupe_bad, fishsoupe_good,
                cake_bad, cake_good,
                cookie_bad, cookie_good
                );
    }
}
