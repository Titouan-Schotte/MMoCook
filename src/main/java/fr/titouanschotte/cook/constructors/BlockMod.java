package fr.titouanschotte.cook.constructors;
import fr.titouanschotte.cook.Main;
import fr.titouanschotte.cook.gui.GuiMainMenu;
import fr.titouanschotte.cook.network.PacketLabelServer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
public class BlockMod extends Block {
    public BlockMod(String name, Material materialIn, int harvestLevel) {
        super(materialIn);
        setHarvestLevel("pickaxe", harvestLevel);
        setUnlocalizedName(name);
        setRegistryName(name);

        setCreativeTab(Main.creativeTab);
    }



    @SideOnly(Side.CLIENT)
    @ParametersAreNonnullByDefault
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer () {
        return BlockRenderLayer.CUTOUT;
    }
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if(!worldIn.isRemote){
            //Main.network.sendToServer(new PacketLabelServer(pos.getX(),pos.getY(),pos.getZ()));
            System.out.println(worldIn.getGameRules().getBoolean("sendCommandFeedback"));
            boolean worth=false;
            if(worldIn.getGameRules().getBoolean("sendCommandFeedback")){
                worldIn.getGameRules().setOrCreateGameRule("sendCommandFeedback", "false");
                worth=true;
            }
            worldIn.getMinecraftServer().getCommandManager().executeCommand(worldIn.getMinecraftServer(), "summon minecraft:armor_stand "+pos.getX()+" " +pos.getY()+" "+pos.getZ()+" {CustomName:\">> §6CUISINER§r <<\",Invulnerable:1b,NoGravity:1b,Invisible:1b,CustomNameVisible:1b}");
            if(!worldIn.getGameRules().getBoolean("sendCommandFeedback") && worth){
                worldIn.getGameRules().setOrCreateGameRule("sendCommandFeedback", "true");
                worth=false;
            }
            System.out.println(worldIn.getGameRules().getBoolean("sendCommandFeedback"));
        }

    }
    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
        if(!worldIn.isRemote){
            //Main.network.sendToServer(new PacketLabelServer(pos.getX(),pos.getY(),pos.getZ()));
            boolean worth=false;
            if(worldIn.getGameRules().getBoolean("sendCommandFeedback")){
                worldIn.getGameRules().setOrCreateGameRule("sendCommandFeedback", "false");
                worth=true;
            }
            worldIn.getMinecraftServer().getCommandManager().executeCommand(worldIn.getMinecraftServer(), "kill @e[type=armor_stand,r=2,x="+pos.getX()+",y="+pos.getY()+",z="+pos.getZ()+"]");
            if(!worldIn.getGameRules().getBoolean("sendCommandFeedback") && worth){
                worldIn.getGameRules().setOrCreateGameRule("sendCommandFeedback", "true");
                worth=false;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        System.out.println("CLICKED");Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu(Minecraft.getMinecraft()));
        return true;
    }
            ///kill @e[type=armor_stand,y=1,z=1,x=1]
}
