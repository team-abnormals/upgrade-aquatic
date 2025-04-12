package com.teamabnormals.upgrade_aquatic.core.data.client;

import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.clayworks.core.api.ClayworksTrims;
import com.teamabnormals.clayworks.core.data.client.ClayworksSpriteSourceProvider;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UATrimMaterials;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

public final class UASpriteSourceProvider extends SpriteSourceProvider {

	public UASpriteSourceProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, helper, UpgradeAquatic.MOD_ID);
	}

	@Override
	protected void addSources() {
		this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS).addSource(BlueprintTrims.materialPatternPermutations(UATrimMaterials.TOOTH));
		this.atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(BlueprintTrims.materialPermutationsForItemLayers(UATrimMaterials.TOOTH));
		this.atlas(ClayworksTrims.DECORATED_POT_ATLAS).addSource(ClayworksTrims.materialPatternPermutations(UATrimMaterials.TOOTH));
	}
}