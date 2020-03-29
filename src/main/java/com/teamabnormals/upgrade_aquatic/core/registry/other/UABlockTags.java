package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class UABlockTags {
	public static final Tag<Block> DIRT_LIKE = new BlockTags.Wrapper(new ResourceLocation(Reference.MODID, "dirt_like"));
}
