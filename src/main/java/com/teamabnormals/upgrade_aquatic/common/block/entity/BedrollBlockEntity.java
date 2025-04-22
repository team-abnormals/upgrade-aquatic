package com.teamabnormals.upgrade_aquatic.common.block.entity;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlockEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BedrollBlockEntity extends BlockEntity {
	private int rgb;

	public BedrollBlockEntity(BlockPos pos, BlockState state) {
		super(UABlockEntityTypes.BEDROLL.get(), pos, state);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	public int getRgb() {
		return this.rgb;
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return this.saveCustomOnly(registries);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putInt("rgb", this.rgb);
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.rgb = tag != null && tag.contains("rgb") ? tag.getInt("rgb") : DyedItemColor.LEATHER_COLOR;
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder components) {
		super.collectImplicitComponents(components);
		components.set(DataComponents.DYED_COLOR, new DyedItemColor(this.rgb, true));
	}

	@Override
	protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
		super.applyImplicitComponents(componentInput);
		this.rgb = componentInput.getOrDefault(DataComponents.DYED_COLOR, new DyedItemColor(DyedItemColor.LEATHER_COLOR, true)).rgb();
	}

	@Override
	public void removeComponentsFromTag(CompoundTag tag) {
		super.removeComponentsFromTag(tag);
		tag.remove("rgb");
	}

	public ItemStack getBedrollAsItem() {
		ItemStack itemstack = UABlocks.BEDROLL.asItem().getDefaultInstance();
		itemstack.applyComponents(this.collectComponents());
		return itemstack;
	}
}
