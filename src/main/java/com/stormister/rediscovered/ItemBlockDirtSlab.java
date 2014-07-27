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

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemBlockDirtSlab extends ItemSlab
{
	public ItemBlockDirtSlab(Block block, BlockDirtSlab singleSlab, BlockDirtSlab doubleSlab) 
	{
		super(block, singleSlab, doubleSlab, block == doubleSlab);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 7;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) 
	{
		BlockDirtSlab slab = (BlockDirtSlab)Block.getBlockFromItem(itemStack.getItem());
		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(slab.func_150002_b(itemStack.getItemDamage())).toString();
	}
}