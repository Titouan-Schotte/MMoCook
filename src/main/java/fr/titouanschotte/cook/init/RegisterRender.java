package fr.titouanschotte.cook.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Objects;

import static fr.titouanschotte.cook.init.FirstInit.*;

public class RegisterRender {
    public static void registerRenders(ModelRegistryEvent event) {
        registerRender(icon);
        registerRender(truite_bad);registerRender(truite_good);
        registerRender(steak_bad);registerRender(steak_good);
        registerRender(omelet_bad);registerRender(omelet_good);
        registerRender(greensoupe_bad);registerRender(greensoupe_good);
        registerRender(confiture_bad);registerRender(confiture_good);
        registerRender(brochette_viande_bad);registerRender(brochette_viande_good);
        registerRender(brochette_champi_bad);registerRender(brochette_champi_good);
        registerRender(fishsoupe_bad);registerRender(fishsoupe_good);
        registerRender(cake_bad);registerRender(cake_good);
        registerRender(cookie_bad);registerRender(cookie_good);
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }
}
