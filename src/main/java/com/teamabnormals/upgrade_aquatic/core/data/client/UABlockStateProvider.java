package com.teamabnormals.upgrade_aquatic.core.data.client;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

public class UABlockStateProvider extends BlockStateProvider {
	public static final String[] DEFAULT_BOOKSHELF_POSITIONS = new String[]{"top_left", "top_mid", "top_right", "bottom_left", "bottom_mid", "bottom_right"};
	public static final String[] DRIFTWOOD_BOOKSHELF_POSITIONS = new String[]{"top_left", "top_right", "bottom_left", "bottom_mid_left", "bottom_mid_right", "bottom_right"};

	public UABlockStateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.hangingSigns(UABlocks.STRIPPED_DRIFTWOOD_LOG.get(), UABlocks.DRIFTWOOD_HANGING_SIGNS.getFirst().get(), UABlocks.DRIFTWOOD_HANGING_SIGNS.getSecond().get());
		this.bookshelfBlocks(DRIFTWOOD_PLANKS.get(), DRIFTWOOD_BOOKSHELF.get(), CHISELED_DRIFTWOOD_BOOKSHELF.get(), DRIFTWOOD_BOOKSHELF_POSITIONS, UpgradeAquatic.MOD_ID + ":block/chiseled_driftwood");

		this.hangingSigns(UABlocks.STRIPPED_RIVER_LOG.get(), UABlocks.RIVER_HANGING_SIGNS.getFirst().get(), UABlocks.RIVER_HANGING_SIGNS.getSecond().get());
		this.bookshelfBlocks(RIVER_PLANKS.get(), RIVER_BOOKSHELF.get(), CHISELED_RIVER_BOOKSHELF.get(), DEFAULT_BOOKSHELF_POSITIONS, UpgradeAquatic.MOD_ID + ":block/chiseled_river");
	}

	public void block(Block block) {
		simpleBlock(block, cubeAll(block));
		blockItem(block);
	}

	public void hangingSigns(Block strippedLog, Block sign, Block wallSign) {
		ModelFile model = particle(sign, blockTexture(strippedLog));
		this.simpleBlock(sign, particle(sign, blockTexture(strippedLog)));
		this.generatedItem(sign, "item");
		this.simpleBlock(wallSign, model);
	}

	public void blockItem(Block block) {
		this.simpleBlockItem(block, new ExistingModelFile(blockTexture(block), this.models().existingFileHelper));
	}

	private void generatedItem(ItemLike item, String type) {
		generatedItem(item, item, type);
	}

	private void generatedItem(ItemLike item, ItemLike texture, String type) {
		itemModels().withExistingParent(ForgeRegistries.ITEMS.getKey(item.asItem()).getPath(), "item/generated").texture("layer0", new ResourceLocation(ForgeRegistries.ITEMS.getKey(texture.asItem()).getNamespace(), type + "/" + ForgeRegistries.ITEMS.getKey(texture.asItem()).getPath()));
	}

	public ModelFile particle(Block block, ResourceLocation texture) {
		return this.models().getBuilder(name(block)).texture("particle", texture);
	}

	public void bookshelfBlocks(Block planks, Block bookshelf, Block chiseledBookshelf, String[] parts, String parent) {
		this.simpleBlock(bookshelf, this.models().cubeColumn(name(bookshelf), blockTexture(bookshelf), blockTexture(planks)));
		this.blockItem(bookshelf);

		BlockModelBuilder model = this.models().withExistingParent(name(chiseledBookshelf), "block/chiseled_bookshelf").texture("top", blockTexture(chiseledBookshelf) + "_top").texture("side", blockTexture(chiseledBookshelf) + "_side");
		MultiPartBlockStateBuilder builder = getMultipartBuilder(chiseledBookshelf);
		for (Direction direction : Direction.Plane.HORIZONTAL) {
			int rotation = (int) (direction.toYRot() + 180) % 360;
			builder.part().modelFile(model).rotationY(rotation).uvLock(true).addModel().condition(HorizontalDirectionalBlock.FACING, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[0], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_0_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[1], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_1_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[2], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_2_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[3], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_3_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[4], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_4_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[5], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_5_OCCUPIED, direction);
		}

		this.simpleBlockItem(chiseledBookshelf, this.models().withExistingParent(name(chiseledBookshelf) + "_inventory", "block/chiseled_bookshelf_inventory").texture("top", blockTexture(chiseledBookshelf) + "_top").texture("side", blockTexture(chiseledBookshelf) + "_side").texture("front", blockTexture(chiseledBookshelf) + "_empty"));
	}

	public void chiseledBookshelfParts(MultiPartBlockStateBuilder builder, Block chiseledBookshelf, String part, String parent, BooleanProperty property, Direction direction) {
		int rotation = (int) (direction.toYRot() + 180) % 360;
		builder.part().modelFile(this.models().withExistingParent(name(chiseledBookshelf) + "_occupied_slot_" + part, parent + "_bookshelf_slot_" + part).texture("texture", blockTexture(chiseledBookshelf) + "_occupied")).rotationY(rotation).uvLock(true).addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(property, true);
		builder.part().modelFile(this.models().withExistingParent(name(chiseledBookshelf) + "_empty_slot_" + part, parent + "_bookshelf_slot_" + part).texture("texture", blockTexture(chiseledBookshelf) + "_empty")).rotationY(rotation).uvLock(true).addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(property, false);
	}

	private String name(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block).getPath();
	}

	private ResourceLocation prefix(String prefix, ResourceLocation rl) {
		return new ResourceLocation(rl.getNamespace(), prefix + rl.getPath());
	}

	private ResourceLocation suffix(ResourceLocation rl, String suffix) {
		return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
	}
}