package fr.titouanschotte.cook.init;

import fr.titouanschotte.cook.References;
import fr.titouanschotte.cook.constructors.ItemCustomFood;
import fr.titouanschotte.cook.constructors.ItemMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

import static fr.titouanschotte.cook.init.FirstInit.*;

@Mod.EventBusSubscriber(modid = References.MODID)
public class ItemsMod {

    public static void init() {
        Init.init();
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        RegisterItems.register(event);
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        RegisterRender.registerRenders(event);
    }

}
