package fr.titouanschotte.cook.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketInventaireServer implements IMessage {

    public PacketInventaireServer() {
    }

    public PacketInventaireServer(int index, int isGood) {

    }


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PacketInventaireServer, PacketInventaireClient> {


        @Override
        public PacketInventaireClient onMessage(PacketInventaireServer message, MessageContext ctx) {
            int fishCount=0,viandeCount=0,shroomCount=0,carrotCount=0,patateCount=0,sugarCount=0,appleCount=0,cacaoCount=0,wheatCount=0,eggCount=0;
            for(int item = 0; item< ctx.getServerHandler().player.inventory.mainInventory.size(); item++){
                String itemname = String.valueOf(ctx.getServerHandler().player.inventory.mainInventory.get(item).getItem().getRegistryName());
                int count=ctx.getServerHandler().player.inventory.mainInventory.get(item).getCount();
                switch(itemname){
                    case "minecraft:fish":fishCount+=count;break;
                    case "minecraft:beef":viandeCount+=count;break;
                    case "minecraft:red_mushroom":shroomCount+=count;break;
                    case "minecraft:brown_mushroom":shroomCount+=count;break;
                    case "minecraft:carrot":carrotCount+=count;break;
                    case "minecraft:potato":patateCount+=count;break;
                    case "minecraft:sugar":sugarCount+=count;break;
                    case "minecraft:apple":appleCount+=count;break;
                    case "minecraft:dye":cacaoCount+=count;break;
                    case "minecraft:wheat":wheatCount+=count;break;
                    case "minecraft:egg":eggCount+=count;break;
                }


            }
            System.out.println(viandeCount);


            return new PacketInventaireClient(fishCount,viandeCount,shroomCount,carrotCount,patateCount,sugarCount,appleCount,cacaoCount,wheatCount,eggCount);
        }
    }
}
