package com.stormister.rediscovered;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumStatus;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockDreamBed extends BlockBed
{
    /** Maps the foot-of-bed block to the head-of-bed block. */
    public static final int[][] footBlockToHeadBlockMap = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};
    @SideOnly(Side.CLIENT)
    private IIcon[] field_94472_b;
    @SideOnly(Side.CLIENT)
    private IIcon[] bedSideIcons;
    @SideOnly(Side.CLIENT)
    private IIcon[] bedTopIcons;

    public BlockDreamBed()
    {
        super();
        this.setLightLevel(0.9f);
        this.setBounds();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            int i1 = par1World.getBlockMetadata(par2, par3, par4);

            if (!isBlockHeadOfBed(i1))
            {
                int j1 = getDirection(i1);
                par2 += footBlockToHeadBlockMap[j1][0];
                par4 += footBlockToHeadBlockMap[j1][1];

                if (!par1World.getBlock(par2, par3, par4).equals(this))
                {
                    return true;
                }

                i1 = par1World.getBlockMetadata(par2, par3, par4);
            }

            if (par1World.getBiomeGenForCoords(par2, par4) != BiomeGenBase.hell && par1World.getBiomeGenForCoords(par2, par4) != BiomeGenBase.sky)
            {
                if (isBedOccupied(i1))
                {
                    EntityPlayer entityplayer1 = null;
                    Iterator iterator = par1World.playerEntities.iterator();

                    while (iterator.hasNext())
                    {
                        EntityPlayer entityplayer2 = (EntityPlayer)iterator.next();

                        if (entityplayer2.isPlayerSleeping())
                        {
                            ChunkCoordinates chunkcoordinates = entityplayer2.playerLocation;

                            if (chunkcoordinates.posX == par2 && chunkcoordinates.posY == par3 && chunkcoordinates.posZ == par4)
                            {
                                entityplayer1 = entityplayer2;
                            }
                        }
                    }

                    if (entityplayer1 != null)
                    {
                        par5EntityPlayer.addChatComponentMessage(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
                        return true;
                    }

                    setBedOccupied(par1World, par2, par3, par4, false);
                }

                EnumStatus enumstatus = par5EntityPlayer.sleepInBedAt(par2, par3, par4);

                if (enumstatus == EnumStatus.OK || par1World.getBiomeGenForCoords(par2, par4) == mod_Rediscovered.heaven || mod_Rediscovered.DaytimeBed)
                {
                    setBedOccupied(par1World, par2, par3, par4, true);
                    if (par5EntityPlayer instanceof EntityPlayerMP)
	                {
	                	EntityPlayerMP thePlayer = (EntityPlayerMP) par5EntityPlayer;
	                	ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) thePlayer);
	                	if (par5EntityPlayer.dimension != mod_Rediscovered.DimID)
	                	{
	                		props.setRespawn(par2, par3, par4);
	                		thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, mod_Rediscovered.DimID, new SkyDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(mod_Rediscovered.DimID)));
	                	}
	                	else
	                	{
	                		thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new SkyDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(0)));
	                		ChunkCoordinates coordinates = props.getRespawn();
	                		if(coordinates!=null)	
	                			coordinates = this.verifyRespawnCoordinates(thePlayer.mcServer.worldServerForDimension(0), coordinates, true, thePlayer);
	                		if(coordinates == null) {
	                        	coordinates = par1World.getSpawnPoint();
	                    		thePlayer.setPositionAndUpdate((double) coordinates.posX + 0.5D, (double) coordinates.posY + 3, (double) coordinates.posZ + 0.5D);
	                    		if(!thePlayer.worldObj.isRemote)
	                    			thePlayer.addChatComponentMessage(new ChatComponentTranslation("message.missingbed", new Object[0]));
	                		}
	                		else if(coordinates != null) {
	                    		thePlayer.setPositionAndUpdate((double) coordinates.posX + 0.5D, (double) coordinates.posY + 3, (double) coordinates.posZ + 0.5D);
	                        }
	                	}
                	}
                    return true;
                }
                else
                {
                    if (enumstatus == EnumStatus.NOT_POSSIBLE_NOW && par1World.getBiomeGenForCoords(par2, par4) != mod_Rediscovered.heaven)
                    {
                        par5EntityPlayer.addChatComponentMessage(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
                    }
                    else if (enumstatus == EnumStatus.NOT_SAFE)
                    {
                        par5EntityPlayer.addChatComponentMessage(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
                    }

                    return true;
                }
            }
            else
            {
                double d0 = (double)par2 + 0.5D;
                double d1 = (double)par3 + 0.5D;
                double d2 = (double)par4 + 0.5D;
                par1World.setBlockToAir(par2, par3, par4);
                int k1 = getDirection(i1);
                par2 += footBlockToHeadBlockMap[k1][0];
                par4 += footBlockToHeadBlockMap[k1][1];

                if (par1World.getBlock(par2, par3, par4).equals(this))
                {
                    par1World.setBlockToAir(par2, par3, par4);
                    d0 = (d0 + (double)par2 + 0.5D) / 2.0D;
                    d1 = (d1 + (double)par3 + 0.5D) / 2.0D;
                    d2 = (d2 + (double)par4 + 0.5D) / 2.0D;
                }

                par1World.newExplosion((Entity)null, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), 5.0F, true, true);
                return true;
            }
        }
    }
    
    public static ChunkCoordinates verifyRespawnCoordinates(World par0World, ChunkCoordinates par1ChunkCoordinates, boolean par2, EntityPlayerMP player)
    {
//    	player.addChatComponentMessage(new ChatComponentText("" + player.mcServer.worldServerForDimension(0).getBlock(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ)));
        if (player.mcServer.worldServerForDimension(0).getBlock(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ)== Blocks.bed || player.mcServer.worldServerForDimension(0).getBlock(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ)== mod_Rediscovered.DreamBed)
        {
            return par1ChunkCoordinates;
        }
        else
        {
            return null;
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {
        if (par1 == 0)
        {
            return Blocks.planks.getBlockTextureFromSide(par1);
        }
        else
        {
            int k = getDirection(par2);
            int l = Direction.bedDirection[k][par1];
            int i1 = isBlockHeadOfBed(par2) ? 1 : 0;
            return (i1 != 1 || l != 2) && (i1 != 0 || l != 3) ? (l != 5 && l != 4 ? this.bedTopIcons[i1] : this.bedSideIcons[i1]) : this.field_94472_b[i1];
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.bedTopIcons = new IIcon[] {par1IconRegister.registerIcon(mod_Rediscovered.modid + ":" + "dreambed_feet_top"), par1IconRegister.registerIcon(mod_Rediscovered.modid + ":" + "dreambed_head_top")};
        this.field_94472_b = new IIcon[] {par1IconRegister.registerIcon(mod_Rediscovered.modid + ":" + "dreambed_feet_end"), par1IconRegister.registerIcon("bed_head_end")};
        this.bedSideIcons = new IIcon[] {par1IconRegister.registerIcon(mod_Rediscovered.modid + ":" + "dreambed_feet_side"), par1IconRegister.registerIcon(mod_Rediscovered.modid + ":" + "dreambed_head_side")};
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 14;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.setBounds();
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        int j1 = getDirection(i1);

        if (isBlockHeadOfBed(i1))
        {
            if (!par1World.getBlock(par2 - footBlockToHeadBlockMap[j1][0], par3, par4 - footBlockToHeadBlockMap[j1][1]).equals(this))
            {
                par1World.setBlockToAir(par2, par3, par4);
            }
        }
        else if (!par1World.getBlock(par2 + footBlockToHeadBlockMap[j1][0], par3, par4 + footBlockToHeadBlockMap[j1][1]).equals(this))
        {
            par1World.setBlockToAir(par2, par3, par4);

            if (!par1World.isRemote)
            {
                this.dropBlockAsItem(par1World, par2, par3, par4, i1, 0);
            }
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return mod_Rediscovered.DreamBedItem;
    }

    /**
     * Set the bounds of the bed block.
     */
    private void setBounds()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
    }

    /**
     * Returns whether or not this bed block is the head of the bed.
     */
    public static boolean isBlockHeadOfBed(int par0)
    {
        return (par0 & 8) != 0;
    }

    /**
     * Return whether or not the bed is occupied.
     */
    public static boolean isBedOccupied(int par0)
    {
        return (par0 & 4) != 0;
    }

    /**
     * Sets whether or not the bed is occupied.
     */
    public static void setBedOccupied(World par0World, int par1, int par2, int par3, boolean par4)
    {
        int l = par0World.getBlockMetadata(par1, par2, par3);

        if (par4)
        {
            l |= 4;
        }
        else
        {
            l &= -5;
        }

        par0World.setBlockMetadataWithNotify(par1, par2, par3, l, 4);
    }

    /**
     * Gets the nearest empty chunk coordinates for the player to wake up from a bed into.
     */
    public static ChunkCoordinates getNearestEmptyChunkCoordinates(World par0World, int par1, int par2, int par3, int par4)
    {
        int i1 = par0World.getBlockMetadata(par1, par2, par3);
        int j1 = BlockDirectional.getDirection(i1);

        for (int k1 = 0; k1 <= 1; ++k1)
        {
            int l1 = par1 - footBlockToHeadBlockMap[j1][0] * k1 - 1;
            int i2 = par3 - footBlockToHeadBlockMap[j1][1] * k1 - 1;
            int j2 = l1 + 2;
            int k2 = i2 + 2;

            for (int l2 = l1; l2 <= j2; ++l2)
            {
                for (int i3 = i2; i3 <= k2; ++i3)
                {
                    if (par0World.doesBlockHaveSolidTopSurface(par0World, l2, par2 - 1, i3) && par0World.isAirBlock(l2, par2, i3) && par0World.isAirBlock(l2, par2 + 1, i3))
                    {
                        if (par4 <= 0)
                        {
                            return new ChunkCoordinates(l2, par2, i3);
                        }

                        --par4;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (!isBlockHeadOfBed(par5))
        {
            super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
        }
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return new ItemStack(mod_Rediscovered.DreamBedItem);
    }

    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
    {
        if (par6EntityPlayer.capabilities.isCreativeMode && isBlockHeadOfBed(par5))
        {
            int i1 = getDirection(par5);
            par2 -= footBlockToHeadBlockMap[i1][0];
            par4 -= footBlockToHeadBlockMap[i1][1];

            if (par1World.getBlock(par2, par3, par4).equals(this))
            {
                par1World.setBlockToAir(par2, par3, par4);
            }
        }
    }
}
