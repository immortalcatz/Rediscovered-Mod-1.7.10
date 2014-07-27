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
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class MapGenSky
{
    protected int range;
    protected Random rand;
    protected World worldObj;

    public MapGenSky()
    {
        range = 8;
        rand = new Random();
    }

    public void generate(IChunkProvider ichunkprovider, World world, int i, int j, Block blocks[])
    {
        int k = range;
		worldObj = world;
        rand.setSeed(worldObj.getSeed());
        long l = (rand.nextLong() / 2L) * 2L + 1L;
        long l1 = (rand.nextLong() / 2L) * 2L + 1L;
        for(int i1 = i - k; i1 <= i + k; i1++)
        {
            for(int j1 = j - k; j1 <= j + k; j1++)
            {
                rand.setSeed((long)i1 * l + (long)j1 * l1 ^ worldObj.getSeed());
                recursiveGenerate(worldObj, i1, j1, i, j, blocks);
            }
        }
    }

    public void populate(World world, int i, int j)
    {
        int k = range;
		worldObj = world;
        rand.setSeed(worldObj.getSeed());
        long l = (rand.nextLong() / 2L) * 2L + 1L;
        long l1 = (rand.nextLong() / 2L) * 2L + 1L;
        for(int i1 = i - k; i1 <= i + k; i1++)
        {
            for(int j1 = j - k; j1 <= j + k; j1++)
            {
                rand.setSeed((long)i1 * l + (long)j1 * l1 ^ worldObj.getSeed());
                double[] found = find(world, i1, j1);
                if(found != null)
                {
                	populator(worldObj, found[0], found[1], found[2], i, j);
                }
            }
        }
    }
    
    protected void recursiveGenerate(World world, int i, int j, int k, int l, Block blocks[])
    {
    }
    
    protected void populator(World world, double centerX, double centerY, double centerZ, int chunkX, int chunkY)
    {
    }
    
    public double[] find(World world, int bx, int by)
    {
    	return null;
    }
}