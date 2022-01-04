package fr.titouanschotte.cook.network;

import fr.titouanschotte.cook.init.FirstInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketLabelServer implements IMessage {

    private int x;
    private int y;
    private int z;
    public PacketLabelServer() {
    }

    public PacketLabelServer(int x, int y, int z){
        this.x=x;
        this.y=x;
        this.z=x;
    }


    @Override
    public void fromBytes(ByteBuf buf) {

        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    public static class Handler implements IMessageHandler<PacketLabelServer, IMessage> {
        @Override
        public IMessage onMessage(PacketLabelServer message, MessageContext ctx) {
            World world = Minecraft.getMinecraft().player.world;
            world.getMinecraftServer().getCommandManager().executeCommand(world.getMinecraftServer(), "summon minecraft:armor_stand " +message.x+" " +message.y+" "+message.z+" {CustomName:\"CUISINE\",Invulnerable:1b,NoGravity:1b,Invisible:1b,CustomNameVisible:1b}");
            String command = "summon minecraft:armor_stand "+message.x+" " +message.y+" "+message.z+" {CustomName:\"CUISINE\",Invulnerable:1b,NoGravity:1b,Invisible:1b,CustomNameVisible:1b}";
//            world.getMinecraftServer().getCommandManager().executeCommand(world.getMinecraftServer(), command);
            return null;
        }
    }
}
