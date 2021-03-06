package com.valeriotor.beyondtheveil.network;

import com.valeriotor.beyondtheveil.events.special.CrawlerWorshipEvents;
import com.valeriotor.beyondtheveil.worship.CrawlerWorship;
import com.valeriotor.beyondtheveil.worship.Worship;
import com.valeriotor.beyondtheveil.worship.ActivePowers.IActivePower;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageActivatePower implements IMessage{
	
	public MessageActivatePower() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {}

	@Override
	public void toBytes(ByteBuf buf) {}
	
	public static class ActivatePowerMessageHandler implements IMessageHandler<MessageActivatePower, IMessage>{

		@Override
		public IMessage onMessage(MessageActivatePower message, MessageContext ctx) {
			EntityPlayerMP p = ctx.getServerHandler().player;
			p.getServerWorld().addScheduledTask(() -> {
				IActivePower power = Worship.getPower(p);
				if(power != null && power.hasRequirement(p)) {
					int cooldown = Worship.getPowerCooldown(p, power.getIndex());
					if(cooldown > 0) {
						p.sendMessage(new TextComponentTranslation("power.cooldown", cooldown/20));
					} else {
						boolean success = power.activatePower(p);
						if(success) {
							int newCooldown = power.getCooldownTicks();
							CrawlerWorship cw = CrawlerWorshipEvents.getWorship(p);
							if(cw != null) newCooldown = cw.getPowerCooldown(newCooldown);
							Worship.setPowerCooldown(p, newCooldown, power.getIndex());
						}
					}
				}
			});
			return null;
		}
		
	}
	
}
