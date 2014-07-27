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

import net.minecraft.world.gen.NoiseGenerator;

public class OldNoiseGeneratorOctaves2 extends NoiseGenerator
{

    public OldNoiseGeneratorOctaves2(Random random, int i)
    {
        field_4233_b = i;
        field_4234_a = new OldNoiseGenerator2[i];
        for(int j = 0; j < i; j++)
        {
            field_4234_a[j] = new OldNoiseGenerator2(random);
        }

    }

    public double[] func_4112_a(double ad[], double d, double d1, int i, int j, 
            double d2, double d3, double d4)
    {
        return func_4111_a(ad, d, d1, i, j, d2, d3, d4, 0.5D);
    }

    public double[] func_4111_a(double ad[], double d, double d1, int i, int j, 
            double d2, double d3, double d4, double d5)
    {
        d2 /= 1.5D;
        d3 /= 1.5D;
        if(ad == null || ad.length < i * j)
        {
            ad = new double[i * j];
        } else
        {
            for(int k = 0; k < ad.length; k++)
            {
                ad[k] = 0.0D;
            }

        }
        double d6 = 1.0D;
        double d7 = 1.0D;
        for(int l = 0; l < field_4233_b; l++)
        {
            field_4234_a[l].func_4157_a(ad, d, d1, i, j, d2 * d7, d3 * d7, 0.55000000000000004D / d6);
            d7 *= d4;
            d6 *= d5;
        }

        return ad;
    }

    private OldNoiseGenerator2 field_4234_a[];
    private int field_4233_b;
}