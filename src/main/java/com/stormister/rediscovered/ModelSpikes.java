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

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSpikes extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Spike1;
    ModelRenderer Spike2;
    ModelRenderer Spike3;
    ModelRenderer Spike4;
    ModelRenderer Spike5;
    ModelRenderer Spike6;
    ModelRenderer Spike7;
    ModelRenderer Spike8;
    ModelRenderer Spike9;
    ModelRenderer Spike10;
  
  public ModelSpikes()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 0, 14);
      Base.addBox(0F, 0F, 0F, 16, 2, 16);
      Base.setRotationPoint(-8F, 22F, -8F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Spike1 = new ModelRenderer(this, 0, 0);
      Spike1.addBox(0F, 0F, 0F, 5, 13, 0);
      Spike1.setRotationPoint(-2.5F, 9F, 0F);
      Spike1.setTextureSize(64, 32);
      Spike1.mirror = true;
      setRotation(Spike1, 0F, 0F, 0F);
      Spike2 = new ModelRenderer(this, 54, 0);
      Spike2.addBox(0F, 0F, 0F, 0, 13, 5);
      Spike2.setRotationPoint(0F, 9F, -2.5F);
      Spike2.setTextureSize(64, 32);
      Spike2.mirror = true;
      setRotation(Spike2, 0F, 0F, 0F);
      Spike3 = new ModelRenderer(this, 54, 0);
      Spike3.addBox(0F, 0F, 0F, 0, 13, 5);
      Spike3.setRotationPoint(5F, 9F, 2.5F);
      Spike3.setTextureSize(64, 32);
      Spike3.mirror = true;
      setRotation(Spike3, 0F, 0F, 0F);
      Spike4 = new ModelRenderer(this, 0, 0);
      Spike4.addBox(0F, 0F, 0F, 5, 13, 0);
      Spike4.setRotationPoint(2.5F, 9F, 5F);
      Spike4.setTextureSize(64, 32);
      Spike4.mirror = true;
      setRotation(Spike4, 0F, 0F, 0F);
      Spike5 = new ModelRenderer(this, 0, 0);
      Spike5.addBox(0F, 0F, 0F, 5, 13, 0);
      Spike5.setRotationPoint(-7.5F, 9F, 5F);
      Spike5.setTextureSize(64, 32);
      Spike5.mirror = true;
      setRotation(Spike5, 0F, 0F, 0F);
      Spike6 = new ModelRenderer(this, 54, 0);
      Spike6.addBox(0F, 0F, 0F, 0, 13, 5);
      Spike6.setRotationPoint(-5F, 9F, 2.5F);
      Spike6.setTextureSize(64, 32);
      Spike6.mirror = true;
      setRotation(Spike6, 0F, 0F, 0F);
      Spike7 = new ModelRenderer(this, 54, 0);
      Spike7.addBox(0F, 0F, 0F, 0, 13, 5);
      Spike7.setRotationPoint(-5F, 9F, -7.5F);
      Spike7.setTextureSize(64, 32);
      Spike7.mirror = true;
      setRotation(Spike7, 0F, 0F, 0F);
      Spike8 = new ModelRenderer(this, 0, 0);
      Spike8.addBox(0F, 0F, 0F, 5, 13, 0);
      Spike8.setRotationPoint(-7.5F, 9F, -5F);
      Spike8.setTextureSize(64, 32);
      Spike8.mirror = true;
      setRotation(Spike8, 0F, 0F, 0F);
      Spike9 = new ModelRenderer(this, 0, 0);
      Spike9.addBox(0F, 0F, 0F, 5, 13, 0);
      Spike9.setRotationPoint(2.5F, 9F, -5F);
      Spike9.setTextureSize(64, 32);
      Spike9.mirror = true;
      setRotation(Spike9, 0F, 0F, 0F);
      Spike10 = new ModelRenderer(this, 54, 0);
      Spike10.addBox(0F, 0F, 0F, 0, 13, 5);
      Spike10.setRotationPoint(5F, 9F, -7.5F);
      Spike10.setTextureSize(64, 32);
      Spike10.mirror = true;
      setRotation(Spike10, 0F, 0F, 0F);
  }
  
  public void render(float scale, double x, double y, double z, float ang, float angY, boolean renderLantern, boolean lanternOn, boolean renderHeadTorch)
  {
      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5, y + 0.5 , z + 0.5);
      GL11.glPushMatrix();
      GL11.glRotatef(ang, 1f, 0f, 0f);
      GL11.glRotatef(angY, 0f, 1f, 0f);
      Base.render(scale);
      Spike1.render(scale);
      Spike2.render(scale);
      Spike3.render(scale);
      Spike4.render(scale);
      Spike5.render(scale);
      Spike6.render(scale);
      Spike7.render(scale);
      Spike8.render(scale);
      Spike9.render(scale);
      Spike10.render(scale);

      GL11.glPopMatrix();
      GL11.glPopMatrix();
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void renderAll() 
  {
	  Base.render(0.0625F);
	  Spike1.render(0.0625F);
	  Spike2.render(0.0625F);
	  Spike3.render(0.0625F);
	  Spike4.render(0.0625F);
	  Spike5.render(0.0625F);
	  Spike6.render(0.0625F);
	  Spike7.render(0.0625F);
	  Spike8.render(0.0625F);
	  Spike9.render(0.0625F);
	  Spike10.render(0.0625F);
  }

}