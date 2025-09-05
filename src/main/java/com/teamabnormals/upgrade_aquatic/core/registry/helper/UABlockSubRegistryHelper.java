package com.teamabnormals.upgrade_aquatic.core.registry.helper;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public class UABlockSubRegistryHelper extends BlockSubRegistryHelper {

	public UABlockSubRegistryHelper(RegistryHelper parent) {
		super(parent);
	}

	public <B extends Block> DeferredBlock<B> createWallBlock(String name, Supplier<? extends B> supplier, Supplier<? extends B> wallSupplier, Direction direction) {
		DeferredBlock<B> block = this.deferredRegister.register(name, wallSupplier);
		this.itemRegister.register(name.replace("wall_", ""), () -> new StandingAndWallBlockItem(supplier.get(), block.get(), new Item.Properties(), direction));
		return block;
	}
}