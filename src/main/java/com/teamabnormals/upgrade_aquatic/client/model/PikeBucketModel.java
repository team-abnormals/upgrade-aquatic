package com.teamabnormals.upgrade_aquatic.client.model;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class PikeBucketModel implements BakedModel {
	private final BakedModel model;
	private final ItemOverrides overrideList;

	public PikeBucketModel(Map<ModelResourceLocation, BakedModel> modelManager) {
		this.model = modelManager.get(ModelResourceLocation.standalone(UpgradeAquatic.location("item/pike_bucket/redfin_pickerel")));
		this.overrideList = new Overrides(modelManager);
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand) {
		return this.model.getQuads(state, side, rand);
	}

	@Override
	public boolean useAmbientOcclusion() {
		return this.model.useAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return this.model.isGui3d();
	}

	@Override
	public boolean usesBlockLight() {
		return this.model.usesBlockLight();
	}

	@Override
	public boolean isCustomRenderer() {
		return this.model.isCustomRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		return this.model.getParticleIcon();
	}

	@Override
	public ItemOverrides getOverrides() {
		return this.overrideList;
	}

	private static class Overrides extends ItemOverrides {
		private final Map<ModelResourceLocation, BakedModel> modelManager;
		private final BakedModel model;
		private final Map<ResourceLocation, ModelResourceLocation> locationCache;
		private final Map<ModelResourceLocation, ModelResourceLocation> modelLocations;

		private Overrides(Map<ModelResourceLocation, BakedModel> modelManager) {
			this.modelManager = modelManager;
			this.model = modelManager.get(ModelResourceLocation.standalone(UpgradeAquatic.location("item/pike_bucket/redfin_pickerel")));
			this.locationCache = new HashMap<>();
			this.modelLocations = new HashMap<>();
			for (ResourceLocation location : Minecraft.getInstance().getResourceManager().listResources("models/item/pike_bucket", s -> s.getPath().endsWith(".json")).keySet()) {
				this.modelLocations.put(
						ModelResourceLocation.inventory(location.withPath(location.getPath().substring("models/item/pike_bucket/".length(), location.getPath().length() - ".json".length()))),
						ModelResourceLocation.standalone(location.withPath(location.getPath().substring("models/".length(), location.getPath().length() - ".json".length())))
				);
			}
		}

		@Nullable
		@Override
		public BakedModel resolve(BakedModel model, ItemStack stack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity entity, int p_173469_) {
			ClientLevel level = clientLevel;
			if (level == null) {
				level = Minecraft.getInstance().level;
			}

			CustomData data = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
			if (!data.isEmpty() && level != null) {
				CompoundTag tag = data.copyTag();
				if (tag.contains("BucketVariantTag", Tag.TAG_STRING)) {
					ModelResourceLocation variant = this.locationCache.computeIfAbsent(ResourceLocation.parse(tag.getString("BucketVariantTag")), ModelResourceLocation::inventory);
					if (this.modelLocations.containsKey(variant)) {
						return this.modelManager.get(this.modelLocations.get(variant));
					}
				}
			}
			return this.model;
		}
	}


	@SubscribeEvent
	public static void registerAdditional(ModelEvent.RegisterAdditional event) {
		for (ResourceLocation location : Minecraft.getInstance().getResourceManager().listResources("models/item/pike_bucket", s -> s.getPath().endsWith(".json")).keySet()) {
			event.register(ModelResourceLocation.standalone(location.withPath(location.getPath().substring("models/".length(), location.getPath().length() - ".json".length()))));
		}
	}

	@SubscribeEvent
	public static void modifyBakingResult(ModelEvent.ModifyBakingResult event) {
		event.getModels().put(ModelResourceLocation.inventory(UAItems.PIKE_BUCKET.getId()), new PikeBucketModel(event.getModels()));
	}
}
