package com.valeriotor.beyondtheveil.entities;

import java.util.UUID;
import java.util.function.Supplier;

import com.valeriotor.beyondtheveil.animations.Animation;
import com.valeriotor.beyondtheveil.animations.AnimationRegistry;
import com.valeriotor.beyondtheveil.entities.AI.AIProtectMaster;
import com.valeriotor.beyondtheveil.entities.AI.AIRevenge;
import com.valeriotor.beyondtheveil.entities.AI.AISpook;
import com.valeriotor.beyondtheveil.lib.BTVSounds;
import com.valeriotor.beyondtheveil.worship.DGWorshipHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDeepOne extends EntityCreature implements IPlayerGuardian, ISpooker, IAnimatedAttacker{
	private int i = 0;
	private boolean isTargetInWater = false;
	private Block facingBlock;
	private Block facingBlockUp;
	private UUID master;
	private int counter = -1;
	private Animation roarAnim = null;
	private Animation attackAnim = null;
	private int roarCooldown = 300;
	
	private static final DataParameter<Integer> ARM_RAISED = EntityDataManager.<Integer>createKey(EntityDeepOne.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> ROARING = EntityDataManager.<Boolean>createKey(EntityDeepOne.class, DataSerializers.BOOLEAN);
	public EntityDeepOne(World worldIn) {
		super(worldIn);
		
	}
	
	public EntityDeepOne(World worldIn, int time) {
		this(worldIn);
		this.counter = time;
	}
	
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	
	protected void applyEntityAttributes() {
	    super.applyEntityAttributes(); 

	    // standard attributes registered to EntityLivingBase
	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
	   

	    // need to register any additional attributes
	   getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(18.0D);
	}
	
	 protected void initEntityAI() {	
		 	
	        //this.tasks.addTask(0, new EntityAISwimming(this));
	        //this.tasks.addTask(1, new AISwim(this));
	        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	        this.tasks.addTask(8, new EntityAILookIdle(this));
	        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.5D, true));
	        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
	        this.tasks.addTask(2, new AISpook(this));
	        this.targetTasks.addTask(1, new AIProtectMaster(this));
	        this.targetTasks.addTask(2, new AIRevenge(this));
	        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, 10, true, false,  p -> (this.master == null && !DGWorshipHelper.areDeepOnesFriendly(p))));
	 }
	 
	 @Override
	 protected void entityInit() {
		super.entityInit();
		this.dataManager.register(ROARING, false);
	 }
	  
	 @Override
	 public void setAttackTarget(EntityLivingBase entitylivingbaseIn) {
		 super.setAttackTarget(entitylivingbaseIn);
	 }
	 
	 @Override
	 public boolean attackEntityAsMob(Entity entityIn) {
		 boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));
		 return flag;
	 }
	 
	public void setMaster(EntityPlayer p) {
		this.master = p.getPersistentID();
	}

	 @Override
	 public EntityPlayer getMaster() {
		return this.world.getMinecraftServer().getPlayerList().getPlayerByUUID(this.master);
	 }
	 
	 public void onLivingUpdate() {
		 super.onLivingUpdate();
		 
		 if(this.world.isRemote) {
			 if(this.getDataManager().get(ROARING)) {
				 if(this.roarAnim == null)
					 this.roarAnim = new Animation(AnimationRegistry.deep_one_roar);
				 else this.roarAnim.update();
			 }else {
				 if(this.roarAnim != null) {
					 if(this.roarAnim.isDone()) this.roarAnim = null;
					 else this.roarAnim.update();
				 } else if(this.attackAnim != null) {
					 this.attackAnim.update();
					 if(this.attackAnim.isDone()) 
						 this.attackAnim = null;
				 }
			 }
		 }
		 
		 if(!this.world.isRemote) {
			 if(this.counter > -1) {
				 this.counter--;
				 if(this.counter == 0) this.world.removeEntity(this);
			 }
			 if(this.roarCooldown > 0) this.roarCooldown--;
		 }
		 
		 if(this.isInWater()) {
			 i++;
			 if (this.getHealth() < this.getMaxHealth() && i>=20)
	            {
	                this.heal(1.0F);
	                i=0;
	            }
			 if(this.getAttackTarget() != null) {
				 if(this.world.getBlockState(this.getPosition().add(0, 1, 0)).getBlock() != Blocks.WATER) {
					 if(this.getAttackTarget().posY>this.posY+0.3 && isTargetInWater) {
					 		this.motionY=0.1;
					 }
				 }else if(this.getAttackTarget().posY>this.posY) {
				 		this.motionY=0.1;
				 		
				 }
			 
			 double angle = Math.atan((Math.abs(this.getAttackTarget().posX-this.posX))/(Math.abs(this.getAttackTarget().posZ-this.posZ)));
			 double horizontalDist = Math.sqrt(Math.pow(this.getAttackTarget().posX-this.posX,2)+Math.pow(this.getAttackTarget().posZ-this.posZ,2));
			 if(horizontalDist > 0.7) {
				 if(this.getAttackTarget().posX>this.posX) {
				 	 this.motionX = Math.sin(angle)/2.4;
				 	 
				 }else {
				 	 this.motionX = -Math.sin(angle)/2.4;
				 	 
				 }
				 if(this.getAttackTarget().posZ>this.posZ) {
				 	 this.motionZ = Math.cos(angle)/2.4;
				 	 
				 }else {
				 	 this.motionZ = -Math.cos(angle)/2.4;
				 	 
				 }
			 }else if(this.getAttackTarget().posY < this.posY) {
				 this.motionY = -0.3;
			 }
			 this.faceEntity(this.getAttackTarget(), 180, 180);
			 
			 isTargetInWater = this.getAttackTarget().isInWater();
			 facingBlock = this.world.getBlockState(this.getPosition().offset(this.getHorizontalFacing())).getBlock();
			 facingBlockUp = this.world.getBlockState(this.getPosition().offset(this.getHorizontalFacing()).add(0, 1, 0)).getBlock();
			 if((facingBlock != Blocks.WATER && facingBlock != Blocks.AIR) || (facingBlockUp != Blocks.WATER && facingBlockUp != Blocks.AIR)) {
				 this.motionY = 0.3;
			 	}
			 }
			 
			 
		 }
	 }
	 
	 @Override
	 public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		 if(this.master != null)
			 compound.setString("masterID", this.master.toString());
		 compound.setInteger("time", this.counter);
		 return super.writeToNBT(compound);
	 }
	 
	 
	 @Override
	 public void readFromNBT(NBTTagCompound compound) {
		if(compound.hasKey("masterID"))
			this.master = UUID.fromString(compound.getString("masterID"));
		this.counter = compound.getInteger("time");
		super.readFromNBT(compound);
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public Animation getRoarAnim() {
		 return this.roarAnim;
	 }
	 
	@Override
	public UUID getMasterID() {
		return this.master;
	}

	@Override
	public void setSpooking(boolean spook) {
		 this.getDataManager().set(ROARING, spook);
		 this.roarCooldown = 300;
	}

	@Override
	public SoundEvent getSound() {
		return BTVSounds.deepOneRoar;
	}

	@Override
	public void spookSelf() {
		this.motionX = 0;
		this.motionZ = 0;
		if(this.getAttackTarget() != null)
			this.faceEntity(this.getAttackTarget(), 360, 360);
	}

	@Override
	public int getSpookCooldown() {
		return this.roarCooldown;
	}
	
	@Override
	public void swingArm(EnumHand hand) {
		super.swingArm(hand);
		if(!this.world.isRemote)
			this.sendAnimation(rand.nextInt(DeepOneAttacks.values().length));
	}

	@Override
	public void setAttackAnimation(int id) {
		this.attackAnim = DeepOneAttacks.values()[id].getAnim();
	}

	@Override
	public Animation getAttackAnimation() {
		return this.attackAnim;
	}
	 
	 private enum DeepOneAttacks {
		LEFT_SWING(() -> new Animation(AnimationRegistry.deep_one_left_swing)),
		RIGHT_SWING(() -> new Animation(AnimationRegistry.deep_one_right_swing));
		
		private Supplier<Animation> func;
		private DeepOneAttacks(Supplier<Animation> func) {
			this.func = func;
		}
		
		private Animation getAnim() {
			return this.func.get();
		}
	 }
	 
	 
	 
	 
	 

}
