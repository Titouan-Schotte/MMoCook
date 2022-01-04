package fr.titouanschotte.cook.gui;

import fr.titouanschotte.cook.Main;
import fr.titouanschotte.cook.References;
import fr.titouanschotte.cook.network.PacketGiveServer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiCook extends GuiScreen {
    private final ResourceLocation background = new ResourceLocation(References.MODID, "textures/gui/gui_cook.png"); // 256x202

    private final int xSize = 425;
    private final int ySize = 238;
    private final Minecraft minecraft;
    private final int met;
    private int guiLeft;
    private int guiTop;
    private boolean go;
    private int speed = (int)(Math.random()*(10-16+1)+10);
    private int[] goodInterval=new int[2];
    private String metName;
    public GuiCook(Minecraft mc, int met) {
        this.minecraft = mc;this.met = met;
        switch(this.met){
            case 0: metName="truite";break;
            case 1: metName="steak";break;
            case 2: metName="omelet";break;
            case 3: metName="greensoupe";break;
            case 4: metName="confiture";break;
            case 5: metName="brochette_viande";break;
            case 6: metName="brochette_champi";break;
            case 7: metName="fishsoupe";break;
            case 8: metName="cake";break;
            case 9: metName="cookie";break;
        }
    }

    public void initGui() {

        // TODO https://forums.minecraftforge.net/topic/39374-solved-gui-not-updating-values/page/4/
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        buttonList.add(new GuiCustomButton(0, guiLeft + 165, guiTop + 185, 100, 20, "STOP !", 0, 0));
        buttonList.add(new GuiCustomButton(1, guiLeft + 396, guiTop+20, 18, 20, "X", 0, 0));
        buttonList.add(new GuiTexturedButton(2,guiLeft + 40, guiTop+55,12,49, "textures/gui/cursor.png", "textures/gui/cursor.png", "textures/gui/cursor.png",0,0));
        if(metName.equals("cake")){
            buttonList.add(new GuiTexturedButton(2,guiLeft + 196, guiTop+135,38,38, "textures/items/"+metName+"/"+metName+"_bad.png", "textures/items/"+metName+"/"+metName+"_bad.png", "textures/items/"+metName+"/"+metName+"_bad.png",0,0));
        } else if (metName.equals("omelet")){
            buttonList.add(new GuiTexturedButton(2,guiLeft + 199, guiTop+135,35,35, "textures/items/"+metName+"/"+metName+"_bad.png", "textures/items/"+metName+"/"+metName+"_bad.png", "textures/items/"+metName+"/"+metName+"_bad.png",0,0));
        } else {
            buttonList.add(new GuiTexturedButton(2,guiLeft + 191, guiTop+130,49,49, "textures/items/"+metName+"/"+metName+"_bad.png", "textures/items/"+metName+"/"+metName+"_bad.png", "textures/items/"+metName+"/"+metName+"_bad.png",0,0));
        }


    }

    public boolean doesGuiPauseGame() {
        return false;
    }
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        if(go){
            if(buttonList.get(2).x <guiLeft+30+350){
                buttonList.get(2).x += speed;
            }
            else {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString("§4Trop cuit !"));
                Main.network.sendToServer(new PacketGiveServer(this.met,0));
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
            }
        }


    }
    @ParametersAreNonnullByDefault
    public void actionPerformed(GuiButton button) {
        if(button.id == 0){
            if((goodInterval[0]+guiLeft)<=buttonList.get(2).x && buttonList.get(2).x<=(goodInterval[1]+guiLeft)){
                Main.network.sendToServer(new PacketGiveServer(this.met,1));
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString("§6Cuisson PARFAITE"));
            } else {
                if((goodInterval[0]+guiLeft)>=buttonList.get(2).x){
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString("§bPas assez cuit !"));
                } else {
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString("§4Trop cuit !"));
                }
                Main.network.sendToServer(new PacketGiveServer(this.met,0));
            }
            this.mc.displayGuiScreen(null);
            this.mc.setIngameFocus();
        } if (button.id==1){
            this.mc.displayGuiScreen(null);
            this.mc.setIngameFocus();
        }
        else {
            return;
        }
    }

    public void buttonHoveringText(GuiButton button, int mouseX, int mouseY, String[] text, int posX, int posY) {
        if (button.visible && mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height) {
            List<String> temp = Arrays.asList(text);
            drawHoveringText(temp, posX, posY);
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackgroundImage();
        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    public void drawBackgroundImage() {
        minecraft.getTextureManager().bindTexture(background);


        drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, xSize, ySize, xSize, ySize);
        if(met<4){
            minecraft.getTextureManager().bindTexture(new ResourceLocation(References.MODID, "textures/gui/level1.png"));
            goodInterval= new int[]{214, 323};
        } else if (met < 8){
            minecraft.getTextureManager().bindTexture(new ResourceLocation(References.MODID, "textures/gui/level2.png"));
            goodInterval= new int[]{321, 367};
        } else {
            minecraft.getTextureManager().bindTexture(new ResourceLocation(References.MODID, "textures/gui/level3.png"));
            goodInterval= new int[]{208, 235};
        }
        drawModalRectWithCustomSizedTexture(guiLeft+40, guiTop+80, 0, 0, 350, 18, 350, 18);
        go = true;
    }

    public void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent) {

    }

}
