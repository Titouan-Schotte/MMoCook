package fr.titouanschotte.cook.network;

import fr.titouanschotte.cook.init.FirstInit;
import fr.titouanschotte.cook.init.ItemsMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;

public class PacketGiveServer implements IMessage {

    private int index;
    private int isGood;
    private int quantity;


    public PacketGiveServer() {
    }

    public PacketGiveServer(int index, int isGood, int quantity) {
        this.index = index;
        this.isGood = isGood;
        this.quantity = quantity;
    }


    @Override
    public void fromBytes(ByteBuf buf) {

        index = buf.readInt();
        isGood = buf.readInt();
        quantity = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(index);
        buf.writeInt(isGood);
        buf.writeInt(quantity);
    }

    public static class Handler implements IMessageHandler<PacketGiveServer, IMessage> {


        @Override
        public IMessage onMessage(PacketGiveServer message, MessageContext ctx) {
            Item item = null;
            ArrayList clear = new ArrayList();
            String isgood;
            for(int i = 0; i< message.quantity;i++){
                switch(message.index){
                    case 0:if(message.isGood==0){item=FirstInit.truite_bad;}else{item=FirstInit.truite_good;};clear.add(Items.FISH);break;
                    case 1:if(message.isGood==0){item=FirstInit.steak_bad;}else{item=FirstInit.steak_good;};clear.add(Items.BEEF);break;
                    case 2:if(message.isGood==0){item=FirstInit.omelet_bad;}else{item=FirstInit.omelet_good;};clear.add(Items.EGG);clear.add(Items.EGG);break;
                    case 3:if(message.isGood==0){item=FirstInit.greensoupe_bad;}else{item=FirstInit.greensoupe_good;};clear.add(Items.POTATO);clear.add(Items.POTATO);clear.add(Items.CARROT);break;
                    case 4:if(message.isGood==0){item=FirstInit.confiture_bad;}else{item=FirstInit.confiture_good;};clear.add(Items.SUGAR);clear.add(Items.SUGAR);clear.add(Items.CARROT);break;
                    case 5:if(message.isGood==0){item=FirstInit.brochette_viande_bad;}else{item=FirstInit.brochette_viande_good;};clear.add(Items.BEEF);clear.add(Items.BEEF);clear.add(Items.CARROT);break;
                    case 6:if(message.isGood==0){item=FirstInit.brochette_champi_bad;}else{item=FirstInit.brochette_champi_good;};clear.add(Items.MUSHROOM_STEW);clear.add(Items.MUSHROOM_STEW);clear.add(Items.MUSHROOM_STEW);clear.add(Items.BEEF);break;
                    case 7:if(message.isGood==0){item=FirstInit.fishsoupe_bad;}else{item=FirstInit.fishsoupe_good;};clear.add(Items.FISH);clear.add(Items.FISH);clear.add(Items.POTATO);clear.add(Items.POTATO);break;
                    case 8:if(message.isGood==0){item=FirstInit.cake_bad;}else{item=FirstInit.cake_good;};clear.add(Items.WHEAT);clear.add(Items.WHEAT);clear.add(Items.EGG);clear.add(Items.EGG);clear.add(Items.SUGAR);clear.add(Items.SUGAR);break;
                    case 9:if(message.isGood==0){item=FirstInit.cookie_bad;}else{item=FirstInit.cookie_good;};clear.add(Items.SUGAR);clear.add(Items.SUGAR);clear.add(Items.SUGAR);clear.add(Items.WHEAT);clear.add(Items.EGG);clear.add(Items.EGG);clear.add(Items.DYE);clear.add(Items.DYE);clear.add(Items.DYE);break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + message.index);
                };
            }

            ctx.getServerHandler().player.inventory.addItemStackToInventory(new ItemStack(item, message.quantity));

            for(int it = 0 ; it < clear.size(); it++){

                for (int i = 0; i < 35; i++){
                    if (ctx.getServerHandler().player.inventory.getStackInSlot(i).getItem().equals(clear.get(it))){
                        ctx.getServerHandler().player.inventory.getStackInSlot(i).setCount(ctx.getServerHandler().player.inventory.getStackInSlot(i).getCount() - 1);break;
                    }
                }
            }
            return null;
        }
    }
}