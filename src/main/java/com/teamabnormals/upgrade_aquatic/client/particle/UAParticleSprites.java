package com.teamabnormals.upgrade_aquatic.client.particle;

import java.util.EnumMap;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAParticleSprites {
	public enum SpriteTypes {
		PRISMARINE_SHOWER_1, PRISMARINE_SHOWER_2, PRISMARINE_SHOWER_3, PRISMARINE_SHOWER_4, PRISMARINE_SHOWER_5, PRISMARINE_SHOWER_6;
	}
	
	@SuppressWarnings("unused")
	private static final EnumMap<SpriteTypes, TextureAtlasSprite> SPRITES = new EnumMap<>(SpriteTypes.class);
	
	public static ResourceLocation[] PRISMARINE_FRAMES = new ResourceLocation[] {
		new ResourceLocation("upgrade_aquatic:textures/particles/prismarine_coral_crystal_1.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/prismarine_coral_crystal_2.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/prismarine_coral_crystal_3.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/prismarine_coral_crystal_4.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/prismarine_coral_crystal_5.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/prismarine_coral_crystal_6.png")
	};
	
	public static ResourceLocation[] ELDER_PRISMARINE_FRAMES = new ResourceLocation[] {
		new ResourceLocation("upgrade_aquatic:textures/particles/elder_prismarine_coral_crystal_1.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/elder_prismarine_coral_crystal_2.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/elder_prismarine_coral_crystal_3.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/elder_prismarine_coral_crystal_4.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/elder_prismarine_coral_crystal_5.png"),
		new ResourceLocation("upgrade_aquatic:textures/particles/elder_prismarine_coral_crystal_6.png")
	};
	
}
