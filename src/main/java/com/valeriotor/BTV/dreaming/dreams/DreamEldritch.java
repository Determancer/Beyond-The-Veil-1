package com.valeriotor.BTV.dreaming.dreams;

import com.valeriotor.BTV.dreaming.DreamHandler;
import com.valeriotor.BTV.gui.Guis;
import com.valeriotor.BTV.network.BTVPacketHandler;
import com.valeriotor.BTV.network.MessageOpenGuiToClient;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class DreamEldritch extends AbstractDream{

	public DreamEldritch(String name, int priority) {
		super(name, priority);
	}

	@Override
	public boolean activate(EntityPlayer p, World w) {
		if(!DreamHandler.knowsDream(p, "vacuos")) {
			p.sendMessage(new TextComponentTranslation("dreams.alienis.almostthere1"));
			p.sendMessage(new TextComponentTranslation("dreams.alienis.almostthere2"));
			return false;
		}
		boolean knowsAlienis = DreamHandler.knowsDream(p, "alienis");
		if(!DreamHandler.hasDreamtOfVoid(p)) {
			if(!knowsAlienis) {
				p.sendMessage(new TextComponentTranslation("dreams.alienis.needvacuos"));
				return false;
			} else {
				return searchStronghold(p, w);
			}
		}
		BTVPacketHandler.INSTANCE.sendTo(new MessageOpenGuiToClient(Guis.GuiAlienisDream), (EntityPlayerMP)p);
		return true;
	}
	
	private boolean searchStronghold(EntityPlayer p, World w) {
		BlockPos pos = w.findNearestStructure("Stronghold", p.getPosition(), false);
		if(pos != null) p.sendMessage(new TextComponentTranslation("dreams.alienissearch.success", new Object[] {pos.getX(), pos.getZ()}));
		else p.sendMessage(new TextComponentTranslation("dreams.alienissearch.fail"));
		
		return true;
	}

}