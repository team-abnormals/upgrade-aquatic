package com.teamabnormals.upgrade_aquatic.common.block.entity;

import com.teamabnormals.upgrade_aquatic.common.block.BedrollBlock;
import com.teamabnormals.upgrade_aquatic.core.registry.UATileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BedrollBlockEntity extends BlockEntity {
	private DyeColor color;

	public BedrollBlockEntity(BlockPos pos, BlockState state) {
		super(UATileEntities.BEDROLL.get(), pos, state);
	}

	public BedrollBlockEntity(DyeColor colorIn, BlockPos pos, BlockState state) {
		this(pos, state);
		this.setColor(colorIn);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@OnlyIn(Dist.CLIENT)
	public DyeColor getColor() {
		if (this.color == null) {
			this.color = ((BedrollBlock) this.getBlockState().getBlock()).getColor();
		}

		return this.color;
	}

	public void setColor(DyeColor color) {
		this.color = color;
	}
}
