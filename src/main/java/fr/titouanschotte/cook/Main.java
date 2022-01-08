package fr.titouanschotte.cook;

import fr.titouanschotte.cook.constructors.FoodTab;
import fr.titouanschotte.cook.init.BlocksMod;
import fr.titouanschotte.cook.init.ItemsMod;
import fr.titouanschotte.cook.network.PacketGiveServer;
import fr.titouanschotte.cook.network.PacketInventaireClient;
import fr.titouanschotte.cook.network.PacketInventaireServer;
import fr.titouanschotte.cook.network.PacketLabelServer;
import fr.titouanschotte.cook.proxy.ServerProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION)
public class Main
{
    @Mod.Instance
    public static Main instance;
    @SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.SERVER_PROXY, modId = References.MODID)
    public static ServerProxy proxy;
    public static SimpleNetworkWrapper network;
    public static final CreativeTabs creativeTab = new FoodTab("mmocook");
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel("ChannelCook");
        network.registerMessage(PacketGiveServer.Handler.class, PacketGiveServer.class, 1, Side.SERVER);
        network.registerMessage(PacketInventaireServer.Handler.class, PacketInventaireServer.class, 2, Side.SERVER);
        network.registerMessage(PacketInventaireClient.Handler.class, PacketInventaireClient.class, 3, Side.CLIENT);
        network.registerMessage(PacketLabelServer.Handler.class, PacketLabelServer.class, 4, Side.SERVER);
        ItemsMod.init();
        BlocksMod.init();
        System.out.println("MMo COOK : INITIALIZED");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.register();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
    }
}
