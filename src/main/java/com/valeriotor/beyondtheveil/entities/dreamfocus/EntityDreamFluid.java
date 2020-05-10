package com.valeriotor.beyondtheveil.entities.dreamfocus;

import java.util.List;

import javax.vecmath.Point3d;

import com.valeriotor.beyondtheveil.blocks.BlockRegistry;
import com.valeriotor.beyondtheveil.tileEntities.TileDreamFocus;
import com.valeriotor.beyondtheveil.util.FluidHelper;
import com.valeriotor.beyondtheveil.util.MathHelperBTV;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.wrappers.BlockLiquidWrapper;
import net.minecraftforge.fluids.capability.wrappers.BlockWrapper;
import net.minecraftforge.fluids.capability.wrappers.FluidBlockWrapper;

public class EntityDreamFluid extends EntityLiving implements IDreamEntity{
	private int pointCounter = 0;
	private BlockPos focus = null;
	private static final DataParameter<String> FLUIDNAME = EntityDataManager.<String>createKey(EntityDreamFluid.class, DataSerializers.STRING);
	private static final DataParameter<Byte> FLUIDAMOUNT = EntityDataManager.<Byte>createKey(EntityDreamFluid.class, DataSerializers.BYTE);
	private FluidStack fluid = null;
	private boolean removeEntity = false;
	
	public EntityDreamFluid(World worldIn) {
		super(worldIn);
		this.setSize(0.4F, 0.4F);
		this.setEntityBoundingBox(new AxisAlignedBB(0,0,0,0.4,0.4,0.4));
	}
	
	public EntityDreamFluid(World worldIn, FluidStack fluid, BlockPos focusPos) {
		this(worldIn);
		this.fluid = fluid;
		this.focus = focusPos;
	}

	@Override
	public Point3d getNextPoint(List<Point3d> ps) {
		if(this.pointCounter < ps.size()) {
			Point3d p = ps.get(pointCounter);

			if(this.world.isAreaLoaded(((Entity)this).getPosition(), 1)) {
				this.pointCounter++;
			}
			BlockPos pos = new BlockPos(2*p.x-posX,2*p.y-posY,2*p.z-posZ);
			if((this.ticksExisted & 7) == 0) {
				if(this.fluid != null) {
					Fluid f = this.fluid.getFluid();
					if(f == FluidRegistry.WATER) {
						int times = 0;
						for(int x = -2; x <= 2 && fluid.amount >= 50 && times < 3; x++) {
							for(int y = -1; y <= 1 && fluid.amount >= 50 && times < 3; y++) {
								for(int z = -2; z <= 2 && fluid.amount >= 50 && times < 3; z++) {
									BlockPos ppos = this.getPosition().add(x, y, z);
									IBlockState state = world.getBlockState(ppos);
									if(state.getBlock() instanceof IGrowable) {
										IGrowable g = (IGrowable) state.getBlock();
										if(g.canGrow(world, ppos, state, false)) {
											if(this.rand.nextInt(3) == 0) {
												g.grow(world, this.rand, ppos, state);
												this.fluid.amount -= 50;
												times++;
											}
										}
									}
								}
							}
						}
					} else if(f == FluidRegistry.LAVA) {
						world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(posX-0.5, posY-0.5, posZ-0.5, posX+0.5, posY+0.5, posZ+0.5))
						.forEach(e -> e.setFire(40));						
					}
				}
			}
			this.onInsideBlock(world.getBlockState(pos), pos, p);
			return p;
		}
		this.removeEntity = true;
		/*if(this.fluid != null) {
			IBlockState startState = world.getBlockState(this.getPosition());
	        Material destMaterial = startState.getMaterial();
			boolean isDestNonSolid = !destMaterial.isSolid();
	        boolean isDestReplaceable = startState.getBlock().isReplaceable(world, this.getPosition());
	        if(world.isAirBlock(this.getPosition()) || isDestNonSolid || isDestReplaceable) {
	        	if(world.provider.doesWaterVaporize() && fluid.getFluid().doesVaporize(fluid)) {
	        		fluid.getFluid().vaporize(null, world, this.getPosition(), fluid);
	        		this.fluid = null;
	        		this.removeEntity = true;
	        	} else {
	        		if(this.fluid.amount == 1000) {
	        			world.setBlockState(this.getPosition(), this.fluid.getFluid().getBlock().getDefaultState());        			
	        		} else if(this.fluid.getFluid().getBlock().getDefaultState().getPropertyKeys().contains(BlockFluidBase.LEVEL)) {
	        			world.setBlockState(this.getPosition(), this.fluid.getFluid().getBlock().getDefaultState().withProperty(BlockFluidBase.LEVEL, MathHelperBTV.clamp(0, 15, this.fluid.amount*15/1000)));	
	        		}
                	this.fluid = null;
		        }
        	} 
		}*/
		return null;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		String s = this.fluid == null ? "null" : this.fluid.getFluid().getName();
		byte amount = this.fluid == null ? 0 : (byte)(this.fluid.amount / 100);
		this.dataManager.register(FLUIDNAME, s);
		this.dataManager.register(FLUIDAMOUNT, amount);
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		if(compound.hasKey("fluid")) {
			this.fluid = FluidStack.loadFluidStackFromNBT(compound.getCompoundTag("fluid"));
			if(this.fluid != null) {
				this.dataManager.set(FLUIDNAME, fluid.getFluid().getName());
				this.dataManager.set(FLUIDAMOUNT, (byte)(this.fluid.amount/1000));
			}
		}

		this.pointCounter = compound.getInteger("point");
		if(compound.hasKey("focus")) {
			this.focus = BlockPos.fromLong(compound.getLong("focus"));
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setInteger("point", pointCounter);
		if(focus != null) compound.setLong("focus", focus.toLong());
		if(this.fluid != null) {
			compound.setTag("fluid", this.fluid.writeToNBT(new NBTTagCompound()));
		}
	}
	
	public String getFluidName() {
		return this.dataManager.get(FLUIDNAME);
	}
	
	public byte getTenths() {
		return this.dataManager.get(FLUIDAMOUNT);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(world.isRemote) return;
		if(this.focus != null) {
			TileEntity te = this.world.getTileEntity(focus);
			if(te instanceof TileDreamFocus) {
				List<Point3d> ps = ((TileDreamFocus)te).getPoints();
				this.moveToNextPoint(ps);
			} else this.removeEntity = true;
		}
		
		if(this.fluid != null) {
			this.dataManager.set(FLUIDNAME, fluid.getFluid().getName());
			this.dataManager.set(FLUIDAMOUNT, (byte)(this.fluid.amount/100));
		}
		if(removeEntity || (this.focus != null && !(this.world.getTileEntity(focus) instanceof TileDreamFocus))) {
			this.world.removeEntity(this);
		}
		
	}
	
	@Override
	public boolean getIsInvulnerable() {
		return true;
	}
	
	@Override
	public void fall(float distance, float damageMultiplier) {
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	@Override
	public boolean canRenderOnFire() {
		return false;
	}
	
	@Override
	public void setFire(int seconds) {
	}
	
	protected void onInsideBlock(IBlockState state, BlockPos destPos, Point3d nextPoint) {
		if(this.fluid == null) return;
		if(nextPoint != null) {
			TileEntity te = world.getTileEntity(destPos);
			EnumFacing f = EnumFacing.getFacingFromVector((float)(posX-nextPoint.x), (float)(posY-nextPoint.y), (float) (posZ-nextPoint.z));
			if(te != null && te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, f)) {
				IFluidHandler handler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, f);
				int amount = this.fluid.amount;
				amount -= handler.fill(this.fluid, true);
				if(amount == 0) {
					this.fluid = null;
					this.removeEntity = true;
				} else this.fluid.amount = amount;
			} /*else if(/*state.getBlock() instanceof BlockFluidBase  && ((BlockFluidBase)state.getBlock()).getFilledPercentage(world, destPos) == 1
				|| (state.isSideSolid(world, destPos, f) && state.getBlock() != BlockRegistry.BlockDreamFocusFluids)) {
				if(fluid.getFluid().canBePlacedInWorld()) {
					IBlockState startState = world.getBlockState(this.getPosition());
			        Material destMaterial = startState.getMaterial();
					boolean isDestNonSolid = !destMaterial.isSolid();
			        boolean isDestReplaceable = startState.getBlock().isReplaceable(world, this.getPosition());
			        if(world.isAirBlock(this.getPosition()) || isDestNonSolid || isDestReplaceable) {
			        	if(world.provider.doesWaterVaporize() && fluid.getFluid().doesVaporize(fluid)) {
			        		fluid.getFluid().vaporize(null, world, this.getPosition(), fluid);
			        		this.fluid = null;
			        		this.removeEntity = true;
			        	} else {
			        		if(this.fluid.amount == 1000) {
			        			world.setBlockState(this.getPosition(), this.fluid.getFluid().getBlock().getDefaultState());        			
			        		} else if(this.fluid.getFluid().getBlock().getDefaultState().getPropertyKeys().contains(BlockFluidBase.LEVEL)) {
			        			world.setBlockState(this.getPosition(), this.fluid.getFluid().getBlock().getDefaultState().withProperty(BlockFluidBase.LEVEL, MathHelperBTV.clamp(0, 15, this.fluid.amount*15/1000)));	
			        		}
		                	this.fluid = null;
		                	this.removeEntity = true;
			                
				        }
		        	} 
				}
			}*/
		}
		super.onInsideBlock(state);
	}
	
	private static IFluidHandler getFluidBlockHandler(Fluid fluid, World world, BlockPos pos)
    {
        Block block = fluid.getBlock();
        if (block instanceof IFluidBlock)
        {
            return new FluidBlockWrapper((IFluidBlock) block, world, pos);
        }
        else if (block instanceof BlockLiquid)
        {
            return new BlockLiquidWrapper((BlockLiquid) block, world, pos);
        }
        else
        {
            return new BlockWrapper(block, world, pos);
        }
    }
	

}
