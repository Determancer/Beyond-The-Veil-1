package com.valeriotor.BTV.blocks;

import com.valeriotor.BTV.lib.BlockNames;
import com.valeriotor.BTV.lib.References;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockWornBrickStairs extends BlockStairs{

	protected BlockWornBrickStairs(IBlockState modelState) {
		super(modelState);
		this.setRegistryName(References.MODID, BlockNames.WORNBRICKSTAIRS);
		this.setUnlocalizedName(BlockNames.WORNBRICKSTAIRS);
		this.setCreativeTab(References.BTV_TAB);
	}

}
