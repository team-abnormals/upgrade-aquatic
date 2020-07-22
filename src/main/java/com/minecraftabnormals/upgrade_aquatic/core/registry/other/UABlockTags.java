package com.minecraftabnormals.upgrade_aquatic.core.registry.other;

import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;

public class UABlockTags {
	public static final INamedTag<Block> DIRT_LIKE = BlockTags.makeWrapperTag(UpgradeAquatic.MODID + ":dirt_like");
}
