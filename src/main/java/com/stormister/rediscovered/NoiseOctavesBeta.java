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

public class NoiseOctavesBeta extends NoiseGenerator
{
    private NoisePerlinBeta generatorCollection[];
    private int octaves;

    public NoiseOctavesBeta(Random random, int i)
    {
        octaves = i;
        generatorCollection = new NoisePerlinBeta[i];
        for (int j = 0; j < i; j++)
        {
            generatorCollection[j] = new NoisePerlinBeta(random);
        }
    }

    public double func_806_a(double d, double d1)
    {
        double d2 = 0.0D;
        double d3 = 1.0D;
        for (int i = 0; i < octaves; i++)
        {
            d2 += generatorCollection[i].func_801_a(d * d3, d1 * d3) / d3;
            d3 /= 2D;
        }

        return d2;
    }

    public double[] generateNoiseOctaves(double ad[], double d, double d1, double d2,
            int i, int j, int k, double d3, double d4,
            double d5)
    {
        if (ad == null)
        {
            ad = new double[i * j * k];
        }
        else
        {
            for (int l = 0; l < ad.length; l++)
            {
                ad[l] = 0.0D;
            }
        }
        double d6 = 1.0D;
        for (int i1 = 0; i1 < octaves; i1++)
        {
            generatorCollection[i1].func_805_a(ad, d, d1, d2, i, j, k, d3 * d6, d4 * d6, d5 * d6, d6);
            d6 /= 2D;
        }

        return ad;
    }

    public double[] generateNoiseOctaves(double ad[], int i, int j, int k, int l, double d,
            double d1, double d2)
    {
        return generateNoiseOctaves(ad, i, 10D, j, k, 1, l, d, 1.0D, d1);
    }
}