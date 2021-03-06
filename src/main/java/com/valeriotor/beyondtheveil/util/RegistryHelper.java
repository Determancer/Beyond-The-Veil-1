package com.valeriotor.beyondtheveil.util;

import javax.annotation.Nullable;

import com.valeriotor.beyondtheveil.blocks.BlockRegistry;
import com.valeriotor.beyondtheveil.items.ItemRegistry;
import com.valeriotor.beyondtheveil.lib.References;
import com.valeriotor.beyondtheveil.tileEntities.TileArborealGeneratorBottom;
import com.valeriotor.beyondtheveil.tileEntities.TileBarrel;
import com.valeriotor.beyondtheveil.tileEntities.TileBloodWell;
import com.valeriotor.beyondtheveil.tileEntities.TileCityMapper;
import com.valeriotor.beyondtheveil.tileEntities.TileDreamFocus;
import com.valeriotor.beyondtheveil.tileEntities.TileFumeSpreader;
import com.valeriotor.beyondtheveil.tileEntities.TileGearBench;
import com.valeriotor.beyondtheveil.tileEntities.TileHeart;
import com.valeriotor.beyondtheveil.tileEntities.TileLacrymatory;
import com.valeriotor.beyondtheveil.tileEntities.TileMegydrea;
import com.valeriotor.beyondtheveil.tileEntities.TileMemorySieve;
import com.valeriotor.beyondtheveil.tileEntities.TileMutator;
import com.valeriotor.beyondtheveil.tileEntities.TilePlantTerra;
import com.valeriotor.beyondtheveil.tileEntities.TileSlugBait;
import com.valeriotor.beyondtheveil.tileEntities.TileStatue;
import com.valeriotor.beyondtheveil.tileEntities.TileWateryCradle;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegistryHelper {
	
	

	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	ItemRegistry.registerItems(event);
	}
    
	public static void registerEntities() {
		
		
		
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ItemRegistry.initModels();
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileFumeSpreader.class, References.MODID + ":tilefumespreader");
		GameRegistry.registerTileEntity(TileBarrel.class, References.MODID + ":tilebarrel");
		GameRegistry.registerTileEntity(TileSlugBait.class, References.MODID + ":tileslug_bait");
		//GameRegistry.registerTileEntity(TileMutator.class, References.MODID + ":tilemutator");
		GameRegistry.registerTileEntity(TileArborealGeneratorBottom.class, References.MODID + ":tilearboreal_generator");
		//GameRegistry.registerTileEntity(TilePlantTerra.class, References.MODID + ":tileplantterra");
		GameRegistry.registerTileEntity(TileWateryCradle.class, References.MODID + ":tilewaterycradle");
		GameRegistry.registerTileEntity(TileLacrymatory.class, References.MODID + ":tilelacrymatory");
		GameRegistry.registerTileEntity(TileCityMapper.class, References.MODID + ":tilecitymapper");
		GameRegistry.registerTileEntity(TileHeart.class, References.MODID + ":tileheart");
		GameRegistry.registerTileEntity(TileBloodWell.class, References.MODID + ":tilebloodwell");
		GameRegistry.registerTileEntity(TileStatue.class, References.MODID + ":tilestatue");
		GameRegistry.registerTileEntity(TileMemorySieve.class, References.MODID + ":tilememorysieve");
		GameRegistry.registerTileEntity(TileGearBench.class, References.MODID + ":tilegearbench");
		GameRegistry.registerTileEntity(TileDreamFocus.class, References.MODID + ":tiledreamfocus");
		GameRegistry.registerTileEntity(TileMegydrea.class, References.MODID + ":tilemegydrea");
		
	}
	
	
	@SideOnly(Side.CLIENT)
	public static void registerColorHandlers() {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
            {
                return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
            }
        }, new  Block[] {BlockRegistry.BlockRedstoneGrass, BlockRegistry.BlockGhostGrass});
	}
	
	
	/*@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().register(new BiomeInnsmouth(new BiomeProperties("Voided")));
	}*/
	
}
