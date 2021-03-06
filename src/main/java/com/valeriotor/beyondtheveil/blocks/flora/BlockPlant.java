package com.valeriotor.beyondtheveil.blocks.flora;

import com.valeriotor.beyondtheveil.blocks.ModBlock;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPlant extends ModBlock{
	
	public int spreadChance = 0;
	public int spreadMinMutation = 1000;
	
	public BlockPlant(Material materialIn, String name) {
		super(materialIn, name);
	}
	
	public boolean spread(World w, BlockPos pos, int mutation, float multiplier) {
		if(mutation >= this.spreadMinMutation) {
			int random = w.rand.nextInt(1000);
			if(this.spreadChance*multiplier >= random) {
				for(EnumFacing facing : EnumFacing.HORIZONTALS) {
					BlockPos newXZ = pos.offset(facing);
					for(int y = pos.getY() - 3; y < pos.getY() + 3; y++) {
						BlockPos newPos = new BlockPos(newXZ.getX(), y, newXZ.getZ());
						IBlockState state = w.getBlockState(newPos);
						if(state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.MYCELIUM) {
							if(w.getBlockState(newPos.up()).getBlock() ==  Blocks.AIR) {
								w.setBlockState(newPos.up(), getSpreadState(random));
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public IBlockState getSpreadState(int random) {
		return this.getDefaultState();
	}
	

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	
}
