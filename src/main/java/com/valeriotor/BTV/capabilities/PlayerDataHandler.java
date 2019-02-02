package com.valeriotor.BTV.capabilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import com.google.common.collect.Lists;
import com.valeriotor.BTV.lib.References;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class PlayerDataHandler {
	public static final ResourceLocation FLAG_CAP = new ResourceLocation(References.MODID, "flags");
	
	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if((event.getObject() instanceof EntityPlayer)) event.addCapability(FLAG_CAP, new PlayerDataProvider());
		
	}
	
	public static class DataStorage implements Capability.IStorage<IPlayerData>{

		@Override
		public NBTTagCompound writeNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side) {
			final NBTTagCompound tag = new NBTTagCompound();
			
			HashMap<String, Integer> ints = instance.getInts(false);
			List<String> strings = instance.getStrings(false);
			
			NBTTagCompound intTag = new NBTTagCompound();
			for(Entry<String, Integer> entry : ints.entrySet()) {
				intTag.setInteger(entry.getKey(), entry.getValue());
			}
			tag.setTag("ints", intTag);
			
			NBTTagCompound stringTag = new NBTTagCompound();
			for(String string : strings) {
				stringTag.setBoolean(string, true);
			}
			tag.setTag("strings", stringTag);
			
			return tag;
			
		}

		@Override
		public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side, NBTBase nbt) {
			final NBTTagCompound tag = (NBTTagCompound) nbt;
			
			if(tag.hasKey("strings")) {
				NBTTagCompound stringTag = (NBTTagCompound) tag.getTag("strings");
				for(String string : stringTag.getKeySet()) {
					instance.addString(string, false);
				}
			}	
			if(tag.hasKey("ints")) {
				NBTTagCompound intTag = (NBTTagCompound) tag.getTag("ints");
				for(String string : intTag.getKeySet()) {
					Integer a = intTag.getInteger(string);
					if(a != null)
						instance.setInteger(string, a, false);
				}
			}
			
		}
		
	}
	
	public static class Factory implements Callable<IPlayerData>{

		public Factory() {
			
		}
		
		@Override
		public IPlayerData call() throws Exception {
			return new PlayerDataHandler.PlayerData();
		}
	}
	
	public static class PlayerData implements IPlayerData{
		
		public List<String> strings = Lists.newArrayList();
		public HashMap<String, Integer> ints = new HashMap<>();
		public List<String> tempStrings = Lists.newArrayList();
		public HashMap<String, Integer> tempInts = new HashMap<>();
		
		private int dialogueType = 0;
		
		
		
		@Override
		public void setDialogueType(int id) {
			this.dialogueType = id;
			
		}

		@Override
		public int getDialogueType() {
			return this.dialogueType;
		}

		@Override
		public void addString(String string, boolean temporary) {
			if(!temporary) strings.add(string);
			else tempStrings.add(string);
		}

		@Override
		public void removeString(String string) {
			strings.remove(string);
			tempStrings.remove(string);
		}

		@Override
		public void setInteger(String key, int value, boolean temporary) {
			if(!temporary) ints.put(key, Integer.valueOf(value));
			else tempInts.put(key, Integer.valueOf(value));
		}

		@Override
		public void removeInteger(String key) {
			ints.remove(key);
			tempInts.remove(key);
		}

		@Override
		public boolean getString(String string) {
			if(strings.contains(string) || tempStrings.contains(string)) return true;
			return false;
		}

		@Override
		public int getInteger(String key) {
			if(ints.containsKey(key)) return ints.get(key);
			if(tempInts.containsKey(key)) return tempInts.get(key);
			
			return -999;
		}
		
		@Override
		public int getOrSetInteger(String key, int value, boolean temporary) {
			int a = getInteger(key);
			if(a != -999) return a;
			setInteger(key, value, temporary);
			return 0;
		}

		@Override
		public HashMap<String, Integer> getInts(boolean temporary) {
			if(temporary) return tempInts;
			else return ints;
		}

		@Override
		public List<String> getStrings(boolean temporary) {
			if(temporary) return tempStrings;
			else return strings;
		}
		
		
	}
}