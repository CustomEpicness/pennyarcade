package pennyarcade.block.player;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import pennyarcade.PennyArcade;
import pennyarcade.block.tileentity.player.TileEntityHerobrine;

public class BlockHerobrine extends BlockContainer {

	public BlockHerobrine() {
		super(Material.ground);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
    public TileEntity createNewTileEntity(World world, int var2) {
            return new TileEntityHerobrine();
    }
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		if (entity == null) {
		return;
		}

		TileEntityHerobrine tile = (TileEntityHerobrine) world.getTileEntity(x, y, z);
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
		
		if(!this.canBlockStay(world, x, y, z)) {
			
			world.setBlock(x, y, z, this);
			
			}
		}
	
	 @Override
     public int getRenderType() {
             return -1;
     }
	 
	 @Override
     public boolean isOpaqueCube() {
             return false;
     }

     public boolean renderAsNormalBlock() {
             return false;
     }
     
     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
     {
    	 Random rand = new Random();
    	 par1World.playSoundAtEntity(par5EntityPlayer, "mob.ghast.scream", 1.0F, 1.05F);
    	 return true;
     }
     
     @SideOnly(Side.CLIENT)
     public void registerBlockIcons(IIconRegister icon) {
         this.blockIcon = icon.registerIcon(PennyArcade.MODID + ":players/herobrine");
 }

}
