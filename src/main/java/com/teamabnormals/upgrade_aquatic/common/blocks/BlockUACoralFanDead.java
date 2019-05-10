package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCoralFan;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockUACoralFanDead extends BlockCoralFan {

	public BlockUACoralFanDead() {
		super(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0F));
	}

}
