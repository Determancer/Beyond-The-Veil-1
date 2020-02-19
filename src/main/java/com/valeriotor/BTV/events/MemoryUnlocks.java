package com.valeriotor.BTV.events;

import java.util.List;

import com.valeriotor.BTV.capabilities.IPlayerData;
import com.valeriotor.BTV.capabilities.PlayerDataProvider;
import com.valeriotor.BTV.dreaming.Memory;
import com.valeriotor.BTV.lib.PlayerDataLib;
import com.valeriotor.BTV.research.ResearchUtil;
import com.valeriotor.BTV.util.SyncUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class MemoryUnlocks {
	
	public static void researchUnlock(EntityPlayer p, String key, int stage) {
		switch(key) {
		case "VOIDDREAMS":
			if(stage == 0)
				Memory.VOID.unlock(p, false);
			break;
		case "FIRSTCONTACT":
			if(stage == 0)
				Memory.ELDRITCH.unlock(p, false);
			break;
		case "DARKDREAMS":
			if(stage == 0)
				Memory.DARKNESS.unlock(p, false);
			break;
		}
	}
	
	@SubscribeEvent
	public static void change(SaplingGrowTreeEvent event) {
		if(event.getResult() == Result.DEFAULT || event.getResult() == Result.ALLOW) {
			World w = event.getWorld();
			if(w.isRemote) return;
			IBlockState state = w.getBlockState(event.getPos());
			if(state.getBlock() instanceof BlockSapling) {
				List<EntityPlayer> players = w.getPlayers(EntityPlayer.class, p -> p.getDistanceSq(event.getPos()) < 25);
				for(EntityPlayer p : players) {
					if(!Memory.CHANGE.isUnlocked(p) && Memory.CHANGE.isUnlockable(p)) {
						IPlayerData data = p.getCapability(PlayerDataProvider.PLAYERDATA, null);
						int amount = data.getOrSetInteger(PlayerDataLib.SAPLINGS_SEEN, 0, false);
						if(amount > 15) {
							Memory.CHANGE.unlock(p);
						} else {
							data.incrementOrSetInteger(PlayerDataLib.SAPLINGS_SEEN, 1, 1, false);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent 
	public static void power(LivingDeathEvent event) {
		EntityLivingBase e = event.getEntityLiving();
		if(!(e instanceof EntityPlayer) && (e.getMaxHealth() >= 100 || !e.isNonBoss()) && event.getSource().getTrueSource() instanceof EntityPlayer) {
			Memory.POWER.unlock((EntityPlayer)event.getSource().getTrueSource());
		}
	}
	
	@SubscribeEvent
	public static void sentience(PlayerInteractEvent.RightClickItem event) {
		EntityPlayer p = event.getEntityPlayer();
		ItemStack stack = p.getHeldItem(event.getHand());
		if(!p.world.isRemote && stack.getItem() == Items.WRITTEN_BOOK) {
			Memory.SENTIENCE.unlock(p);
		}
	}
	
	@SubscribeEvent
	public static void breakBlockEvent(BlockEvent.BreakEvent event) {
		EntityPlayer p = event.getPlayer();
		if(p instanceof EntityPlayer) {
			Block b = p.world.getBlockState(event.getPos()).getBlock();
			if(b == Blocks.DIAMOND_ORE || b == Blocks.EMERALD_ORE) {
				Memory.CRYSTAL.unlock(p);
			}
		}
	}
	
	@SubscribeEvent
	public static void entityInteractEvent(PlayerInteractEvent.EntityInteractSpecific event) {
		if(event.getWorld().isRemote) return;
		Entity e = event.getTarget();
		EntityPlayer p = event.getEntityPlayer();
		if(e instanceof EntityVillager) {
			Memory.HUMAN.unlock(p);
		} else if(e instanceof EntityAnimal) {
			if(((EntityAnimal)e).isBreedingItem(p.getHeldItem(event.getHand())) && ResearchUtil.isResearchComplete(p, "DREAMBOTTLE")) {
				Memory.ANIMAL.unlock(p);
			}
		}
	}
	
	@SubscribeEvent
	public static void anvilRepairEvent(AnvilRepairEvent event) {
		EntityPlayer p = event.getEntityPlayer();
		if(!p.world.isRemote && ResearchUtil.isResearchComplete(p, "DREAMBOTTLE")) {
			Memory.REPAIR.unlock(event.getEntityPlayer());
		}
	}
	
	@SubscribeEvent
	public static void itemCraftedEvent(ItemCraftedEvent event) {
		if(event.player.world.isRemote)
			return;
		Item i = event.crafting.getItem();
		if(	i == Items.DIAMOND_AXE 		||
			i == Items.DIAMOND_PICKAXE 	||
			i == Items.DIAMOND_SWORD 	||
			i == Items.DIAMOND_SHOVEL	||
			i == Items.DIAMOND_HOE) {
			Memory.TOOL.unlock(event.player);
		}
	}
	
	@SubscribeEvent
	public static void respawnEvent(PlayerRespawnEvent event) {
		EntityPlayer p = event.player;
		if(!p.world.isRemote && ResearchUtil.isResearchComplete(p, "DREAMBOTTLE")) {
			Memory.DEATH.unlock(p);
		}
	}
	
	public static void sleepChamberEvent(EntityPlayer p) {
		if(!Memory.STILLNESS.isUnlocked(p)) {
			int val = SyncUtil.incrementIntDataOnServer(p, false, PlayerDataLib.TIMESCHAMBER, 1, 1);
			if(val >= 10)
				Memory.STILLNESS.unlock(p, true);
		}
	}
	
}
