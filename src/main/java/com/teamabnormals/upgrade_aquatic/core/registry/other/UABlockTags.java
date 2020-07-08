package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;

public class UABlockTags {
	public static final INamedTag<Block> DIRT_LIKE = BlockTags.makeWrapperTag(Reference.MODID + ":dirt_like");
}
