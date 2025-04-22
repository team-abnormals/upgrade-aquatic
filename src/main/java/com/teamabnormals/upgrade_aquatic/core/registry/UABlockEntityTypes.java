package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.teamabnormals.upgrade_aquatic.common.block.entity.BedrollBlockEntity;
import com.teamabnormals.upgrade_aquatic.common.block.entity.ElderEyeBlockEntity;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;

public class UABlockEntityTypes {
	public static final BlockEntitySubRegistryHelper BLOCK_ENTITY_TYPES = UpgradeAquatic.REGISTRY_HELPER.getBlockEntitySubHelper();

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElderEyeBlockEntity>> ELDER_EYE = BLOCK_ENTITY_TYPES.createBlockEntity("elder_eye", ElderEyeBlockEntity::new, () -> Set.of(UABlocks.ELDER_EYE.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BedrollBlockEntity>> BEDROLL = BLOCK_ENTITY_TYPES.createBlockEntity("bedroll", BedrollBlockEntity::new, () -> Set.of(UABlocks.BEDROLL.get()));
}