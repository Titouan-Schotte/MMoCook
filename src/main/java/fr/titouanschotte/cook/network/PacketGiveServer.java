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

public class PacketGiveServer implements IMessage {

    private int index;
    private int isGood;
    public PacketGiveServer() {
    }

    public PacketGiveServer(int index, int isGood) {
        this.index = index;
        this.isGood = isGood;
    }


    @Override
    public void fromBytes(ByteBuf buf) {

        index = buf.readInt();
        isGood = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(index);
        buf.writeInt(isGood);
    }

    public static class Handler implements IMessageHandler<PacketGiveServer, IMessage> {


        @Override
        public IMessage onMessage(PacketGiveServer message, MessageContext ctx) {
            Item item;
            Item[] clear = new Item[10];

            String isgood;
            switch(message.index){
                case 0:if(message.isGood==0){item=FirstInit.truite_bad;}else{item=FirstInit.truite_good;};clear[0]=Items.FISH;break;
                case 1:if(message.isGood==0){item=FirstInit.steak_bad;}else{item=FirstInit.steak_good;};clear[0]=Items.BEEF;break;
                case 2:if(message.isGood==0){item=FirstInit.omelet_bad;}else{item=FirstInit.omelet_good;};clear[0]=Items.EGG;clear[1]=Items.EGG;break;
                case 3:if(message.isGood==0){item=FirstInit.greensoupe_bad;}else{item=FirstInit.greensoupe_good;};clear[0]=Items.POTATO;clear[1]=Items.POTATO;clear[2]=Items.CARROT;break;
                case 4:if(message.isGood==0){item=FirstInit.confiture_bad;}else{item=FirstInit.confiture_good;};clear[0]=Items.SUGAR;clear[1]=Items.SUGAR;clear[2]=Items.CARROT;break;
                case 5:if(message.isGood==0){item=FirstInit.brochette_viande_bad;}else{item=FirstInit.brochette_viande_good;};clear[0]=Items.BEEF;clear[1]=Items.BEEF;clear[2]=Items.CARROT;break;
                case 6:if(message.isGood==0){item=FirstInit.brochette_champi_bad;}else{item=FirstInit.brochette_champi_good;};clear[0]=Items.MUSHROOM_STEW;clear[1]=Items.MUSHROOM_STEW;clear[2]=Items.MUSHROOM_STEW;clear[4]=Items.BEEF;break;
                case 7:if(message.isGood==0){item=FirstInit.fishsoupe_bad;}else{item=FirstInit.fishsoupe_good;};clear[0]=Items.FISH;clear[1]=Items.FISH;clear[2]=Items.POTATO;clear[3]=Items.POTATO;break;
                case 8:if(message.isGood==0){item=FirstInit.cake_bad;}else{item=FirstInit.cake_good;};clear[0]=Items.WHEAT;clear[1]=Items.WHEAT;clear[3]=Items.EGG;clear[4]=Items.EGG;clear[5]=Items.SUGAR;clear[6]=Items.SUGAR;break;
                case 9:if(message.isGood==0){item=FirstInit.cookie_bad;}else{item=FirstInit.cookie_good;};clear[0]=Items.SUGAR;clear[1]=Items.SUGAR;clear[3]=Items.SUGAR;clear[4]=Items.WHEAT;clear[5]=Items.EGG;clear[6]=Items.EGG;clear[7]=Items.DYE;clear[8]=Items.DYE;clear[9]=Items.DYE;break;
                default:
                    throw new IllegalStateException("Unexpected value: " + message.index);
            };

            ctx.getServerHandler().player.inventory.addItemStackToInventory(new ItemStack(item, 1));

            for(Item itemClear : clear){
                for (int i = 0; i < 35; i++){
                    if (ctx.getServerHandler().player.inventory.getStackInSlot(i).getItem().equals(itemClear)){
                        ctx.getServerHandler().player.inventory.getStackInSlot(i).setCount(ctx.getServerHandler().player.inventory.getStackInSlot(i).getCount() - 1);break;
                    }
                }
            }

            return null;
        }
    }
}