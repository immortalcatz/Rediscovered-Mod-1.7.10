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
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
 
public class ItemLC extends ItemArmor
{
        public ItemLC(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
        {
                super(par2EnumArmorMaterial, par3, par4);
        }
 
        @Override
    	public void registerIcons(IIconRegister reg){
    		if(this == mod_Rediscovered.LeatherChainHelmet)
    			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":LCHelmet");
    		if(this == mod_Rediscovered.LeatherChainChest)
    			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":LCChest");
    		if(this == mod_Rediscovered.LeatherChainLegs)
    			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":LCLegs");
    		if(this == mod_Rediscovered.LeatherChainBoots)
    			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":LCBoots");
    	}
        
        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
        {
        	if(stack.getItem().equals(mod_Rediscovered.LeatherChainHelmet)|| stack.getItem().equals(mod_Rediscovered.LeatherChainChest)|| stack.getItem().equals(mod_Rediscovered.LeatherChainBoots)){
    			return mod_Rediscovered.modid + ":textures/models/leatherchain_1.png";
            }
                
        	if(stack.getItem().equals(mod_Rediscovered.LeatherChainLegs)){
    			return mod_Rediscovered.modid + ":textures/models/leatherchain_2.png";
            }
                
            else return null;
        }      
}