package com.valeriotor.beyondtheveil.worship.ActivePowers;

import com.valeriotor.beyondtheveil.worship.Deities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public interface IActivePower {
	
	public boolean activatePower(EntityPlayer p);
	public int getCooldownTicks();
	public Deities getDeity();
	public int getIndex();
	public boolean hasRequirement(EntityPlayer p);
	public ResourceLocation getGuiTexture();
	
}
