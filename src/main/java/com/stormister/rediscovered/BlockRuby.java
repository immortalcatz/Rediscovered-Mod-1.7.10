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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockRuby extends Block
{
	String texture;
	
    public BlockRuby(String texture)
    {
        super(Material.rock);
        this.texture = texture;
        this.setHarvestLevel("pickaxe", 1);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public Block idDropped(int i, Random random, int j)
    {
        return mod_Rediscovered.RubyBlock;
    }
    
//    public void registerBlockIcons(IIconRegister iconRegister) 
//    {
//    	this.blockIcon = iconRegister.registerIcon(mod_Rediscovered.modid + ":" + texture);
//    }
}
