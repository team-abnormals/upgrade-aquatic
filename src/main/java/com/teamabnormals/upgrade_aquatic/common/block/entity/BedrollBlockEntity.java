package com.teamabnormals.upgrade_aquatic.common.block.entity;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BedrollBlockEntity extends BlockEntity {

	public BedrollBlockEntity(BlockPos pos, BlockState state) {
		super(UABlockEntityTypes.BEDROLL.get(), pos, state);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}
