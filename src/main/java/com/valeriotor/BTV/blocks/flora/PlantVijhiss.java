package com.valeriotor.BTV.blocks.flora;

import java.util.Random;

import com.valeriotor.BTV.items.ItemRegistry;
import com.valeriotor.BTV.tileEntities.TileMutator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlantVijhiss extends BlockTallPlant implements IMutationCatalyst{

	public PlantVijhiss(String name) {
		super(Material.PLANTS, name);
		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote)
		if(hand == EnumHand.MAIN_HAND) {
			if(playerIn.getHeldItem(hand).getItem() instanceof ItemSeeds) {
				ItemStack seeds = ItemStack.EMPTY;
				seeds = ItemRegistry.getRandomSeed(RANDOM, 10);
				//for(int i = 0; i < 100 && seeds == ItemStack.EMPTY; i++)
				// seeds = ForgeHooks.getGrassSeed(RANDOM, 0); // Wasn't working as intended. 
				if(seeds == ItemStack.EMPTY) seeds = new ItemStack(getRandomSeed(RANDOM));
				if(seeds != ItemStack.EMPTY) {
					playerIn.getHeldItem(hand).shrink(1);
					playerIn.addItemStackToInventory(seeds);
				}
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	private static Item getRandomSeed(Random r) {
		int a = r.nextInt(25);
		if(a < 8) return Items.WHEAT_SEEDS;
		else if(a < 15) return Items.BEETROOT_SEEDS;
		else if(a < 20) return Items.MELON_SEEDS;
		else return Items.PUMPKIN_SEEDS;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		this.updateMutators(worldIn, pos);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		this.updateMutators(worldIn, pos);
		super.breakBlock(worldIn, pos, state);
	}
	
	public void updateMutators(World worldIn, BlockPos pos) {
		for(int x = -3; x < 4; x++) {
			for(int y = -3; y < 4; y++) {
				for(int z = -3; z < 4; z++) {
					Block b = worldIn.getBlockState(pos.add(x, y, z)).getBlock();
					if(b instanceof BlockMutator) {
						TileEntity te = worldIn.getTileEntity(pos.add(x, y, z));
						if(te != null && te instanceof TileMutator) {
							((TileMutator)te).blockAreaUpdate();
						}
					}
				}
			}
		}
	}

	@Override
	public int mutationIncrease() {
		return 3;
	}
	
	
}