package fr.titouanschotte.cook.gui;

import fr.titouanschotte.cook.Main;
import fr.titouanschotte.cook.References;
import fr.titouanschotte.cook.network.PacketInventaireServer;
import fr.titouanschotte.cook.proxy.ClientProxy;
import fr.titouanschotte.cook.proxy.ServerProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiMainMenu extends GuiScreen {
    private final ResourceLocation background = new ResourceLocation(References.MODID, "textures/gui/gui_base.png"); // 256x202

    private final int xSize = 425;
    private final int ySize = 238;
    private final Minecraft minecraft;
    private int guiLeft;
    private int guiTop;
    private int target;
    private int quantity;
    public static int fishCount, viandeCount, shroomCount, carrotCount, patateCount, sugarCount, appleCount, cacaoCount, wheatCount, eggCount;

    public GuiMainMenu(Minecraft mc) {
        this.minecraft = mc;
    }

    public void initGui() {
        Main.network.sendToServer(new PacketInventaireServer());
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        buttonList.add(new GuiTexturedButton(0, guiLeft+47,guiTop+62, 46,46, "textures/items/truite/truite_bad.png", "textures/items/truite/truite_good.png", "textures/items/truite/truite_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(1, guiLeft+116,guiTop+61, 46,46, "textures/items/steak/steak_bad.png", "textures/items/steak/steak_good.png", "textures/items/steak/steak_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(2, guiLeft+198,guiTop+65, 35,35, "textures/items/omelet/omelet_bad.png", "textures/items/omelet/omelet_good.png", "textures/items/omelet/omelet_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(3, guiLeft+267,guiTop+60, 46,46, "textures/items/greensoupe/greensoupe_bad.png", "textures/items/greensoupe/greensoupe_good.png", "textures/items/greensoupe/greensoupe_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(4, guiLeft+338,guiTop+60, 46,46, "textures/items/confiture/confiture_bad.png", "textures/items/confiture/confiture_good.png", "textures/items/confiture/confiture_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(5, guiLeft+46,guiTop+125, 46,46, "textures/items/brochette_viande/brochette_viande_bad.png", "textures/items/brochette_viande/brochette_viande_good.png", "textures/items/brochette_viande/brochette_viande_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(6, guiLeft+116,guiTop+121, 46,46, "textures/items/brochette_champi/brochette_champi_bad.png", "textures/items/brochette_champi/brochette_champi_good.png", "textures/items/brochette_champi/brochette_champi_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(7, guiLeft+195,guiTop+125, 40,40, "textures/items/fishsoupe/fishsoupe_bad.png", "textures/items/fishsoupe/fishsoupe_good.png", "textures/items/fishsoupe/fishsoupe_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(8, guiLeft+272,guiTop+127, 38,38, "textures/items/cake/cake_bad.png", "textures/items/cake/cake_good.png", "textures/items/cake/cake_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(9, guiLeft+338,guiTop+124, 46,46, "textures/items/cookie/cookie_bad.png", "textures/items/cookie/cookie_good.png", "textures/items/cookie/cookie_locked.png",0,0));
        buttonList.add(new GuiCustomButton(10, guiLeft + 165, guiTop + 185, 100, 20, "Cuisiner !", 0, 0));
        buttonList.add(new GuiCustomButton(11, guiLeft + 396, guiTop+20, 18, 20, "X", 0, 0));
        buttonList.add(new GuiCustomButton(12, guiLeft + 100, guiTop+185, 18, 20, "-", 0, 0));
        buttonList.add(new GuiCustomButton(13, guiLeft + 118, guiTop+185, 18, 20, "1", 20, 0));
        buttonList.add(new GuiCustomButton(14, guiLeft + 136, guiTop+185, 18, 20, "+", 82, 0));


        buttonList.get(10).enabled = false;

        if(fishCount>0){buttonList.get(0).enabled=true;
        } else { buttonList.get(0).enabled=false; }
        if(viandeCount < 1){buttonList.get(1).enabled=false;
        }else { buttonList.get(1).enabled=true; }
        if(eggCount<2){buttonList.get(2).enabled=false;
        }else { buttonList.get(2).enabled=true; }
        if(patateCount<2 || carrotCount<1){buttonList.get(3).enabled=false;
        }else { buttonList.get(3).enabled=true; }
        if(sugarCount<2 || appleCount<1){buttonList.get(4).enabled=false;
        }else { buttonList.get(4).enabled=true; }
        if(viandeCount<2 || carrotCount<1){buttonList.get(5).enabled=false;
        }else { buttonList.get(5).enabled=true; }
        if(shroomCount<3 || viandeCount<1){buttonList.get(6).enabled=false;
        }else { buttonList.get(6).enabled=true; }
        if(fishCount<2 || patateCount<2){buttonList.get(7).enabled=false;
        }else { buttonList.get(7).enabled=true; }
        if(sugarCount<2 || wheatCount<2 || eggCount<2){buttonList.get(8).enabled=false;
        }else { buttonList.get(8).enabled=true; }
        if(sugarCount<3 || wheatCount<1 || eggCount<2 || cacaoCount<3){buttonList.get(9).enabled=false;
        }else { buttonList.get(9).enabled=true; }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    @ParametersAreNonnullByDefault
    public void actionPerformed(GuiButton button) {
        if(button.id<10){
            buttonList.get(button.id).visible=false;
            for(int but=0;but<buttonList.size();but++){
                if(buttonList.get(but)!=buttonList.get(button.id)){
                    buttonList.get(but).visible=true;
                }
            }
            target=button.id;
            buttonList.get(10).enabled=true;
        } else if (button.id == 12) {
            // -
            if(Integer.parseInt(buttonList.get(13).displayString) > 1){
                buttonList.get(13).displayString = String.valueOf(Integer.parseInt(buttonList.get(13).displayString) -1);
            }
        } else if (button.id == 14) {
            // +
            buttonList.get(13).displayString = String.valueOf(Integer.parseInt(buttonList.get(13).displayString) +1);
        } else if (button.id != 13){
            if(button.id == 10){
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                Minecraft.getMinecraft().displayGuiScreen(new GuiCook(Minecraft.getMinecraft(),target, buttonList.get(13).displayString));
            } else {
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
            }
        }
    }
    @Override
    public void updateScreen()
    {
        int quantity = Integer.parseInt(buttonList.get(13).displayString);
        if(fishCount < 1*quantity){buttonList.get(0).enabled=false;
        } else { buttonList.get(0).enabled=true; }
        if(viandeCount < 1*quantity){buttonList.get(1).enabled=false;
        }else { buttonList.get(1).enabled=true; }
        if(eggCount<2*quantity){buttonList.get(2).enabled=false;
        }else { buttonList.get(2).enabled=true; }
        if(patateCount<2*quantity || carrotCount<1*quantity){buttonList.get(3).enabled=false;
        }else { buttonList.get(3).enabled=true; }
        if(sugarCount<2*quantity || appleCount<1*quantity){buttonList.get(4).enabled=false;
        }else { buttonList.get(4).enabled=true; }
        if(viandeCount<2*quantity || carrotCount<1*quantity){buttonList.get(5).enabled=false;
        }else { buttonList.get(5).enabled=true; }
        if(shroomCount<3*quantity|| viandeCount<1*quantity){buttonList.get(6).enabled=false;
        }else { buttonList.get(6).enabled=true; }
        if(fishCount<2*quantity || patateCount<2*quantity){buttonList.get(7).enabled=false;
        }else { buttonList.get(7).enabled=true; }
        if(sugarCount<2*quantity || wheatCount<2*quantity ||eggCount<2*quantity){buttonList.get(8).enabled=false;
        }else { buttonList.get(8).enabled=true; }
        if(sugarCount<3*quantity || wheatCount<1*quantity || eggCount<2*quantity || cacaoCount<3*quantity){buttonList.get(9).enabled=false;
        }else { buttonList.get(9).enabled=true; }

        System.out.println(viandeCount*quantity<1);
        super.updateScreen();

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
        drawCenteredString(fontRenderer, "Quantitée :", guiLeft + 65, guiTop + 191, Color.GRAY.getRGB());
        buttonHoveringText(buttonList.get(0), mouseX, mouseY, new String[]{"§4§n§lPoisson Cuit§r", "- 1 poisson cru"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(1), mouseX, mouseY, new String[]{"§4§n§lSteak Saignant§r", "- 1 viande cru"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(2), mouseX, mouseY, new String[]{"§4§n§lOmelette Baveuse§r", "- 2 oeufs"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(3), mouseX, mouseY, new String[]{"§4§n§lSoupe de Légumes§r", "- 10 patates", "- 1 carotte"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(4), mouseX, mouseY, new String[]{"§4§n§lConfiture de Pomme§r", "- 2 sucres", "- 1 pomme","","§o§3Effet(s) Secondaire(s)§r §o: Vitesse augmentée"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(5), mouseX, mouseY, new String[]{"§4§n§lBrochette du Combattant§r", "- 2 viandes", "- 1 carotte","","§o§3Effet(s) Secondaire(s)§r §o: Force augmentée"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(6), mouseX, mouseY, new String[]{"§4§n§lBrochette aux Champignons§r", "- 3 champignons", "- 1 viande","","§o§3Effet(s) Secondaire(s)§r §o: Saut amélioré"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(7), mouseX, mouseY, new String[]{"§4§n§lSoupe de poisson§r", "- 2 poissons", "- 2 patates","","§o§3Effet(s) Secondaire(s)§r §o: Apnée"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(8), mouseX, mouseY, new String[]{"§4§n§lGâteau Succulent§r", "- 2 sucres", "- 2 blés", "- 2 oeufs","","§o§3Effet(s) Secondaire(s)§r §o: Saturation"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(9), mouseX, mouseY, new String[]{"§4§n§lCookies Royaux§r", "- 3 sucres", "- 1 blés", "- 2 oeufs", "- 3 graines de cacaos","","§o§3Effet(s) Secondaire(s)§r §o: Absorption"}, mouseX, mouseY);
    }

    public void drawBackgroundImage() {
        minecraft.getTextureManager().bindTexture(background);
        drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, xSize, ySize, xSize, ySize);
    }
}
