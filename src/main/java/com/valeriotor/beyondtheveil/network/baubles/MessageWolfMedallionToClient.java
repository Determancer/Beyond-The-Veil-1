package com.valeriotor.beyondtheveil.network.baubles;

import java.util.ArrayList;
import java.util.List;

import com.valeriotor.beyondtheveil.BeyondTheVeil;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageWolfMedallionToClient implements IMessage{
	
	private List<Integer> list = new ArrayList();
	public MessageWolfMedallionToClient() {}
	public MessageWolfMedallionToClient(List<EntityLivingBase> list) {
		for(Entity e : list) this.list.add(e.getEntityId());
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		while(buf.isReadable()) 
			this.list.add(buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		for(Integer i : list)
			buf.writeInt(i);
	}
	
	public static class WolfMedallionToClientMessageHandler implements IMessageHandler<MessageWolfMedallionToClient, IMessage>{

		@Override
		public IMessage onMessage(MessageWolfMedallionToClient message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				for(Integer i : message.list) {
					BeyondTheVeil.proxy.renderEvents.glowificator(i);
				}
				BeyondTheVeil.proxy.cEvents.startWolfMedallionCounter();;
			});
			return null;
		}
		
	}

}
