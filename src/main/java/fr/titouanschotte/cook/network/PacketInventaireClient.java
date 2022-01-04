package fr.titouanschotte.cook.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import static fr.titouanschotte.cook.gui.GuiMainMenu.*;
public class PacketInventaireClient implements IMessage {

    private int PacketfishCount;
    private int PacketviandeCount;
    private int PacketshroomCount;
    private int PacketcarrotCount;
    private int PacketpatateCount;
    private int PacketsugarCount;
    private int PacketappleCount;
    private int PacketcacaoCount;
    private int PacketwheatCount;
    private int PacketeggCount;

    public PacketInventaireClient() {
    }

    public PacketInventaireClient(int fishCount, int viandeCount, int shroomCount, int carrotCount, int patateCount, int sugarCount, int appleCount, int cacaoCount, int wheatCount, int eggCount) {
        this.PacketfishCount=fishCount;
        this.PacketviandeCount=viandeCount;
        this.PacketshroomCount=shroomCount;
        this.PacketcarrotCount=carrotCount;
        this.PacketpatateCount=patateCount;
        this.PacketsugarCount=sugarCount;
        this.PacketappleCount=appleCount;
        this.PacketcacaoCount=cacaoCount;
        this.PacketwheatCount=wheatCount;
        this.PacketeggCount=eggCount;
    }



    @Override
    public void fromBytes(ByteBuf buf) {
        this.PacketfishCount=buf.readInt();
        this.PacketviandeCount=buf.readInt();
        this.PacketshroomCount=buf.readInt();
        this.PacketcarrotCount=buf.readInt();
        this.PacketpatateCount=buf.readInt();
        this.PacketsugarCount=buf.readInt();
        this.PacketappleCount=buf.readInt();
        this.PacketcacaoCount=buf.readInt();
        this.PacketwheatCount=buf.readInt();
        this.PacketeggCount=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(PacketfishCount);
        buf.writeInt(PacketviandeCount);
        buf.writeInt(PacketshroomCount);
        buf.writeInt(PacketcarrotCount);
        buf.writeInt(PacketpatateCount);
        buf.writeInt(PacketsugarCount);
        buf.writeInt(PacketappleCount);
        buf.writeInt(PacketcacaoCount);
        buf.writeInt(PacketwheatCount);
        buf.writeInt(PacketeggCount);
    }

    public static class Handler implements IMessageHandler<PacketInventaireClient, IMessage> {


        @Override
        public IMessage onMessage(PacketInventaireClient message, MessageContext ctx) {
            fishCount=message.PacketfishCount;
            viandeCount=message.PacketviandeCount;
            shroomCount=message.PacketshroomCount;
            carrotCount=message.PacketcarrotCount;
            patateCount=message.PacketpatateCount;
            sugarCount=message.PacketsugarCount;
            appleCount=message.PacketappleCount;
            cacaoCount=message.PacketcacaoCount;
            wheatCount=message.PacketwheatCount;
            eggCount=message.PacketeggCount;
            return null;
        }
    }
}