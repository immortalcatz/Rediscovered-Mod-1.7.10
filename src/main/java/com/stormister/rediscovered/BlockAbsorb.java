//	  Copyright 2012-2014 Matthew Karcz
//
//	  This file is part of The Rediscovered Mod.
//
//    The Rediscovered Mod is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    The Rediscovered Mod is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with The Rediscovered Mod.  If not, see <http://www.gnu.org/licenses/>.




package com.stormister.rediscovered;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockAbsorb extends Block
{
	String texture;
	
    public BlockAbsorb()
    {
        super(Material.sponge);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate()
    {
        return 5;
    }

    public byte getType(World world, int i, int j, int k)
    {
        return (byte)world.getBlockMetadata(i, j, k);
    }

    public byte getRadius(World world, int i, int j, int k)
    {
        return (byte)(getType(world, i, j, k) == 0 || getType(world, i, j, k) == 1 ? 4 : 6);
    }

    public int makeStill(World world, int i, int j, int k, byte byte0)
    {
        Material material = world.getBlock(i, j, k).getMaterial();

        if (material == Material.water && (byte0 == 0 || byte0 == 2) && !world.getBlock(i, j, k).equals(Blocks.water))
        {
            world.setBlock(i, j, k, Blocks.water);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int absorbBlock(World world, int i, int j, int k, byte byte0)
    {
        Material material = world.getBlock(i, j, k).getMaterial();

        if (material == Material.water && (byte0 == 0 || byte0 == 2))
        {
            world.setBlock(i, j, k, Blocks.air);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Called whenever the block is removed.
     */
    public void breakBlock(World world, int i, int j, int k, Block l, int m)
    {
    	super.breakBlock(world, i, j, k, l, m);
        modifyWorld(world, i, j, k, false);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public int onBlockPlaced(World world, int i, int j, int k, int par1, float f, float f2, float f3, int par2)
    {
        modifyWorld(world, i, j, k, true);
        world.scheduleBlockUpdate(i, j, k, this, tickRate());
        return par2;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        modifyWorld(world, i, j, k, true);
        world.scheduleBlockUpdate(i, j, k, this, tickRate());
    }

//    /**
//     * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
//     * block.
//     */
//    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
//    {
//        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
//        return true;
//    }

    public void modifyWorld(World world, int i, int j, int k, boolean flag)
    {
        byte byte0 = getRadius(world, i, j, k);
        byte byte1 = getType(world, i, j, k);
        int l = 0;

        for (int i1 = i - byte0; i1 <= i + byte0; i1++)
        {
            for (int j1 = j - byte0; j1 <= j + byte0; j1++)
            {
                for (int k1 = k - byte0; k1 <= k + byte0; k1++)
                {
                    if (k1 > k - byte0 && k1 < k + byte0 && j1 > j - byte0 && j1 < j + byte0 && i1 > i - byte0 && i1 < i + byte0 && flag)
                    {
                        l += absorbBlock(world, i1, j1, k1, byte1);
                        continue;
                    }

                    if (flag)
                    {
                        l += makeStill(world, i1, j1, k1, byte1);
                        continue;
                    }

                    if (!flag)
                    {
                        world.notifyBlocksOfNeighborChange(i1, j1, k1, this);
                    }
                }
            }
        }
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
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
        return new ItemStack(mod_Rediscovered.Sponge);
    }
}
