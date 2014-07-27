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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockCherryWood extends Block
{
    /** The type of tree this block came from. */
    public static final String[] woodType = new String[] {"cherry"};
    @SideOnly(Side.CLIENT)
    private IIcon iconArray;
    String texture;

    public BlockCherryWood(String texture)
    {
        super(Material.wood);
        this.texture = texture;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

//    @SideOnly(Side.CLIENT)
//
//    /**
//     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
//     */
//    public IIcon getIcon(int par1, int par2)
//    {
//        return this.iconArray;
//    }

//    @SideOnly(Side.CLIENT)
//
//    @Override
//	public void registerBlockIcons(IIconRegister reg)
//    {
//        this.blockIcon = reg.registerIcon(mod_Rediscovered.modid + ":" + "cryingobsidian");
//    }
}
