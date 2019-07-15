package com.teamabnormals.upgrade_aquatic.common.tileentities;

import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBedroll;
import com.teamabnormals.upgrade_aquatic.core.registry.UATileEntities;

import net.minecraft.item.DyeColor;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileEntityBedroll extends TileEntity {
	private DyeColor color;
	
	public TileEntityBedroll() {
		super(UATileEntities.BEDROLL);
	}
	
	public TileEntityBedroll(DyeColor colorIn) {
		this();
		this.setColor(colorIn);
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.pos, 11, this.getUpdateTag());
	}
	
	@OnlyIn(Dist.CLIENT)
	public DyeColor getColor() {
		if (this.color == null) {
			this.color = ((BlockBedroll)this.getBlockState().getBlock()).getColor();
		}
		
		return this.color;
	}
	
	public void setColor(DyeColor color) {
		this.color = color;
	}
}
