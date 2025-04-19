package com.teamabnormals.upgrade_aquatic.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UAPikeVariants;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.lang.reflect.Modifier;
import java.util.Arrays;


public class UAItemModelProvider extends BlueprintItemModelProvider {

	public UAItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		this.getBuilder(name(UAItems.PIKE_BUCKET.get()));
		Arrays.stream(UAPikeVariants.class.getDeclaredFields()).forEach(field -> {
			if (Modifier.isStatic(field.getModifiers()) && ResourceKey.class.isAssignableFrom(field.getType())) {
				try {
					ResourceLocation location = ((ResourceKey<?>) field.get(null)).location().withPath(s -> "item/pike_bucket/" + s);
					this.withExistingParent(location.getPath(), "item/generated").texture("layer0", location);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}